package com.jean.security.api;

import com.jean.security.domain.BaseEntity;
import com.jean.security.service.IBaseService;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * TODO
 *
 * @author jinshubao
 * @since TODO
 */
public abstract class BaseApi<T extends BaseEntity> {

    private IBaseService<T> baseService;

    public BaseApi(IBaseService<T> service) {
        this.baseService = service;
    }

    public T add(T t) {
        return baseService.save(t);
    }

    public Page<T> list(Specification<T> spec, Pageable pageable) {
        return baseService.findAll(spec, pageable);
    }
}
