package cn.jelly.lightspace.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private String phone;
    //一对多关系映射
    private List<Yes> yesList;
    private List<No> noList;
    private List<Question> questionList;
    private List<Note> noteList;

}
