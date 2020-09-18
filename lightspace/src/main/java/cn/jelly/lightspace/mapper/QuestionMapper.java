package cn.jelly.lightspace.mapper;

import cn.jelly.lightspace.entity.Question;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface QuestionMapper {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from question")
    //    将数据库的列名和实体类的属性名对应 id=userMap使得其他的方法可以引用
    @Results(id = "questionMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "title", property = "title"),
            @Result(column = "text", property = "text"),
            @Result(column = "createTime", property = "createTime"),
            @Result(column = "viewNumber", property = "viewNumber"),
            @Result(property = "commentList", column = "id",
                    many = @Many(select = "cn.jelly.lightspace.mapper.CommentMapper.findCommentByQid",
                            fetchType = FetchType.LAZY))
    })
    List<Question> findAll();

    /**
     * 通过uid查询用户所提的问题
     * @param uid
     * @return
     */
    @Select("select * from question where uid=#{uid}")
    @ResultMap("questionMap")
    List<Question> findQuesByUid(int uid);

    /**
     * 添加问题
     * @param question
     */
    @Insert("insert into question(uid,title,text,createTime) values(#{uid},#{title},#{text},#{createTime})")
    @ResultMap("questionMap")
    void addQuestion(Question question);

    /**
     * 通过id查找问题
     * @param id
     * @return
     */
    @Select("select * from question where id=#{id}")
    @ResultMap("questionMap")
    Question findById(int id);

    @Update("update question set viewNumber = #{arg1}  where id = #{arg0}")
    @ResultMap("questionMap")
    void viewNumberAdd(int id,int viewNumber);
}
