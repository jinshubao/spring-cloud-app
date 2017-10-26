package com.jean.test.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jinshubao
 * @date 2017/6/6
 */
@Api(value = "订单接口", description = "订单接口")
@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OrderApi {

    @ApiOperation(value = "订单详情", notes = "根据订单ID查询订单详情")
    @GetMapping("/{orderId}/detail")
    public Object orderDetail(@PathVariable("orderId") Integer orderId) {
        return null;
    }


    @ApiOperation(value = "订单列表", notes = "订单列表")
    @GetMapping("/list")
    public List<Object> orderList() {
        return null;
    }

}
