package com.jean.auto.controller;

import com.jean.auto.constant.CommonConstant;
import com.jean.auto.entity.*;
import com.jean.auto.model.common.ApiResultListHelper;
import com.jean.auto.model.common.ApiSimpleResultHelper;
import com.jean.auto.model.request.AddAssertRequest;
import com.jean.auto.model.request.AddParameterRequest;
import com.jean.auto.model.request.AddTestCaseRequest;
import com.jean.auto.service.IApiService;
import com.jean.auto.service.ITestUnitService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(value = "/cases", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestCaseController extends BaseController<TestCase, Long> {

    @Autowired
    private IApiService apiService;

    @Autowired
    private ITestUnitService testUnitService;

    @PostMapping
    ApiSimpleResultHelper<TestCase> add(@RequestBody @Valid AddTestCaseRequest request) {
        TestUnit testUnit = testUnitService.findOne(request.getTestUnitId());
        if (testUnit == null) {
            return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.PARAMETER_ERROR, null);
        }
        Api api = apiService.findOne(request.getApiId());
        if (api == null) {
            return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.PARAMETER_ERROR, null);
        }
        TestCase object = new TestCase();
        BeanUtils.copyProperties(request, object);
        object.setTestUnit(testUnit);
        object.setApi(api);
        if (request.getParameterList() != null) {
            object.setParameters(new ArrayList<>());
            for (AddParameterRequest param : request.getParameterList()) {
                Parameter parameter = new Parameter();
                BeanUtils.copyProperties(param, parameter);
                object.getParameters().add(parameter);
            }
        }
        if (request.getAssertList() != null) {
            object.setTestAsserts(new ArrayList<>());
            for (AddAssertRequest assertRequest : request.getAssertList()) {
                TestAssert testAssert = new TestAssert();
                BeanUtils.copyProperties(assertRequest, testAssert);
                object.getTestAsserts().add(testAssert);
                testAssert.setTestCase(object);
            }
        }
        return super.add(object);
    }

    @PutMapping
    ApiSimpleResultHelper<TestCase> modify(@RequestBody TestCase object) {
        return super.modify(object);
    }

    @GetMapping("/list")
    ApiResultListHelper<TestCase> list(
            @RequestParam(value = "project_id", required = false) Integer projectId,
            @RequestParam(value = "module_id", required = false) Integer moduleId,
            @RequestParam(value = "test_unit_id", required = false) Integer testUnitId,
            @RequestParam(value = "keyword", required = false) String keyword,
            @Min(value = 1L, message = CommonConstant.RE_ERROR__PAGE_NUMBER_LESS_THAN_MINIMUM_VALUE)
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @Min(value = 1L, message = CommonConstant.RE_ERROR__PAGE_SIZE_LESS_THAN_MINIMUM_VALUE)
            @Max(value = 100L, message = CommonConstant.RE_ERROR__PAGE_SIZE_GREATER_THAN_MAXIMUM_VALUE)
            @RequestParam(value = "size", required = false, defaultValue = CommonConstant.DEFAULT_PAGE_SIZE) Integer size) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Specification<TestCase> specification = (root, query, builder) -> {
            List<Predicate> predicateAnds = new ArrayList<>();
            List<Predicate> predicateLikes = new ArrayList<>();
            if (testUnitId != null) {
                predicateAnds.add(builder.equal(root.get("testUnit").get("id"), testUnitId));
            }
            if (moduleId != null) {
                predicateAnds.add(builder.equal(root.get("testUnit").get("module").get("id"), moduleId));
            }
            if (projectId != null) {
                predicateAnds.add(builder.equal(root.get("testUnit").get("module").get("project").get("id"), projectId));
            }
            if (!StringUtils.isEmpty(keyword)) {
                //path转化
                predicateLikes.add(builder.like(root.get("name"), "%" + keyword + "%"));
                predicateLikes.add(builder.like(root.get("description"), "%" + keyword + "%"));
            }
            List<Predicate> where = new ArrayList<>();
            if (!predicateAnds.isEmpty()) {
                where.add(builder.or(predicateAnds.toArray(new Predicate[predicateAnds.size()])));
            }
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
