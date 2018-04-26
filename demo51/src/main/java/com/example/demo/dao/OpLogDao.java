package com.example.demo.dao;

import com.example.demo.pojo.OpLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface OpLogDao {
    /**
     * 将用户操作插入用户操作表
     * @param opLog
     */
    @Insert("insert into user_op_log (op_time,op_type) values (#{op_time},#{op_type})")
    public void insertOpLog( OpLog opLog);
}
