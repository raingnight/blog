package top.fx7.yinlu.service;

import org.springframework.security.core.userdetails.UserDetails;
import top.fx7.yinlu.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 人家故里
 * @since 2020-08-15
 */
public interface IUserService extends IService<User> {
    /**
     * 登录
     * @param username 用户名
     * @return 用户信息
     */
    UserDetails login(String username);

    /**
     * 用户注册
     * @param user User对象
     * @return
     */
    int register(User user);

    /**
     * 通过用户名获取User对象
     * @param username 用户名
     * @return User对象
     */
    User getByUserName(String username);
}
