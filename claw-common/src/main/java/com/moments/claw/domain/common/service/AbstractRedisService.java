package com.moments.claw.domain.common.service;
 
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.*;
 
/**
 * AbstractRedisService
 *
 * @author xm.z
 */
@Slf4j
public abstract class AbstractRedisService implements RedisService {
 
	/**
	 * Get Helper class that simplifies Redis data access code.
	 * @return RedisTemplate 获取
	 */
	protected abstract RedisTemplate<String, Object> getTemplate();
 
	public HashOperations<String, Object, Object> hashOps() {
		return this.getTemplate().opsForHash();
	}
 
	public ValueOperations<String, Object> valueOps() {
		return this.getTemplate().opsForValue();
	}
 
	public ListOperations<String, Object> listOps() {
		return this.getTemplate().opsForList();
	}
 
	public SetOperations<String, Object> setOps() {
		return this.getTemplate().opsForSet();
	}
 
	public ZSetOperations<String, Object> zSetOps() {
		return this.getTemplate().opsForZSet();
	}
 
	public StreamOperations<String, Object, Object> streamOps() {
		return this.getTemplate().opsForStream();
	}
 
	public GeoOperations<String, Object> geoOps() {
		return this.getTemplate().opsForGeo();
	}
 
}