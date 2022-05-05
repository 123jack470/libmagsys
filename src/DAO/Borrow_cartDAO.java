package DAO;

import Entity.Cart;
import Util.SQLoper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 对borrow-cart的增删改查
 * @author jack li
 * @create 2021-03-14 16:41
 */
public class Borrow_cartDAO  extends BaseDAO{

    public <T,E>void upData(Connection con, T name, E target, int id)  {
        PreparedStatement ps = null;
        try {
            String sql = "update borrow_cart set ? = ? where book_id = ?";
            ps = con.prepareStatement(sql);

            //占位符填充
            ps.setObject(1,name);
            ps.setObject(2,target);
            ps.setObject(3,id);

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

    //添加信息
    public void add(Connection con,Object... args)  {
        PreparedStatement ps = null;
        try {
            String sql = "insert into borrow_cart(book_id,reader_id,borrow_librarian_id) value(?,?)";

            ps = con.prepareStatement(sql);

            //占位符填充
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,i);
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
     * 查询购物车情况,根据读者id
     * @param con
     * @param id 读者的id
     */
    public void searchCart(Connection con,long id){
        PreparedStatement ps = null;

        try {
            String sql = "select book_id bookid,reader_id readid,submit_time  submitime from  borrow_cart where reader_id = ?";

            List<Cart> values = getValues(con, Cart.class, sql, id);
            System.out.println("ReaderID\tBookID\tSubmitState");

            if(values.size() != 0) {
                for (Cart cart : values) {
                    System.out.println(cart.getReaderid() + "\t" + cart.getBookid() + "\t" + cart.getSubmitime());
                }
            }else{
                System.out.println("读者购物车为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

         finally {
            try {
                //关闭ps
                SQLoper.closeCon(null,ps);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
