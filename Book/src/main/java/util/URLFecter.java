package util;

import model.BookInfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import parse.Parse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author OnTheRoad_
 * @Project: Book
 * @Package:util
 * @date 2018/9/29 16:45
 * @description
 **/
public class URLFecter {

    public static List<BookInfo> URLParser(HttpClient client, HttpGet httpGet) throws IOException {
        //用来接收解析的数据
        List<BookInfo> bookInfos = new ArrayList<>();
        //获取网站响应的HTML
        HttpResponse response = client.execute(httpGet);
        //获取响应状态码
        int statusCode = response.getStatusLine().getStatusCode();
        //如果状态码为200，则获取HTML实例内容或者json文件
        if (statusCode == 200){
            String entity = EntityUtils.toString(response.getEntity(),"utf-8");
            bookInfos = Parse.getData(entity);
            EntityUtils.consume(response.getEntity());
        } else {
            //否则，消耗掉实体
            EntityUtils.consume(response.getEntity());
        }
        return bookInfos;
    }

}
