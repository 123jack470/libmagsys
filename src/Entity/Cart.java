package Entity;


import java.util.Date;

/**
 * 显示读者的借阅车的消息增加了bookname，readerid，readername
 * @author jack li
 * @create 2021-03-13 11:16
 */
public class Cart {
    private long bookid;
    private String bookname;
    private long  readerid;
    private String readername;
    private Date submitime;// 当reader把借阅车中的的书提交借阅时，添加时间

    //空参构造器


    public Cart() {
    }

    public long  getBookid() {
        return bookid;
    }


    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public void setReaderid(int readerid) {
        this.readerid = readerid;
    }

    public String getReadername() {
        return readername;
    }

    public void setReadername(String readername) {
        this.readername = readername;
    }

    public Date getSubmitime() {
        return submitime;
    }

    public void setSubmitime(Date submitime) {
        this.submitime = submitime;
    }

    public void setBookid(long bookid) {
        this.bookid = bookid;
    }

    public long getReaderid() {
        return readerid;
    }

    public void setReaderid(long readerid) {
        this.readerid = readerid;
    }

    @Override
    public String toString() {
        return
                "bookid=" + bookid +
                "\t bookname='" + bookname + '\'' +
                "\t readerid=" + readerid +
                "\t readername='" + readername + '\'' +
                "\t submitime=" + submitime ;
    }
}
