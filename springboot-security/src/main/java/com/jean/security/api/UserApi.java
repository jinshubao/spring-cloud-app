package com.jean.security.api;

import com.jean.security.constant.CommConstant;
import com.jean.security.domain.SysUser;
import com.jean.security.model.common.PageableResult;
import com.jean.security.model.common.SimpleDataResult;
import com.jean.security.model.common.SimpleListResult;
import com.jean.security.model.request.AddRoleRequest;
import com.jean.security.model.request.AddUserRequest;
import com.jean.security.model.response.RoleResponse;
import com.jean.security.model.response.UserResponse;
import com.jean.security.service.ISysUserService;
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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author jinshubao
 */
@RestController
@RequestMapping("/user")
public class UserApi extends BaseApi<SysUser> {

    private ISysUserService userService;

    @Autowired
    public UserApi(ISysUserService userService) {
        super(userService);
        this.userService = userService;
    }

    @PostMapping("/add")
    public SimpleDataResult<UserResponse> add(@RequestBody @Valid AddUserRequest request) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(request, sysUser);
        sysUser.setAccountNonExpired(true);
        sysUser.setAccountNonLocked(true);
        sysUser.setCredentialsNonExpired(true);
        sysUser.setEnabled(true);
        SysUser save = userService.save(sysUser);
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(save, response);
        return new SimpleDataResult.Builder<UserResponse>()
                .resCode(CommConstant.Response.SUCCESS.getResCode())
                .resDesc(CommConstant.Response.SUCCESS.getResDesc())
                .data(response).build();
    }

    @GetMapping("/list")
    public PageableResult<UserResponse> list(
            @RequestParam(name = "page", defaultValue = "1", required = false) @Min(value = 1) Integer page,
            @RequestParam(name = "size", defaultValue = "20", required = false) @Min(value = 1) Integer size,
            @RequestParam(name = "direction", defaultValue = "ASC", required = false) String direction,
            @RequestParam(name = "property", defaultValue = "id", required = false) String property,
            @RequestParam(name = "keyword", defaultValue = "", required = false) String keyword) {

        Specification<SysUser> specification = (root, query, cb) -> {
            if (!StringUtils.isEmpty(keyword)) {
                String kw = "%" + keyword + "%";
                Predicate name = cb.like(root.get("name"), kw);
                Predicate realName = cb.like(root.get("realName"), kw);
                Predicate email = cb.like(root.get("email"), kw);
                Predicate description = cb.like(root.get("description"), kw);
                return cb.or(name, realName, email, description);
            }
            return null;
        };
        Pageable pageable = new PageRequest(page - 1, size, new Sort(Sort.Direction.valueOf(direction), property));
        Page<UserResponse> responses = super.list(specification, pageable).map(source -> {
            UserResponse response = new UserResponse();
            Set<RoleResponse> roles = source.getRoles().stream().map(role -> {
                RoleResponse roleResponse = new RoleResponse();
                BeanUtils.copyProperties(role, roleResponse);
                return roleResponse;
            }).collect(Collectors.toSet());
            BeanUtils.copyProperties(source, response, "roles");
            response.setRoles(roles);
            return response;
        });
        return new PageableResult.Builder<UserResponse>()
                .resCode(CommConstant.Response.SUCCESS.getResCode())
                .resDesc(CommConstant.Response.SUCCESS.getResDesc())
                .withPage(responses).build();
    }

    @GetMapping("/{userId}")
    public SimpleDataResult<UserResponse> detail(@PathVariable("userId") Long userId) {
        SysUser sysUser = userService.findOne(userId);
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(sysUser, response);
        return new SimpleDataResult.Builder<UserResponse>()
                .resCode(CommConstant.Response.SUCCESS.getResCode())
                .resDesc(CommConstant.Response.SUCCESS.getResDesc())
                .data(response).build();
    }

    /**
     * 修改用户角色
     *
     * @param userId 用户ID
     * @param roles  角色
     * @return 角色列表
     */
    @PostMapping("/{userId}/roles")
    public SimpleListResult<RoleResponse> addRole(@PathVariable("userId") Long userId, @RequestBody Set<AddRoleRequest> roles) {
        Set<String> roleNames = roles.stream().map(AddRoleRequest::getName).collect(Collectors.toSet());
        SysUser sysUser = userService.updateRole(userId, roleNames);
        List<RoleResponse> responseList = sysUser.getRoles().stream().map(role -> {
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
