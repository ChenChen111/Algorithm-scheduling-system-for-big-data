package com.example.demo.service;

import com.example.demo.dao.JobDao;
import com.example.demo.dao.ScheduleLogDao;
import com.example.demo.pojo.Job;
import com.example.demo.pojo.ScheduleLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobDao jobDao1;

    @Autowired
    private ScheduleLogDao scheduleLogDao;

    public List<Job> getEarliestJob(){
        return jobDao1.selectByStartTime();
    }

    //刘茹月专用函数
    public List<Job> findJobById(String job_id){
        return jobDao1.selectById(job_id);
    }

    public List<Job> findAllJob(){
        return jobDao1.selectAllJob();
    }

    public void executeJob( Job job, String algorithm_chg,int res_remain) throws InterruptedException {

        jobDao1.updateExecutingByID(job.getJob_id());
        System.out.println("成功：将job_info中的相应job_state改为executing");

        //新建一条调度表条目
        ScheduleLog scheduleLogNow = new ScheduleLog();
        scheduleLogNow.setSched_time(job.getStart_time());
        scheduleLogNow.setJob_id(job.getJob_id());
        scheduleLogNow.setJob_name(job.getJob_name());
        scheduleLogNow.setJob_type(job.getJob_type());
        scheduleLogNow.setPriority(job.getPriority());
        scheduleLogNow.setRes(job.getRes());
        scheduleLogNow.setState_chg("executing");
        scheduleLogNow.setExe_times(job.getExe_times());
        scheduleLogNow.setAlgorithm_chg(algorithm_chg);
        scheduleLogNow.setRes_remain(res_remain);
        System.out.println("成功：新建一条调度表条目");

        //将调度表条目写入调度表
        scheduleLogDao.insertScheduleLog(scheduleLogNow);
        System.out.println("成功：将调度表条目写入调度表");

        //将job_info中相应的state改成executing




        //执行一定的时间
        long exe_time = ChronoUnit.SECONDS.between(job.stringToStartTime(),job.stringToEndTime());
        System.out.println("成功：开始执行");
        Thread.sleep(exe_time);
        System.out.println("成功：执行完一定的时间");

        scheduleLogNow.setSched_time(job.getEnd_time());
        scheduleLogNow.setState_chg("finished");
        scheduleLogNow.setExe_times(job.getExe_times()+1);
        scheduleLogNow.setRes_remain(res_remain+job.getRes());
        //执行完后将改变状态的条目插入调度表
        scheduleLogDao.insertScheduleLog(scheduleLogNow);
        System.out.println("成功：执行完后将改变状态的条目插入调度表");

        if(job.getExe_cycle()==0){
            //若该任务无执行周期，则将任务表相应条目的job_state改为finished
            // jobService.chgStateToFinished(job.getJob_id());
            jobDao1.updateFinishedByID(job.getJob_id());
            System.out.println("成功：无周期任务，则将任务表相应条目的job_state改为finished");
        }
        else{
            //若该任务有执行周期，则将任务表相应条目的job_state改为waiting
            //jobService.chgStateToWaiting(job.getJob_id());
            jobDao1.updateWaitingByID(job.getJob_id());
            System.out.println("成功：有周期任务，将任务表相应条目的job_state改为waiting");

            //执行次数加一
            //jobService.incExeTimes(job.getJob_id());
            jobDao1.updateExeTimes(job.getJob_id());
            //更改下一次执行的开始时间和结束时间
            DateTimeFormatter ft = DateTimeFormatter.ofPattern("HH:mm:ss");


            System.out.println(job.getStart_time());
            System.out.println(job.getEnd_time());

            job.setStart_time(job.stringToStartTime().plusSeconds(job.getExe_cycle()).format(ft));
            job.setEnd_time(job.stringToEndTime().plusSeconds(job.getExe_cycle()).format(ft));

            System.out.println(job.getStart_time());
            System.out.println(job.getEnd_time());

            jobDao1.updateStartTimeByJob(job);
            jobDao1.updateEndTimeByJob(job);
        }
        System.out.println("成功：结束一次任务调度 ");


    }
}
