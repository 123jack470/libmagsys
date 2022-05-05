package Entity;

/**
 * 数据库表中librarian对应的管理员实体
 * @author jack li
 * @create 2021-03-13 10:44
 */
public class Librarian {
    private long  id;     //编号
    private String name;//管理员姓名
    private String password;//管理员密码
    private String state;//

    //空参构造器
    public Librarian() {
    }
    //带参构造器
    public Librarian( String name, String password, String state){
        this.name = name;
        this.password = password;
        this.state = state;
    }

    //提供属性的get和set方法

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    //重写tostring方法

    @Override
    public String toString() {
        return "Librarian:\'" +
                "id=" + id +
                ", \t\t name='" + name + '\'' +
                ",\t\t password='" + password + '\'' +
                ", \t\t state='" + state + '\'';
    }
}
