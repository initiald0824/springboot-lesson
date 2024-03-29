package com.spark.springbootlesson.entity;


import com.spark.springbootlesson.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author initiald0824
 * @date 2019/6/28 16:53
 */
@Entity
@Table(name = "t_user")
public class UserEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="t_id")
    private Long id;

    @Column(name="t_name")
    private String name;

    @Column(name="t_age")
    private Integer age;

    @Column(name="t_address")
    private String address;

    @Column(name = "t_pwd")
    private String pwd;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
