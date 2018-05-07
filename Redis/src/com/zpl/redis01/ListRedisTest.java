package com.zpl.redis01;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

/**
 * ���򼯺�--redis����
 * @author zhangpengliang
 *
 */
public class ListRedisTest {

	public static void main(String[] args) {
		Jedis redis=new Jedis("192.168.179.128", 6379);
		redis.flushDB();
		//���
		redis.zadd("list", 12, "lisi");
		Map<String, Double> map=new HashMap<String,Double>();
		map.put("zhangdan", 6.0);
		map.put("niju", 19.0);
		redis.zadd("list", map);
		
		//ȡ�����е�ֵ
		Set<String> set=redis.zrange("list", 0, -1);
		System.out.println(set.toString());
		//ȡ������0-18֮���
		redis.zrangeByScore("list", 0, 18);
		
		Set<Tuple> set1=redis.zrangeWithScores("list", 0, -1);
		for(Tuple t:set1){
			System.out.println(t.getElement()+"  ����:  "+t.getScore());
		}
		
		//�Ƴ�
		redis.zrem("list", "lisi");
		
		redis.zrangeByLex("list", "-", "+");
		System.out.println(redis.zrange("list", 0, -1));
		
		//���ϴ�С
		long a=redis.zcard("list");
		System.out.println("��С:"+a);
	}

}
