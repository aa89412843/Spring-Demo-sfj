package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@MapperScan("com.dao")//等于在public interface StudentMapper添加@Mapper 注解一个效果
@SpringBootApplication
@EnableTransactionManagement//开启SpringBoot事务支持  然后在需要连接数据库service的地方添加@Transactional注解,当有异常的时候回滚
public class MyBatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBatisApplication.class, args);
    }

    第一次提交
    第二次提交
}
