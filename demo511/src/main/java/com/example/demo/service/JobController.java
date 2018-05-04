package com.example.demo.service;

import com.example.demo.pojo.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Array;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
public class JobController {

    
    private String algorithm;//当前算法
    private int resNow;//当前资源，不是资源总数
    private int resMax;//资源总数
    private Job jobNow;
    private Queue<Job> jobPriorityQueue = new PriorityQueue<>(priorityComparator);
    private int priMax;

   // @Autowired
    //private JobDao jobDao;

    @Autowired
    private JobService jobService;

    @RequestMapping(value = "/jobList",method = RequestMethod.GET)
    public String findJob( String job_id, Model model){
        List<Job> list = jobService.findJobById(job_id);
        model.addAttribute("jobList",list);
        return "itemsList";
    }

    @RequestMapping(value = "/allJobList",method = RequestMethod.GET)
    public String findAllJob(Model model){
        List<Job> allJobList = jobService.findAllJob();
        model.addAttribute("allJobList",allJobList);
        return "jobFind";
    }

    public static Comparator<Job> startTimeComparator = new Comparator<Job>() {
        @Override
        public int compare( Job o1, Job o2 ) {
            return (int) ChronoUnit.SECONDS.between(o2.stringToStartTime(),o1.stringToStartTime());
        }
    };

    public static Comparator<Job> priorityComparator = new Comparator<Job>() {
        @Override
        public int compare( Job o1, Job o2 ) {
            //return o2.getPriority()-o1.getPriority();
            int comp = (int) ChronoUnit.SECONDS.between(o2.stringToStartTime(),o1.stringToStartTime());
            if(comp == 0){
                comp = o1.getPriority() - o2.getPriority();
            }
            return comp;
        }
    };

    public static Comparator<Job> resComparator = new Comparator<Job>() {
        @Override
        public int compare( Job o1, Job o2 ) {
            //return o1.getRes()-o2.getRes();
            int comp = (int) ChronoUnit.SECONDS.between(o2.stringToStartTime(),o1.stringToStartTime());
            if(comp == 0){
                comp = o1.getRes() - o2.getRes();
            }
            return comp;
        }
    };

    public void getQueue(String algorithm,List<Job> jobs){
        switch (algorithm){
            case "orderSched":
                this.jobPriorityQueue = new PriorityQueue<>(startTimeComparator);
                break;
            case "prioritySched":
                this.jobPriorityQueue = new PriorityQueue<>(priorityComparator);
                break;
            default://"resShortSched"
                this.jobPriorityQueue = new PriorityQueue<>(resComparator);
                break;
        }
        for(int i=0;i<jobs.size();i++){
            jobPriorityQueue.add(jobs.get(i));
        }
    }

    public void transferAlgorithm(String transAlgorithm){
        List<Job> transJobList = new ArrayList<>(this.jobPriorityQueue);
        this.jobPriorityQueue.clear();
        getQueue(transAlgorithm,transJobList);
    }

    private String initAlgorithm;
    private int initRes;

    public void jobControllerInit(){//初始化算法与初始化资源总数
        this.setResNow(this.getInitRes());
        this.setAlgorithm(this.getInitAlgorithm());
        this.setResMax(this.getInitRes());

        List<Job> dbJobs = jobService.findAllJob();
        getQueue(this.getInitAlgorithm(),dbJobs);
        if(!this.jobPriorityQueue.isEmpty()){
            return;
        }
        else{
            this.setJobNow(this.jobPriorityQueue.poll());
            this.setResNow(this.getInitRes()-jobNow.getRes());
        }
    }

    public void scheduleJobs() throws InterruptedException {
        while(!this.jobPriorityQueue.isEmpty()){
            this.setJobNow(this.jobPriorityQueue.poll());
            setResNow(this.getResNow() - jobNow.getRes());
            jobService.executeJob(this.getJobNow(),this.getAlgorithm(),this.getResNow());
            //setResNow(this.getResNow() + jobNow.getRes());

            if(jobNow.getExe_cycle()==0){
                //无执行周期
            }
            else{
                //如果任务有执行周期，改变其时间重新插入优先队列
                DateTimeFormatter ft = DateTimeFormatter.ofPattern("HH:mm:ss");
                jobNow.setStart_time(jobNow.stringToStartTime().plusSeconds(jobNow.getExe_cycle()).format(ft));
                jobNow.setEnd_time(jobNow.stringToEndTime().plusSeconds(jobNow.getExe_cycle()).format(ft));

                if(algorithm=="prioritySched"){
                    jobNow.setPriority(jobNow.getPriority()+this.getPriMax());
                }
                this.jobPriorityQueue.add(jobNow);
            }

            //是否需要转换算法
            if(this.getResNow() < this.getResMax()/2){
                this.setAlgorithm("resShortSched");
                transferAlgorithm("resShortSched");
            }
            //记得把按钮触发加上
        }
    }


    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm( String algorithm ) {
        this.algorithm = algorithm;
    }

    public void setResNow( int resNow ) {
        this.resNow = resNow;
    }

    public int getResNow() {
        return resNow;
    }

    public int getPriMax() {
        return priMax;
    }

    public void setPriMax( int priMax ) {
        this.priMax = priMax;
    }

    public Job getJobNow() {
        return jobNow;
    }

    public void setJobNow( Job jobNow ) {
        this.jobNow = jobNow;
    }

    public int getResMax() {
        return resMax;
    }

    public void setResMax( int sumSource ) {
        this.resMax = resMax;
    }

    public int getInitRes() {
        return initRes;
    }

    public void setInitRes( int initRes ) {
        this.initRes = initRes;
    }

    public String getInitAlgorithm() {
        return initAlgorithm;
    }

    public void setInitAlgorithm( String initAlgorithm ) {
        this.initAlgorithm = initAlgorithm;
    }
}
