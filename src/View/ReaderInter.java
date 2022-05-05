package View;

import Entity.Book;
import Entity.Reader;
import service.ReaderOper;

import java.util.Scanner;

/**
 * @author jack li
 * @create 2021-03-14 17:12
 */
public class ReaderInter extends Reader {
    private ReaderOper readerOper;
    public ReaderInter(Reader reader){
        readerOper = new ReaderOper(reader.getName(),  reader.getPassword(),  reader.getEmail());
        readerOper.setId(reader.getId());
    }

    //读者的界面展示
    public void reaInterface(){
        Boolean isFlag = true;
        Scanner scanner = new Scanner(System.in);
        String number;     //记录用户选择的功能
        while(isFlag){
            System.out.println("********************************************************\n");
            System.out.println("\t\tLibrarian ID\t" + "Librarian Name\t" + "Lirarian State\t");
            System.out.println( "\t\t" +readerOper.getId() + "\t\t\t\t\t" + readerOper.getName() + "\t\t\t" + readerOper.getState());
            System.out.println("1:搜索图书");
            System.out.println("2:查阅读书信息");
            System.out.println("3:添加书籍至购物车");
            System.out.println("4:修改信息");
            System.out.println("5:查看借阅记录");
            System.out.println("6:查看还书记录");
            System.out.println("7:查看购物车");
            System.out.println("8:退出");
            System.out.println("请选择相应的功能");

            number = scanner.nextLine();
            switch(number){
                case "1":
                    //搜索图书
                    searchBook();
                    break;
                case "2":
                    //查阅图书信息
                    finBook();
                    break;
                case "3":
                    //添加书籍到购物车
                    addBookCart();
                    break;
                case "4":
                    //修改信息
                    changeRea();
                    break;
                case "5":
                    //查看借阅记录
                    borrowItem();
                    break;
                case "6":
                    //查看还书记录
                    returnBook();
                    break;
                case "7":
                    //查看购物车
                    searchCart();
                    break;
                case "8":
                    //退出
                    isFlag = false;
                    break;

                default:
                    System.out.println("输入的字符不和法，请重新输入");

            }

        }
    }

    //搜索图书
    //图书查询
    //目前只支持isbn和书名查询
    public void searchBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查询图书的属性isbn或者name");
        String name = scanner.nextLine();
        System.out.println("请输入对应的属性值");
        String target = scanner.nextLine();
        readerOper.searchBook(name, target);
        scanner.close();
    }

    //查看图书的详细信息,会显示详细信息
    public void finBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查询图书的isbn");
        String name = scanner.nextLine();
        readerOper.findBook(name);
        scanner.close();
    }

    //修改个人信息
    public void changeRea(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要修改的读者的属性");
        String name = scanner.nextLine();
        System.out.println("请输入修改后的属性值");
        String target = scanner.nextLine();
        readerOper.changeReader(name,target,readerOper.getId());
        scanner.close();
    }

    //添加到购物车
    public  void addBookCart(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要添加到购物车的图书id");
        long id = scanner.nextLong();
        scanner.nextLine();
        readerOper.addCart(id,readerOper.getId());

        scanner.close();
    }

    //查看购物车
    public void searchCart(){
        readerOper.viewCart(readerOper.getId());
    }

    //查看借阅记录
    public void borrowItem(){
        readerOper.searchItem(readerOper.getId());
    }

    //查看还书记录
    public void returnBook(){
        readerOper.returnBook(readerOper.getId());

    }
}
