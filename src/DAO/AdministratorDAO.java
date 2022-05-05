package DAO;

import Entity.Administrator;
import Entity.Librarian;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * 图书馆管理员的更新操作，图书馆管理员只有一个
 *
 * 图书馆管理员的增删改操作
 * @author jack li
 * @create 2021-03-13 14:27
 */
public class AdministratorDAO extends BaseDAO {

    /**
     * 获取图书馆管理员的信息，可由父类getValue完成
     */

    /**
     * 修改图书馆管理员信息
     * @param con
     * @param name
     * @param target
     */
    public   void upDateAdm(Connection con, String name,String target){
        String sql = "update administrator set "+name +" = ? where administrator_id = 1";
        upData(con,sql,target);
    }
    /**
     * 按照id查询管理员的信息
     * @param con
     * @param id
     * @return Librarian 返回查询到的管理员
     */
    public Administrator search (Connection con , int id){
        String sql = "select administrator_id id,administrator_name name,administrator_password password from administrator where administrator_id = ?";
        Administrator value = getValue(con, Administrator.class, sql, id);
        return value;

    }








}
