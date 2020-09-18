package db;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

/**
 * @author OnTheRoad_
 * @Project: Book
 * @Package:db
 * @date 2018/9/29 11:50
 * @description
 **/
public class MyDataSource {

    public static DataSource getDataSource(String connectURL){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("910816");
        ds.setUrl(connectURL);
        return ds;
    }
}
