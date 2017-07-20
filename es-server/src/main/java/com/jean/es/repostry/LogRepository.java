package com.jean.es.repostry;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by jinshubao on 2017/7/17.
 */
@Repository
public interface LogRepository<T, S extends Serializable> extends ElasticsearchRepository<T, S> {

}
