package org.github.omnbmh.cobra.config;

import lombok.extern.slf4j.Slf4j;
import org.github.omnbmh.cobra.commons.tools.GsonTools;
import org.github.omnbmh.cobra.entity.Resource;
import org.github.omnbmh.cobra.entity.Role;
import org.github.omnbmh.cobra.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Configuration
@Slf4j
public class CobraFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    ResourceService resourceService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Resource> resources = resourceService.resourceList();
        for (Resource resource : resources) {
            List<Role> roles = resourceService.resourceRoles(resource.getNo());
            if (antPathMatcher.match(resource.getUrlPattern(), requestUrl) && roles.size() > 0) {
                String[] roleArr = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    roleArr[i] = roles.get(i).getName();
                }
                log.info("request_url: " + requestUrl);
                log.info("pattern: " + resource.getUrlPattern());
                log.info("security: " + GsonTools.toJsonString(roleArr));
                return SecurityConfig.createList(roleArr);
            }
        }
        // 没有匹配上 则返 回 登录 权限
        log.info("request_url: " + requestUrl);
        log.info("pattern: None");
        log.info("security: " + "[ROLE_LOGIN]");
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
