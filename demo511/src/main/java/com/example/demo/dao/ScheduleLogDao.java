package com.example.demo.dao;

import com.example.demo.pojo.ScheduleLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScheduleLogDao {
    /**
     * 将一条调度日志插入调度日志表
     * @param scheduleLog
     */
    @Insert("insert into schedule_log (sched_time,job_id,job_name,job_type,priority,res,state_chg,exe_times,algorithm_chg,res_remain) values (#{sched_time},#{job_id},#{job_name},#{job_type},#{priority},#{res},#{state_chg},#{exe_times},#{algorithm_chg},#{res_remain})")
    public void insertScheduleLog( ScheduleLog scheduleLog);

    /**
     * 查询所有调度日志
     * @return
     */
    @Select("select * from schedule_log")
    public List<ScheduleLog> selectAllScheduleLog();
}
