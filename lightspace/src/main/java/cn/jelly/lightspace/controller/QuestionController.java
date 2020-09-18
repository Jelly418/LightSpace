package cn.jelly.lightspace.controller;

import cn.jelly.lightspace.entity.Question;
import cn.jelly.lightspace.entity.User;
import cn.jelly.lightspace.service.QuestionService;
import cn.jelly.lightspace.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;


    @PostMapping(value = "api/loadQuestion")
    public PageInfo<Question> loadQuestion(@RequestParam int currentPage, @RequestParam int pageSize) {
        return questionService.findAll(currentPage, pageSize);
    }

    @PostMapping(value = "api/addQuestion")
    public void addQuestion(@RequestBody Question question, @RequestParam(value = "username") String username) {
        User u = userService.findByName(username);
        question.setUid(u.getId());
        questionService.addQuestion(question);
    }

    @PostMapping(value = "api/findQuesById")
    public Question findQuesById(@RequestParam(value = "id") int id) {
        return questionService.findById(id);
    }

    @PostMapping(value = "api/viewNumberAdd")
    public void viewNumberAdd(@RequestParam(value = "id") int id, @RequestParam(value = "viewNumber") int viewNumber) {
        questionService.viewNumberAdd(id, viewNumber);
    }

}
