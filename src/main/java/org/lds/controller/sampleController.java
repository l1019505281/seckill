package org.lds.controller;

import org.lds.pojo.User;
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
}
