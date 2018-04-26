package com.example.demo.pojo;

import org.springframework.cglib.core.Local;

import java.sql.Time;
import java.time.LocalTime;

public class Job {
    private String job_id;
    private String job_name;
    private String job_type;
    private String start_time;
    private String end_time;
    private int exe_cycle;
    private String job_state;
    private int priority;
    private int res;

    private int exe_times;//已经执行了的次数

    public LocalTime stringToStartTime(){
        return LocalTime.parse(this.start_time);
    }

    public LocalTime stringToEndTime(){
        return LocalTime.parse(this.end_time);
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setStart_time( String start_time ) {
        this.start_time = start_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setEnd_time( String end_time ) {
        this.end_time = end_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setExe_cycle( int exe_cycle ) {
        this.exe_cycle = exe_cycle;
    }

    public int getExe_cycle() {
        return exe_cycle;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority( int priority ) {
        this.priority = priority;
    }

    public String getJob_state() {
        return job_state;
    }

    public void setJob_state( String job_state ) {
        this.job_state = job_state;
    }

    public void setRes( int res ) {
        this.res = res;
    }

    public int getRes() {
        return res;
    }

    public void setExe_times( int exe_times ) {
        this.exe_times = exe_times;
    }

    public int getExe_times() {
        return exe_times;
    }
}
