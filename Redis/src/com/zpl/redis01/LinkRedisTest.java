package com.zpl.redis01;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.BinaryClient.LIST_POSITION;

/**
 * ����ṹ�Ĳ���
 * @author zhangpengliang
 *
 */
public class LinkRedisTest {

	public static void main(String[] args) {
		Jedis redis=new Jedis("192.168.179.128", 6379);
		redis.flushDB();
		//�ŵ�һ��������
		//�ŵ����
		redis.lpush("charator", "a","b","c");
		//�ŵ��Ҳ�
		redis.rpush("charator", "d","e");
		//��ȡ���е�ֵ
		System.out.println(redis.lrange("charator", 0, -1));
		//����index����ȡ,�����Ǵ��Ҳ࿪ʼ��
		System.out.println(redis.lindex("charator", -2));
		
		//lpop rpop ������ɾ��
		redis.lpop("charator");
		redis.rpop("charator");
		System.out.println(redis.lrange("charator", 0, -1));

		redis.rpush("charator", "a","d");
		//ɾ��--count����ɾ��������������ظ��ģ�����0�����࿪ʼɾ��С��0����Ҳ�ɾ��,=0��˼��ȫɾ
		// value ��Ҫָ��ɾ����ֵ
		redis.lrem("charator", -1, "a");
		System.out.println(redis.lrange("charator", 0, -1));
		
		//����
		String qie=redis.ltrim("charator", 1, 2);
		System.out.println(qie);
		System.out.println(redis.lrange("charator", 0, -1));
		//����������ȡ
		redis.lindex("charator", 2);
		
		//��ȡ�ж��ٸ��ڵ㣬����
		long l=redis.llen("charator");
		
		//�������,��a�ĺ������һ��y.������ظ���a�����ڵ�һ��a����
		redis.linsert("charator", LIST_POSITION.AFTER, "a", "y");
		/**
		 * charator��β������1��,�ŵ�dest��������
		 */
		redis.rpoplpush("charator", "dest");
		redis.del("dest");
		//brpop key timeout :һ���Ự�ȴ���һ���Ự��key�з���ֵ�������ȴ�ʱ���Ͳ����ȡ�ˡ�
		//ִ��һ������͵���һ�Ρ�blpop key timeout ����൯��
		
		redis.brpoplpush("charator", "dest", 0);//0����һֵ�ȴ�
		System.out.println(redis.lrange("dest", 0, -1));
	}

}
