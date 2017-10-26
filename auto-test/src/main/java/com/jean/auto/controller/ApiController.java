package com.jean.auto.controller;

import com.jean.auto.constant.CommonConstant;
import com.jean.auto.entity.Api;
import com.jean.auto.entity.Module;
import com.jean.auto.entity.QApi;
import com.jean.auto.model.common.ApiResultListHelper;
import com.jean.auto.model.common.ApiSimpleResultHelper;
import com.jean.auto.model.request.AddApiRequest;
import com.jean.auto.model.request.ModifyApiRequest;
import com.jean.auto.model.response.ApiResponse;
import com.jean.auto.service.IApiService;
import com.jean.auto.service.IModuleService;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
@RequestMapping(value = "/apis", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiController extends BaseController<Api, Long> {

    @Autowired
    private IModuleService moduleService;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @PostMapping
    ApiSimpleResultHelper<Api> add(@RequestBody @Valid AddApiRequest request) {
        Module module = moduleService.findOne(request.getModuleId());
        if (module == null) {
            return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.PARAMETER_ERROR, null);
        }
        Api object = new Api();
        BeanUtils.copyProperties(request, object);
        object.setModule(module);
        return super.add(object);
    }

    @PutMapping
    ApiSimpleResultHelper<Api> update(@RequestBody @Valid ModifyApiRequest request) {
        Api object = baseService.findOne(request.getId());
        BeanUtils.copyProperties(request, object);
        Api save = baseService.save(object);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, save);
    }

    @GetMapping("/list")
    ApiResultListHelper<Api> list(
            @RequestParam(value = "project_id", required = false) Integer projectId,
            @RequestParam(value = "module_id", required = false) Integer moduleId,
            @RequestParam(value = "keyword", required = false) String keyword,
            @Min(value = 1L, message = CommonConstant.RE_ERROR__PAGE_NUMBER_LESS_THAN_MINIMUM_VALUE)
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @Min(value = 1L, message = CommonConstant.RE_ERROR__PAGE_SIZE_LESS_THAN_MINIMUM_VALUE)
            @Max(value = 100L, message = CommonConstant.RE_ERROR__PAGE_SIZE_GREATER_THAN_MAXIMUM_VALUE)
            @RequestParam(value = "size", required = false, defaultValue = CommonConstant.DEFAULT_PAGE_SIZE) Integer size) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Specification<Api> specification = (root, query, builder) -> {
            List<Predicate> predicateAnds = new ArrayList<>();
            List<Predicate> predicateLikes = new ArrayList<>();
            if (moduleId != null) {
                predicateAnds.add(builder.equal(root.get("module").get("id"), moduleId));
            }
            if (projectId != null) {
                predicateAnds.add(builder.equal(root.get("module").get("project").get("id"), projectId));
            }
            if (!StringUtils.isEmpty(keyword)) {
                //path转化
                predicateLikes.add(builder.like(root.get("name"), "%" + keyword + "%"));
                predicateLikes.add(builder.like(root.get("description"), "%" + keyword + "%"));
            }
            List<Predicate> where = new ArrayList<>();
            if (!predicateAnds.isEmpty()) {
                where.add(builder.and(predicateAnds.toArray(new Predicate[predicateAnds.size()])));
            }
            if (!predicateLikes.isEmpty()) {
                where.add(builder.or(predicateLikes.toArray(new Predicate[predicateLikes.size()])));
            }
            query.where(where.toArray(new Predicate[where.size()]));
            return null;
        };
        Page<Api> all = baseService.findAll(specification, pageRequest);
        ApiResultListHelper<Api> listHelper = new ApiResultListHelper<>(CommonConstant.ApiResponse.SUCCESS, all.getContent());
        listHelper.setNumber(all.getNumber() + 1);
        listHelper.setSize(all.getSize());
        listHelper.setTotal(all.getTotalElements());
        listHelper.setTotalPages(all.getTotalPages());
        return listHelper;
    }


    @GetMapping("/names")
    ApiSimpleResultHelper<List<ApiResponse>> names(@RequestParam(value = "module_id", required = false) Long moduleId) {
        List<ApiResponse> modules = ((IApiService) baseService).findAllNames(moduleId);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, modules);
    }

    void test() {
        QApi _Q_api = QApi.api;
        jpaQueryFactory.select(_Q_api.id, _Q_api.name, _Q_api.createdTime)
                .from(_Q_api)
                .where(_Q_api.name.like("").and(_Q_api.module.name.like("")))
                .groupBy(_Q_api.module.id)
                .orderBy(_Q_api.createdTime.desc())
                .fetch();
    }
}
