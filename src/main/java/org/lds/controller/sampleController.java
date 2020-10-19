package org.lds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class sampleController {
    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model)
    {
        model.addAttribute("name","lds");
        return "hello";
    }
}
