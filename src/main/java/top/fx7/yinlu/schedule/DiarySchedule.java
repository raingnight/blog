package top.fx7.yinlu.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DiarySchedule {
    @Scheduled(initialDelay = 0,fixedRate = 60*10)
    public void loadDiary(){
        
    }
}
