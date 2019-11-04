package org.github.omnbmh.cobra;

import org.github.omnbmh.cobra.commons.tools.IdGenTools;
import org.github.omnbmh.cobra.entity.*;
import org.github.omnbmh.cobra.mapper.RoleMapper;
import org.github.omnbmh.cobra.mapper.UserMapper;
import org.github.omnbmh.cobra.mapper.UserRoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {


    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Test
    public void insertUser() {
        User user = new User();
        user.setNo(IdGenTools.getId());
        user.setUsername("testuser");
        user.setPsswd(passwordEncoder.encode("123456"));
        user.setIsEnable(true);
        user.setIsLocked(false);
        user.setCreateAt(new Date());
        user.setCreator("system");
        assert userMapper.insertSelective(user) > 0;
    }

    @Test
    public void insertRole() {
        Role role = new Role();
        role.setNo(IdGenTools.getId());
        role.setName("user");
        role.setNameZh("用户");
        role.setCreateAt(new Date());
        role.setCreator("system");
        assert roleMapper.insertSelective(role) > 0;
    }

    @Test
    public void authAll() {
        List<User> users = userMapper.selectByExample(new UserExample());
        List<Role> roles = roleMapper.selectByExample(new RoleExample());

        for (User user : users) {
            for (Role role : roles) {
                UserRole userRole = new UserRole();
                userRole.setNo(IdGenTools.getId());
                userRole.setUserNo(user.getNo());
                userRole.setRoleNo(role.getNo());
                userRole.setRemark(user.getUsername() + " - " + role.getName());
                userRole.setCreateAt(new Date());
                userRole.setCreator("system");
                assert userRoleMapper.insertSelective(userRole) > 0;
            }
        }
    }

}
