package Entity;

import java.util.ArrayList;

/**
 * 数据库表中librarian对应的读者实体
 * @author jack li
 * @create 2021-03-13 10:58
 */
public class Reader {
    private long  id;
    private String  name;
    private String password; //密码
    private String email;//邮箱
    private String state;// 表示账号状态 ，“blockade”为被锁定，且不可登入；“unlock”为正常状
    private ArrayList<Cart> carlist; //借阅车的状态
    private ArrayList<BorrowItem> borrpwHistory; //当前借阅情况

    //空参构造器

    public Reader() {
        carlist = new ArrayList<Cart>();
        borrpwHistory = new ArrayList<BorrowItem>();
    }

    public Reader( String name, String password, String email) {
        //this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.state = "unlock";
        carlist = new ArrayList<Cart>();
        borrpwHistory = new ArrayList<BorrowItem>();
    }

    //设置相应的属性getset方法

    public long  getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<Cart> getCarlist() {
        return carlist;
    }

    public void setCarlist(ArrayList<Cart> carlist) {
        this.carlist = carlist;
    }

    public ArrayList<BorrowItem> getBorrpwHistory() {
        return borrpwHistory;
    }

    public void setBorrpwHistory(ArrayList<BorrowItem> borrpwHistory) {
        this.borrpwHistory = borrpwHistory;
    }

    //重写tostring方法

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", state='" + state + '\'' +
                ", carlist=" + carlist +
                ", borrpwHistory=" + borrpwHistory +
                '}';
    }
}
