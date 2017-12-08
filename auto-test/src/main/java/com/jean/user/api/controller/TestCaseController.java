package com.jean.user.api.controller;

import com.jean.user.api.constant.CommonConstant;
import com.jean.user.api.entity.Parameter;
import com.jean.user.api.entity.TestAssert;
import com.jean.user.api.entity.TestCase;
import com.jean.user.api.model.common.ApiResultListHelper;
import com.jean.user.api.model.common.ApiSimpleResultHelper;
import com.jean.user.api.model.request.AddTestCaseRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cases", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestCaseController extends BaseController<TestCase, Long> {

    @PostMapping
    ApiSimpleResultHelper<TestCase> add(@RequestBody @Valid AddTestCaseRequest request) {
        TestCase object = new TestCase();
        BeanUtils.copyProperties(request, object);
        if (request.getParameterList() != null) {
            List<Parameter> parameters = request.getParameterList().stream().map(param -> {
                Parameter parameter = new Parameter();
                BeanUtils.copyProperties(param, parameter);
                return parameter;
            }).collect(Collectors.toList());
            object.setParameters(parameters);
        }
        if (request.getAssertList() != null) {
            List<TestAssert> testAsserts = request.getAssertList().stream().map(assertRequest -> {
                TestAssert testAssert = new TestAssert();
                BeanUtils.copyProperties(assertRequest, testAssert);
                return testAssert;
            }).collect(Collectors.toList());
            object.setTestAsserts(testAsserts);
        }
        return super.add(object);
    }

    @PutMapping
    ApiSimpleResultHelper<TestCase> modify(@RequestBody TestCase object) {
        return super.modify(object);
    }

    @GetMapping("/list")
    ApiResultListHelper<TestCase> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @Min(value = 1L, message = CommonConstant.RE_ERROR__PAGE_NUMBER_LESS_THAN_MINIMUM_VALUE)
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @Min(value = 1L, message = CommonConstant.RE_ERROR__PAGE_SIZE_LESS_THAN_MINIMUM_VALUE)
            @Max(value = 100L, message = CommonConstant.RE_ERROR__PAGE_SIZE_GREATER_THAN_MAXIMUM_VALUE)
            @RequestParam(value = "size", required = false, defaultValue = CommonConstant.DEFAULT_PAGE_SIZE) Integer size) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Specification<TestCase> specification = (root, query, builder) -> {
            List<Predicate> predicateLikes = new ArrayList<>();
            if (!StringUtils.isEmpty(keyword)) {
                //path转化
                predicateLikes.add(builder.like(root.get("name"), "%" + keyword + "%"));
                predicateLikes.add(builder.like(root.get("description"), "%" + keyword + "%"));
            }
            List<Predicate> where = new ArrayList<>();
            if (!predicateLikes.isEmpty()) {
                where.add(builder.or(predicateLikes.toArray(new Predicate[predicateLikes.size()])));
            }
            query.where(where.toArray(new Predicate[where.size()]));
            return null;
        };
        Page<TestCase> all = baseService.findAll(specification, pageRequest);
        ApiResultListHelper<TestCase> listHelper = new ApiResultListHelper<>(CommonConstant.ApiResponse.SUCCESS, all.getContent());
        listHelper.setNumber(all.getNumber() + 1);
        listHelper.setSize(all.getSize());
        listHelper.setTotal(all.getTotalElements());
        listHelper.setTotalPages(all.getTotalPages());
        return listHelper;
    }
}
