package cn.jelly.lightspace;

import cn.jelly.lightspace.entity.User;
import cn.jelly.lightspace.mapper.UserMapper;
import cn.jelly.lightspace.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LightspaceApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Test
    public void findById() {
        User user = userMapper.findById(1);
        System.out.println(user);
    }

    @Test
    public void findByName() {
        User user = userService.findByName("jessica");
        System.out.println("用户的信息");
        System.out.println(user);
        System.out.println(user.getYesList());
    }
}
