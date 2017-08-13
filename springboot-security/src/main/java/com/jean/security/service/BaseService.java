package com.jean.security.service;


import com.jean.security.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public abstract class BaseService<T extends BaseEntity> {

    @Autowired
    protected JpaRepository<T, Long> repository;

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return this.repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<T> findAll(Sort sort) {
        return this.repository.findAll(sort);
    }

    @Transactional(readOnly = true)
    public <S extends T> List<S> findAll(Example<S> example) {
        return this.repository.findAll(example);
    }

    @Transactional(readOnly = true)
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return this.repository.findAll(example, sort);
    }

    @Transactional
    public T findOne(Long id) {
        return repository.findOne(id);
    }

    @Transactional
    public T save(T t) {
        Date now = new Date();
        t.setCreatedTime(now);
        t.setModifiedTime(now);
        return repository.save(t);
    }

    @Transactional
    public void delete(T t) {
        t.setEnabled(false);
        repository.save(t);
    }

    @Transactional
    public T update(T t) {
        Date now = new Date();
        t.setModifiedTime(now);
        return repository.save(t);
    }

}
