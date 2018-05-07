package com.zpl.redis01;

import java.util.List;

import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;
/**
 * redis���ݽṹ����,�ַ�����ز�������
 * @author zhangpengliang
 *
 */
public class StringRedisTest {

	public static void main(String[] args) {
		Jedis redis=new Jedis("192.168.179.128", 6379);
		//����ֵ��ͬʱҲ����������,����Ϊ��-->��Ӧ���set site www.baidu.com ex 10
		redis.setex("site", 10, "www.baidu.com");//ex 10����expire 10
		
		//����Ϊ����ģ�set age 19 px 10000
		redis.psetex("age", 10000, "19");//����Ϊ���룺
		redis.set("exist2", "456");//�������set key value xx ��ָ���������key��ʱ�����ִ�в���
		//setnx �������������set�ɹ�,������ھ�setʧ��
		long a=redis.setnx("exist2", "1234");//set key value nx
		System.out.println(a);//ʧ�ܾͷ���0
		System.out.println(redis.get("exist2"));
		System.out.println(redis.keys("*"));
		/**---------------���Ͽ���ͬʱ���ã�set key value ex 10 xx�������ֵ�---------------------------*/
		
		//һ�������ö��key-vlaue
		redis.flushDB();
		redis.mset("a","A","b","B","c","C");
		System.out.println(redis.keys("*"));//�����a b c
		
		//һ���Ի�ȡ���
		List<String> list=redis.mget("a","b","c");
		System.out.println(list.toString());//--A B C
		
		//�޸�keyΪa��ֵ,ƫ������0,�޸�ΪN.Ҳ����a��ֵ�������,��0λ��ΪN,�����������ó�N
		redis.setrange("a", 0, "N");
		Long l=redis.setrange("n", 1, "not");//�����ھ�����ֵ,���ƫ�������Ǵ�0��ʼ��ô���ǣ��ո�not
		System.out.println(redis.get("n").trim());
		
		redis.append("a", "BNCBNC");
		System.out.println(redis.get("a"));
		//��ȡa�Ĳ����ַ���.����star��stop//�����0 �Ҳ���-1
		String notall=redis.getrange("a", 0, 3);// getrange key star stop
		String all=redis.getrange("a", 0, -1);//��ȡ��ȫ����
		System.out.println(notall +" "+ all);
		//getset �������ɵ�ֵ�����أ����������µ�ֵ
		String old=redis.getSet("a", "SeLD");//-->getset key value
		System.out.println(old);
		//TODO
		redis.set("age", "9");
		redis.incr("age");//ÿ����1
		redis.incrBy("age", 5);//��5
		redis.incrByFloat("age", 0.5);//��0.5
		System.out.println(redis.get("age"));
		
		redis.flushDB();
		redis.mset("a","A","b","b","c","c");
		//��λ�ĽǶ����޸Ĵ�Сд
		redis.setbit("b", 2, "0");//ͨ��λ����,��Сдת�ɴ�д
		redis.setbit("a", 2, "1");//ͨ��λ��������дת��Сд
		//bitop��λ���в���& | ~
		redis.set("a", "1");
		redis.set("b", "3");
		redis.bitop(BitOP.XOR, "dest", "a","b");//bit operation
		System.out.println(redis.get("dest"));
	}

}
