package DAO;

import Entity.Book;
import Entity.BookInLib;
import com.oracle.deploy.update.UpdateCheck;

import java.sql.Connection;

/**
 * 图书的增删改查操作
 * @author jack li
 * @create 2021-03-14 8:34
 */
public class BookDAO extends BaseDAO {
    /**
     * 添加图书操作
     * @param con
     * @param book
     * @param bookin
     */
    public void add(Connection con, Book book,BookInLib bookin){
        //根据需求先查询该图书是否存在
        String sql = "select * from book where isbn = ?";
        Object value = getValue(con, sql, book.getIsbn());
        if(value == null){
            //没有这本书，则将书插入book表和bookinlibrary表
            String sql1 = "insert into book(isbn,book_price,book_name,ook_description,publisher_id) value(?,?,?,?,?)";
            upData(con,sql1,book.getIsbn(),book.getPrivce(),book.getName(),book.getDescription(),book.getPubId());

            String sql2 = "insert into book_in_library(isbn,book_location,state)";
            upData(con,sql2,bookin.getIsbn(),bookin.getLocation(),bookin.getState());
        }else{
            //存在这本书，将书插入到bookinlibrary表中
            String sql2 = "insert into book_in_library(isbn,book_location,state)";
            upData(con,sql2,bookin.getIsbn(),bookin.getLocation(),bookin.getState());
        }
    }

    /**
     * 根据id查询在bookinlibrary中的图书信息
     * @param con
     * @param id
     */
    public BookInLib search(Connection con,int id){
        String sql = "select book_id id,isbn isbn,book_location location,state state from book_in_library where book_id = ?";
        BookInLib value = getValue(con, BookInLib.class, sql, id);
        return value;

    }
    /**
     * 根据isbn查询在bookinlibrary中的图书信息
     * @param con
     * @param isbn
     */
    public BookInLib search(Connection con,String isbn){
        String sql = "select book_id id,isbn isbn,book_location location,state state from book_in_library where isbn = ?";
        BookInLib value = getValue(con, BookInLib.class, sql, isbn);
        return value;

    }
    /**
     * 根据name查询在book中的图书信息
     * @param con
     * @param name
     */
    public BookInLib searchName(Connection con,String name){

        String sql = "select isbn from book where book_name = ?";
        Object value = getValue(con, sql, name);
        //System.out.println(value);
        if(value != null) {
            return search(con, (String) value);
        }else{
            System.out.println("搜索的图书不存在");
            return null;
        }

    }

    /**
     * 根据书的作者搜索图书，目前数据库不支持该操作
     */

    /**
     * 根据图书id，删除在bookinlibrary中的图书
     * @param con
     * @param id
     */
    public void delete(Connection con,int id){
        //查询图书是否存在
        BookInLib book = search(con, id);
        if(book.getBook_id() != 0){
            //图书在bookinlibrary中
            if(book.getState().equals("inlib")){
                //图书未被借走
                String sql = "delete from book_in_library where book_id = ?";
                upData(con,sql,id);
            }else{
                System.out.println("图书被借出，不能删除");
            }
        }else{
            System.out.println("图书不存在，删除失败");
        }
    }

    /**
     * 在book表中根据isbn查询书的信息
     * @param con
     * @param isbn
     * @return
     */
    public Book searchBook(Connection con ,String isbn){
        String sql = "select isbn isbn,book_price privce,book_name name,book_description description,publisher_id pubId from book where isbn = ?";
        Book value = getValue(con, Book.class, sql, isbn);
        return value;
    }

    /**
     * 修改book中图书的信息
     * @param con
     * @param object 要修改的属性
     * @param target  修改后的值
     * @param isbn    图书的isbn
     * @param <T>
     */
    public <T> void upDat(Connection con, T object, T target,String isbn){
        String sql = "update book set ? = ? where isbn =?";
        upData(con,sql,object,target,isbn);
    }
    public <T> void upDat(Connection con, T object, T target,int idbook){
        String sql = "update book set ? = ? where book_id =?";
        upData(con,sql,object,target,idbook);
    }

}
