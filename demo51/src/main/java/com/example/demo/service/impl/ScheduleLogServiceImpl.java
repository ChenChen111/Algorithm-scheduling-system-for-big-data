package com.example.demo.service.impl;

import com.example.demo.dao.ScheduleLogDao;
import com.example.demo.pojo.ScheduleLog;
import com.example.demo.service.ScheduleLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleLogServiceImpl implements ScheduleLogService {
    @Autowired
    private ScheduleLogDao scheduleLogDao;

    @Override
    public void writeScheduleLog( ScheduleLog scheduleLog){
        scheduleLogDao.insertScheduleLog(scheduleLog);
    }
}
