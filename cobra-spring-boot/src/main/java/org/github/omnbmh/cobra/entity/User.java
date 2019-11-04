package org.github.omnbmh.cobra.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class User implements UserDetails {
    private Long id;

    private String no;

    private String username;

    private String psswd;

    private Boolean isEnable;

    private Boolean isLocked;

    private Date createAt;

    private String creator;

    private Date modifyAt;

    private String modifier;

    private String remark;

    private Boolean delFlag;

    private List<Role> roles;

    // 获取当前用户对象所具有的角色信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        if (roles != null){
            for (Role role :roles){
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return psswd;
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

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPsswd() {
        return psswd;
    }

    public void setPsswd(String psswd) {
        this.psswd = psswd == null ? null : psswd.trim();
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

}