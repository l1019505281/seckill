package org.lds.controller;

import org.lds.pojo.SeckillUser;
import org.lds.pojo.vo.LoginVo;
import org.lds.redis.RedisService;
import org.lds.service.SeckillUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    SeckillUserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public String doLogin(HttpServletResponse response, LoginVo loginVo) {
        log.info(loginVo.toString());
        //登录
        boolean login = userService.login(response, loginVo);
        System.out.println(login);
        return "msg";
    }
}
