package com.ServiceImpl;

import com.dao.StudentMapper;
import com.model.Student;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    //    注入SpringBoot自动配置好的StringRedisTemplate
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public /*synchronized*/ List<Student> getStudents() {
        //    创建字符串的序列化器，设置序列化器，解决redis desktop key乱码问题
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);

//        高并发条件下，有点问题，缓存穿透    解决方法1：synchronized  2：双重检测锁
//查询缓存
        List<Student> allstudents = (List<Student>) (redisTemplate.opsForValue().get("allstudents111"));
//        双重检测锁
        if (allstudents == null) {
            synchronized (this) {
                allstudents = (List<Student>) (redisTemplate.opsForValue().get("allstudents111"));
//        双重检测锁
                //缓存为null，查数据库，顺便放入缓存中
                if (allstudents == null) {
                    System.out.println("查询的数据库...........");
                    allstudents = studentMapper.selectAllStudent();
                    redisTemplate.opsForValue().set("allstudents111", allstudents);
                } else {
                    System.out.println("查询的缓存数据。。。。。。。。。");
                }
            }
        } else {
            System.out.println("查询的缓存数据。。。。。。。。。");
        }
        return allstudents;

//        查询的数据库...........
//        2018-10-31 00:27:48.935  INFO 16396 --- [ool-1-thread-25] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
//        2018-10-31 00:27:49.676  INFO 16396 --- [ool-1-thread-25] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
//                查询的缓存数据。。。。。。。。。
//        查询的缓存数据。。。。。。。。。
    }

//    @Override缓存穿透结果
    public /*synchronized*/ List<Student> getStudentsa() {
        //    创建字符串的序列化器，设置序列化器，解决redis desktop key乱码问题
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);

//        高并发条件下，有点问题，缓存穿透    解决方法1：synchronized  2：双重检测锁
//查询缓存
        List<Student> allstudents = (List<Student>) (redisTemplate.opsForValue().get("allstudents111"));
//        双重检测锁
        if (allstudents == null) {
                //缓存为null，查数据库，顺便放入缓存中
                if (allstudents == null) {
                    System.out.println("查询的数据库...........");
                    allstudents = studentMapper.selectAllStudent();
                    redisTemplate.opsForValue().set("allstudents111", allstudents);
                } else {
                    System.out.println("查询的缓存数据。。。。。。。。。");
                }

        }
        return allstudents;

//        查询的数据库.........
//        查询的数据库.........
//        查询的数据库.........
//        查询的数据库.........
//        查询的数据库.........
//        查询的数据库.........
//        查询的缓存数据。。。。。。。。。
//        查询的缓存数据。。。。。。。。。
    }



    @Override
    @Transactional
    public int update() {
        Student student = new Student();
        student.setId("111");
        student.setName("测试名字");
        student.setAge(33);
        int i = studentMapper.updateStudent(student);
        System.out.print("update:" + i);
//除数不能为0，此处抛出运行时异常，上一步的更新就会回滚
//        int a = 10 / 0;
        return i;
    }





    第二次提交




}
