package com.example.demo.action;

import com.example.demo.pojo.Job;
import com.example.demo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChgAlgorithm {
   // @Autowired
   // private JobService jobService;

    public void orderSched(Job firstJob) throws InterruptedException {
        System.out.println("成功：进入orderSched()");
        Executor jobExecutor = new Executor();
        //List<Job> firstJob = new ArrayList<Job>(jobService.findJobByStartTime());
        System.out.println(firstJob.getRes());
        jobExecutor.executeJob(firstJob,"orderSched");
    }

    public void prioritySched(){

    }

    public void resShortSched(){

    }

}
