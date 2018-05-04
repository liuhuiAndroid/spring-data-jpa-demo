package com.example.demo.dto;

import org.hibernate.annotations.NamedQuery;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity             // 标识这个实体类是一个JPA实体，告诉JPA在程序运行的时候记得生成这个实体类所对应的表~！
@Data               // 提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
@AllArgsConstructor // 为类提供一个无参的构造方法
@NoArgsConstructor  // 为类提供一个无参的构造方法
@NamedQuery(name="Customer.findByFirstName",query = "select c from Customer c where c.firstName = ?1")
public class Customer {

    @Id // 把这个类里面所在的变量设置为主键Id。
    @GeneratedValue(strategy=GenerationType.AUTO) // 设置主键的生成策略
    private Long id;
    private String firstName;
    private String lastName;

    //一对多，一个客户对应多个订单，关联的字段是订单里的cId字段
    @OneToMany
    @JoinColumn(name = "cId")
    private List<MyOrder> myOrders;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}
