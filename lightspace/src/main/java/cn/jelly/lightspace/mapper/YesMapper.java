package cn.jelly.lightspace.mapper;

import cn.jelly.lightspace.entity.Yes;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface YesMapper {
    /**
     * 通过uid查找yesList
     * @param uid
     * @return
     */
    @Select("select * from yes where uid=#{uid}")
    @Results(id = "yesMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "value", property = "value"),
            @Result(column = "uid", property = "uid"),
    })
    List<Yes>  findYesByUid(int uid);


    /**
     * 添加yes
     */
    @Insert("insert into yes(uid,value) values(#{uid},#{value})")
    @ResultMap("yesMap")
    void yesAdd(Yes yes);
}
