package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


//因为返回JSP页面所以不能使用@RestController
@Controller
public class JSPController {

    @GetMapping("boot/index")
    public String index(Model model) {
        model.addAttribute("msg","Spring Boot 集成JSP.");
        return "index";
    }

}
