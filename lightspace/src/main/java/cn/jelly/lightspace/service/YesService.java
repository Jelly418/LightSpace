package cn.jelly.lightspace.service;

import cn.jelly.lightspace.entity.Yes;
import cn.jelly.lightspace.mapper.YesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class YesService {
    @Resource
    private YesMapper yesMapper;

    public List<Yes> findByUid(int uid) {
        return yesMapper.findYesByUid(uid);
    }

    public void yesAdd(Yes yes) {
        yesMapper.yesAdd(yes);
    }
}
