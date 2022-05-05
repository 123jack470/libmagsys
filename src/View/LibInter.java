package View;

import Entity.Book;
import Entity.BookInLib;
import Entity.Librarian;
import Entity.Reader;
import service.LibrarianOper;

import java.lang.annotation.Target;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author jack li
 * @create 2021-03-14 17:13
 */
public class LibInter {
    private LibrarianOper libOper;
    public LibInter(Librarian lib){
        libOper = new LibrarianOper(lib.getName(),lib.getPassword(),lib.getState());
        libOper.setId(lib.getId());

    }

    //主界面
    public void libInterface(){
        Boolean isFlag = true;
        Scanner scanner = new Scanner(System.in);
        String number;     //记录用户选择的功能
        while(isFlag){
            System.out.println("\t\tLibrarian ID\t" + "Librarian Name\t" + "Lirarian State\t");
            System.out.println( "\t\t" +libOper.getId() + "\t\t\t\t\t" + libOper.getName() + "\t\t\t" + libOper.getState());
            System.out.println("1:修改密码");
            System.out.println("2:图书添加");
            System.out.println("3:图书删除");
            System.out.println("4:图书查询");
            System.out.println("5:图书归还");
            System.out.println("6:读者管理");
            System.out.println("7:读者删除");
            System.out.println("8:读者信息修改");
            System.out.println("9:读者信息查询");
            System.out.println("10:借阅图书");
            System.out.println("11:展示购物车");
            System.out.println("12:退出");
            System.out.println("请选择相应的功能");

            number = scanner.nextLine();

            switch(number){
                case "1":
                    //修改密码
                    change();
                    break;
                case "2":
                    //图书添加
                    addBook();
                    break;
                case "3":
                    //图书删除
                    deleteBook();
                    break;
                case "4":
                    //图书查询
                    searchBook();
                    break;
                case "5":
                    //图书归还
                    returnBook();
                    break;
                case "6":
                    //读者管理
                    manReader();
                    break;
                case "7":
                    //读者删除
                    deleteReader();
                    break;
                case "8":
                    //读者信息修改
                    changeReader();
                    break;
                case "9":
                    //读者信息查询
                   searchReader();
                    break;
                case "10":
                    //借阅图书
                    borrowBook();
                    break;
                case "11":
                    //展示购物车
                    showCart();
                    break;
                case "12":
                    isFlag = false;
                    break;
                default:
                    System.out.println("输入的字符不和法，请重新输入");

            }

        }
    }

    //修改密码
    public void change(){
        Scanner scanner = new Scanner(System.in);
        String name = "librarian_password";
        System.out.println("请输入新密码");
        String target = scanner.nextLine();
        libOper.upDateLib(name,target,libOper.getId());
    }

    //图书添加
    public void addBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入图书的isbn");
        String isbn = scanner.nextLine();
        System.out.println("请输入图书的price");
        BigDecimal price = scanner.nextBigDecimal();
        scanner.nextLine();
        System.out.println("请输入图书的name");
        String name = scanner.nextLine();
        System.out.println("请输入图书的description");
        String desc = scanner.nextLine();
        System.out.println("请输入图书的publisherId");
        long publishId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("请输入图书的location");
        String  location = scanner.nextLine();
        System.out.println("请输入图书的state(默认为inlib)");
        String state  = scanner.nextLine();

        //创建book和bookin对象
        Book book=new Book(isbn,price,name,desc,publishId);
        BookInLib bookin = new BookInLib(isbn,location,state);

        libOper.insertBo(book,bookin);
    }

    //图书删除
    public void deleteBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要删除的图书的id");
        int id = scanner.nextInt();
        scanner.nextLine();
        libOper.deleteBook(id);
    }

    //图书查询
    //目前只支持isbn和书名查询
    public void searchBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查询图书的属性isbn或者name");
        String name = scanner.nextLine();
        System.out.println("请输入对应的属性值");
        String target = scanner.nextLine();
        libOper.searchBook(name, target);
    }

    //图书归还
    public void returnBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要归还的图书id");
        int id = scanner.nextInt();
        scanner.nextLine();
        libOper.bookBack(id);
    }

    //读者管理
    public void manReader(){
        Scanner scanner = new Scanner(System.in);
        int number;  //记录选择的功能
        Boolean isFlag = true;
        while(isFlag){
            //查询全部读者
            libOper.searchAll();
            System.out.println("1:添加读者");
            System.out.println("2:查询读者");
            System.out.println("3:退出");
            System.out.println("请选择相应的功能");
            number = scanner.nextInt();
            scanner.nextLine();
            switch(number){
                case 1:
                    System.out.println("请输入读者的名字");
                    String  name = scanner.nextLine();
                    System.out.println("请输入读者的email");
                    String  email = scanner.nextLine();
                    System.out.println("请输入读者的密码");
                    String  password = scanner.nextLine();
//                    System.out.println("请输入读者的state(默认是unlock)");
//                    String  state = scanner.nextLine();
                    Reader reader = new Reader(name, email, password);
                    libOper.addReader(reader);
                    break;
                case 2:
                    searchReader();
                    break;
                case 3:
                    isFlag = false;
                default:
                    System.out.println("输入的数据非法，请重新输入");
            }

        }

    }

    //读者信息修改
    public void changeReader(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要修改的读者编号");
        int id = scanner.nextInt();
        scanner.nextLine();
        libOper.searchReader(id);
        System.out.println("请输入要修改的读者的属性");
        String name = scanner.nextLine();
        System.out.println("请输入修改后的属性值");
        String target = scanner.nextLine();
        libOper.changeReader(name,target,id);
    }

    //读者信息查询
    public void searchReader(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查找的读者编号");
        int id = scanner.nextInt();
        scanner.nextLine();
        libOper.searchReader(id);
    }

    //读者删除
    public void deleteReader(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要删除的读者编号");
        int id = scanner.nextInt();
        scanner.nextLine();
        libOper.deleteReader(id);
    }

    //借阅图书
    public void borrowBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入借阅图书的读者的id");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("请输入借阅的图书id");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        libOper.borrowBook(id,bookId);
    }

    //展示购物车
    public void showCart(){
        libOper.showCart();
    }

}
