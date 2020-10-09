package top.fx7.yinlu.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisUtil {
    @Autowired
    private JedisPool jedisPool;
    /**
     * 向Redis中存值，永久有效
     */
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } catch (Exception e) {
            return "0";
        } finally {
            RedisUtil.close(jedis);
        }
    }

    /**
     * 根据传入Key获取指定Value
     */
    public String get(String key) {
        Jedis jedis = null;
        String value;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            return "0";
        } finally {
            RedisUtil.close(jedis);
        }
        return value;
    }

    /**
     * 校验Key值是否存在
     */
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            return false;
        } finally {
            RedisUtil.close(jedis);
        }
    }

    /**
     * 删除指定Key-Value
     */
    public Long delete(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } catch (Exception e) {
            return 0L;
        } finally {
            RedisUtil.close(jedis);
        }
    }

    // 以上为常用方法，更多方法自行百度

    /**
     * 释放连接
     */
    public static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
    public String set(String key, Object value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key.getBytes(), SerializeUtil.serialize(value));
        } catch (Exception e) {
            return "0";
        } finally {
            RedisUtil.close(jedis);
        }
    }
}
