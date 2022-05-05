package Entity;

/**
 * 数据库表中author对应的作者实体
 * @author jack li
 * @create 2021-03-13 10:55
 */
public class Author {
    private int id; //作者编号
    private String name;//作者姓名
    private String description;//描述信息

    //空参构造器
    public Author(){

    }

    public Author(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    //提供相应的get和set方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    //重写tostring方法

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
