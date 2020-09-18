package cn.jelly.lightspace.service;

import cn.jelly.lightspace.entity.No;
import cn.jelly.lightspace.mapper.NoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NoService {
    @Resource
    NoMapper noMapper;

    public void noAdd(No no) {
        noMapper.noAdd(no);
    }

//    public No findById(int id){
//        return noMapper.findById(id);
//    }

    public void changeFlag(int id){
        noMapper.changeFlag(id);
    }
}
