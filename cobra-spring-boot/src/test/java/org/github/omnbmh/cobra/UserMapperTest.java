package org.github.omnbmh.cobra;

import org.github.omnbmh.cobra.commons.tools.IdGenTools;
import org.github.omnbmh.cobra.entity.User;
import org.github.omnbmh.cobra.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

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

}
