package org.github.omnbmh.cobra.service;

import lombok.extern.slf4j.Slf4j;
import org.github.omnbmh.cobra.entity.User;
import org.github.omnbmh.cobra.mapper.RoleMapper;
import org.github.omnbmh.cobra.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userMapper.loadUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        log.info("用户 " + user.getUsername() + "(" + user.getNo() + ") 尝试登录...");
        user.setRoles(roleMapper.getUserRolesByUserNo(user.getNo()));
        return user;
    }
}
