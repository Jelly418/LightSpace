package main;


import db.MySqlControl;
import model.BookInfo;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import util.URLFecter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author OnTheRoad_
 * @Project: Book
 * @Package:main
 * @date 2018/9/29 16:38
 * @description
 **/
public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        //初始化一个HTTPClient
        HttpClient client = new DefaultHttpClient();
        //集合保存数据
        List<BookInfo> books = new ArrayList<>();
        //我们要爬取的一个地址，利用循环可以爬取一个URL队列
        for (int i = 1; i < 10; i++){
            HttpGet httpGet = new HttpGet("https://book.douban.com/top250?start="+i*25);
            //设置header 避免403forbidden
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
            //抓取数据,加入到集合中
            books.addAll(URLFecter.URLParser(client,httpGet));
        }
//		for (BookInfo book : books){
//			System.out.println(book.toString());
//		}
        MySqlControl.executeInsert(books);
    }
}
