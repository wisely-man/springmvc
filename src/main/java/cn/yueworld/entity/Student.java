package cn.yueworld.entity;

import java.io.Serializable;

/**
 * Created by jiangyi on 2017/5/24.
 */
public class Student implements Serializable{

    private static final long serialVersionUID = 4187244360137801223L;

    private Long id;
    private String name;
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
