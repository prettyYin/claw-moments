package com.moments.claw.domain.common.service;
 
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
 
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
 
/**
 * Redis 服务
 *
 * @author xm.z
 */
public interface RedisService<K,V> {

	/**
	 * 获取所有符合指定表达式的 key
	 * @param pattern 表达式
	 * @return java.util.Set
	 * @see <a href="http://redis.io/commands/keys">Keys Command</a>
	 */
	Collection<String> keys(String pattern);

	/**
	 * 保存属性
	 * @param key key值
	 * @param value value值
	 * @param time 时间戳
	 */
	 void set(String key, V value, long time);

	/**
	 * 保存属性
	 * @param key key值
	 * @param value value值
	 */
	 void set(String key, V value);

	/**
	 * 获取属性
	 * @param key key值
	 * @return 返回对象
	 */
	 V get(String key);

	/**
	 * 从指定的 keys 批量获取属性
	 * @param keys keys
	 * @return values list，当值为空时，该 key 对应的 value 为 null
	 * @see <a href="http://redis.io/commands/mget">MGet Command</a>
	 */
	 List<V> mGet(Collection<String> keys);

	/**
	 * 批量获取 keys 的属性，并返回一个 map
	 * @param keys keys
	 * @return map，key 和 value 的键值对集合，当 value 获取为 null 时，不存入此 map
	 */
	 Map<String, V> mGetToMap(Collection<String> keys);

	/**
	 * 删除属性
	 * @param key key值
	 * @return 返回成功
	 */
	Boolean del(String key);

	/**
	 * 批量删除属性
	 * @param keys key值集合
	 * @return 返回删除数量
	 */
	Long del(Collection<String> keys);

	/**
	 * 设置过期时间
	 * @param key key值
	 * @param time 时间戳
	 * @return 返回成功
	 */
	Boolean expire(String key, long time);

	/**
	 * 获取过期时间
	 * @param key key值
	 * @return 返回时间戳
	 */
	Long getExpire(String key);

	/**
	 * 判断key是否存在
	 * @param key key值
	 * @return 返回
	 */
	Boolean hasKey(String key);

	/**
	 * 按delta递增
	 * @param key key值
	 * @param delta delta值
	 * @return 返回递增后结果
	 */
	Long incr(String key, long delta);

	/**
	 * 按delta递增并设置过期时间
	 * @param key key值
	 * @param delta delta值
	 * @param timeout 过期时间（单位：秒）
	 * @return 返回递增后结果
	 */
	Long incrAndExpire(String key, long delta, long timeout);

	/**
	 * 按delta递减
	 * @param key key值
	 * @param delta delta值
	 * @return 返回递减后结果
	 */
	Long decr(String key, long delta);

	/**
	 * 按delta递减并设置过期时间
	 * @param key key值
	 * @param delta delta值
	 * @param timeout 过期时间（单位：秒）
	 * @return 返回递减后结果
	 */
	Long decrAndExpire(String key, long delta, long timeout);

	/**
	 * 获取Hash结构中的属性
	 * @param key 外部key值
	 * @param hashKey 内部key值
	 * @return 返回内部key的value
	 */
	V hGet(String key, String hashKey);

	/**
	 * 向Hash结构中放入一个属性
	 * @param key 外部key
	 * @param hashKey 内部key
	 * @param value 内部key的value
	 * @param time 过期时间
	 * @return 返回是否成功
	 */
	 Boolean hSet(String key, String hashKey, V value, long time);

	/**
	 * 向Hash结构中放入一个属性
	 * @param key 外部key
	 * @param hashKey 内部key
	 * @param value 内部key的value
	 */
	 void hSet(String key, String hashKey, V value);

	/**
	 * 直接获取整个Hash结构
	 * @param key 外部key值
	 * @return 返回hashMap
	 */
	Map<String, V> hGetAll(String key);

	/**
	 * 直接设置整个Hash结构
	 * @param key 外部key
	 * @param map hashMap值
	 * @param time 过期时间
	 * @return 返回是否成功
	 */
	 Boolean hSetAll(String key, Map<String, V> map, long time);

	/**
	 * 直接设置整个Hash结构
	 * @param key 外部key
	 * @param map hashMap值
	 */
	 void hSetAll(String key, Map<String, V> map);

	/**
	 * 删除Hash结构中的属性
	 * @param key 外部key值
	 * @param hashKey 内部key值
	 */
	 void hDel(String key, K... hashKey);

	/**
	 * 判断Hash结构中是否有该属性
	 * @param key 外部key
	 * @param hashKey 内部key
	 * @return 返回是否存在
	 */
	Boolean hHasKey(String key, String hashKey);

	/**
	 * Hash结构中属性递增
	 * @param key 外部key
	 * @param hashKey 内部key
	 * @param delta 递增条件
	 * @return 返回递增后的数据
	 */
	Long hIncr(String key, String hashKey, Long delta);

	/**
	 * Hash结构中属性递减
	 * @param key 外部key
	 * @param hashKey 内部key
	 * @param delta 递增条件
	 * @return 返回递减后的数据
	 */
	Long hDecr(String key, String hashKey, Long delta);

	/**
	 * 获取Set结构
	 * @param key key
	 * @return 返回set集合
	 */
	 Set<V> sMembers(String key);

	/**
	 * 向Set结构中添加属性
	 * @param key key
	 * @param values value集
	 * @return 返回增加数量
	 */
	 Long sAdd(String key, V... values);

	/**
	 * 向Set结构中添加属性
	 * @param key key
	 * @param time 过期时间
	 * @param values 值集合
	 * @return 返回添加的数量
	 */
	 Long sAdd(String key, long time, V... values);

	/**
	 * 是否为Set中的属性
	 * @param key key
	 * @param value value
	 * @return 返回是否存在
	 */
	 Boolean sIsMember(String key, V value);

	/**
	 * 获取Set结构的长度
	 * @param key key
	 * @return 返回长度
	 */
	Long sSize(String key);

	/**
	 * 删除Set结构中的属性
	 * @param key key
	 * @param values value集合
	 * @return 删除掉的数据量
	 */
	 Long sRemove(String key, V... values);

	/**
	 * 获取List结构中的属性
	 * @param key key
	 * @param start 开始
	 * @param end 结束
	 * @return 返回查询的集合
	 */
	 List<V> lRange(String key, long start, long end);

	/**
	 * 获取List结构的长度
	 * @param key key
	 * @return 长度
	 */
	Long lSize(String key);

	/**
	 * 根据索引获取List中的属性
	 * @param key key
	 * @param index 索引
	 * @return 对象
	 */
	 V lIndex(String key, long index);

	/**
	 * 向List结构中添加属性
	 * @param key key
	 * @param value value
	 * @return 增加后的长度
	 */
	 Long lPush(String key, V value);

	/**
	 * 向List结构中添加属性
	 * @param key key
	 * @param value value
	 * @param time 过期时间
	 * @return 增加后的长度
	 */
	 Long lPush(String key, V value, long time);

	/**
	 * 向List结构中批量添加属性
	 * @param key key
	 * @param values value 集合
	 * @return 增加后的长度
	 */
	 Long lPushAll(String key, V... values);

	/**
	 * 向List结构中批量添加属性
	 * @param key key
	 * @param time 过期时间
	 * @param values value集合
	 * @return 增加后的长度
	 */
	 Long lPushAll(String key, Long time, V... values);

	/**
	 * 从List结构中移除属性
	 * @param key key
	 * @param count 总量
	 * @param value value
	 * @return 返回删除后的长度
	 */
	 Long lRemove(String key, long count, V value);

	/**
	 * 向bitmap中新增值
	 * @param key key
	 * @param offset 偏移量
	 * @param b 状态
	 * @return 结果
	 */
	Boolean bitAdd(String key, int offset, boolean b);

	/**
	 * 从bitmap中获取偏移量的值
	 * @param key key
	 * @param offset 偏移量
	 * @return 结果
	 */
	Boolean bitGet(String key, int offset);

	/**
	 * 获取bitmap的key值总和
	 * @param key key
	 * @return 总和
	 */
	Long bitCount(String key);

	/**
	 * 获取bitmap范围值
	 * @param key key
	 * @param limit 范围
	 * @param offset 开始偏移量
	 * @return long类型集合
	 */
	List<Long> bitField(String key, int limit, int offset);

	/**
	 * 获取所有bitmap
	 * @param key key
	 * @return 以二进制字节数组返回
	 */
	byte[] bitGetAll(String key);

	/**
	 * 增加坐标
	 * @param key key
	 * @param x x
	 * @param y y
	 * @param name 地点名称
	 * @return 返回结果
	 */
	Long geoAdd(String key, Double x, Double y, String name);

	/**
	 * 根据城市名称获取坐标集合
	 * @param key key
	 * @param place 地点
	 * @return 坐标集合
	 */
	 List<Point> geoGetPointList(String key, Object... place);

	/**
	 * 计算两个城市之间的距离
	 * @param key key
	 * @param placeOne 地点1
	 * @param placeTwo 地点2
	 * @return 返回距离
	 */
	Distance geoCalculationDistance(String key, String placeOne, String placeTwo);

	/**
	 * 获取附该地点附近的其他地点
	 * @param key key
	 * @param place 地点
	 * @param distance 附近的范围
	 * @param limit 查几条
	 * @param sort 排序规则
	 * @return 返回附近的地点集合
	 */
	 GeoResults<RedisGeoCommands.GeoLocation<Object>> geoNearByPlace(String key, String place, Distance distance,
			long limit, Sort.Direction sort);
 
	/**
	 * 获取地点的hash
	 * @param key key
	 * @param place 地点
	 * @return 返回集合
	 */
	List<String> geoGetHash(String key, String... place);
 
}