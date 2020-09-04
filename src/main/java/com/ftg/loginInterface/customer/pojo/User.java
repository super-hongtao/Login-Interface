package com.ftg.loginInterface.customer.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户id
     */
    private String userId;

    /**
     * 密码
     */
    private String password;
    /**
     * token
     */
    private String token;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 性别（0保密，1男，2女）
     */
    private String sex;
    private String email;
    private String wechatId;
    private String status;

    /**
     * 创建时间（时间戳13位）
     */
    private Long createTime;
    private Long updateTime;
}
