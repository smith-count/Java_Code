package Demo;

import redis.clients.jedis.Jedis;

public class redisUse {
    public static void main(String[] args) {
        //���ӱ��ص� Redis ����
        Jedis jedis = new Jedis("localhost");
        // ��� Redis �������������룬��Ҫ�������У�û�оͲ���Ҫ
        // jedis.auth("123456"); 
        System.out.println("���ӳɹ�");
        
        //�鿴�����Ƿ�����
        System.out.println("������������: "+jedis.ping());
//        jedis.hset("student:scofield","English","45");
//        jedis.hset("student:scofield","Math","45");
//        jedis.hset("student:scofield","Computer","100");
        System.out.println(jedis.hgetAll("student:scofield"));
        System.out.print("�����");
    }
}
