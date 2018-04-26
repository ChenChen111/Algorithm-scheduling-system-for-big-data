package com.example.demo.service;

import com.example.demo.pojo.Job;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public interface JobService {
    Job findJobById(String job_id);
    void addJobtoDB(Job job);
    void deleteJobFromDB(String job_id);
    List<Job> fingJobByPriority();
    void chgJobState();
    void chgStateToExecuting(String job_id);
    void chgStateToFinished(String job_id);
    void chgStateToWaiting(String job_id);
    void chgStartTime( String job_id, String start_time);
    void chgEndTime(String job_id,String end_time);
    List<Job> findJobByStartTime();
    int noJobWaiting();
    void incExeTimes(String job_id);

    int fingMinPriority();
}
//public void updateExecuting();
//public void updateStartTimeByID( String job_id, LocalTime start_time);