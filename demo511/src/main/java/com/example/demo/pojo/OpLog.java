package com.example.demo.pojo;

import java.time.LocalTime;

public class OpLog {
    private String op_time;
    private String op_type;

    public LocalTime stringToOpTime(){
        return LocalTime.parse(this.getOp_time());
    }

    public String getOp_time() {
        return op_time;
    }

    public void setOp_time( String op_time ) {
        this.op_time = op_time;
    }

    public String getOp_type() {
        return op_type;
    }

    public void setOp_type( String op_type ) {
        this.op_type = op_type;
    }
}
