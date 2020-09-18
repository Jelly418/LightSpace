package cn.jelly.lightspace.controller;

import cn.jelly.lightspace.entity.Comment;
import cn.jelly.lightspace.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping(value = "api/commentAdd")
    @ResponseBody
    public void commentAdd(@RequestBody Comment comment){
        commentService.commentAdd(comment);
    }
}
