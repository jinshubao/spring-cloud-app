package com.jean.security.model.dto;

import com.jean.security.model.enums.ResourceType;

/**
 * 使用接口作为查询结果时，缓存会有问题
 *
 * @author jinshubao
 * @since 1.0
 */
public interface RoleDtoTest {

    public String getRole();

    public String getResource();

    public ResourceType getResourceType();

}
