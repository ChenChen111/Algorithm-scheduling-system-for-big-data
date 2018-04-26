package com.example.demo.dao;

import com.example.demo.pojo.Job;
import org.apache.ibatis.annotations.*;

import java.time.LocalTime;
import java.util.List;

@Mapper
public interface JobDao {

    /**
     * 添加新的任务
     * @param job
     */
    @Insert("insert into job_info (job_id,job_name,job_type,start_time,end_time,exe_cycle,job_state,priority,exe_times) values(#{job_id},#{job_name},#{job_type},#{start_time},#{end_time},#{exe_cycle},#{job_state},#{priority},#{res},#{exe_times})")
    public void insertJob(Job job);

    /**
     * 根据任务id在数据库中查询任务
     * @param job_id
     * @return
     */
    @Select("select * from job_info where job_id=#{job_id}")
    public Job selectById(String job_id);

    /**
     * 选出优先级最高（值最小）的任务
     * @return
     */
    @Select("select * from job_info where priority=(select min(priority) from job_info)")
    public List<Job> selectByPriority();

    /**
     * 根据任务id删除任务
     * @param job_id
     */
    @Delete("delete from job_info where job_id=#{job_id}")
    void deleteJob(String job_id);



    @Update("update job_info set job_state='正在执行' where priority=(select min(priority) from job_info)" )
    public void updateExecuting();

    /**
     * 根据job_id更改job的状态为executing
     * @param job_id
     */
    @Update("update job_info set job_state = 'executing' where job_id=#{job_id}")
    public void updateExecutingByID(String job_id);

    /**
     * 根据job_id更改job的状态为finished
     * @param job_id
     */
    @Update("update job_info set job_state = 'finished' where job_id=#{job_id}")
    public void updateFinishedByID(String job_id);

    @Update("update job_info set job_state = 'waiting' where job_id=#{job_id}")
    public void updateWaitingByID(String job_id);

    @Update("update job_info set start_time = #{start_time} where job_id=#{job_id}")
    public void updateStartTimeByID( String job_id, String start_time);

    @Update("update job_info set end_time = #{end_time} where job_id=#{job_id}")
    public void updateEndTimeByID(String job_id,String end_time);

    @Select("select * from job_info where start_time = (select min(start_time) from job_info)")
    public List<Job> selectByStartTime();

    //test
    @Select("select min(priority) from job_info")
    public int selectMinPriority();

    @Select("select count(job_state) from job_info where job_state = 'waiting'")
    public int selectCountJobState();

    //@Select("select * from job_info where priority=(select min(priority) from job_info)")
    //public List<Job> selectByPriority();

    @Update("update job_info set exe_times = exe_time -1 where job_id=#{job_id}")
    public void updateExeTimes(String job_id);

}

/*  private String job_id;
    private String job_name;
    private String job_type;
    private LocalTime start_time;
    private LocalTime end_time;
    private int exe_cycle;
    private String job_state;
    private int priority;
    private int res;*/