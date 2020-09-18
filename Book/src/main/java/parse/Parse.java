package parse;

import model.BookInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author OnTheRoad_
 * @Project: Book
 * @Package:parse
 * @date 2018/9/29 16:48
 * @description 获取HTML中的图书信息
 **/
public class Parse {
    public static List<BookInfo> getData(String html) {
        //获取的数据，存放在集合中
        List<BookInfo> data = new ArrayList<>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取HTML标签中的内容
        Elements elements = doc.select("div[class=indent]").select("table");
        for (Element ele : elements){
            String picUrl = ele.select("img").attr("src");
            String name = ele.select("div[class=pl2]").text();
            String pubinfo = ele.select("p").text();
            //pubinfo:[美] 卡勒德·胡赛尼 / 李继宏 / 上海人民出版社 / 2006-5 / 29.00元
            //               作者       / 翻译  /    出版社      /出版时间/价格
            // 没有翻译人员的格式：钱锺书 / 人民文学出版社 / 1991-2 / 19.00
            String[] infos = pubinfo.split("/");
            String ratingStr = ele.select("div[class=star clearfix]").select("span[class=rating_nums]").text();
            Double rating = Double.valueOf(ratingStr);
            //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
            BookInfo book = new BookInfo();
            book.setName(name);
            book.setPic(picUrl);
            book.setAuthor(infos[0]);
            book.setPublisher(infos[infos.length-3]);
            book.setPublishTime(infos[infos.length-2]);
            book.setRating(rating);
            //将每一个对象的值，保存到list集合中
            data.add(book);
        }
        //返回数据
        return data;
    }
}
