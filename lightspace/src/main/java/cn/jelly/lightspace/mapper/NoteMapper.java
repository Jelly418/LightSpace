package cn.jelly.lightspace.mapper;

import cn.jelly.lightspace.entity.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from note")
    //    将数据库的列名和实体类的属性名对应 id=userMap使得其他的方法可以引用
    @Results(id = "noteMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "value", property = "value"),
            @Result(column = "createTime", property = "createTime"),
    })
    List<Note> findAll();

    /**
     *
     * @param uid
     * @return
     */
    @Select("select * from note where uid=#{uid}")
    @ResultMap("noteMap")
    List<Note> findNoteByUid(int uid);


    /**
     *
     * @param id
     * @return
     */
    @Select("select * from note where id=#{id}")
    @ResultMap("noteMap")
    Note findById(int id);


    /**
     *
     * @param note
     */
    @Insert("insert into note(uid,value,createTime) values(#{uid},#{value},#{createTime})")
    @ResultMap("noteMap")
    void addNote(Note note);

    /**
     *
     * @param note
     */
    @Insert("update note set value=#{value} where id=#{id}")
    @ResultMap("noteMap")
    void updateNote(Note note);
}
