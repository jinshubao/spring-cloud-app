package com.jean.mybatis.demo.dao;

import com.jean.mybatis.demo.mode.TAdvertisingEntity;

public interface BaseDao<T> {
    public int deleteByPrimaryKey(Integer id);

    public int insertSelective(T record);

    public T selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(T record);


}
