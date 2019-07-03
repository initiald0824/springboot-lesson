package com.spark.springbootlesson.entity;

import com.spark.springbootlesson.validator.FlagValidator;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author initiald0824
 * @date 2019/7/3 14:23
 */
public class DemoEntity implements Serializable {

    @NotBlank
    @Length(min = 2, max = 10)
    private String name;

    @Min(value = 1)
    private int age;

    @NotBlank
    @Email
    private String mail;

    @FlagValidator(values = "1,2,3")
    private String flag;

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
