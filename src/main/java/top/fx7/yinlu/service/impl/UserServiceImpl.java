package top.fx7.yinlu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.fx7.yinlu.mapper.UserMapper;
import top.fx7.yinlu.model.User;
import top.fx7.yinlu.security.UserInfo;
import top.fx7.yinlu.service.IUserService;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails login(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);//根据用户名查询用户
        if (user == null) {//为空则说明用户不存在
            return null;
        }
//用户存在
        log.debug(">>> user{}", user);
        String[] authorities = {"admin"};
        UserInfo userInfo = new UserInfo(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList(authorities)
        );
        userInfo.setId(user.getId());
        log.debug(">>> {}", user.getUsername());
        log.debug(">>>userInfo {}", userInfo);
        return userInfo;
    }

    @Override
    public int register(User user) {
        log.debug("开始注册");
        user.setPassword(encode(user.getPassword()));
        int rows = userMapper.insert(user);
        return rows;
    }

    @Override
    public User getByUserName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    private static String encode(String rawPassword) {
        String encodePassword = "{bcrypt}" + passwordEncoder.encode(rawPassword);
        return encodePassword;
    }


}
