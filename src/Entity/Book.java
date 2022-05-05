package Entity;

import java.math.BigDecimal;

/**
 *
 * 数据库中book图书对应的实体
 * @author jack li
 * @create 2021-03-13 12:25
 */
public class Book {
    private String isbn;//图书号
    private BigDecimal privce;//图书价格
    private String name;//图书名
    private String description;//描述
    private long pubId;  //出版商编号

    public Book() {
    }

    public Book(String isbn, BigDecimal privce, String name, String description, long pubId) {
        this.isbn = isbn;
        this.privce = privce;
        this.name = name;
        this.description = description;
        this.pubId = pubId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getPrivce() {
        return privce;
    }

    public void setPrivce(BigDecimal privce) {
        this.privce = privce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPubId() {
        return pubId;
    }

    public void setPubId(long pubId) {
        this.pubId = pubId;
    }

    @Override
    public String toString() {
        return "BookDAO{" +
                "isbn='" + isbn + '\'' +
                ", privce=" + privce +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pubId=" + pubId +
                '}';
    }
}
