package cn.jelly.lightspace.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private int id;
    private int qid;
    private String value;
    private Date createTime;
}
