package com.example.demo;

import com.example.demo.action.Scheduler;
import com.example.demo.pojo.Job;
import com.example.demo.service.AllTestServie;
import com.example.demo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	@Autowired
	private JobService jobService;

	@Autowired
	Scheduler scheduler;

	@Autowired
	private AllTestServie allTestServie;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*Job job1=new Job();
		Job job2=new Job();

		job1.setJob_id("J001");
		job1.setPriority(1);
		job1.setJob_state("待执行");
		job2.setJob_id("J002");
		job2.setPriority(3);
		job2.setJob_state("待执行");

		jobService.addJobtoDB(job1);
		jobService.addJobtoDB(job2);*/

	/*	int int1 = allTestServie.findMinInt();
		System.out.println(int1);


		int a = jobService.fingMinPriority();
		System.out.println(a);

		//修改job_state的测试
		List<Job> jobNow = new ArrayList<Job>((Collection<? extends Job>) jobService.fingJobByPriority());
		//for(int i=0;i<jobNow.size();i++){
			System.out.println(jobNow.get(0).getJob_state());
		//	jobNow.get(i).setJob_state("正在执行");
		//}
		//jobService.chgJobState();*/

	//	List<Job> jobTest = new ArrayList<Job>((Collection<? extends Job>) jobService.findJobByStartTime());
	//	System.out.println(jobTest.get(0).getJob_id());
		System.out.println("begin");
		//LocalTime timeTest = jobService.fingMinTime();*/

		scheduler.setResNow(5);
		scheduler.setUser_selected("orderSched");
		scheduler.scheduleJobs();
	}
}
