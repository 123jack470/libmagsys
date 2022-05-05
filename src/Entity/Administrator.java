package Entity;

/**
 * 数据库表中administrator对应的图书管理员实体，只有一个
 * @author jack li
 * @create 2021-03-13 10:51
 */
public class Administrator {
    private long  id;//编号
    private String name;//姓名
    private String password;//密码

    //空参构造器
    public Administrator(){

    }
    public Administrator(long id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    //提供相应属性的get和set方法

    public long getId() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //重写tostring方法

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
