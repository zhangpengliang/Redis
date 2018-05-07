package com.zpl.redis01;

import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * ���Ͻṹ��redis����--���������ԣ�Ψһ��
 * @author zhangpengliang
 *
 */
public class SetRedis {

	public static void main(String[] args) {
		Jedis redis=new Jedis("192.168.179.128", 6379);
		redis.flushDB();
		redis.sadd("set", "��","Ů","c","d");
		//��ȡ���е�ֵ
		System.out.println(redis.smembers("set"));
		//ɾ��
		redis.srem("set", "a","c");
		System.out.println(redis.smembers("set"));
		
		//spop�������һ��Ԫ�ز�ɾ��
		redis.spop("set");
		System.out.println(redis.smembers("set"));
		//srandmember.���ȡ��set�е�һԱ
		System.out.println(redis.srandmember("set"));
		redis.sadd("set", "hj","ds");
		//���ȡ��2��
		System.out.println(redis.srandmember("set", 2));
		//�ж��Ƿ����ĳ��Ԫ��
		boolean isexist=redis.sismember("set", "hj");
		System.out.println(isexist);
		//�ж�set�ļ��ϸ���
		long len=redis.scard("set");
		System.out.println(len);
		System.out.println(redis.smembers("set"));
		//�ƶ�
		redis.smove("set", "dest", "d");
		System.out.println(redis.smembers("set"));
		System.out.println(redis.smembers("dest"));
		
		redis.sadd("set", "a","d","u");
		//�󽻼�
		Set<String> st=redis.sinter("set","dest");
		System.out.println(st.toString());
		//�������������
		redis.sinterstore("source", "set","dest");
		System.out.println(redis.smembers("source"));
		
		//����
		redis.sunion("set","dest");
		redis.sunionstore("binji", "set","dest");
		redis.sadd("binji", "l","hs","u");
		//�
		redis.sdiff("set","bingji");
		System.out.println("���set��"+redis.smembers("set"));
		redis.sdiffstore("diff","binji","set");//binji-set
		System.out.println("�"+redis.smembers("diff"));
		
		
	}

}
