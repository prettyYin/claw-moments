package com.moments.claw.domain.common.service;
 
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Distance;
import org.springframework.data.redis.core.*;
 
/**
 * AbstractRedisService
 *
 * @author xm.z
 */
@Slf4j
public abstract class AbstractRedisService<K,V> implements RedisService<K,V> {

	public abstract Distance geoCalculationDistance(String key, String placeOne, String placeTwo);

	/**
	 * Get Helper class that simplifies Redis data access code.
	 * @return RedisTemplate 获取
	 */
	protected abstract RedisTemplate getTemplate();
 
	public HashOperations<String, String, V> hashOps() {
		return this.getTemplate().opsForHash();
	}
 
	public ValueOperations<String, V> valueOps() {
		return this.getTemplate().opsForValue();
	}
 
	public ListOperations<String, V> listOps() {
		return this.getTemplate().opsForList();
	}
 
	public SetOperations<String, V> setOps() {
		return this.getTemplate().opsForSet();
	}
 
	public ZSetOperations<String, V> zSetOps() {
		return this.getTemplate().opsForZSet();
	}
 
	public StreamOperations<String, K, V> streamOps() {
		return this.getTemplate().opsForStream();
	}
 
	public GeoOperations<String, Object> geoOps() {
		return this.getTemplate().opsForGeo();
	}
 
}