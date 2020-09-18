package cn.jelly.lightspace.entity;

import lombok.Data;

@Data
public class Book {
    private int id;
    private String name;
    private String pic;
    private String author;
    private String publisher; //出版社
    private String publishTime; //出版时间
    private double rating; //豆瓣评分

}
