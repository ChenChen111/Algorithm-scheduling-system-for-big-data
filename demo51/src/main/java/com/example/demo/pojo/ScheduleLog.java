package com.example.demo.pojo;

import java.time.LocalTime;

public class ScheduleLog extends Job {
    private String sched_time;
    private String state_chg;
    private int exe_times;
    private String algorithm_chg;

    public String  getSched_time() {
        return sched_time;
    }

    public void setSched_time( String sched_time ) {
        this.sched_time = sched_time;
    }

    public String getState_chg() {
        return state_chg;
    }

    public void setState_chg( String state_chg ) {
        this.state_chg = state_chg;
    }

    public int getNum_of_time() {
        return exe_times;
    }

    public void setNum_of_time( int num_of_time ) {
        this.exe_times = num_of_time;
    }

    public String getAlgorithm_chg() {
        return algorithm_chg;
    }

    public void setAlgorithm_chg( String algorithm_chg ) {
        this.algorithm_chg = algorithm_chg;
    }
}
