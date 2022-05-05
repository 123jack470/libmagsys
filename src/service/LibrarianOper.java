package service;

import DAO.BookDAO;
import DAO.Borrow_itemDAO;
import DAO.LibrarianDAO;
import DAO.ReaderDAO;
import Entity.*;
import Util.SQLoper;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.Connection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author jack li
 * @create 2021-03-14 11:13
 */
public class LibrarianOper extends Librarian {
    ReaderDAO readdao;
    BookDAO bookdao;
    LibrarianDAO lib;
    Borrow_itemDAO bor;
    public LibrarianOper( String name, String password, String state){
        super(name,password,state);
        readdao = new ReaderDAO();
        bookdao = new BookDAO();
        lib = new LibrarianDAO();
        bor = new Borrow_itemDAO();
    }


    /**
     * 修改图书管理员信息
     * @param name 需要修改的属性
     * @param target 修改后的值
     * @param id 需要修改的图书管理员的id
     */
    public void upDateLib(String name, String target,long id) {
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            lib.upDat(con,name,target,id);
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
     * 图书添加
     * @param book 要添加的图书
     * @param bookin
     */
    public void insertBo(Book book, BookInLib bookin)  {
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            bookdao.add(con,book,bookin);
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

    //图书删除
    public void deleteBook(int id){
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            bookdao.delete(con,id);
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
     *  根据图书id将图书归还
     * @param id
     * @throws Exception
     */
    public void bookBack(int id)  {
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            //查询要归还的图书在bookinlibrary中的信息
            BookInLib search = bookdao.search(con, id);
            if(search.getState().equals("borrowed")){

                //图书被借出
                //将该图书在bookinlinbrary中的state信息更新成inlib
                bookdao.upDat(con,"state","inlib",search.getIsbn());

                //更新borrow——item中相应的信息
                //添加还书对应的时间
                Date date = new Date();
                java.sql.Date date1 = new java.sql.Date(date.getTime());
                bor.upData(con,"return_time",date1,search.getBook_id());
                //添加还书对应的管理员的id
                bor.upData(con,"borrow_librarian_id",super.getId(),search.getBook_id());
            }
            else{
                //图书没有被借出
                System.out.println("还书id错误");
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
     * //修改图书操作
     * @param an 查找的索引
     * @param name 要修改的属性
     * @param target 修改之后的值
     * @param <T>
     * @param <E>
     * @param <D>

     */
    public <T,E,D> void changeBook(T an, E name, D target )  {
        Connection con = null;
        try {
            con = SQLoper.creatCon();

            //判断an，是isbn，还是book_id
            if(an instanceof String){
                //按照isbn查找
                bookdao.upDat(con,name,target,(String)an);
            }else if(an instanceof Integer){
                //按照bookid查找
                BookInLib search = bookdao.search(con, (Integer) an);
                bookdao.upDat(con,name,target,search.getIsbn());
            }else{
                System.out.println("输入的类型非法");
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
     * 添加读者
     * @param reader 要添加的读者
     */
    public void addReader(Reader reader){
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            readdao.add(con,reader);
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
     * 根据读者id修改读者信息
     * @param name 要更改的属性
     * @param id 读者id
     */
    public void changeReader(String name,String target,int id){
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            readdao.upDat(con,name,target,id);
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
     * 读者删除,根据读者id
     * @param id
     */
    public void deleteReader(int id){
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            readdao.delete(con, id);
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
     * 查询全部读者信息
     */
    public void searchAll(){
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            List<Reader> list = readdao.searchAll(con);
            for(Reader r : list){
                System.out.println(r);
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
     * 根据id查询个人信息
     * @param id 要查询的读者的id
     */
    public void searchReader(int id){
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            Reader search = readdao.search(con, id);
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
     * 借阅图书
     * @param id 读者的id
     * @param idbook 借阅图书的id
     */
    public void borrowBook(int id,int idbook){
        Connection con = null;
        try {
            con = SQLoper.creatCon();

            //查询读者是否存在
            Reader readerWait = readdao.search(con, id);
            if(readerWait != null && readerWait.getState().equals("unblock")){
                //读者存在且账号未被封锁
                //将信息添加到borrow_item中
                bor.add(con,id,idbook,super.getId(),new java.sql.Date(new Date().getTime()));
                //修改book_in_library中的信息
                bookdao.upDat(con,"state","borrowed",idbook);

            }else{
                System.out.println("您目前不能借书");
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

    //展示购物车
     public void showCart (){
         Connection con = null;
         try {
             con = SQLoper.creatCon();
             List<Cart> carts = lib.searchShowCart(con);
             for(Cart c : carts){
                 System.out.println(c);
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

}
