package DAO;

import Entity.Reader;

import java.sql.Connection;
import java.util.List;

/**
 * 读者的增删改查操作
 * @author jack li
 * @create 2021-03-13 16:52
 */
public class ReaderDAO  extends BaseDAO{
    /**
     * 按照id查询读者的信息
     * @param con
     * @param id
     */
    public Reader search (Connection con, int id){
        String sql = "select reader_id id,reader_name name,reader_password password,reader_email email,state state from reader where reader_id = ?";
        Reader value = getValue(con, Reader.class, sql, id);
       return value;
    }

    /**
     * 根据id修改读者的信息
     * @param con
     * @param name 要修改的属性
     * @param target 修改后的属性值
     * @param id 要修改的读者的id
     * @param <T>
     * @param <E>
     */
    public <T,E>void upDat(Connection con,T name ,E target,long id){
        String sql;
        switch((String)name){
            case "password":
                 sql = "update reader set reader_password = ? where reader_id = ?";
                break;
            case "name":
                 sql = "update reader set reader_name = ? where reader_id = ?";
                 break;
            case "email":
                 sql = "update reader set reader_email = ? where reader_id = ?";
                 break;
            default:
                 sql = "update reader set state = ? where reader_id = ?";
                 break;


        }
        //String sql = "updata reader set reader_password = ? where reader_id = ?";
        upData(con,sql,target,id);
    }

    /**
     * 增加新的读者信息
     * @param con
     * @param read
     */
    public void add(Connection con,Reader read){
        String sql = "insert into Reader(reader_name,reader_password,reader_email) value(?,?,?)";
        upData(con,sql,read.getName(),read.getPassword(),read.getEmail());
    }

    /**
     * 查询所有读者的信息
     * @param con
     */
    public List<Reader> searchAll(Connection con){
        String sql ="select reader_id id,reader_name name,reader_password password,reader_email email,state state from reader";
        List<Reader> list = getValues(con, Reader.class, sql);
        return list;

    }

    /**
     * 根据id删除读者信息
     * @param con
     * @param id
     */
    public void delete (Connection con,int id){
        String sql = "delete Reader where reader_id = ?";

        String sql1 = "select * from librarian where id = ? and return_time = null";
        Object value = getValue(con, sql1, id);
        if((long)value == 0) {
            upData(con,sql,id);
        }else{
            System.out.println("该读者还有借阅图书未还，不能将其删除");
        }
    }




}
