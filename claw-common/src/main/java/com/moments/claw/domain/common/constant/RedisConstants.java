package com.moments.claw.domain.common.constant;
 
import org.springframework.data.redis.core.script.DefaultRedisScript;
 
/**
 * RedisConstants
 *
 * @author xm.z
 */
public final class RedisConstants {
 
	/**
	 * 递增并设置过期时间的 lua 脚本
	 */
	public static final DefaultRedisScript<Long> INCR_BY_EXPIRE_LUA_SCRIPT = new DefaultRedisScript<>(
			"local r = redis.call('INCRBY', KEYS[1], ARGV[1]) redis.call('EXPIRE', KEYS[1], ARGV[2]) return r",
			Long.class);
 
	/**
	 * 递减并设置过期时间的 lua 脚本
	 */
	public static final DefaultRedisScript<Long> DECR_BY_EXPIRE_LUA_SCRIPT = new DefaultRedisScript<>(
			"local r = redis.call('DECRBY', KEYS[1], ARGV[1]) redis.call('EXPIRE', KEYS[1], ARGV[2]) return r",
			Long.class);
 
	private RedisConstants() {
	}
 
}