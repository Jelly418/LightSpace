package cn.jelly.lightspace.controller;

import cn.jelly.lightspace.entity.User;
import cn.jelly.lightspace.entity.Yes;
import cn.jelly.lightspace.service.UserService;
import cn.jelly.lightspace.service.YesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class YesController {
    @Autowired
    YesService yesService;
    @Autowired
    UserService userService;

    @PostMapping(value = "api/yesAdd")
    public void yesAdd(@RequestBody Yes yes, @RequestParam(value="username") String username){
        User u = userService.findByName(username);
        yes.setUid(u.getId());
        yesService.yesAdd(yes);
    }
}
