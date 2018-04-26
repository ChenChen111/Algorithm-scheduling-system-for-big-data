package com.example.demo.service;

import com.example.demo.pojo.AllTest;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public interface AllTestServie {

    public Time findMinTime();

    public int findMinInt();
}
