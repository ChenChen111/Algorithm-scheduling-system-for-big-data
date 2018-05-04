package com.example.demo.dao;

import com.example.demo.pojo.OpLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OpLogDao {

    /**
     * 插入一条新的记录
     * @param opLog
     */
    @Insert("insert into op_log (op_time,op_type) values(#{op_time},#{op_type})")
    public void insertOpLog( OpLog opLog);

    /**
     * 查询所有的用户操作记录
     * @return
     */
    @Select("select * from op_log")
    public List<OpLog> selectAllOpLog();
}
