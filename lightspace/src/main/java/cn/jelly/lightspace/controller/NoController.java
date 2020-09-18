package cn.jelly.lightspace.controller;

import cn.jelly.lightspace.entity.No;
import cn.jelly.lightspace.entity.User;
import cn.jelly.lightspace.service.NoService;
import cn.jelly.lightspace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class NoController {
    @Autowired
    NoService noService;
    @Autowired
    UserService userService;

    @PostMapping(value = "api/noAdd")
    public void noAdd(@RequestBody No no, @RequestParam(value="username") String username){
        User u = userService.findByName(username);
        no.setUid(u.getId());
        no.setFlag(true);
        noService.noAdd(no);
    }

    @PostMapping(value = "api/addToDoneList")
    public void addToDoneList(@RequestParam(value="id") int id){
        noService.changeFlag(id);
    }
}
