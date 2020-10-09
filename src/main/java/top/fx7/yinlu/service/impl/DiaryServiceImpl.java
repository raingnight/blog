package top.fx7.yinlu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import top.fx7.yinlu.mapper.DiaryMapper;
import top.fx7.yinlu.model.Diary;
import top.fx7.yinlu.redis.SerializeUtil;
import top.fx7.yinlu.service.IDiaryService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper, Diary> implements IDiaryService {
    @Autowired
    DiaryMapper diaryMapper;

    @Value("5")//分页查询的页面大小，即一次查询多少条数据
    private Integer pageSize;

    @Autowired
    JedisPool jedisPool;

    private static Jedis jedis;

    @Override
    public List<Diary> getDiaries(Integer startPage) {
        QueryWrapper<Diary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);//状态为1表示正常，0表示删除
        queryWrapper.orderByDesc("id");//倒序查出最新的数据
        queryWrapper.last("limit " + startPage + "," + pageSize);//分页查询，限制一次查询的数量
        List<Diary> diaries = diaryMapper.selectList(queryWrapper);
        return diaries;
    }

    @Override
    public Object getDiariesStr(Integer startPage) {
//        if (jedis == null) {
        jedis = jedisPool.getResource();
        QueryWrapper<Diary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);//状态为1表示正常，0表示删除
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit " + startPage + "," + pageSize);
        List<Diary> diaries = diaryMapper.selectList(queryWrapper);
        jedis.set("diaries".getBytes(), SerializeUtil.serialize(diaries));
//        }
        return SerializeUtil.unserialize(jedis.get("diaries").getBytes());
    }

    @Override
    public int modifyDiary(Integer diary_id) {
        //Todo
        return 0;
    }

    @Override
    public int addDiary(Diary diary) {
        diary.setCreated(LocalDateTime.now());
        int rows = diaryMapper.insert(diary);
        return rows;
    }

    @Override
    public Diary getById(Integer id) {
        return diaryMapper.selectById(id);
    }

    public int updateViewCount(Integer id) {
        int rows = diaryMapper.updateViewCount(id);
        return rows;
    }

    @Override
    public int daleteById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        Diary diary = diaryMapper.selectOne(queryWrapper);
        diary.setStatus(0);
        int rows = diaryMapper.updateById(diary);
        return rows;
    }
}
