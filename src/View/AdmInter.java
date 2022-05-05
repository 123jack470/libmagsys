package View;

import DAO.AdministratorDAO;
import Entity.Administrator;
import Entity.Librarian;
import service.AdministratorOper;

import java.util.Scanner;

/**
 * 图书馆管理员界面
 * @author jack li
 * @create 2021-03-14 17:13
 */
public class AdmInter {
    AdministratorOper admOper;
    public AdmInter(Administrator adm){
        admOper = new AdministratorOper(adm.getId(),adm.getPassword(),adm.getPassword());
    }
    public void admInterface(){
        Boolean isflag = true;
        Scanner scanner = new Scanner(System.in);
        String  numer;
        while(isflag){
            System.out.println("--------------欢迎"+admOper.getName()+ "------------");
            System.out.println();
            System.out.println();
            System.out.println("------------欢迎登录图书馆管理员系统------------");
            System.out.println("            1、修改个人信息");
            System.out.println("            2、添加图书管理员");
            System.out.println("            3、搜索图书管理员");
            System.out.println("            4、搜索全部图书管理员");
            System.out.println("            5、修改图书管理员信息");
            System.out.println("            6、删除图书管理员信息");
            System.out.println("            7、退出");
            System.out.println("请选择要执行的功能");
            numer = scanner.nextLine();
            switch(numer){
                case "1":
                    change();
                    break;
                case "2":
                    addLib();
                    break;
                case "3":
                    searchLib();
                    break;
                case "4":
                    searchLibAll();
                    break;
                case "5":
                    changeLib();
                    break;
                case "6":
                    deleteLib();
                    break;
                case "7":
                    isflag = false;
                    break;
                default:
                    System.out.println("输入的数据非法");

            }

        }

    }

    //修改个人信息
    public void change(){
        Scanner scanner = new Scanner(System.in);
        String name;
        String target;
        System.out.println("请输入要修改的属性");
        name = scanner.nextLine();
        System.out.println("请输入要修改的属性的值");
        target = scanner.nextLine();
        if(name.equals("name")){
            name = "administrator_name";
        }else if(name.equals("password")){
            name = "administrator_password";
        }else{
            name = "administrator_id";
        }
        admOper.upDateAdm(name,target);
    }
    //添加图书管理员
    public void addLib(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要插入管理员的名字");
        String name = scanner.nextLine();
        System.out.println("请输入要插入管理员的密码");
        String password = scanner.nextLine();
        System.out.println("请输入要插入管理员的状态(默认请写unlock)");
        String state = scanner.nextLine();
        Librarian librarian = new Librarian(name, password, state);
        admOper.addLib(librarian);

    }
    //搜索图书管理员
    public void searchLib(){
        System.out.println("请输入要搜索的图书管理员的id");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        scanner.nextLine();
        admOper.findLib(id);

    }
    //搜索全部图书管理员
    public void searchLibAll(){
        Boolean isflag = true;
        Scanner scanner = new Scanner(System.in);
        int number;
        while(isflag) {
            admOper.finall();
            System.out.println("1:修改图书管理员");
            System.out.println("2:删除图书管理员");
            System.out.println("3:退出");
            number =scanner.nextInt();
            scanner.nextLine();
            switch(number){
                case 1:
                    changeLib();
                    break;
                case 2:
                    deleteLib();
                    break;
                case 3:
                    isflag = false;
                    break;
                default:
                    System.out.println("输入的数据非法，请重新输入");
            }
        }


    }

    //修改图书管理员
    public void changeLib(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要修改的管理员的id");
        int id = scanner.nextInt();
        scanner.nextLine();
        String name; //存放将要修改的属性
        String target; //存放修改之后的属性值
        System.out.println("请输入要修改的属性");
        name = scanner.nextLine();
        if(name.equals("name")){
            name = "librarian_name";
        }else if(name.equals("password")){
            name = "librarian_password";
        }
        System.out.println("请输入要修改的属性的值");
        target = scanner.nextLine();
        admOper.upDataLib(name,target,id);
    }
    //删除图书管理员
    public void deleteLib(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要删除的管理员的id");
        int id = scanner.nextInt();
        scanner.nextLine();
        admOper.deleteLib(id);
    }

}
