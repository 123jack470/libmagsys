package Entity;


import java.util.Date;

/**
 * 记录图书的借阅情况，是数据库中borrow-item的实体
 * @author jack li
 * @create 2021-03-13 12:12
 */
public class BorrowItem {
     private long  id;
     private long readId;//读者的id
    private long  bookId;//图书id
    private long  blibId;//批准借阅的图书管理员的id
    private long  rlibId;//还书审批的管理员的id
    private String name;
    private Date bdate;//借阅时间
    private Date rdate;//还书时间

    //空参构造器
    public BorrowItem() {
    }

    public BorrowItem(int id, int readId, int bookId, int blibId, int rlibId, Date bdate, Date rdate) {
        this.id = id;
        this.readId = readId;
        this.bookId = bookId;
        this.blibId = blibId;
        this.rlibId = rlibId;
        this.bdate = bdate;
        this.rdate = rdate;
    }

    //属性的get和set方法


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long  getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long  getReadId() {
        return readId;
    }

    public void setReadId(int readId) {
        this.readId = readId;
    }

    public long  getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public long  getBlibId() {
        return blibId;
    }

    public void setBlibId(int blibId) {
        this.blibId = blibId;
    }

    public long  getRlibId() {
        return rlibId;
    }

    public void setRlibId(int rlibId) {
        this.rlibId = rlibId;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    //重写tostring方法

    @Override
    public String toString() {
        return "BorrowItem{" +
                "id=" + id +
                ", readId=" + readId +
                ", bookId=" + name +
                ", blibId=" + blibId +

                ", bdate=" + bdate +
                ", rdate=" + rdate +
                '}';
    }
}
