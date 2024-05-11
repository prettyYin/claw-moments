package com.moments.claw.biz;

import com.moments.claw.domain.base.entity.Activity;
import com.moments.claw.domain.base.entity.ActivityArticle;
import com.moments.claw.domain.base.entity.Article;
import com.moments.claw.domain.base.entity.User;
import com.moments.claw.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZScoreBiz {

    // 权重分配
    private static final double WEIGHT_LIKES = 0.4;
    private static final double WEIGHT_COMMENTS = 0.3;
    private static final double WEIGHT_FOLLOWERS = 0.2;
    private static final double WEIGHT_POINTS = 0.1;

    private final ActivityArticleService activityArticleService;
    private final ActivityService activityService;
    private final ArticleService articleService;
    private final UserService userService;
    private final UserMemberService userMemberService;
    private final AdvertiseService advertiseService;

    @Scheduled(cron = "0 0 * * *") // 24小时执行一次
    public List<Double> computeActivtiyZScore() {
        List<Double> ZScores = new ArrayList<>();

        // 1.获取已结束的活动列表
        List<Activity> activityList = activityService.getEndedActivityList();
        // 2.根据每个活动下的文章列表，计算Z-Score
        activityList.forEach(activity -> {
            List<ActivityArticle> activityArticleList = activityArticleService.getActivityArticleByActivityId(activity.getId());
            // 文章列表
            List<Long> articleIds = activityArticleList.stream().map(ActivityArticle::getArticleId).collect(Collectors.toList());
            List<Article> articleList = articleService.selectListByIds(articleIds);
            // 获取该文章的点赞数、评论数、用户的粉丝数、积分数
            ArrayList<Double> sumLikes = new ArrayList<>(); // 总的点赞数
            ArrayList<Double> sumComments = new ArrayList<>(); // 总的评论数
            ArrayList<Double> sumFollowers = new ArrayList<>(); // 总的粉丝数
            ArrayList<Double> sumIntegrals = new ArrayList<>(); // 总的积分数

            calSums(articleList, sumLikes, sumComments, sumFollowers, sumIntegrals);
            // 3.计算出Z-Score ==> Z = (X-μ)/σ  Z:标准分数；X个体观测值；μ：平均数；σ：标准差

            for (Article article : articleList) {
                Long userId = article.getUserId();
                User user = userService.getById(userId);
                Long likes = article.getPraise(); // 点赞数
                Long comments = article.getComment(); // 评论数
                Long followers = user.getFollow(); // 粉丝数
                Long integral = userMemberService.getMemberInfoByUserId(userId).getIntegral(); // 积分数
                double ZScore = calculateCompositeZScore(
                        likes, calculateMean(sumLikes), calculateSampleStandardDeviation(sumLikes),
                        comments, calculateMean(sumComments), calculateSampleStandardDeviation(sumComments),
                        followers, calculateMean(sumFollowers), calculateSampleStandardDeviation(sumFollowers),
                        integral, calculateMean(sumIntegrals), calculateSampleStandardDeviation(sumIntegrals)
                );
                ZScores.add(ZScore);
            }
        });
        // 4.Z-Score值最大的5个活动，作为推荐列表插入广告表中
        List<Double> fiveZScores = ZScores.stream().sorted(Comparator.reverseOrder()).limit(5).collect(Collectors.toList());
        return fiveZScores;
    }

    /**
     * 计算总的 点赞数、评论数、粉丝数、积分数
     */
    private void calSums(List<Article> articleList, ArrayList<Double> sumLikes, ArrayList<Double> sumComments, ArrayList<Double> sumFollowers, ArrayList<Double> sumIntegrals) {
        for (Article article : articleList) {
            Long userId = article.getUserId();
            User user = userService.getById(userId);
            Long likes = article.getPraise(); // 点赞数
            Long comments = article.getComment(); // 评论数
            Long followers = user.getFollow(); // 粉丝数
            Long integral = userMemberService.getMemberInfoByUserId(userId).getIntegral(); // 积分数
            sumLikes.add(Double.parseDouble(likes.toString()));
            sumComments.add(Double.parseDouble(comments.toString()));
            sumFollowers.add(Double.parseDouble(followers.toString()));
            sumIntegrals.add(Double.parseDouble(integral.toString()));
        }
    }

    /**
     * 计算Z-Score值
     * @param likes 点赞数
     * @param avg_likes 点赞平均值
     * @param stddev_likes 点赞标准差
     * @param comments 评论数
     * @param AVG_COMMENTS 评论平均值
     * @param stddev_comments 评论标准差
     * @param followers 粉丝数
     * @param AVG_FOLLOWERS 粉丝平均值
     * @param stddev_followers 粉丝标准差
     * @param points 积分数
     * @param AVG_POINTS 积分平均值
     * @param stddev_points 积分标准差
     * @return Z-Score值
     */
    public static double calculateCompositeZScore(double likes, double avg_likes,double stddev_likes,
                                                  double comments, double AVG_COMMENTS,double stddev_comments,
                                                  double followers, double AVG_FOLLOWERS,double stddev_followers,
                                                  double points, double AVG_POINTS,double stddev_points) {
        // 计算每个单项的Z-Score
        double zLikes = (likes - avg_likes) / stddev_likes;
        double zComments = (comments - AVG_COMMENTS) / stddev_comments;
        double zFollowers = (followers - AVG_FOLLOWERS) / stddev_followers;
        double zPoints = (points - AVG_POINTS) / stddev_points;

        // 加权求和得到综合Z-Score
        double compositeZScore = WEIGHT_LIKES * zLikes +
                WEIGHT_COMMENTS * zComments +
                WEIGHT_FOLLOWERS * zFollowers +
                WEIGHT_POINTS * zPoints;

        return compositeZScore;
    }

    /**
     * 计算平均值
     * @param data 要计算的平均值的数据
     * @return 平均值
     */
    public static double calculateMean(List<Double> data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data list cannot be empty or null");
        }
        double sum = 0;
        for (Double value : data) {
            sum += value;
        }
        return sum / data.size();
    }

    /**
     * 计算样本标准差
     * @param data 要计算的标准差的数据
     * @return 标准差
     */
    public static double calculateSampleStandardDeviation(List<Double> data) {
        double mean = calculateMean(data);
        double sumOfSquares = 0;
        for (Double value : data) {
            sumOfSquares += Math.pow(value - mean, 2);
        }
        return Math.sqrt(sumOfSquares / (data.size() - 1));
    }
}
