package com.example.demo.service.impl;

import com.example.demo.dao.JobDao;
import com.example.demo.pojo.Job;
import com.example.demo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class JobServiceImpl implements JobService{
    @Autowired
    private JobDao jobDao;

    public Job findJobById(String job_id) {
        return jobDao.selectById(job_id);
    }

    public void addJobtoDB(Job job) {
        jobDao.insertJob(job);
    }

    public void deleteJobFromDB(String job_id) {
        jobDao.deleteJob(job_id);
    }

    @Override
    public List<Job> fingJobByPriority() {
        return jobDao.selectByPriority();
    }

    @Override
    public void chgJobState(){
        jobDao.updateExecuting();
    }

    @Override
    public void chgStateToExecuting(String job_id){
        jobDao.updateExecutingByID(job_id);
    }

    @Override
    public void chgStateToFinished(String job_id){
        jobDao.updateFinishedByID(job_id);
    }

    @Override
    public void chgStateToWaiting(String job_id){
        jobDao.updateWaitingByID(job_id);
    }

    @Override
    public void chgStartTime( String job_id, String start_time){
        jobDao.updateStartTimeByID(job_id,start_time);
    }

    @Override
    public void chgEndTime(String job_id,String  end_time){
        jobDao.updateEndTimeByID(job_id,end_time);
    }

    @Override
    public List<Job> findJobByStartTime(){
        return jobDao.selectByStartTime();
    }

    @Override
    public int noJobWaiting(){
        if(jobDao.selectCountJobState() > 0)
            return 0;  //有job等待
        else
            return 1;
    }

    @Override
    public void incExeTimes(String job_id){
        jobDao.updateExeTimes(job_id);
    }

    @Override
    public int fingMinPriority(){
        return jobDao.selectMinPriority();
    }
}
