package cn.jelly.lightspace.mapper;

import cn.jelly.lightspace.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

//开启二级缓存
//@CacheNamespace(blocking = true)
@Mapper
public interface UserMapper {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from user")
    //    将数据库的列名和实体类的属性名对应 id=userMap使得其他的方法可以引用
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "phone", property = "phone"),
            @Result(property = "yesList", column = "id",
                    many = @Many(select = "cn.jelly.lightspace.mapper.YesMapper.findYesByUid",
                            fetchType = FetchType.LAZY)),
            @Result(property = "noList", column = "id",
                    many = @Many(select = "cn.jelly.lightspace.mapper.NoMapper.findNoByUid",
                            fetchType = FetchType.LAZY)),
            @Result(property = "questionList", column = "id",
                    many = @Many(select = "cn.jelly.lightspace.mapper.QuestionMapper.findQuesByUid",
                            fetchType = FetchType.LAZY)),
            @Result(property = "noteList", column = "id",
                    many = @Many(select = "cn.jelly.lightspace.mapper.NoteMapper.findNoteByUid",
                            fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
    @ResultMap("userMap")
    User findById(int id);




    /**
     * 登录
     */
    @Select("select * from user where username=#{username} And password=#{password}")
    @ResultMap("userMap")
    User login(User user);

    /**
     * 修改
     *
     * @param user
     */
    @Update("update user set username=#{username},password=#{password} where id=#{id}")
    @ResultMap("userMap")
    void updateUser(User user);

    /**
     * 删除
     *
     * @param id
     */
    @Delete("delete from user where id=#{id}")
    @ResultMap("userMap")
    void deleteUser(int id);


    /**
     *
     * @param user
     */
    @Insert("insert into user(username,password,phone) values(#{username},#{password},#{phone})")
    @ResultMap("userMap")
    void saveUser(User user);


    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    @Select("select * from user where username=#{username}")
    @ResultMap("userMap")
    User findByName(String username);

    /**
     * 通过手机号查找
     * @param phone
     */
    @Select("select * from user where phone=#{phone}")
    @ResultMap("userMap")
    User findByPhone(String phone);
}

