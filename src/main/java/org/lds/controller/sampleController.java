package org.lds.controller;

import org.lds.pojo.User;
import org.lds.redis.RedisService;
import org.lds.redis.UserKey;
import org.lds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/demo")
public class sampleController {
    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;
    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model)
    {
        model.addAttribute("name","lds");
        model.addAttribute("password","lds");
        return "hello";
    }
    @RequestMapping("/db/get1")
    public String ResultgetUserById(Model model)
    {
        User user = userService.getByIdService(1);
        model.addAttribute("name",user.getName());
        return "hello";
    }
    @RequestMapping("/redisget")
    public String RedisGet(Model model)
    {
        User user=redisService.get(UserKey.getById, ""+2, User.class);
        model.addAttribute("msg",user.getName());
        return "msg";
    }
    @RequestMapping("/redisset")
    public String RedisSet(Model model)
    {
        User user = new User(2,"joson2");

        redisService.set(UserKey.getById,""+user.getId(),user);
        model.addAttribute("msg","set成功,key:"+user.getId()+"value"+user.getName());
        return "msg";
    }
}
