package com.example.demo.service.impl;

import com.example.demo.dao.AllTestDao;
import com.example.demo.pojo.AllTest;
import com.example.demo.service.AllTestServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class AllTestServiceImpl implements AllTestServie{
    @Autowired
    private AllTestDao allTestDao;

    @Override
    public Time findMinTime(){
        return allTestDao.selectMinTime();
    }

    @Override
    public int findMinInt(){
        return allTestDao.selectMinInt();
    }
}
