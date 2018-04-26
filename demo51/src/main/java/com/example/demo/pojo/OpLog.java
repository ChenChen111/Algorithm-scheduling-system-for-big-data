package com.example.demo.pojo;

import java.time.LocalTime;
import java.util.Date;

/**
 * 用于记录用户产生操作的时间和具体的操作
 */
public class OpLog {

    private LocalTime op_time;
    private String op_type;

    public void setOp_time( LocalTime op_time ) {
        this.op_time = op_time;
    }

    public LocalTime getOp_time() {
        return op_time;
    }

    public void setOp_type( String op_type ) {
        this.op_type = op_type;
    }

    public String getOp_type() {
        return op_type;
    }
}
