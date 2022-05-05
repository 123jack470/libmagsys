package service;

import DAO.AdministratorDAO;
import DAO.LibrarianDAO;
import Entity.Administrator;
import Entity.Librarian;
import Util.SQLoper;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

/**
 * 图书馆管理员的基本操作
 * @author jack li
 * @create 2021-03-14 11:08
 */
public class AdministratorOper extends Administrator {
    private LibrarianDAO libr;
    private AdministratorDAO adm;
    public AdministratorOper(long id,String name,String password){
        super(id,name,password);
         libr = new LibrarianDAO();
         adm = new AdministratorDAO();
    }



    /**
     * 修改个人信息
     * @param name 要修改的属性
     * @param target 修改后的值
     */
    public void upDateAdm( String name, String target)  {
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            adm.upDateAdm(con, name, target);
            //System.out.println("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        try {
            SQLoper.closeCon(con,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 添加图书管理员
     * @param librarian 要添加的图书管理员信息
     */
    public void addLib(Librarian librarian)  {
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            libr.add(con,librarian);
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
     * 根据id搜索图书管理员
     * @param id
     */
    public void findLib(int id){
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            Librarian search = libr.search(con, id);

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
     * 查询所有图书管理员的信息
     */
    public void finall(){
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            List<Librarian> list = libr.searchAll(con);
            Iterator<Librarian> iterator = list.iterator();
            for(Librarian adm : list){
                System.out.println(adm);
            };
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
     * 根据id修改图书管理员信息
     * @param name 需要修改的属性
     * @param target 修改之后的值
     * @param id 需要修改的管理员的id
     */
    public void upDataLib(String name,String target,long id)  {
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            //首先查询有无此图书管理员
            Librarian librarian = libr.search(con, id);
            if(librarian.getId() != 0){
                libr.upDat(con,name,target,id);
            }else{
                System.out.println("不存在此管理员");
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

    //删除图书管理员
    public void deleteLib(int id){
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            libr.delete(con,id);
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
