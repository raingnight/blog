package top.fx7.yinlu.jedis;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import top.fx7.yinlu.model.Diary;
import top.fx7.yinlu.redis.RedisUtil;
import top.fx7.yinlu.redis.SerializeUtil;
import top.fx7.yinlu.service.IDiaryService;

import java.util.List;

@SpringBootTest
@Slf4j
public class JedisTest {
    @Autowired
    JedisPool jedisPool;
    @Autowired
    IDiaryService diaryService;

    @Test
    public void setTest() {
        log.debug("JedisPool>>> {}", jedisPool);
        log.debug("JedisPool>>> {}", jedisPool.getNumActive());
        Jedis jedis = jedisPool.getResource();
//        jedis.set("1","2");
//        jedis.expire("1",10);
        Diary diary = diaryService.getById(1);
        jedis.set("diary", JSON.toJSONString(diary));
        String str = jedis.get("diary");
        log.debug(str);

        List<Diary> diaries = diaryService.getDiaries(1);
        jedis.set("diaries", JSON.toJSONString(diaries));
        str = jedis.get("diaries");
        log.debug(str);

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

    @Test
    public void a() {
        Jedis jedis = jedisPool.getResource();
        Diary diary = diaryService.getById(1);
        List<Diary> diaries = diaryService.getDiaries(1);
        jedis.set("list".getBytes(),SerializeUtil.serialize(diaries));
        List<Diary> list = (List<Diary>) SerializeUtil.unserialize(jedis.get("list".getBytes()));
        log.debug("{}",list);

        jedis.set("key1".getBytes(), SerializeUtil.serialize(diary));
        log.debug("{}", jedis);

        byte[] key = jedis.get("key1".getBytes());
//        log.debug(key);
        Diary diary1 = (Diary) SerializeUtil.unserialize(key);
        log.debug(">>> {}", diary1);
    }
}
