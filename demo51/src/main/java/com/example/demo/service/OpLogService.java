package com.example.demo.service;

import com.example.demo.pojo.OpLog;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface OpLogService {
    public void writeOpLog( OpLog opLog);
}