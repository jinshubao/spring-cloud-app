package com.jean.test.api;

import com.jean.test.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinshubao on 2017/6/6.
 */
@Api(value = "User接口", description = "User接口")
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserApi {

    @ApiOperation(value = "用户详情", notes = "根据用户ID查询用户详情")
    @GetMapping("/{userId}/detail")
    public User userDetail(@PathVariable("userId") Integer userId) {
        User user = new User();
        user.setUserId(userId);
        user.setUsername("张三 " + userId);
        return user;
    }

    @Deprecated
    @ApiOperation(value = "用户详情", notes = "根据用户ID查询用户详情")
    @GetMapping("/details")
    public User details(@RequestParam("userId") Integer userId) {
        User user = new User();
        user.setUserId(userId);
        user.setUsername("张三 " + userId);
        return user;
    }

    @ApiOperation(value = "用户列表", notes = "用户列表")
    @GetMapping("/list")
    public List<User> userList() {
        List<User> users = new ArrayList<>();
        for (int i = 100; i < 120; i++) {
            User user = new User();
            user.setUserId(i);
            user.setUsername("张三 " + i);
            users.add(user);
        }
        return users;
    }

}
