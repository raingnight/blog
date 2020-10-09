package top.fx7.yinlu.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.fx7.yinlu.model.User;

@SpringBootTest
@Slf4j
public class UserServiceTests {
    @Autowired
    IUserService userService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Test
    void registerTest(){
        User user = new User();
        user.setId(3);
        user.setUsername("liu");
        user.setPassword("{bcrypt}"+passwordEncoder.encode("123"));
        user.setIsAdmin(1);
        userService.register(user);
    }
}
