package DAO;

import Entity.Administrator;
import Entity.Cart;
import Entity.Librarian;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

/**
 * 管理员的增删改查操作
 * @author jack li
 * @create 2021-03-13 16:18
 */
public class LibrarianDAO extends BaseDAO {
    /**
     * 增加管理员操作
     * @param con
     * @param lib
     */
    public void add(Connection con, Librarian lib){
        String sql = "insert into librarian(librarian_name,state,librarian_password) value(?,?,?)";
        upData(con,sql,lib.getName(),lib.getState(),lib.getPassword());
    }

    /**
     * 修改管理员信息根据id修改
     * @param con
     * @param name 需要修改的属性
     * @param target 修改后的值
     * @param id  需要修改的管理员的id
     */
    public <T> void upDat(Connection con,T name,T target ,long id){
        String sql = "update librarian set "+ name + " = ? where librarian_id = ? ";
        upData(con,sql,target,id);

    }

    /**
     * 删除管理员信息
     * @param con
     * @param id
     */
    public void delete(Connection con,int id){
        String sql ="delete from librarian where librarian_id = ?";
        upData(con,sql,id);
    }

    /**
     * 查询所有的管理员信息
     * @param con
     */
    public List<Librarian> searchAll(Connection con){
        String sql = "select librarian_id id,librarian_name name,state state,librarian_password password from librarian";
        List<Librarian> list = getValues(con, Librarian.class, sql);
        return list;
    }

    /**
     * 按照id查询管理员的信息
     * @param con
     * @param id
     * @return Librarian 返回查询到的管理员
     */
    public Librarian search (Connection con ,long id){
        String sql = "select librarian_id id,librarian_name name,state state,librarian_password password from librarian where librarian_id = ?";
        Librarian value = getValue(con, Librarian.class, sql, id);
        return value;

    }
    public List<Cart> searchShowCart(Connection con){
        String sql = "SELECT r.reader_id readerid,reader_name readername,bil.book_id bookid,book_name bookname,submit_time `submitime`\n" +
                "FROM book b,reader r,borrow_cart bc,`book_in_library` bil\n" +
                "WHERE bc.`book_id`= bil.`book_id` AND bil.`isbn` = b.`isbn` AND bc.`reader_id` = r.`reader_id`;";
        List<Cart> list = getValues(con, Cart.class, sql);
        return list;
    }



}
