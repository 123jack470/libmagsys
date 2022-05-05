package DAO;

import Util.SQLoper;

import java.awt.*;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用的增删改操作
 * @author jack li
 * @create 2021-03-13 14:34
 */
public class BaseDAO {
    /**
     *  对数据库实现通用的增删改操作
     * @param con 数据库连接
     * @param sql 待执行的sql语句
     * @param args 占位符
     */
    public void upData(Connection con,String sql,Object... args)  {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);

            //占位符填充
            for(int i = 0 ;i < args.length;i++){
                ps.setObject(i + 1,args[i]);
            }

            //执行
            ps.execute();

            //返回消息
            System.out.println("修改成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭ps
                SQLoper.closeCon(null,ps);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 通用的单条查询操作
     * @param con
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T getValue(Connection con,Class<T> clazz, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            //填充占位符
            for(int i = 0 ; i < args.length;i ++){
                ps.setObject(i + 1, args[i]);
            }

            //执行获取结果集
            rs = ps.executeQuery();
            //获取metadata实例
            ResultSetMetaData metaData = rs.getMetaData();
            //获取属性的个数
            int count = metaData.getColumnCount();

            //将结果集封装到实体中
            if(rs.next()){
                T t = clazz.newInstance();
                for(int i = 0; i < count;i ++){
                    //获取第i列的属性名
                    String label = metaData.getColumnLabel(i + 1);
                    //获取第i列的值
                    Object object = rs.getObject(i + 1);
                    //通过反射为属性赋值
                    Field field = clazz.getDeclaredField(label);
                    field.setAccessible(true);

                    field.set(t,object);
                }
                return t;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            try {
                SQLoper.closeCon(null,ps,rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 通用的多条查询结果
     * @param con
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> List<T> getValues(Connection con, Class<T> clazz, String sql, Object... args)  {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //预编译sql语句，获得preparedStatement实例
            ps = con.prepareStatement(sql);

            //填充占位符
            for(int i = 0; i < args.length; i ++){
                ps.setObject(i+1,args[i]);
            }

            //执行，获取结果集
            rs = ps.executeQuery();

            //获取matedata实例，求出行数和列数
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();

            ArrayList<T> list = new ArrayList<>();

            while(rs.next()){
                T t = clazz.newInstance();
                for(int j = 0;j < count; j ++){
                    //获取每列属性的别名
                    String label = metaData.getColumnLabel(j + 1);
                   // System.out.println(label);
                    //获取相应的属性值
                    Object object = rs.getObject(label);
                    //System.out.println(object);
                    Field field = clazz.getDeclaredField(label);
                    //System.out.println(field);
                    field.setAccessible(true);
                    field.set(t,object);
                }
                list.add(t);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            try {
                SQLoper.closeCon(null,ps,rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 通用的查询结果
     * @param con
     * @param sql
     * @param args
     * @return
     */
    public Object getValue(Connection con,String sql,Object... args)  {
        PreparedStatement ps = null;
        ResultSet rs = null;//执行，获取结果集
        try {
            ps = con.prepareStatement(sql);
            //填充占位符
            for(int i = 0; i < args.length; i ++){
                ps.setObject(i+1,args[i]);
            }
            //执行，获取结果集
            rs = ps.executeQuery();
            return rs.getObject(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                SQLoper.closeCon(null,ps,rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
