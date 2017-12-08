package com.jean.security.service;


import com.jean.security.domain.BaseEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @author jinshubao
 */
public interface IBaseService<T extends BaseEntity> {

    /**
     * 查询所有对象
     *
     * @return 所有对象
     */
    public List<T> findAll();

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 分页结果
     */
    Page<T> findAll(Pageable pageable);

    /**
     * 分页查询
     *
     * @param spec  参数
     * @param pageable 分页参数
     * @return 分页结果
     */
    Page<T> findAll(Specification<T> spec, Pageable pageable);

    /**
     * 查询单个对象
     *
     * @param id ID
     * @return 返回对象
     */
    public T findOne(Long id);

    /**
     * 保存单个对象
     *
     * @param t 对象实例
     * @return 返回对象本身
     */
    public T save(T t);

    /**
     * 删除对象
     *
     * @param t 要删除的对象
     */
    public void delete(T t);

    /**
     * 更新对象
     *
     * @param t 要更新的对象
     * @return 返回对象本身
     */
    public T update(T t);

}
