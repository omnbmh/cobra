package org.github.omnbmh.cobra.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Configuration
@Slf4j
public class CobraAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        Collection<? extends GrantedAuthority> auths = authentication.getAuthorities();
        for (ConfigAttribute ca : collection) {
            if ("ROLE_LOGIN".equals(ca.getAttribute()) && authentication instanceof UsernamePasswordAuthenticationToken) {
                log.info("需要登录权限 " + ca.getAttribute());
                return;
            }
            for (GrantedAuthority ga : auths) {
                if (ca.getAttribute().equals(ga.getAuthority())) {
                    log.info("当前用户 有权限 " + ga.getAuthority());
                    return;
                }
            }
            log.info("当前用户 没有权限 " + ca.getAttribute());
        }

        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
