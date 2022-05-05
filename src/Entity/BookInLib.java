package Entity;

/**
 * 数据库中再管图书的实体
 * @author jack li
 * @create 2021-03-14 8:29
 */
public class BookInLib {
    private long id; //图书存储id
    private String isbn; //图书编号
    private String location;//图书位置
    private String state;//图书状态

    //空参构造器
    public  BookInLib(){

    }

    public BookInLib(String isbn, String location, String state) {
        this.isbn = isbn;
        this.location = location;
        this.state = state;
    }

    public long  getBook_id() {
        return id;
    }

    public void setBook_id(int book_id) {
        this.id = book_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "BookInLib{" +
                "book_id=" + id +
                ", isbn='" + isbn + '\'' +
                ", location='" + location + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
