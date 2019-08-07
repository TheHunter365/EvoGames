package fr.evogames.evogamescore.database;

import fr.evogames.evogamesapi.database.DataBase;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDataBase implements DataBase {

    private JedisPool jedisPool;
    private Jedis jedis;

    public RedisDataBase(String host, int port, int database) {

        this.jedisPool = new JedisPool(host, port);

        this.jedis = this.getResource();
    }

    public Jedis getResource() {
        return this.jedisPool.getResource();
    }

    public void set(String key, String value) {
        this.jedis.set(key, value);
    }

    public String get(String key) {
        return this.jedis.get(key);
    }



}
