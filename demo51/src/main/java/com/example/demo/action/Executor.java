package com.example.demo.action;

import com.example.demo.pojo.Job;
import com.example.demo.pojo.ScheduleLog;
import com.example.demo.service.JobService;
import com.example.demo.service.ScheduleLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Controller
public class Executor {
    @Autowired
    private JobService jobService;
    private ScheduleLogService scheduleLogService;

    public void executeJob( Job job,String algorithm_chg) throws InterruptedException {
        //将job_info中的相应job_state改为executing
        jobService.chgStateToExecuting(job.getJob_id());
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
        System.out.println("成功：新建一条调度表条目");

        //将调度表条目写入调度表
        scheduleLogService.writeScheduleLog(scheduleLogNow);
        System.out.println("成功：将调度表条目写入调度表");

        //执行一定的时间
        long exe_time = ChronoUnit.SECONDS.between(job.stringToStartTime(),job.stringToEndTime());
        System.out.println("成功：开始执行");
        Thread.sleep(exe_time);
        System.out.println("成功：执行完一定的时间");

        scheduleLogNow.setSched_time(job.getEnd_time());
        scheduleLogNow.setState_chg("finished");
        //执行完后将改变状态的条目插入调度表
        scheduleLogService.writeScheduleLog(scheduleLogNow);
        System.out.println("成功：执行完后将改变状态的条目插入调度表");

        if(job.getExe_cycle()==0){
            //若该任务无执行周期，则将任务表相应条目的job_state改为finished
            jobService.chgStateToFinished(job.getJob_id());
            System.out.println("成功：无周期任务，则将任务表相应条目的job_state改为finished");
        }
        else{
            //若该任务有执行周期，则将任务表相应条目的job_state改为waiting
            jobService.chgStateToWaiting(job.getJob_id());
            System.out.println("成功：有周期任务，将任务表相应条目的job_state改为waiting");

            //执行次数加一
            jobService.incExeTimes(job.getJob_id());
            //更改下一次执行的开始时间和结束时间
            DateTimeFormatter ft = DateTimeFormatter.ofPattern("HH:mm:ss");
            scheduleLogNow.setStart_time(scheduleLogNow.stringToStartTime().plusSeconds(scheduleLogNow.getExe_cycle()).format(ft));
            scheduleLogNow.setEnd_time(scheduleLogNow.stringToEndTime().plusSeconds(scheduleLogNow.getExe_cycle()).format(ft));
            //scheduleLogNow.setStart_time(scheduleLogNow.getStart_time().plusSeconds(scheduleLogNow.getExe_cycle()));
            //scheduleLogNow.setEnd_time(scheduleLogNow.getEnd_time().plusSeconds(scheduleLogNow.getExe_cycle()));
            jobService.chgStartTime(job.getJob_id(),scheduleLogNow.getStart_time());
            jobService.chgEndTime(job.getJob_id(),scheduleLogNow.getEnd_time());
        }
        System.out.println("成功：结束一次任务调度 ");


    }
}
//public void updateExecutingByID(String job_id);