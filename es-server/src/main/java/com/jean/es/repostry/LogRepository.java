package com.jean.es.repostry;

import com.jean.es.model.Log;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author jinshubao
 * @date 2017/7/17
 */
@Repository
public interface LogRepository extends ElasticsearchRepository<Log, String> {

    /**
     * 按日志级别查询
     *
     * @param level
     * @return
     */
    List<Log> queryLogByLevel(String level);

}
