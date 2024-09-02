package com.example.vip_rent.service.user;

import com.example.vip_rent.dto.UserRequest;
import com.example.vip_rent.modal.entity.user.User;
import com.example.vip_rent.result.DataResult;
import com.example.vip_rent.result.Result;

import java.util.List;

public interface IUserService {

    DataResult<List<User>> getAllUsers();
    DataResult<User> getUserById(Long userId);
    DataResult<User> findByUsername(String username);
    DataResult<User> findByIdentityNumber(String identityNumber);
    Result saveUser(UserRequest request);
    Result updateUser(Long userId, UserRequest request);
    Result deleteUser(Long userId);


}
