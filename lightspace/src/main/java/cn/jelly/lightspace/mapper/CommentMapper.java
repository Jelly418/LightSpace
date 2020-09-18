package cn.jelly.lightspace.mapper;

import cn.jelly.lightspace.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    /**
     * 通过uid查找yesList
     * @param qid
     * @return
     */
    @Select("select * from comment where qid=#{qid}")
    @Results(id = "commentMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "value", property = "value"),
            @Result(column = "createTime", property = "createTime"),
            @Result(column = "qid", property = "qid"),
    })
    List<Comment> findCommentByQid(int qid);

    /**
     * 添加comment
     */
    @Insert("insert into comment(qid,value,createTime) values(#{qid},#{value},#{createTime})")
    @ResultMap("commentMap")
    void commentAdd(Comment comment);

}
