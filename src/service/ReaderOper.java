package service;

import DAO.BookDAO;
import DAO.Borrow_cartDAO;
import DAO.Borrow_itemDAO;
import DAO.ReaderDAO;
import Entity.*;
import Util.SQLoper;

import java.sql.Connection;
import java.util.List;

/**
 * @author jack li
 * @create 2021-03-14 11:18
 */
public class ReaderOper extends Reader {
    BookDAO bookdao;
    ReaderDAO readerdao;
    Borrow_cartDAO cart;
    Borrow_itemDAO item;
    public ReaderOper( String name, String password, String email){
        super(name,password,email);
        bookdao = new BookDAO();
        readerdao = new ReaderDAO();
        item = new Borrow_itemDAO();
    }

    /**
     * 根据isbn搜索图书
     * @param isbn 要查找图书的isbn
     */
    public void findBook(String isbn){
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            Book book = bookdao.searchBook(con, isbn);
            System.out.println(book);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                SQLoper.closeCon(con,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查看本人信息
     */
    public void  findSelf(){
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            Reader search = readerdao.search(con, (int)super.getId());
            System.out.println(search);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                SQLoper.closeCon(con,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //查阅图书信息
    /**
     * //图书查询
     * @param name 查询图书的条件
     * @param target 条件值
     */
    public void searchBook(String name,String target){
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            BookInLib search;
            if(name.equals("isbn")){
                //若输入的是isbn则直接可以在bookinlib中查询
                search = bookdao.search(con, target);
            }else{
                //根据书名搜索图书
                search = bookdao.searchName(con, target);
            }
            System.out.println(search);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                SQLoper.closeCon(con,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 添加书籍到购物车
     * @param id 图书的id
     * @param readerId 读者的id
     */
    public void addCart(long id,long readerId){
        Connection con = null;
        try {
            con = SQLoper.creatCon();
             cart = new Borrow_cartDAO();
             cart.add(con,id,readerId);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                SQLoper.closeCon(con,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 修改读者信息
     * @param name 需要修改的属性
     * @param target 修改后的值
     * @param id 需要修改的读者的id
     */
    public void changeReader(String name, String target,long id) {
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            readerdao.upDat(con,name,target,id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                SQLoper.closeCon(con,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据读者id查看借阅记录
     * @param id 读者id
     */
    public void searchItem(long id){
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            List<BorrowItem> borrowItems = item.searchCart(con,id);
            for(BorrowItem item : borrowItems){
                System.out.println(item);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                SQLoper.closeCon(con,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //查看还书记录
    public void returnBook(long id){
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            List<BorrowItem> borrowItems = item.searchCart(con,id);
            for(BorrowItem item : borrowItems){
                if(item.getRdate() != null) {
                    System.out.println(item);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                SQLoper.closeCon(con,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 根据读者id查看购物车
     * @param id 读者的id
     */
    public void viewCart(long id){
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            cart = new Borrow_cartDAO();
            cart.searchCart(con,id);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                SQLoper.closeCon(con,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
