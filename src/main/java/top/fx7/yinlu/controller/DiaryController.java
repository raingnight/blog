package top.fx7.yinlu.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import top.fx7.yinlu.VO.R;
import top.fx7.yinlu.model.Diary;
import top.fx7.yinlu.security.UserInfo;
import top.fx7.yinlu.service.IDiaryService;
import top.fx7.yinlu.service.IUserService;
import top.fx7.yinlu.service.ex.InsertException;
import top.fx7.yinlu.service.ex.NotFoundException;
import top.fx7.yinlu.service.ex.PermissionNotAllowed;
import top.fx7.yinlu.service.ex.UpdateException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 人家故里
 * @since 2020-08-15
 */
@RestController
@Slf4j
@RequestMapping("/api/v1/diary")
public class DiaryController {
    @Autowired
    IDiaryService diaryService;
    @Autowired
    IUserService userService;

    //获取日记列表 未使用redis
    @GetMapping("/getDiary/{page}")
    public R<List<Diary>> getDiaries(@PathVariable("page") Integer page) {
        List<Diary> diaries = diaryService.getDiaries(page);
        return R.ok(diaries);
    }

    //获取日记列表 使用redis缓存
    @RequestMapping("/getDiaries/{page}")
    public R<String> getDiaTest(@PathVariable("page") Integer page) {
        List<Diary> diaries = (List<Diary>) diaryService.getDiariesStr(page);
        log.debug("{}", diaries);
        return R.ok(diaries);
    }

    @PostMapping("/write")
    public R<Void> writeDiary(Diary diary, @AuthenticationPrincipal UserDetails userDetails) {
        diary.setCreated(LocalDateTime.now());
        diary.setUserId(userService.getByUserName(userDetails.getUsername()).getId());
        int rows = diaryService.addDiary(diary);
        if (rows != 1) {
            throw new InsertException("写入diary记录错误");
        }
        return R.ok();
    }

    @RequestMapping("/getById/{id}")
    public R<Diary> getById(@PathVariable("id") Integer id) {
        Diary diary = diaryService.getById(id);
        diaryService.updateViewCount(id);
        if (diary == null) {
            throw new NotFoundException("没有找到这条记录！");
        }
        return R.ok(diary);
    }

    @RequestMapping("/delete/{id}")
    public R<Void> delete(@PathVariable("id") Integer id, @AuthenticationPrincipal UserInfo userInfo) {
        Integer authId = diaryService.getById(id).getId();
        if (userInfo.getId() == authId) {
            int rows = diaryService.daleteById(id);
            if (rows != 1) {
                throw new UpdateException("删除记录失败！");
            }
            return R.ok();
        } else {
            throw new PermissionNotAllowed("您不是文章作者，不能删除此文章！");
        }
    }
}
