package com.example.demo;

import com.example.demo.service.JobService;
import com.example.demo.service.JobController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	private JobController jobController;

	//test---remember to delete!!!
	@Autowired
	private JobService jobService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*Job job1 = new Job();
		job1.setJob_id("J0001");
		job1.setJob_name("aaaa");
		job1.setJob_type("spark");
		job1.setStart_time("22:22:22");
		job1.setEnd_time("11:11:11");
		job1.setExe_cycle(120);
		job1.setJob_state("waiting");
		job1.setPriority(1);
		job1.setRes(2);
		job1.setExe_times(0);

		jobExecutor.testChgTimeByJob(job1);

		//jobExecutor.testChgStartTime("J0001","22:22:22"); //无法实现
		//jobExecutor.testChgStartTime111("J0001");//这条能实现*/

		/*这里是测试顺序调度的
		scheduler.setResNow(5);
		scheduler.setUser_selected("orderSched");
		scheduler.scheduleJobs();*/

		jobController.setInitAlgorithm("orderSched");
		jobController.setInitRes(5);
		jobController.jobControllerInit();
		jobController.scheduleJobs();
	}
}
