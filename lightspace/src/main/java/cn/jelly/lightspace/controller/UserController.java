package cn.jelly.lightspace.controller;

import cn.jelly.lightspace.entity.*;
import cn.jelly.lightspace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

//spring4里的注解，@RestController是@ResponseBody和@Controller的缩写
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;



    @PostMapping(value = "api/login")
    @ResponseBody
    public String login(@RequestBody User user, HttpSession session) {
        User u = userService.login(user);
        if(u != null){
            session.setAttribute("user",user);
            return "success";
        }else{
            return "error";
        }
    }


    @PostMapping(value = "api/findPhone")
    @ResponseBody
    public String findPhone(@RequestBody User user) {
        User u = userService.findByPhone(user.getPhone());
        if(u != null){
            return "success";
        }else{
            return "error";
        }
    }


    @PostMapping(value = "api/insertUser")
    @ResponseBody
    public String insertUser(@RequestBody User user){
        return userService.inserUser(user);

    }

//    @CrossOrigin
//    @PostMapping(value = "api/findById")
//    public void findById(@PathVariable String username){
//        User u = userService.findByName(username);
//            System.out.println(u);
//        User user = userService.findById(u.getId());
//            System.out.println("用户的信息");
//            System.out.println(user);
//            System.out.println(user.getYesList());
//        }

    /**
     * 获取yesList
     * @param user
     * @return
     */
    @PostMapping(value = "api/getYesList")
    @ResponseBody
    public List<Yes> getYesList(@RequestBody User user){
        User u = userService.findByName(user.getUsername());
        return  u.getYesList();
    }

    /**
     * 获取noList
     * @param user
     * @return
     */
    @PostMapping(value = "api/getNoList")
    @ResponseBody
    public List<No> getNoList(@RequestBody User user){
        User u = userService.findByName(user.getUsername());
        return  u.getNoList();
    }

    /**
     *
     * @param username
     * @return
     */
    @PostMapping(value = "api/myQuestionDisplay")
    @ResponseBody
    public List<Question> myQuestionDisplay(@RequestParam String username){
        User u = userService.findByName(username);
        return u.getQuestionList();
    }

    /**
     *
     * @param username
     * @return
     */
    @PostMapping(value = "api/loadNote")
    @ResponseBody
//    @RequestParam int  currentPage,@RequestParam int pageSize
    public List<Note> loadNote(@RequestParam String username){
        User u  = userService.findByName(username);
//        PageHelper.startPage(currentPage,pageSize, true);
//        List<Note> noteList = u.getNoteList();
//        PageInfo<Note> pageInfo = new PageInfo<>(noteList);
//        return pageInfo;
        return u.getNoteList();
    }
}

