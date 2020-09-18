package main;


import model.Vedio;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import util.URLFecter;

import java.io.IOException;
import java.util.List;

/**
 * @author OnTheRoad_
 * @Project: Vedio
 * @Package:main
 * @date 2018/9/29 16:38
 * @description
 **/
public class Main {
    public static void main(String[] args) throws IOException {

        //初始化一个HTTPClient
        HttpClient client = new DefaultHttpClient();
        //我们要爬取的一个地址，利用循环可以爬取一个URL队列
        HttpGet httpGet = new HttpGet("https://www.bilibili.com/v/life/funny?spm_id_from=333.851.b_7072696d6172794368616e6e656c4d656e75.29#/all/click/0/1");
        //设置header 避免403forbidden
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
        //抓取数据
        List<Vedio> vedios = URLFecter.URLParser(client,httpGet);
        for (Vedio vedio : vedios){
            System.out.println(vedio.toString());
        }
    }
}
