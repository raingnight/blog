package top.fx7.yinlu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.fx7.yinlu.service.IDiaryService;
import top.fx7.yinlu.service.ex.UpdateException;

@Controller
public class SystemController {

    @GetMapping("/write.html")
    public String write() {
        return "write";
    }

    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

    @GetMapping("/detail.html")
    public String detail() {
        return "detail";
    }
}
