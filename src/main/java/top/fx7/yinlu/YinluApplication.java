package top.fx7.yinlu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.fx7.yinlu.mapper")
public class YinluApplication {
    public static void main(String[] args) {
        SpringApplication.run(YinluApplication.class, args);
    }
}
