package com.jean.security.api;

import com.jean.security.constant.CommConstant;
import com.jean.security.domain.SysAuthority;
import com.jean.security.domain.SysResource;
import com.jean.security.model.common.PageableResult;
import com.jean.security.model.common.SimpleDataResult;
import com.jean.security.model.request.AddResourceRequest;
import com.jean.security.model.response.ResourceResponse;
import com.jean.security.service.ISysResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.Min;
import java.util.HashSet;

/**
 * @author jinshubao
 */
@RestController
@RequestMapping("/resource")
public class ResourceApi extends BaseApi<SysResource> {

    ISysResourceService resourceService;

    @Autowired
    public ResourceApi(ISysResourceService resourceService) {
        super(resourceService);
        this.resourceService = resourceService;
    }

    @GetMapping("/list")
    public PageableResult<ResourceResponse> list(
            @RequestParam(name = "page", defaultValue = "1", required = false) @Min(value = 1) Integer page,
            @RequestParam(name = "size", defaultValue = "20", required = false) @Min(value = 1) Integer size,
            @RequestParam(name = "direction", defaultValue = "ASC", required = false) String direction,
            @RequestParam(name = "property", defaultValue = "id", required = false) String property,
            @RequestParam(name = "keyword", defaultValue = "", required = false) String keyword) {
        Specification<SysResource> specification = (root, query, cb) -> {
            if (!StringUtils.isEmpty(keyword)) {
                Predicate name = cb.like(root.get("name"), "%" + keyword + "%");
                Predicate description = cb.like(root.get("description"), "%" + keyword + "%");
                Predicate resource = cb.like(root.get("resource"), "%" + keyword + "%");
                Predicate method = cb.like(root.get("method"), "%" + keyword + "%");
                return cb.or(name, resource, method, description);
            }
            return null;
        };
        Pageable pageable = new PageRequest(page - 1, size, new Sort(Sort.Direction.valueOf(direction), property));
        Page<ResourceResponse> responses = super.list(specification, pageable).map(source -> {
            ResourceResponse response = new ResourceResponse();
            BeanUtils.copyProperties(source, response);
            return response;
        });
        return new PageableResult.Builder<ResourceResponse>()
                .resCode(CommConstant.Response.SUCCESS.getResCode())
                .resDesc(CommConstant.Response.SUCCESS.getResDesc())
                .withPage(responses).build();
    }

    @PostMapping("/add")
    public SimpleDataResult<ResourceResponse> add(@RequestBody AddResourceRequest request) {
        SysResource resource = new SysResource();
        BeanUtils.copyProperties(request, resource);
        resource.setAuthorities(new HashSet<>());
        if (request.getAuthorityId() != null) {
            //避免多条SQL
            SysAuthority authority = new SysAuthority();
            authority.setId(request.getAuthorityId());
            resource.getAuthorities().add(authority);
        }
        SysResource sysResource = resourceService.save(resource);
        ResourceResponse response = new ResourceResponse();
        BeanUtils.copyProperties(sysResource, response);
        return new SimpleDataResult.Builder<ResourceResponse>()
                .resCode(CommConstant.Response.SUCCESS.getResCode())
                .resDesc(CommConstant.Response.SUCCESS.getResDesc())
                .data(response).build();
    }

    @GetMapping("/{resourceId}")
    public SimpleDataResult<ResourceResponse> detail(@PathVariable("resourceId") Long resourceId) {
        SysResource sysResource = resourceService.findOne(resourceId);
        ResourceResponse response = new ResourceResponse();
        BeanUtils.copyProperties(sysResource, response);
        return new SimpleDataResult.Builder<ResourceResponse>()
                .resCode(CommConstant.Response.SUCCESS.getResCode())
                .resDesc(CommConstant.Response.SUCCESS.getResDesc())
                .data(response).build();
    }
}
