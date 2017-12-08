package com.jean.security.api;

import com.jean.security.constant.CommConstant;
import com.jean.security.domain.SysRole;
import com.jean.security.model.common.PageableResult;
import com.jean.security.model.common.SimpleDataResult;
import com.jean.security.model.common.SimpleListResult;
import com.jean.security.model.request.AddRoleRequest;
import com.jean.security.model.response.RoleResponse;
import com.jean.security.service.ISysRoleService;
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
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jinshubao
 */
@RestController
@RequestMapping("/role")
public class RoleApi extends BaseApi<SysRole> {

    private ISysRoleService roleService;

    @Autowired
    public RoleApi(ISysRoleService roleService) {
        super(roleService);
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public SimpleDataResult<RoleResponse> add(@RequestBody @Valid AddRoleRequest request) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(request, sysRole);
        sysRole.setEnabled(true);
        SysRole save = roleService.save(sysRole);
        RoleResponse response = new RoleResponse();
        BeanUtils.copyProperties(save, response);
        return new SimpleDataResult.Builder<RoleResponse>()
                .resCode(CommConstant.Response.SUCCESS.getResCode())
                .resDesc(CommConstant.Response.SUCCESS.getResDesc())
                .data(response).build();
    }

    @GetMapping("/list")
    public PageableResult<RoleResponse> list(
            @RequestParam(name = "page", defaultValue = "1", required = false) @Min(value = 1) Integer page,
            @RequestParam(name = "size", defaultValue = "20", required = false) @Min(value = 1) Integer size,
            @RequestParam(name = "direction", defaultValue = "ASC", required = false) String direction,
            @RequestParam(name = "property", defaultValue = "id", required = false) String property,
            @RequestParam(name = "keyword", defaultValue = "", required = false) String keyword) {

        Specification<SysRole> specification = (root, query, cb) -> {
            if (!StringUtils.isEmpty(keyword)) {
                String kw = "%" + keyword + "%";
                Predicate name = cb.like(root.get("name"), kw);
                Predicate role = cb.like(root.get("role"), kw);
                Predicate description = cb.like(root.get("description"), kw);
                return cb.or(name, role, description);
            }
            return null;
        };
        Pageable pageable = new PageRequest(page - 1, size, new Sort(Sort.Direction.valueOf(direction), property));
        Page<RoleResponse> responses = super.list(specification, pageable).map(source -> {
            RoleResponse response = new RoleResponse();
            BeanUtils.copyProperties(source, response);
            return response;
        });
        return new PageableResult.Builder<RoleResponse>()
                .resCode(CommConstant.Response.SUCCESS.getResCode())
                .resDesc(CommConstant.Response.SUCCESS.getResDesc())
                .withPage(responses).build();
    }

    @GetMapping("/all")
    public SimpleListResult<RoleResponse> all(){
        List<RoleResponse> responseList = roleService.findAll().stream().map(role -> {
            RoleResponse response = new RoleResponse();
            BeanUtils.copyProperties(role, response);
            return response;
        }).collect(Collectors.toList());
        return new SimpleListResult.Builder<RoleResponse>()
                .resCode(CommConstant.Response.SUCCESS.getResCode())
                .resDesc(CommConstant.Response.SUCCESS.getResDesc())
                .list(responseList).build();
    }

}
