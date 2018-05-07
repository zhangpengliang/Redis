package com.zpl.redis01;

import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * 集合结构的redis操作--集合无序性，唯一性
 * @author zhangpengliang
 *
 */
public class SetRedis {

	public static void main(String[] args) {
		Jedis redis=new Jedis("192.168.179.128", 6379);
		redis.flushDB();
		redis.sadd("set", "男","女","c","d");
		//获取所有的值
		System.out.println(redis.smembers("set"));
		//删除
		redis.srem("set", "a","c");
		System.out.println(redis.smembers("set"));
		
		//spop随机弹出一个元素并删除
		redis.spop("set");
		System.out.println(redis.smembers("set"));
		//srandmember.随机取出set中的一员
		System.out.println(redis.srandmember("set"));
		redis.sadd("set", "hj","ds");
		//随机取出2个
		System.out.println(redis.srandmember("set", 2));
		//判断是否存在某个元素
		boolean isexist=redis.sismember("set", "hj");
		System.out.println(isexist);
		//判断set的集合个数
		long len=redis.scard("set");
		System.out.println(len);
		System.out.println(redis.smembers("set"));
		//移动
		redis.smove("set", "dest", "d");
		System.out.println(redis.smembers("set"));
		System.out.println(redis.smembers("dest"));
		
		redis.sadd("set", "a","d","u");
		//求交集
		Set<String> st=redis.sinter("set","dest");
		System.out.println(st.toString());
		//求出交集并保存
		redis.sinterstore("source", "set","dest");
		System.out.println(redis.smembers("source"));
		
		//并集
		redis.sunion("set","dest");
		redis.sunionstore("binji", "set","dest");
		redis.sadd("binji", "l","hs","u");
		//差集
		redis.sdiff("set","bingji");
		System.out.println("差集后set："+redis.smembers("set"));
		redis.sdiffstore("diff","binji","set");//binji-set
		System.out.println("差集"+redis.smembers("diff"));
		
		
	}

}
