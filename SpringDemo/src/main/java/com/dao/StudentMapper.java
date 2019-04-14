package com.dao;

import com.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
@Mapper
public interface StudentMapper {
    List<Student> selectAllStudent();

    int updateStudent(Student student);
}
