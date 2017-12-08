package com.jean.security.service.impl;


import com.jean.security.domain.BaseEntity;
import com.jean.security.repository.BaseRepository;
import com.jean.security.service.IBaseService;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author jinshubao
 */
public abstract class BaseServiceImpl<T extends BaseEntity> implements IBaseService<T> {

    @Autowired
    protected BaseRepository<T, Long> repository;

    @Override
    public T findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T save(T t) {
        Date now = new Date();
        t.setCreatedTime(now);
        t.setModifiedTime(now);
        return repository.save(t);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(T t) {
        t.setEnabled(false);
        repository.save(t);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T update(T t) {
        Date now = new Date();
        t.setModifiedTime(now);
        return repository.save(t);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    @Override
    public List<T> findAll() {
        Iterable<T> iterable = repository.findAll();
        return IteratorUtils.toList(iterable.iterator());
    }
}
