package Util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库连接的工具类，数据库连接池实现
 * @author jack li
 * @create 2021-03-13 14:11
 */
public class SQLoper {

    //创建数据库连接池

    /**
     *创建德鲁伊数据库连接池
     * @return
     * @throws Exception
     */
    private static DataSource dataSource;
    static{
        try {
            //加载配置文件
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druil.properties");
            Properties ps = new Properties();
            ps.load(is);
            //创建数据库连接池
            dataSource = DruidDataSourceFactory.createDataSource(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection creatCon1() throws Exception {
        Connection con = dataSource.getConnection();
        return con;
    }
    /**
     * 创建数据库连接
     * @return
     * @throws Exception
     */
    public static Connection creatCon() throws Exception {
        InputStream rs = SQLoper.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pp = new Properties();
        pp.load(rs);

        String user = pp.getProperty("user");
        String password = pp.getProperty("password");
        String url = pp.getProperty("url");
        String driverClass = pp.getProperty("driverClass");

        Class.forName(driverClass);

        //创建连接
        Connection con = DriverManager.getConnection(url,user,password);

        return con;
    }

    /**
     * 关闭数据库连接
     * @param con
     * @param ps
     * @throws Exception
     */
    public static void closeCon(Connection con, PreparedStatement ps) throws Exception {
        try {
            if(con != null)
            con.close();
            if(ps != null)
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void closeCon(Connection con, PreparedStatement ps, ResultSet rs) {
        try {
            if(con != null)
            con.close();
            if(ps!= null)
            ps.close();
            if(rs != null)
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
