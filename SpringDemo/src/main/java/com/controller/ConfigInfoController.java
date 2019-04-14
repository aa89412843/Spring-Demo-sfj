package com.controller;

import com.config.ConFigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigInfoController {
    //读取配置中自定义的值
    @Value("${boot.name}")
    private String bootName;

    @Value("${boot.location}")
    private String bootLocation;

    @Autowired
    private ConFigInfo conFigInfo;

    @GetMapping("bootName")
    public String getConfigInfoController() {
        return bootName+bootLocation;
    }

    @GetMapping("bootNamePre")
    public String getConfigInfoPre() {
        return conFigInfo.getName()+conFigInfo.getLocation();
    }
}
