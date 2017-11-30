package com.jean.es.service;

import com.jean.es.model.Log;
import com.jean.es.repostry.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jinshubao
 * @date 2017/7/17
 */
@Service
public class LogService {

    @Autowired
    LogRepository repository;

    public List<Log> findList(String level) {
        return repository.queryLogByLevel(level);
    }
}
