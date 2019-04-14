package com.controller;

import com.model.Student;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RunAs;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class MyBatisControoler {

    @Autowired
    private StudentService studentService;

    @GetMapping("boot/student")
    public Object setStudents() {
//线程，该线程调用底层方法查询所有学生
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                studentService.getStudents();
            }
        };
//        多线程测试缓存穿透问题创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(25);
        for (int i = 0; i < 10000; i++) {
            executorService.submit(runnable);
        }

        return studentService.getStudents();
    }

    @GetMapping("boot/update")
    public Object update() {
        return studentService.update();
    }


}

