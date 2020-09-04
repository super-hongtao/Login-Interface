package com.ftg.loginInterface.customer.pojo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : HongTao
 * @version : v1.0
 * @description TODO
 * @date : 2020/8/23 23:24
 */
@Data
public class UserDTO implements Serializable {
    private String userId;

    private String password;

    private String userName;

    private String email;
}
