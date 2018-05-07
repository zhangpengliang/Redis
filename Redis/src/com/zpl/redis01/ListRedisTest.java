package com.zpl.redis01;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

/**
 * 有序集合--redis操作
 * @author zhangpengliang
 *
 */
public class ListRedisTest {

	public static void main(String[] args) {
		Jedis redis=new Jedis("192.168.179.128", 6379);
		redis.flushDB();
		//添加
		redis.zadd("list", 12, "lisi");
		Map<String, Double> map=new HashMap<String,Double>();
		map.put("zhangdan", 6.0);
		map.put("niju", 19.0);
		redis.zadd("list", map);
		
		//取出所有的值
		Set<String> set=redis.zrange("list", 0, -1);
		System.out.println(set.toString());
		//取分数在0-18之间的
		redis.zrangeByScore("list", 0, 18);
		
		Set<Tuple> set1=redis.zrangeWithScores("list", 0, -1);
		for(Tuple t:set1){
			System.out.println(t.getElement()+"  分数:  "+t.getScore());
		}
		
		//移出
		redis.zrem("list", "lisi");
		
		redis.zrangeByLex("list", "-", "+");
		System.out.println(redis.zrange("list", 0, -1));
		
		//集合大小
		long a=redis.zcard("list");
		System.out.println("大小:"+a);
	}

}
