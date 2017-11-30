package com.jean.security.config.sec;

import com.jean.security.service.ISysResourceService;
import com.jean.security.service.ISysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 权限资源
 *
 * @author jinshubao
 */
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final static Logger logger = LoggerFactory.getLogger(FilterInvocationSecurityMetadataSource.class);

    private final ISysResourceService sysResourceService;

    private final ISysRoleService sysRoleService;

    private final Map<String, Collection<ConfigAttribute>> resourcesMap = new HashMap<>();

    @Autowired
    public MyFilterInvocationSecurityMetadataSource(ISysResourceService sysResourceService, ISysRoleService sysRoleService) {
        this.sysResourceService = sysResourceService;
        this.sysRoleService = sysRoleService;
    }


    /**
     * 返回本次访问需要的权限，可以有多个权限
     * 在上面的实现中如果没有匹配的url直接返回null
     * 没有配置权限的url默认都为白名单，想要换成默认是黑名单只要修改这里即可
     *
     * @param object FilterInvocation对象
     * @return 权限集合
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        HttpServletRequest request = filterInvocation.getRequest();
        String method = request.getMethod().toUpperCase();
        Collection<ConfigAttribute> attributes = getAllConfigAttributes();
        for (ConfigAttribute attribute : attributes) {
            if (attribute instanceof MyConfigAttribute) {
                MyConfigAttribute myConfigAttribute = (MyConfigAttribute) attribute;
                if (myConfigAttribute.isAllMethd() || Objects.equals(method, myConfigAttribute.getMethod())) {
                    AntPathRequestMatcher matcher = new AntPathRequestMatcher(myConfigAttribute.getResource(), myConfigAttribute.getMethod());
                    if (matcher.matches(request)) {
                        //当前要拦截的URL
                        return getResourceAttributes(myConfigAttribute.getMethod(), myConfigAttribute.getResource());
                    }
                }
            }
        }
        return null;
    }

    protected Collection<ConfigAttribute> getResourceAttributes(String method, String resource) {
        //从数据库加载权限, 按需做缓存
        Collection<String> authorities = sysResourceService.findAuthorities(method, resource);
        logger.debug("Request {}, {}, auth:{}", method, resource, Arrays.toString(authorities.toArray()));
        return authorities.stream().map(SecurityConfig::new).collect(Collectors.toSet());
    }

    /**
     * 返回所有定义的权限资源
     * Spring Security会在启动时校验每个ConfigAttribute是否配置正确
     * 不需要校验直接返回null
     * 本类继承AbstractSecurityInterceptor才会调用本方法
     *
     * @return 权限集合
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        logger.info("加载权限列表...");
        return sysResourceService.findAllResources()
                .stream()
                .map(res -> new MyConfigAttribute(res.getAuthority(), res.getResource(), res.getMethod()))
                .collect(Collectors.toSet());
    }


    /**
     * 返回类对象是否支持校验，web项目一般使用FilterInvocation来判断，或者直接返回true。
     *
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}