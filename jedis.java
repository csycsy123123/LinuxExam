package com.atguigu.jdbc;

import redis.clients.jedis.Jedis;

public class jedis {
    public static void main(String[] args) {
        Jedis jedis=new Jedis("180.76.239.115",6379);
        System.out.println(jedis.ping());
    }
}
