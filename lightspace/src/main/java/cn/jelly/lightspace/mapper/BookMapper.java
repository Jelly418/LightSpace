package cn.jelly.lightspace.mapper;

import cn.jelly.lightspace.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface BookMapper {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from book")
    //    将数据库的列名和实体类的属性名对应 id=userMap使得其他的方法可以引用
    @Results(id = "bookMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "author", property = "author"),
            @Result(column = "publisher", property = "publisher"),
            @Result(column = "publish_time", property = "publishTime"),
            @Result(column = "rating", property = "rating"),
    })
    List<Book> findAll();

}
