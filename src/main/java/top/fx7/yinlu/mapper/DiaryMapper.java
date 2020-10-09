package top.fx7.yinlu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.fx7.yinlu.model.Diary;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 人家故里
 * @since 2020-08-15
 */
@Repository
public interface DiaryMapper extends BaseMapper<Diary> {
    List<Diary> getDiaries();
    int updateViewCount(int id);
    int insertDiary(Diary diary);
}
