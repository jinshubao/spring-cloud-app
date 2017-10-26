package com.jean.security.service;


import com.jean.security.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author jinshubao
 */
public abstract class BaseService<T extends BaseEntity> {

    protected JpaRepository<T, Long> repository;

    @Autowired
    public void setRepository(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    public List<T> findAll() {
        return this.repository.findAll();
    }

    public List<T> findAll(Sort sort) {
        return this.repository.findAll(sort);
    }

    public <S extends T> List<S> findAll(Example<S> example) {
        return this.repository.findAll(example);
    }

    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return this.repository.findAll(example, sort);
    }

    public T findOne(Long id) {
        return repository.findOne(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public T save(T t) {
        Date now = new Date();
        t.setCreatedTime(now);
        t.setModifiedTime(now);
        return repository.save(t);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(T t) {
        t.setEnabled(false);
        repository.save(t);
    }

    @Transactional(rollbackFor = Exception.class)
    public T update(T t) {
        Date now = new Date();
        t.setModifiedTime(now);
        return repository.save(t);
    }

}
