package com.example.demo.action;

import com.example.demo.pojo.Job;
import com.example.demo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class Scheduler {
    private String last_algorithm;
    private String this_algorithm;
    private String user_selected;
    private int resNow;

    @Autowired
    private JobService jobService;

    public void scheduleJobs() throws InterruptedException {
        System.out.println("成功：进入scheduleJobs()");
        this.last_algorithm = this.user_selected;

        while (true){
            if(this.last_algorithm == "orderSched"){
                List<Job> earliestJob = new ArrayList<Job>((Collection<? extends Job>) jobService.findJobByStartTime());
               // List<Job> earliestJob = jobService.findJobByStartTime();
                System.out.println(earliestJob.get(0).getRes());
                if(earliestJob.get(0).getRes() <= this.resNow){
                    this.this_algorithm = this.last_algorithm;
                }else{
                    this.this_algorithm = "resShortSched";
                }
            }else if(this.last_algorithm == "prioritySched"){

            }else{ //this.last_algorithm == "resShortSched"

            }

            ChgAlgorithm chgAlgorithm = new ChgAlgorithm();
            if(this.this_algorithm == "orderSched"){
                chgAlgorithm.orderSched(earliestJob.get(0));
            }else if(this.this_algorithm == "prioritySched"){
                chgAlgorithm.prioritySched();
            }else{ //this.this_algoritm == "resShortSched"
                chgAlgorithm.resShortSched();
            }

            if(jobService.noJobWaiting() == 0){
                this.last_algorithm = this.this_algorithm;
            }else {
                break;
            }
        }
    }


    public String getLast_algorithm() {
        return last_algorithm;
    }

    public void setLast_algorithm( String last_algorithm ) {
        this.last_algorithm = last_algorithm;
    }

    public String getThis_algorithm() {
        return this_algorithm;
    }

    public void setThis_algorithm( String this_algorithm ) {
        this.this_algorithm = this_algorithm;
    }

    public String getUser_selected() {
        return user_selected;
    }

    public void setUser_selected( String user_selected ) {
        this.user_selected = user_selected;
    }

    public void setResNow( int resNow ) {
        this.resNow = resNow;
    }

    public int getResNow() {
        return resNow;
    }
}
