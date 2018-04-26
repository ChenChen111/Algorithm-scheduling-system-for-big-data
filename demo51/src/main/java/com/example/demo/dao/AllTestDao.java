package com.example.demo.dao;

import com.example.demo.pojo.AllTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Mapper
public interface AllTestDao {
    @Select("select min(test_time) from test_info")
    public Time selectMinTime();

    @Select("select min(test_int) from test_info")
    public int selectMinInt();
}
//@Select("select * from job_info where job_id=#{job_id}")
//    public Job selectById(String job_id);