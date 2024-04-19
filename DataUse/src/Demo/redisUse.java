package Demo;

import redis.clients.jedis.Jedis;

public class redisUse {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
        // jedis.auth("123456"); 
        System.out.println("连接成功");
        
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
//        jedis.hset("student:scofield","English","45");
//        jedis.hset("student:scofield","Math","45");
//        jedis.hset("student:scofield","Computer","100");
        System.out.println(jedis.hgetAll("student:scofield"));
        System.out.print("已完成");
    }
}
