package cn.jelly.lightspace.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Note {
    private int id;
    private int uid;
    private String value;
    private Date createTime;
}
