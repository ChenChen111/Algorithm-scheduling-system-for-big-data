package com.example.demo.service.impl;

import com.example.demo.dao.OpLogDao;
import com.example.demo.pojo.OpLog;
import com.example.demo.service.OpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class OpLogServiceImpl implements OpLogService{
    @Autowired
    OpLogDao opLogDao;

    @Override
    public void writeOpLog( OpLog opLog){
        opLogDao.insertOpLog(opLog);
    }
}