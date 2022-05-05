package Util;

import DAO.AdministratorDAO;
import DAO.LibrarianDAO;
import DAO.ReaderDAO;
import Entity.Administrator;
import Entity.Librarian;
import Entity.Reader;
import View.AdmInter;
import View.LibInter;
import View.ReaderInter;

import java.sql.Connection;
import java.util.Scanner;

/**
 * 登录系统的工具类
 * @author jack li
 * @create 2021-03-14 18:36
 */
public class Login {
    //登录系统验证阶段
    public void verify(String kind) {
        while (true) {
            int account;//记录输入的账号
            String password;//记录输入的密码
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入" + kind + "的账号（数字）");
            account =  scanner.nextInt();
            if(account == 0){
                break;
            }

            scanner.nextLine();
            System.out.println("请输入" + kind + "的密码");
            password = scanner.nextLine();
            switch (kind) {
                case "administrator":
                    Administrator adm;
                    adm = (Administrator)findAccount(kind,account);
                    if(adm != null && adm.getPassword().equals(password)){
                        //进入图书馆管理员界面
                        AdmInter admInter = new AdmInter(adm);
                        admInter.admInterface();
                        return ;

                    }else{
                        System.out.println("输入的账号或密码不正确，请重新输入或者输入0退出");
                    }
                    break;
                case "librarian":
                    Librarian lib;
                    lib = (Librarian)findAccount(kind,account);
                    if(lib != null && lib.getPassword().equals(password)){
                        //进入图书管理员界面
                        LibInter libInter = new LibInter(lib);
                        libInter.libInterface();
                        return ;


                    }else{
                        System.out.println("输入的账号或密码不正确，请重新输入或者输入0退出");
                    }
                    break;
                case "reader":
                    Reader reader;
                    reader = (Reader)findAccount(kind,account);
                    if(reader != null && reader.getPassword().equals(password)){
                        //进入读者界面
                        ReaderInter readerInter = new ReaderInter(reader);
                        readerInter.reaInterface();
                        return;
                    }else{
                        System.out.println("输入的账号或密码不正确，请重新输入或者输入0退出");
                    }
                    break;
            }


        }
    }

    //去对应的数据库中查询是否有此账号
    public Object findAccount(String kind, int account)  {
        Connection con = null;
        try {
            con = SQLoper.creatCon();
            switch (kind) {
                case "administrator":
                    AdministratorDAO admdao = new AdministratorDAO();
                    return (Object) admdao.search(con, account);

                case "librarian":
                    LibrarianDAO libdao = new LibrarianDAO();
                    return (Object) libdao.search(con, account);

                default:
                    ReaderDAO readerdao = new ReaderDAO();
                    return (Object) readerdao.search(con, account);
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
        return null;
    }
}

