package cn.jelly.lightspace.mapper;

import cn.jelly.lightspace.entity.No;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoMapper {
    /**
     * 通过uid查找noList
     * @param uid
     * @return
     */
    @Select("select * from no where uid=#{uid}")
    @Results(id = "noMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "value", property = "value"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "flag", property = "flag"),
    })
    List<No> findNoByUid(int uid);

    /**
     * 添加no
     */
    @Insert("insert into no(uid,value,flag) values(#{uid},#{value},#{flag})")
    @ResultMap("noMap")
    void noAdd(No no);


    /**
     * 更具no的id改变flag状态
     * @param id
     * @return
     */
    @Update("update no set flag=false where id = #{id}")
    @ResultMap("noMap")
    void changeFlag(int id);
}
