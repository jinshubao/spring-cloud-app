package com.jean.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public abstract class BaseService<T, ID extends Serializable> {

    @Autowired
    protected JpaRepository<T, ID> repository;

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return this.repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<T> findAll(Sort sort) {
        return this.repository.findAll(sort);
    }

    @Transactional(readOnly = true)
    public List<T> findAll(Iterable<ID> ids) {
        return this.repository.findAll(ids);
    }

    @Transactional
    public <S extends T> List<S> save(Iterable<S> entities) {
        return this.repository.save(entities);
    }

    @Transactional
    public void flush() {
        this.repository.flush();
    }

    @Transactional
    public <S extends T> S saveAndFlush(S entity) {
        return this.repository.saveAndFlush(entity);
    }

    @Transactional
    public void deleteInBatch(Iterable<T> entities) {
        this.repository.deleteInBatch(entities);
    }

    @Transactional
    public void deleteAllInBatch() {
        this.repository.deleteAllInBatch();
    }

    @Transactional(readOnly = true)
    public T getOne(ID id) {
        return this.repository.getOne(id);
    }

    @Transactional(readOnly = true)
    public <S extends T> List<S> findAll(Example<S> example) {
        return this.repository.findAll(example);
    }

    @Transactional(readOnly = true)
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return this.repository.findAll(example, sort);
    }

}
