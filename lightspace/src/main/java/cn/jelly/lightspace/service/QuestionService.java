package cn.jelly.lightspace.service;

import cn.jelly.lightspace.entity.Question;
import cn.jelly.lightspace.mapper.QuestionMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionService {
     @Resource
     private QuestionMapper questionMapper;

    public PageInfo<Question> findAll(int  currentPage,int pageSize){
        PageHelper.startPage(currentPage,pageSize, true);
        List<Question> questionList = questionMapper.findAll();
        PageInfo<Question> pageInfo = new PageInfo<>(questionList);
        return pageInfo;
    }
    public List<Question> findByUid(int uid) {
        return questionMapper.findQuesByUid(uid);
    }
    public void addQuestion(Question question) {
        questionMapper.addQuestion(question);
    }
    public Question findById(int id) {
        return questionMapper.findById(id);
    }
    public void viewNumberAdd(int id,int viewNumber) {
         viewNumber = viewNumber+1;
         questionMapper.viewNumberAdd(id,viewNumber);
    }

}
