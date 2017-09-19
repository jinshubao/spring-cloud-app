package com.jean.auto.controller;

import com.jean.auto.constant.CommonConstant;
import com.jean.auto.entity.BaseEntity;
import com.jean.auto.entity.Project;
import com.jean.auto.model.common.ApiResultHelper;
import com.jean.auto.model.common.ApiResultListHelper;
import com.jean.auto.model.common.ApiSimpleResultHelper;
import com.jean.auto.service.IBaseService;
import com.jean.auto.service.IProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @GetMapping
    ApiResultListHelper<T> list(@Min(value = 1L, message = CommonConstant.RE_ERROR__PAGE_NUMBER_LESS_THAN_MINIMUM_VALUE)
                                @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                @Min(value = 1L, message = CommonConstant.RE_ERROR__PAGE_SIZE_LESS_THAN_MINIMUM_VALUE)
                                @Max(value = 100L, message = CommonConstant.RE_ERROR__PAGE_SIZE_GREATER_THAN_MAXIMUM_VALUE)
                                @RequestParam(value = "size", required = false, defaultValue = CommonConstant.DEFAULT_PAGE_SIZE) Integer size,
                                @RequestParam(value = "keyword", required = false) String keyword) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Specification<T> specification = (root, query, builder) -> {
            if (!StringUtils.isEmpty(keyword)) {
                List<Predicate> predicates = new ArrayList<>();
                //path转化
                predicates.add(builder.like(root.get("name"), "%" + keyword + "%"));
                predicates.add(builder.like(root.get("description"), "%" + keyword + "%"));
                predicates.add(builder.like(root.get("remark"), "%" + keyword + "%"));
                return builder.or(predicates.toArray(new Predicate[predicates.size()]));
            }
            return null;
        };
        Page<T> all = baseService.findAll(specification, pageRequest);
        ApiResultListHelper<T> listHelper = new ApiResultListHelper<>(CommonConstant.ApiResponse.SUCCESS, all.getContent());
        listHelper.setNumber(all.getNumber() + 1);
        listHelper.setSize(all.getSize());
        listHelper.setTotal(all.getTotalElements());
        listHelper.setTotalPages(all.getTotalPages());
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return listHelper;
    }

    @PostMapping
    ApiSimpleResultHelper<T> add(@RequestBody T object) {
        object.setCreatedTime(new Date());
        object.setModifiedTime(object.getCreatedTime());
        object.setEnabled(true);
        T o = baseService.save(object);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, o);
    }


    @DeleteMapping("/{id}")
    ApiResultHelper delete(@PathVariable("id") ID id) {
        baseService.delete(id);
        return new ApiResultHelper(CommonConstant.ApiResponse.SUCCESS);
    }


    @GetMapping("/all")
    ApiSimpleResultHelper<List<Project>> all() {
        List<Project> modules = ((IProjectService) baseService).findAll(new Sort(new Sort.Order(Sort.Direction.DESC, "createdTime")));
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, modules);
    }


    public Logger getLogger() {
        return logger;
    }
}
