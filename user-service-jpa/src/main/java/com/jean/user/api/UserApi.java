package com.jean.user.api;

import com.jean.user.entity.User;
import com.jean.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * TODO
 *
 * @author jinshubao
 * @since TODO
 */
@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public Page<User> users(@RequestParam(name = "page", defaultValue = "1", required = false) @Min(value = 1) Integer page,
                            @RequestParam(name = "size", defaultValue = "10", required = false) @Min(value = 1) Integer size) {
        return userService.list(new PageRequest(page - 1, size));
    }

    @GetMapping("/all")
    public List<User> all() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User user(@PathVariable("id") Long id) {
        return userService.findOne(id);
    }
}
