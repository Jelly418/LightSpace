package parse;

import model.Vedio;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author OnTheRoad_
 * @Project: Vedio
 * @Package:parse
 * @date 2018/9/29 16:48
 * @description 获取HTML中的视频信息
 **/
public class Parse {
    public static List<Vedio> getData(String html) {
        //获取的数据，存放在集合中
        List<Vedio> data = new ArrayList<>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取HTML标签中的内容
        Elements elements= doc.select("ul[class=vd-list mod-2]").first().select("li");
        for (Element ele : elements){
//            String author = ele.select("div.r").select("div.up-info").select("a").text();
                Elements list = ele.select("div[class=l-item]");
                String picUrl = ele.select("img").attr("src");
                String desc = ele.select("div[class=v-desc]").text();
                String title = ele.select("p").text();
//            System.out.println(author);
                System.out.println(picUrl);
                System.out.println(desc);
                System.out.println(title);
                //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
                Vedio vedio = new Vedio();
                vedio.setPic(picUrl);
//            vedio.setAuthor(author);
                vedio.setTitle(title);
                vedio.setDesc(desc);
                //将每一个对象的值，保存到list集合中
                data.add(vedio);
            }
        //返回数据
        return data;
    }
}

