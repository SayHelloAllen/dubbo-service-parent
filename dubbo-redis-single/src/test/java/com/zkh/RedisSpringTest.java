package com.zkh;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;


public class RedisSpringTest {
	private static final Log log = LogFactory.getLog(RedisSpringTest.class);
	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			context.start();
			ShardedJedisPool pool = (ShardedJedisPool) context.getBean("shardedJedisPool");
			ShardedJedis jedis = pool.getResource();
			String key = "wusc";
			String value = "";
			jedis.del(key); // 删数据
			jedis.set(key, "WuShuicheng"); // 存数据
			value = jedis.get(key); // 取数据
			log.info(key + "=" + value);
			jedis.set(key, "WuShuicheng2"); // 存数据
			value = jedis.get(key); // 取数据
			log.info(key + "=" + value);	
			jedis.del(key); // 删数据
			value = jedis.get(key); // 取数据
			log.info(key + "=" + value);
			context.stop();
		} catch (Exception e) {
			log.error("==>RedisSpringTest context start error:", e);
			System.exit(0);
		} finally {
			log.info("===>System.exit");
			System.exit(0);
		}
	}
}
