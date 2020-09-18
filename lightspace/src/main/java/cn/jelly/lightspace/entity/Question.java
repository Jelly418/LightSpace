package cn.jelly.lightspace.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Question  implements Serializable {
    private int id;
    private int uid;
    private String title;
    private String text;
    private Date  createTime;
    private int viewNumber;

    private List<Comment> commentList;
}
