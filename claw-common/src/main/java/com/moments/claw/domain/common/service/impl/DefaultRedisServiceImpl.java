package com.moments.claw.domain.common.service.impl;

import com.moments.claw.domain.common.constant.RedisConstants;
import com.moments.claw.domain.common.service.AbstractRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 默认 Redis 服务实现
 *
 * @author xm.z
 */
@RequiredArgsConstructor
public class DefaultRedisServiceImpl<K,V> extends AbstractRedisService<K,V>{

	@Resource
	private final RedisTemplate redisTemplate;

	/**
	 * 获取所有符合指定表达式的 key
	 * @param pattern 表达式
	 * @return java.util.Set
	 * @see <a href="http://redis.io/commands/keys">Keys Command</a>
	 */
	@Override
	public Collection<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}

	/**
	 * 保存属性
	 * @param key key值
	 * @param value value值
	 * @param time 时间戳
	 */
	@Override
	public void set(String key, V value, long time) {
		valueOps().set(key, value, time, TimeUnit.SECONDS);
	}

	/**
	 * 保存属性
	 * @param key key值
	 * @param value value值
	 */
	@Override
	public void set(String key, V value) {
		valueOps().set(key, value);
	}

	/**
	 * 获取属性
	 * @param key key值
	 * @return 返回对象
	 */
	@Override
	public V get(String key) {
		return valueOps().get(key);
	}

	/**
	 * 从指定的 keys 批量获取属性
	 * @param keys keys
	 * @return values list，当值为空时，该 key 对应的 value 为 null
	 * @see <a href="http://redis.io/commands/mget">MGet Command</a>
	 */
	@Override
	public List<V> mGet(Collection<String> keys) {
		return valueOps().multiGet(keys);
	}

	/**
	 * 批量获取 keys 的属性，并返回一个 map
	 * @param keys keys
	 * @return map，key 和 value 的键值对集合，当 value 获取为 null 时，不存入此 map
	 */
	@Override
	public Map<String, V> mGetToMap(Collection<String> keys) {
		List<V> values = valueOps().multiGet(keys);
		Map<String, V> map = new HashMap<>(keys.size());
		if (values == null || values.isEmpty()) {
			return map;
		}

		Iterator<String> keysIterator = keys.iterator();
		Iterator<V> valuesIterator = values.iterator();
		while (keysIterator.hasNext()) {
			String key = keysIterator.next();
			V value = valuesIterator.next();
			if (value != null) {
				map.put(key, value);
			}
		}
		return map;
	}

	/**
	 * 删除属性
	 * @param key key值
	 * @return 返回成功
	 */
	@Override
	public Boolean del(String key) {
		return redisTemplate.delete(key);
	}

	/**
	 * 批量删除属性
	 * @param keys key值集合
	 * @return 返回删除数量
	 */
	@Override
	public Long del(Collection<String> keys) {
		return redisTemplate.delete(keys);
	}

	/**
	 * 设置过期时间
	 * @param key key值
	 * @param time 时间戳
	 * @return 返回成功
	 */
	@Override
	public Boolean expire(String key, long time) {
		return redisTemplate.expire(key, time, TimeUnit.SECONDS);
	}

	/**
	 * 获取过期时间
	 * @param key key值
	 * @return 返回时间戳
	 */
	@Override
	public Long getExpire(String key) {
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}

	/**
	 * 判断key是否存在
	 * @param key key值
	 * @return 返回
	 */
	@Override
	public Boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 按delta递增
	 * @param key key值
	 * @param delta delta值
	 * @return 返回递增后结果
	 */
	@Override
	public Long incr(String key, long delta) {
		return valueOps().increment(key, delta);
	}

	/**
	 * 按delta递增并设置过期时间
	 * @param key key值
	 * @param delta delta值
	 * @param timeout 过期时间（单位：秒）
	 * @return 返回递增后结果
	 */
	@Override
	public Long incrAndExpire(String key, long delta, long timeout) {
		return (Long) redisTemplate.execute(RedisConstants.INCR_BY_EXPIRE_LUA_SCRIPT, Collections.singletonList(key), String.valueOf(delta),
				String.valueOf(timeout));
	}

	/**
	 * 按delta递减
	 * @param key key值
	 * @param delta delta值
	 * @return 返回递减后结果
	 */
	@Override
	public Long decr(String key, long delta) {
		return valueOps().decrement(key, delta);
	}

	/**
	 * 按delta递减并设置过期时间
	 * @param key key值
	 * @param delta delta值
	 * @param timeout 过期时间（单位：秒）
	 * @return 返回递减后结果
	 */
	@Override
	public Long decrAndExpire(String key, long delta, long timeout) {
		return (Long) redisTemplate.execute(RedisConstants.DECR_BY_EXPIRE_LUA_SCRIPT, Collections.singletonList(key), String.valueOf(delta),
				String.valueOf(timeout));
	}

	/**
	 * 获取Hash结构中的属性
	 * @param key 外部key值
	 * @param hashKey 内部key值
	 * @return 返回内部key的value
	 */
	@Override
	public V hGet(String key, String hashKey) {
		return hashOps().get(key, hashKey);
	}

	/**
	 * 向Hash结构中放入一个属性
	 * @param key 外部key
	 * @param hashKey 内部key
	 * @param value 内部key的value
	 * @param time 过期时间
	 * @return 返回是否成功
	 */
	@Override
	public Boolean hSet(String key, String hashKey, V value, long time) {
		hashOps().put(key, hashKey, value);
		return expire(key, time);
	}

	/**
	 * 向Hash结构中放入一个属性
	 * @param key 外部key
	 * @param hashKey 内部key
	 * @param value 内部key的value
	 */
	@Override
	public void hSet(String key, String hashKey, V value) {
		hashOps().put(key, hashKey, value);
	}

	/**
	 * 直接获取整个Hash结构
	 * @param key 外部key值
	 * @return 返回hashMap
	 */
	@Override
	public Map<String, V> hGetAll(String key) {
		return hashOps().entries(key);
	}

	/**
	 * 直接设置整个Hash结构
	 * @param key 外部key
	 * @param map hashMap值
	 * @param time 过期时间
	 * @return 返回是否成功
	 */
	@Override
	public Boolean hSetAll(String key, Map<String, V> map, long time) {
		hashOps().putAll(key, map);
		return expire(key, time);
	}

	/**
	 * 直接设置整个Hash结构
	 * @param key 外部key
	 * @param map hashMap值
	 */
	@Override
	public void hSetAll(String key, Map<String, V> map) {
		hashOps().putAll(key, map);
	}

	/**
	 * 删除Hash结构中的属性
	 * @param key 外部key值
	 * @param hashKey 内部key值
	 */
	@Override
	public void hDel(String key, Object... hashKey) {
		hashOps().delete(key, hashKey);
	}

	/**
	 * 判断Hash结构中是否有该属性
	 * @param key 外部key
	 * @param hashKey 内部key
	 * @return 返回是否存在
	 */
	@Override
	public Boolean hHasKey(String key, String hashKey) {
		return hashOps().hasKey(key, hashKey);
	}

	/**
	 * Hash结构中属性递增
	 * @param key 外部key
	 * @param hashKey 内部key
	 * @param delta 递增条件
	 * @return 返回递增后的数据
	 */
	@Override
	public Long hIncr(String key, String hashKey, Long delta) {
		return hashOps().increment(key, hashKey, delta);
	}

	/**
	 * Hash结构中属性递减
	 * @param key 外部key
	 * @param hashKey 内部key
	 * @param delta 递增条件
	 * @return 返回递减后的数据
	 */
	@Override
	public Long hDecr(String key, String hashKey, Long delta) {
		return hashOps().increment(key, hashKey, -delta);
	}

	/**
	 * 获取Set结构
	 * @param key key
	 * @return 返回set集合
	 */
	@Override
	public Set<V> sMembers(String key) {
		return setOps().members(key);
	}

	/**
	 * 向Set结构中添加属性
	 * @param key key
	 * @param values value集
	 * @return 返回增加数量
	 */
	@Override
	public Long sAdd(String key, V... values) {
		return setOps().add(key, values);
	}

	/**
	 * 向Set结构中添加属性
	 * @param key key
	 * @param time 过期时间
	 * @param values 值集合
	 * @return 返回添加的数量
	 */
	@Override
	public Long sAdd(String key, long time, V... values) {
		Long count = setOps().add(key, values);
		expire(key, time);
		return count;
	}

	/**
	 * 是否为Set中的属性
	 * @param key key
	 * @param value value
	 * @return 返回是否存在
	 */
	@Override
	public Boolean sIsMember(String key, V value) {
		return setOps().isMember(key, value);
	}

	/**
	 * 获取Set结构的长度
	 * @param key key
	 * @return 返回长度
	 */
	@Override
	public Long sSize(String key) {
		return setOps().size(key);
	}

	/**
	 * 删除Set结构中的属性
	 * @param key key
	 * @param values value集合
	 * @return 删除掉的数据量
	 */
	@Override
	public Long sRemove(String key, V... values) {
		return setOps().remove(key, values);
	}

	/**
	 * 获取List结构中的属性
	 * @param key key
	 * @param start 开始
	 * @param end 结束
	 * @return 返回查询的集合
	 */
	@Override
	public List<V> lRange(String key, long start, long end) {
		return listOps().range(key, start, end);
	}

	/**
	 * 获取List结构的长度
	 * @param key key
	 * @return 长度
	 */
	@Override
	public Long lSize(String key) {
		return listOps().size(key);
	}

	/**
	 * 根据索引获取List中的属性
	 * @param key key
	 * @param index 索引
	 * @return 对象
	 */
	@Override
	public V lIndex(String key, long index) {
		return listOps().index(key, index);
	}

	/**
	 * 向List结构中添加属性
	 * @param key key
	 * @param value value
	 * @return 增加后的长度
	 */
	@Override
	public Long lPush(String key, V value) {
		return listOps().rightPush(key, value);
	}

	/**
	 * 向List结构中添加属性
	 * @param key key
	 * @param value value
	 * @param time 过期时间
	 * @return 增加后的长度
	 */
	@Override
	public Long lPush(String key, V value, long time) {
		Long index = listOps().rightPush(key, value);
		expire(key, time);
		return index;
	}

	/**
	 * 向List结构中批量添加属性
	 * @param key key
	 * @param values value 集合
	 * @return 增加后的长度
	 */
	@Override
	public Long lPushAll(String key, V... values) {
		return listOps().rightPushAll(key, values);
	}

	/**
	 * 向List结构中批量添加属性
	 * @param key key
	 * @param time 过期时间
	 * @param values value集合
	 * @return 增加后的长度
	 */
	@Override
	public Long lPushAll(String key, Long time, V... values) {
		Long count = listOps().rightPushAll(key, values);
		expire(key, time);
		return count;
	}

	/**
	 * 从List结构中移除属性
	 * @param key key
	 * @param count 总量
	 * @param value value
	 * @return 返回删除后的长度
	 */
	@Override
	public Long lRemove(String key, long count, Object value) {
		return listOps().remove(key, count, value);
	}

	/**
	 * 向bitmap中新增值
	 * @param key key
	 * @param offset 偏移量
	 * @param b 状态
	 * @return 结果
	 */
	@Override
	public Boolean bitAdd(String key, int offset, boolean b) {
		return valueOps().setBit(key, offset, b);
	}

	/**
	 * 从bitmap中获取偏移量的值
	 * @param key key
	 * @param offset 偏移量
	 * @return 结果
	 */
	@Override
	public Boolean bitGet(String key, int offset) {
		return valueOps().getBit(key, offset);
	}

	/**
	 * 获取bitmap的key值总和
	 * @param key key
	 * @return 总和
	 */
	@Override
	public Long bitCount(String key) {
		return (Long) redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes()));
	}

	/**
	 * 获取bitmap范围值
	 * @param key key
	 * @param limit 范围
	 * @param offset 开始偏移量
	 * @return long类型集合
	 */
	@Override
	public List<Long> bitField(String key, int limit, int offset) {
		return (List<Long>) redisTemplate.execute((RedisCallback<List<Long>>) con -> con.bitField(key.getBytes(),
				BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.unsigned(limit)).valueAt(offset)));
	}

	/**
	 * 获取所有bitmap
	 * @param key key
	 * @return 以二进制字节数组返回
	 */
	@Override
	public byte[] bitGetAll(String key) {
		return (byte[]) redisTemplate.execute((RedisCallback<byte[]>) con -> con.get(key.getBytes()));
	}

	/**
	 * 增加坐标
	 * @param key key
	 * @param x x
	 * @param y y
	 * @param name 地点名称
	 * @return 返回结果
	 */
	@Override
	public Long geoAdd(String key, Double x, Double y, String name) {
		return geoOps().add(key, new Point(x, y), name);
	}

	/**
	 * 根据城市名称获取坐标集合
	 * @param key key
	 * @param place 地点
	 * @return 坐标集合
	 */
	@Override
	public List<Point> geoGetPointList(String key, Object... place) {
		return geoOps().position(key, place);
	}

	/**
	 * 计算两个城市之间的距离
	 * @param key key
	 * @param placeOne 地点1
	 * @param placeTow 地点2
	 * @return 返回距离
	 */
	@Override
	public Distance geoCalculationDistance(String key, String placeOne, String placeTow) {
		return geoOps().distance(key, placeOne, placeTow, RedisGeoCommands.DistanceUnit.KILOMETERS);
	}

	/**
	 * 获取附该地点附近的其他地点
	 * @param key key
	 * @param place 地点
	 * @param distance 附近的范围
	 * @param limit 查几条
	 * @param sort 排序规则
	 * @return 返回附近的地点集合
	 */
	@Override
	public GeoResults<RedisGeoCommands.GeoLocation<Object>> geoNearByPlace(String key, String place, Distance distance,
																   long limit, Sort.Direction sort) {
		RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
			.includeDistance()
			.includeCoordinates();
		// 判断排序方式
		if (Sort.Direction.ASC == sort) {
			args.sortAscending();
		}
		else {
			args.sortDescending();
		}
		args.limit(limit);
		return geoOps().radius(key, place, distance, args);
	}

	/**
	 * 获取地点的hash
	 * @param key key
	 * @param place 地点
	 * @return 返回集合
	 */
	@Override
	public List<String> geoGetHash(String key, String... place) {
		return geoOps().hash(key, place);
	}

	/**
	 * Get Helper class that simplifies Redis data access code.
	 * @return RedisTemplate 获取
	 */
	@Override
	protected RedisTemplate getTemplate() {
		return redisTemplate;
	}
 
}