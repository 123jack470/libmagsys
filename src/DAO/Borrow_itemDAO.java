package DAO;

import Entity.BorrowItem;
import Util.SQLoper;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jack li
 * @create 2021-03-14 15:13
 */
public class Borrow_itemDAO extends BaseDAO {
    /**
     * 更改borrow_item中的信息
     *
     * @param con
     * @param name   要更改的属性
     * @param target 更改之后的值
     * @param id     根据书的id进行更改
     * @param <T>
     * @param <E>
     */
    public <T, E> void upData(Connection con, T name, E target, long id) {
        PreparedStatement ps = null;
        try {
            String sql = "update borrow_item set ? = ? where book_id = ?";
            ps = con.prepareStatement(sql);

            //占位符填充
            ps.setObject(1, name);
            ps.setObject(2, target);
            ps.setObject(3, id);

            //执行
            ps.execute();

            //返回消息
            System.out.println("修改成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭ps
                SQLoper.closeCon(null, ps);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //添加信息
    public void add(Connection con, Object... args) {
        PreparedStatement ps = null;
        try {
            String sql = "insert into borrow_item(reader_id,book_id,borrow_librarian_id,borrow_time) value(?,?,?,?)";

            ps = con.prepareStatement(sql);

            //占位符填充
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, i);
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
                SQLoper.closeCon(null, ps);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 查询借阅记录，根据读者id
     *
     * @param con
     * @param id
     * @return
     */
    public List<BorrowItem> searchCart(Connection con, long id)  {
        String sql = "SELECT  it.book_id bookId,b.book_name `name`,it.borrow_librarian_id blibId,it.borrow_time bdate, it.return_time rdate FROM `borrow_item` it,book_in_library bi,`book` b WHERE it.reader_id = ? AND  it.book_id = bi.book_id AND bi.isbn = b.`isbn`; ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            //填充占位符
            ps.setObject(1, id);

            rs = ps.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();

            int count = metaData.getColumnCount();

            ArrayList<BorrowItem> list = new ArrayList<>();

            while (rs.next()) {
                BorrowItem t = BorrowItem.class.newInstance();
                for (int j = 0; j < count; j++) {
                    //获取每列属性的别名
                    String label = metaData.getColumnLabel(j + 1);
                    // System.out.println(label);
                    //获取相应的属性值
                    Object object = rs.getObject(label);
                    //System.out.println(object);
                    Field field = BorrowItem.class.getDeclaredField(label);
                    //System.out.println(field);
                    field.setAccessible(true);
                    field.set(t, object);
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
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            SQLoper.closeCon(con,ps,rs);
        }
        return null;
    }
}
