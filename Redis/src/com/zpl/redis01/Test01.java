package com.zpl.redis01;


import redis.clients.jedis.Jedis;
/**
 * Jedis�ļ�ʹ��
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
		//ɾ��һ��key
		redis.del("11");
		//����key������
		redis.rename("age", "ages");
		System.out.println(redis.keys("*"));
		//���������������key���������޸ĳɹ�����ʧ��
		redis.renamenx("ages", "site");
		System.out.println(redis.keys("*"));
		redis.select(1);//�л���1�ſ�
		//keys(*)��ѯ���е�key
		System.out.println(redis.keys("*"));
		redis.select(0);
		System.out.println(redis.keys("*"));
		//������Ч��,10�ĵ�λ����
		redis.expire("site", 10);
		//��ѯ��Ч��
		redis.ttl("site");
		redis.set("site", "www.baidu.com");
		//������Ч�ڣ���λ�Ǻ���
		redis.pexpire("site", 10000);
		redis.pttl("site");
		//����key����Ϊ�־û�,����ʧЧ
		redis.persist("site");
		
	}

}
