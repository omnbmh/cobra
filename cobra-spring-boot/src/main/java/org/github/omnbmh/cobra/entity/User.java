package org.github.omnbmh.cobra.entity;

import lombok.Data;
import org.github.omnbmh.cobra.base.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class User extends BaseEntity implements UserDetails {

    private String username;

    private String passwd;

    private Boolean isEnable;

    private Boolean isLocked;

    private List<Role> roles;

    // 获取当前用户对象所具有的角色信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        if (roles != null) {
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return passwd;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // 账户不过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否锁定
    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    // 证书不过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 是否启用
    @Override
    public boolean isEnabled() {
        return isEnable;
    }
}