package cn.jelly.lightspace.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Yes implements Serializable {
    private int id;
    private int uid;
    private  String value;
}
