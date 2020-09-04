package com.ftg.loginInterface.customer.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "account")
@Data
public class Account {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String accountId;
    private String userId;
    private Integer accountAmount;
    private String isDelete;
    private Long createTime;
    private Long updateTime;
}
