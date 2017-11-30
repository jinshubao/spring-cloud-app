package com.jean.security.api;

import com.jean.security.domain.BaseEntity;
import com.jean.security.service.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;

/**
 * TODO
 *
 * @author jinshubao
 * @since TODO
 */
public abstract class BaseApi<T extends BaseEntity> {

    private IBaseService<T> baseService;

    public BaseApi(IBaseService<T> service) {
        this.baseService = service;
    }

    @PostMapping("/add")
    public T add(@RequestBody T t) {
        return baseService.save(t);
    }

    @GetMapping("/list")
    public Page<T> list(@RequestParam(name = "page", defaultValue = "1", required = false) @Min(value = 0) Integer page,
                        @RequestParam(name = "size", defaultValue = "10", required = false) @Min(value = 1) Integer size,
                        @RequestParam(name = "direction", defaultValue = "ASC", required = false) String direction,
                        @RequestParam(name = "property", defaultValue = "id", required = false) String property) {
        return baseService.findAll(new PageRequest(page, size, new Sort(Sort.Direction.valueOf(direction), property)));
    }
}
