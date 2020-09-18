package cn.jelly.lightspace.service;

import cn.jelly.lightspace.entity.Comment;
import cn.jelly.lightspace.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommentService {
    @Resource
    private CommentMapper commentMapper;

    public void commentAdd(Comment comment) {
        commentMapper.commentAdd(comment);
    }
}
