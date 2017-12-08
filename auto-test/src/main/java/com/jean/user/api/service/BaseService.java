package com.jean.user.api.service;

import com.jean.user.api.entity.BaseEntity;
import com.jean.user.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public class BaseService<T extends BaseEntity, ID extends Serializable> implements IBaseService<T, ID> {

    @Autowired
    protected BaseRepository<T, ID> repository;

    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        return repository.save(entity);
    }

    @Override
    public T findOne(ID id) {
        return repository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        repository.delete(id);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    @Transactional
    public void deleteInBatch(Iterable<T> entities) {
        repository.deleteInBatch(entities);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public List<T> findAll(Iterable<ID> ids) {
        return repository.findAll(ids);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return repository.findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return repository.findAll(example, sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }
}
