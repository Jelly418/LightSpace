package cn.jelly.lightspace.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;



@Data

@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class No {
    private int id;
    private int uid;
    private String value;
    private boolean flag;
}
