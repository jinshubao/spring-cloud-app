package com.jean.mybatis.demo.dao;

import com.jean.mybatis.demo.mode.TMediaEntity;
import org.apache.ibatis.annotations.Insert;

public interface TMediaDao extends BaseDao<TMediaEntity> {


    @Insert("INSERT INTO T_MEDIA(id,NAME,EMAIL,ADDR_ID, PHONE) VALUES(#{studId},#{name},#{email},#{address.addrId},#{phone})")
    @Override
    int insertSelective(TMediaEntity record);
}