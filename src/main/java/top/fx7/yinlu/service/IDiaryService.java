package top.fx7.yinlu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.fx7.yinlu.model.Diary;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 人家故里
 * @since 2020-08-15
 */
public interface IDiaryService extends IService<Diary> {
    /**
     * 分页获取日记列表
     * @param startPage  获取的起始页
     * @return 日记列表
     */
    List<Diary> getDiaries(Integer startPage);

    /**
     * 添加日记
     * @param diary 日记
     * @return 影响的行数
     */
    int addDiary(Diary diary);

    /**
     * 通过id获取日记
     * @param id 日记id
     * @return 获取到的日记
     */
    Diary getById(Integer id);

    /**
     * 添加访问次数
     * @param id 日记的id
     * @return 是否成功
     */
    int updateViewCount(Integer id);

    /**
     * 删除日记（状态标记为删除）
     * @param id 要删除的日记的id
     * @return 影响的行数
     */
    int daleteById(Integer id);

    /**
     * 获取日记
     * @param startPage
     * @return
     */
    Object getDiariesStr(Integer startPage);

    /**
     * 修改日记
     * @param diary_id 日记id
     * @return 影响的行数
     */
    int modifyDiary(Integer diary_id);
}
