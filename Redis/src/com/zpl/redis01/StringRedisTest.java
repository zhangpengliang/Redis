package com.zpl.redis01;

import java.util.List;

import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;
/**
 * redis数据结构操作,字符串相关操作命令
 * @author zhangpengliang
 *
 */
public class StringRedisTest {

	public static void main(String[] args) {
		Jedis redis=new Jedis("192.168.179.128", 6379);
		//设置值的同时也设置了周期,周期为秒-->对应命令：set site www.baidu.com ex 10
		redis.setex("site", 10, "www.baidu.com");//ex 10代表：expire 10
		
		//周期为毫秒的：set age 19 px 10000
		redis.psetex("age", 10000, "19");//设置为毫秒：
		redis.set("exist2", "456");//但是命令：set key value xx 是指必须有这个key的时候才能执行操作
		//setnx 是如果不存在则set成功,如果存在就set失败
		long a=redis.setnx("exist2", "1234");//set key value nx
		System.out.println(a);//失败就返回0
		System.out.println(redis.get("exist2"));
		System.out.println(redis.keys("*"));
		/**---------------以上可以同时设置：set key value ex 10 xx类似这种的---------------------------*/
		
		//一次性设置多个key-vlaue
		redis.flushDB();
		redis.mset("a","A","b","B","c","C");
		System.out.println(redis.keys("*"));//结果是a b c
		
		//一次性获取多个
		List<String> list=redis.mget("a","b","c");
		System.out.println(list.toString());//--A B C
		
		//修改key为a的值,偏移量是0,修改为N.也就是a得值如果存在,第0位改为N,不存在则设置成N
		redis.setrange("a", 0, "N");
		Long l=redis.setrange("n", 1, "not");//不存在就设置值,如果偏移量不是从0开始那么就是：空格not
		System.out.println(redis.get("n").trim());
		
		redis.append("a", "BNCBNC");
		System.out.println(redis.get("a"));
		//获取a的部分字符串.包含star和stop//左侧是0 右侧是-1
		String notall=redis.getrange("a", 0, 3);// getrange key star stop
		String all=redis.getrange("a", 0, -1);//获取了全部的
		System.out.println(notall +" "+ all);
		//getset 读出来旧的值并返回，并且设置新的值
		String old=redis.getSet("a", "SeLD");//-->getset key value
		System.out.println(old);
		//TODO
		redis.set("age", "9");
		redis.incr("age");//每次涨1
		redis.incrBy("age", 5);//涨5
		redis.incrByFloat("age", 0.5);//涨0.5
		System.out.println(redis.get("age"));
		
		redis.flushDB();
		redis.mset("a","A","b","b","c","c");
		//从位的角度来修改大小写
		redis.setbit("b", 2, "0");//通过位操作,将小写转成大写
		redis.setbit("a", 2, "1");//通过位操作将大写转成小写
		//bitop对位进行操作& | ~
		redis.set("a", "1");
		redis.set("b", "3");
		redis.bitop(BitOP.XOR, "dest", "a","b");//bit operation
		System.out.println(redis.get("dest"));
	}

}
