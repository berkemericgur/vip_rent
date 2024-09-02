package com.example.vip_rent.controller.user;

import com.example.vip_rent.dto.UserRequest;
import com.example.vip_rent.modal.entity.user.User;
import com.example.vip_rent.result.DataResult;
import com.example.vip_rent.result.Result;
import com.example.vip_rent.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping(value = "/getAll")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public DataResult<List<User>> getAll(){

        return this.userService.getAllUsers();
    }

    @GetMapping(value = "/getById")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public DataResult<User> getById(@RequestParam Long userId){

        return this.userService.getUserById(userId);
    }

    @PostMapping(value = "/save")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Result save(@RequestBody UserRequest request){

        return this.userService.saveUser(request);
    }

    @PutMapping(value = "/update")
    @ResponseStatus(value = HttpStatus.OK)
    public Result update(@RequestParam Long userId, @RequestBody UserRequest request){

        return this.userService.updateUser(userId, request);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Result delete(@PathVariable("id") Long userId){

        return this.userService.deleteUser(userId);
    }
}
