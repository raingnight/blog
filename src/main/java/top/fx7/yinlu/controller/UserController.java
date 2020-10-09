package top.fx7.yinlu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.fx7.yinlu.VO.R;
import top.fx7.yinlu.mapper.UserMapper;
import top.fx7.yinlu.model.User;
import top.fx7.yinlu.security.UserInfo;
import top.fx7.yinlu.service.IUserService;
import top.fx7.yinlu.service.ex.InsertException;
import top.fx7.yinlu.service.ex.UserExistedException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 人家故里
 * @since 2020-08-15
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping("/register")
    public R<Void> register(User user){
        User u = userService.getByUserName(user.getUsername());
        if (u != null) {
            throw new UserExistedException("用户名已存在，请重新输入！");
        }
        int rows = userService.register(user);
        if (rows != 1) {
            throw new InsertException("注册失败！请稍后再试！");
        }
        return R.ok();
    }
    @GetMapping("/userInfo")
    public UserDetails getUserInfo(@AuthenticationPrincipal UserInfo userInfo){
        return userInfo;
    }

}
