package Entity;

import com.sun.org.glassfish.gmbal.Description;

/**
 * 创建数据库表中publisher对应的实体
 * @author jack li
 * @create 2021-03-13 10:39
 */
public class Publisher {
    private int id;     //编号
    private String name; //姓名
    private String description;  //描述

    //空参构造器
    public Publisher(){

    }
    //带参构造器
    public Publisher(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    //设置相应属性的构造器方法

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

    //重写Tostring方法

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
