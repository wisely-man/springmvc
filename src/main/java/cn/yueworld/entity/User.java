package cn.yueworld.entity;

import cn.yueworld.core.annotation.validtor.Phone;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by jiangyi on 2017/4/20.
 */
public class User implements Serializable{

    private static final long serialVersionUID = 593303383832885052L;

    @NotBlank
    @Size(min=3, max=20)
    private String name;

    @NotNull
    @Min(value = 18)
    @Max(value = 100)
    private int age;

    @NotBlank
    @Phone(message = "{user.phone.error}")
    private String phone;

    @NotBlank
    @Email
    @Size(max=50)
    private String email;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
