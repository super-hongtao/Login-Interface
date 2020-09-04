package com.ftg.loginInterface.customer.controller;

import com.ftg.loginInterface.common.ApiResponse;
import com.ftg.loginInterface.customer.pojo.model.UserDTO;
import com.ftg.loginInterface.customer.pojo.model.UserVO;
import com.ftg.loginInterface.customer.pojo.User;
import com.ftg.loginInterface.customer.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Resource
    CustomerService customerService;

    /**
     * 用户注册
     * @return
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public ApiResponse register(@RequestBody UserDTO userDTO){
        try {
            customerService.register(userDTO);
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
        return ApiResponse.success(userDTO);
    }

    /**
     * 用户登录
     * @param userId        登录id
     * @param password      密码
     * @return  UserVO
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ApiResponse login(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "password") String password
    ){
        UserVO userVO = new UserVO();
        try {
            userVO = customerService.login(userId,password);
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
        return ApiResponse.success(userVO);
    }

    /**
     * 用户信息的显示
     * @param token        token
     * @return       User
     */
    @RequestMapping(value = "infoDetail",method = RequestMethod.POST)
    public ApiResponse infoDetail(
            @RequestParam(value = "token") String token
    ){
        User user = customerService.infoDetail(token);
        return ApiResponse.success(user);
    }

    /**
     * 修改用户信息
     * @param token
     * @param newUserName
     * @param newEmail
     * @param newWechatId
     * @return User
     */
    @RequestMapping(value = "changeInfo",method = RequestMethod.POST)
    public ApiResponse changeInfo(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "newUserName") String newUserName,
            @RequestParam(value = "newEmail") String newEmail,
            @RequestParam(value = "newWechatId") String newWechatId
    ){
        User user = new User();
        user = customerService.changeInfo(token,newUserName,newEmail,newWechatId);
        return ApiResponse.success(user);
    }

    /**
     * 修改密码
     * @param newPassword
     * @param token
     * @return  user
     */
    @RequestMapping(value = "changePassword",method = RequestMethod.POST)
    public ApiResponse changePassword(
            @RequestParam(value = "newPassword") String newPassword,
            @RequestParam(value = "token") String token
    ){
       User user = new User();
       user = customerService.changePassword(newPassword,token);
       return ApiResponse.success(user);
    }
}
