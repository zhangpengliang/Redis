package com.zpl.redis01;


import redis.clients.jedis.Jedis;
/**
 * Jedis的简单使用
 * @author zhangpengliang
 *
 */
public class Test01 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		Jedis redis=new Jedis("192.168.179.128", 6379);
		redis.set("11", "dsd");
		System.out.println(redis.get("11"));
		redis.set("age", "19");
		redis.set("site", "www.baidu.com");
		//删除一个key
		redis.del("11");
		//更换key的名称
		redis.rename("age", "ages");
		System.out.println(redis.keys("*"));
		//如果更换的新名称key不存在这修改成功否则失败
		redis.renamenx("ages", "site");
		System.out.println(redis.keys("*"));
		redis.select(1);//切换到1号库
		//keys(*)查询所有的key
		System.out.println(redis.keys("*"));
		redis.select(0);
		System.out.println(redis.keys("*"));
		//设置有效期,10的单位是秒
		redis.expire("site", 10);
		//查询有效期
		redis.ttl("site");
		redis.set("site", "www.baidu.com");
		//设置有效期，单位是毫秒
		redis.pexpire("site", 10000);
		redis.pttl("site");
		//将该key设置为持久化,不能失效
		redis.persist("site");
		
	}

}
