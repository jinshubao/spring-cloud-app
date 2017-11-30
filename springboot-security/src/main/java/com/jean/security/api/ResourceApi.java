package com.jean.security.api;

import com.jean.security.domain.SysResource;
import com.jean.security.model.request.AddResourceAuthorityRequest;
import com.jean.security.model.request.UpdateResourceAuthorityRequest;
import com.jean.security.service.ISysAuthorityService;
import com.jean.security.service.ISysResourceService;
import com.jean.security.service.impl.SysAuthorityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinshubao
 */
@RestController
@RequestMapping("/resource")
public class ResourceApi extends BaseApi<SysResource> {

    @Autowired
    private ISysAuthorityService authorityService;

    @Autowired
    public ResourceApi(ISysResourceService resourceService) {
        super(resourceService);
    }

    @PostMapping("/add_authority")
    public void addAuthorities(@RequestBody AddResourceAuthorityRequest request){

    }

    @PostMapping("/update_authority")
    public void updateAuthorities(@RequestBody UpdateResourceAuthorityRequest request){

    }
}
