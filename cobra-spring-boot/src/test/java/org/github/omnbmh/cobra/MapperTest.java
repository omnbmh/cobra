package org.github.omnbmh.cobra;

import org.github.omnbmh.cobra.commons.tools.IdGenTools;
import org.github.omnbmh.cobra.entity.*;
import org.github.omnbmh.cobra.mapper.ApiMapper;
import org.github.omnbmh.cobra.mapper.RoleMapper;
import org.github.omnbmh.cobra.mapper.UserMapper;
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
    ApiMapper apiMapper;

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
    public void inserApi() {
        Api api = new Api();
        api.preInsert();
        api.setPattern("/user/hello");
        apiMapper.insertSelective(api);
        api.preInsert();
        api.setPattern("/hello");
        apiMapper.insertSelective(api);
    }

}
