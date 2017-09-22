package com.jean.auto.controller;

import com.jean.auto.constant.CommonConstant;
import com.jean.auto.entity.BaseEntity;
import com.jean.auto.model.common.ApiResultHelper;
import com.jean.auto.model.common.ApiSimpleResultHelper;
import com.jean.auto.service.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;

@Validated
public class BaseController<T extends BaseEntity, ID extends Serializable> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected IBaseService<T, ID> baseService;

    @GetMapping("/{id}")
    ApiSimpleResultHelper<T> detail(@PathVariable("id") ID id) {
        T object = baseService.findOne(id);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, object);
    }

    ApiSimpleResultHelper<T> add(@RequestBody @Valid T object) {
        object.setCreatedTime(new Date());
        object.setModifiedTime(object.getCreatedTime());
        object.setEnabled(true);
        T o = baseService.save(object);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, o);
    }

    ApiSimpleResultHelper<T> modify(@RequestBody T object) {
        object.setModifiedTime(new Date());
        T o = baseService.save(object);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, o);
    }


    @DeleteMapping("/{id}")
    ApiResultHelper delete(@PathVariable("id") ID id) {
        baseService.delete(id);
        return new ApiResultHelper(CommonConstant.ApiResponse.SUCCESS);
    }

    public Logger getLogger() {
        return logger;
    }
}
