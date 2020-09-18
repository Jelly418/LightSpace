package db;

import model.BookInfo;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author OnTheRoad_
 * @Project: Book
 * @Package:db
 * @date 2018/9/29 11:50
 * @description
 **/
public class MySqlControl {

    static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/lightspace?serverTimezone=UTC&characterEncoding=UTF-8");
    static QueryRunner qr = new QueryRunner(ds);

    //第一类方法
    public static void executeUpdate(String sql){
        try {
            qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //度二类方法
    public static void executeInsert(List<BookInfo> bookInfos) throws SQLException {
        /**
         * 定义一个Object数组，列行
         * 3表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是堆数组赋值，这里用到集合的get方法
         */

        Object[][] params = new Object[bookInfos.size()][6];
        for (int i = 0; i < params.length; i++){
            params[i][0] = bookInfos.get(i).getPic();
            params[i][1] = bookInfos.get(i).getName();
            params[i][2] = bookInfos.get(i).getAuthor();
            params[i][3] = bookInfos.get(i).getPublisher();
            params[i][4] = bookInfos.get(i).getPublishTime();
            params[i][5] = bookInfos.get(i).getRating();
        }
        qr.batch("insert into book(id,pic,name,author,publisher,publish_time,rating) values(null,?,?,?,?,?,?)",params);
        System.out.println("执行数据库完毕："+"成功插入数据："+bookInfos.size()+"条");
    }
}
