package cn.jelly.lightspace.service;

import cn.jelly.lightspace.entity.User;
import cn.jelly.lightspace.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User login(User user) {
        return userMapper.login(user);
    }

    public String  inserUser(User user) {
        User u1 = userMapper.findByName(user.getUsername());
        if(u1 == null){
            User u2 = userMapper.findByPhone(user.getPhone());
            if(u2 == null){
                userMapper.saveUser(user);
                return "success";
            }else{
                return "phoneError";
            }
        }else{
            return "nameError";
        }
    }

    public User findById(int id) {
        return userMapper.findById(id);
    }

    public User findByName(String username){
        return userMapper.findByName(username);
    }

    public User findByPhone(String phone) {
        return userMapper.findByPhone(phone);
    }

}
