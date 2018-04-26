package com.example.demo.service;

import com.example.demo.pojo.ScheduleLog;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleLogService {
    void writeScheduleLog( ScheduleLog scheduleLog);
}
