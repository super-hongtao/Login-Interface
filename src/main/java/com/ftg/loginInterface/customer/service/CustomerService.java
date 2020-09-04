package com.ftg.loginInterface.customer.service;

import com.ftg.loginInterface.customer.pojo.model.UserDTO;
import com.ftg.loginInterface.customer.pojo.model.UserVO;
import com.ftg.loginInterface.customer.pojo.User;
import com.ftg.loginInterface.customer.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Service
public class CustomerService {

    @Resource
    UserRepository userRepository;

    /**
     * 用户注册
     */
    public UserDTO register(UserDTO userDTO) throws Exception {

        // 检查用户id是否重复
        if (checkUserIdIsExist(userDTO.getUserId())) {
            throw new Exception("用户已存在");
        }
        try {
            User user = new User();
            user.setUserId(userDTO.getUserId());
            user.setPassword(userDTO.getPassword());
            user.setUserName(userDTO.getUserName());
            user.setEmail(userDTO.getEmail());
            user.setSex("0");
            user.setCreateTime(System.currentTimeMillis());
            user.setWechatId("");
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("注册失败");
        }
        return userDTO;
    }

    /**
     * 检查用户是否存在
     * @param userId    用户id
     * @return  存在返回true
     */
    public boolean checkUserIdIsExist(String userId){
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            return false;
        }
        return true;
    }

    /**
     * 用户登录
     * @param userId        登录id
     * @param password      密码
     * @return  UserVO
     * @throws Exception
     */
    public UserVO login(String userId, String password) throws Exception {
        User user = userRepository.findByUserIdAndPassword(userId,password);
        String token = "";
        // 更新用户token
        if (user != null) {
            token = DigestUtils.md5DigestAsHex((userId+System.currentTimeMillis()).getBytes());
            user.setToken(token);
            userRepository.save(user);
        } else {
            throw new Exception("账号/密码错误");
        }
        UserVO userVO = new UserVO();
        userVO.setToken(token);
        userVO.setUserName(user.getUserName());
        userVO.setSex(user.getSex());
        return userVO;
    }

    /**
     * 用户信息的显示
     * @param token
     * @return User
     */
    public User infoDetail(String token)
    {
        User user = userRepository.findByToken(token);
        return user;
    }

    /**
     * 修改用户信息
     * @param token
     * @param newUserName
     * @param newEmail
     * @param newWechatId
     * @return
     */
    public User changeInfo(String token, String newUserName, String newEmail, String newWechatId) {
        User user = userRepository.findByToken(token);
        user.setUserName(newUserName);
        user.setEmail(newEmail);
        user.setWechatId(newWechatId);
        user.setUpdateTime(System.currentTimeMillis());
        userRepository.save(user);
        return user;
    }

    /**
     * 修改密码
     * @param newPassword
     * @param token
     * @return user
     */
    public User changePassword(String newPassword,String token) {
        User user = userRepository.findByToken(token);
        user.setPassword(newPassword);
        userRepository.save(user);
        return user;
    }
///
}
