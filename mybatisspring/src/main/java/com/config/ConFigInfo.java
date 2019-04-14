package com.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//Spring自定义配置，配置文件映射成对象

@Getter
@Setter
@Component
//把ConFigInfo注入Spring变成一个组件,@Autowired引用
@ConfigurationProperties(prefix = "boot")
//读取配置中的boot属性注入到name,location中
public class ConFigInfo {
    private  String name;
    private  String location;
}
