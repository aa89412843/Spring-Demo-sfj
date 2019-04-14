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

import java.util.List;

@Service
public class RedisStudentServiceImpl
//        implements StudentService
{

    @Autowired
    private StudentMapper studentMapper;

    //    注入SpringBoot自动配置好的StringRedisTemplate
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    //    @Override
    public List<Student> getStudents() {
        //    创建字符串的序列化器，设置序列化器，解决redis desktop key乱码问题
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);

//查询缓存
        List<Student> allstudents = (List<Student>) (redisTemplate.opsForValue().get("allstudents"));
//缓存为null，查数据库，顺便放入缓存中
        if (allstudents == null) {
            allstudents = studentMapper.selectAllStudent();
            redisTemplate.opsForValue().set("allstudents", allstudents);
        }
        return allstudents;
    }


    //    @Override
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
}
