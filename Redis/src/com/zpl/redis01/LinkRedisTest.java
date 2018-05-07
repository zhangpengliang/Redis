package com.zpl.redis01;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.BinaryClient.LIST_POSITION;

/**
 * 链表结构的操作
 * @author zhangpengliang
 *
 */
public class LinkRedisTest {

	public static void main(String[] args) {
		Jedis redis=new Jedis("192.168.179.128", 6379);
		redis.flushDB();
		//放到一个链表中
		//放到左侧
		redis.lpush("charator", "a","b","c");
		//放到右侧
		redis.rpush("charator", "d","e");
		//获取所有的值
		System.out.println(redis.lrange("charator", 0, -1));
		//根据index来获取,负数是从右侧开始数
		System.out.println(redis.lindex("charator", -2));
		
		//lpop rpop 弹出并删除
		redis.lpop("charator");
		redis.rpop("charator");
		System.out.println(redis.lrange("charator", 0, -1));

		redis.rpush("charator", "a","d");
		//删除--count代表：删除几个，如果有重复的，大于0则从左侧开始删，小于0则从右侧删除,=0意思是全删
		// value 是要指定删除的值
		redis.lrem("charator", -1, "a");
		System.out.println(redis.lrange("charator", 0, -1));
		
		//剪切
		String qie=redis.ltrim("charator", 1, 2);
		System.out.println(qie);
		System.out.println(redis.lrange("charator", 0, -1));
		//根据索引获取
		redis.lindex("charator", 2);
		
		//获取有多少个节点，长度
		long l=redis.llen("charator");
		
		//插入操作,在a的后面插入一个y.如果有重复的a，就在第一个a后面
		redis.linsert("charator", LIST_POSITION.AFTER, "a", "y");
		/**
		 * charator的尾部弹出1个,放到dest的左侧放入
		 */
		redis.rpoplpush("charator", "dest");
		redis.del("dest");
		//brpop key timeout :一个会话等待另一个会话向key中放入值，超过等待时间后就不会获取了。
		//执行一次命令就弹出一次。blpop key timeout 从左侧弹出
		
		redis.brpoplpush("charator", "dest", 0);//0代表一值等待
		System.out.println(redis.lrange("dest", 0, -1));
	}

}
