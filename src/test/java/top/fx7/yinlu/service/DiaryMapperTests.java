package top.fx7.yinlu.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.fx7.yinlu.mapper.DiaryMapper;
import top.fx7.yinlu.model.Diary;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Slf4j
public class DiaryMapperTests {
    @Autowired
    IDiaryService diaryService;
    @Autowired
    DiaryMapper diaryMapper;

    @Test
    void addDiary(){
        Diary diary = new Diary();
        diary.setContent("这是内容57");
        diary.setCreated(LocalDateTime.now());
        int rows = diaryMapper.insert(diary);
//        int rows = diaryMapper.insertDiary(diary);
        log.debug("rows >>> {}",rows);
    }

    @Test
    void updateViewCount(){
        int rows = diaryMapper.updateViewCount(1);
        log.debug(">>>{}",rows);
    }
    @Test
    void getDiaryByPage(){
        Integer start = 1;
        List<Diary> diaries = diaryService.getDiaries(start);
        log.debug(">>>{}",diaries);
    }

    @Test
    void getToken(){
        String accessKey = "57THhc6zpcwkT4I4tdwfX8OlC2IXvU34pcc4yXLt";
        String secretKey = "uiUdVCl64yiAEM6BxhfZliyR0s-PN_RcoprnXzI8";
        Configuration cf = new Configuration(Region.huanan());
        UploadManager um = new UploadManager(cf);
        Auth auth = Auth.create(accessKey,secretKey);
        String token = auth.uploadToken("rjgl");
        File file = new File("");
        try {
            Response response = um.put("hello".getBytes(),"da.txt",token);
            log.debug("response>>> {}",response);
        } catch (QiniuException e) {
            log.debug("e>>> {}",e);
            e.printStackTrace();
        }
    }
}
