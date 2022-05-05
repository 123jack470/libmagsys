package View;

import Util.Login;

import java.util.Scanner;

/**
 * @author jack li
 * @create 2021-03-14 17:12
 */
public class MainInterface {
    public static void main(String args[]){
        //登录界面1
        Boolean isflag = true;
        String s ;//记录用户选择
        String kind;//记录登录的用户的类型
        Login login= new Login();
        Scanner scanner = new Scanner(System.in);
        while(isflag){
            System.out.println("------------欢迎登录图书馆管理系统------------");
            System.out.println("                1:图书馆管理员");
            System.out.println("                2:图书管理员");
            System.out.println("                3:读者");
            System.out.println("                4:退出");
            System.out.println("请选择要执行的功能");
            s = scanner.nextLine();
            switch(s){
                case "1":
                    System.out.println("------------欢迎进入图书馆管理员登录界面------------");
                    kind = "administrator";
                    login.verify(kind);
                    break;
                case "2":
                    System.out.println("------------欢迎进入图书管理员登录界面------------");
                    kind = "librarian";
                    login.verify(kind);
                    break;
                case "3":
                    System.out.println("------------欢迎进入读者登录界面------------");
                    kind = "reader";
                    login.verify(kind);
                    break;
                case "4":
                    isflag = false;
                    break;
                    default:
                        System.out.println("输入的数据非法");
            }

        }


    }
}
