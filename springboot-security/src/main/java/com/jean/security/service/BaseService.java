package com.jean.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public abstract class BaseService<T, ID extends Serializable> {

    @Autowired
    protected JpaRepository<T, ID> repository;

    public List<T> findAll() {
        return this.repository.findAll();
    }

    public List<T> findAll(Sort sort) {
        return this.repository.findAll(sort);
    }

    public List<T> findAll(Iterable<ID> ids) {
        return this.repository.findAll(ids);
    }

    public <S extends T> List<S> save(Iterable<S> entities) {
        return this.repository.save(entities);
    }

    public void flush() {
        this.repository.flush();
    }

    public <S extends T> S saveAndFlush(S entity) {
        return this.repository.saveAndFlush(entity);
    }

    public void deleteInBatch(Iterable<T> entities) {
        this.repository.deleteInBatch(entities);
    }

    public void deleteAllInBatch() {
        this.repository.deleteAllInBatch();
    }

    public T getOne(ID id) {
        return this.repository.getOne(id);
    }

    public <S extends T> List<S> findAll(Example<S> example) {
        return this.repository.findAll(example);
    }

    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return this.repository.findAll(example, sort);
    }

}
