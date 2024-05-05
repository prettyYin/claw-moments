package com.moments.claw.test;

import com.google.gson.Gson;
import com.moments.claw.PetServerApplication;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import java.io.FileInputStream;

@SpringBootTest(classes = PetServerApplication.class)
@Component
@Data
public class OSSTest {

	@Value(value = "${oss.qiniuyun.accessKey}")
	private String accessKey;
	@Value(value = "${oss.qiniuyun.secretKey}")
	private String secretKey;
	@Value(value = "${oss.qiniuyun.bucket}")
	private String bucket;

	@Test
	public void OssTest() {
		//构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.autoRegion());
		cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
		//...其他参数参考类注释

		UploadManager uploadManager = new UploadManager(cfg);
		//...生成上传凭证，然后准备上传
//		String accessKey = "OSElehf53cj6E7VG0eObBuGHK42VRNnYiumv_POy";
//		String secretKey = "ateu3UUtYXG6I18WZ6plBq9k6zwZH_TX2DK8R6-S";
//		String bucket = "chandler-blog";

		//默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = null;

		try {
			FileInputStream fileInputStream = new FileInputStream("E:\\MyUserData\\Desktop\\OAuth2单点登录.png");
			Auth auth = Auth.create(accessKey, secretKey);
			String upToken = auth.uploadToken(bucket);

			try {
				Response response = uploadManager.put(fileInputStream, key, upToken, null, null);
				//解析上传成功的结果
				DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
				System.out.println(putRet.key);
				System.out.println(putRet.hash);
			} catch (QiniuException ex) {
				Response r = ex.response;
				System.err.println(r.toString());
				try {
					System.err.println(r.bodyString());
				} catch (QiniuException ex2) {
					//ignore
				}
			}
		} catch (Exception ex) {
			//ignore
		}


	}
}