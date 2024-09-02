package com.example.vip_rent.service.user;

import com.example.vip_rent.dto.UserRequest;
import com.example.vip_rent.modal.entity.user.User;
import com.example.vip_rent.repository.user.UserRepository;
import com.example.vip_rent.result.DataResult;
import com.example.vip_rent.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;


    @Override
    public DataResult<List<User>> getAllUsers() {

        List<User> users = this.userRepository.findAll();
        if (users.isEmpty())
            return new DataResult<>(null,
                    Result.showMessage(Result.SERVER_ERROR, "User not found"));
        return new DataResult<>(users,
                Result.showMessage(Result.SUCCESS, "Users listed succesfully"));
    }

    @Override
    public DataResult<User> getUserById(Long userId) {

        Optional<User> user = this.userRepository.findById(userId);

        if (user.isEmpty())
            return new DataResult<>(null,
                    Result.showMessage(Result.SERVER_ERROR, "User not found"));
        return new DataResult<>(user.get(),
                Result.showMessage(Result.SUCCESS, "User listed succesfully."));
    }

    @Override
    public DataResult<User> findByUsername(String username){

        Optional<User> user = this.userRepository.findUserByUserName(username);

        return user.map(value ->
                new DataResult<>(value, Result.showMessage(Result.SUCCESS, "User found successfully")))
                .orElseGet(() ->
                        new DataResult<>(null, Result.showMessage(Result.SERVER_ERROR, "User not found")));

    }

    @Override
    public DataResult<User> findByIdentityNumber(String identityNumber){

        Optional<User> user = this.userRepository.findUserByIdentityNumber(identityNumber);

        //(user.isEmpty) ifadesinin lambda kullanımı
        return user.map(value ->
                new DataResult<>(value, Result.showMessage(Result.SUCCESS, "User found successfully")))
                .orElseGet(() ->
                        new DataResult<>(null, Result.showMessage(Result.SERVER_ERROR, "User not found")));
    }

    @Override
    public Result saveUser(UserRequest request) {

        if (request.getUsername() == null || request.getUsername().isEmpty())
            return null;
        if (request.getPassword() == null || request.getPassword().isEmpty())
            return null;
        if (request.getEmail() == null || request.getEmail().isEmpty())
            return null;
        if (request.getIdentityNumber() == null || request.getIdentityNumber().isEmpty())
            return null;
        if (request.getFirstName() == null || request.getFirstName().isEmpty())
            return null;
        if (request.getLastName() == null || request.getLastName().isEmpty())
            return null;
        if (request.getPhoneNumber() == null || request.getPhoneNumber().isEmpty())
            return null;

        User createdUser = User.builder()
                .userName(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .identityNumber(request.getIdentityNumber())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .build();

        try {
            this.userRepository.save(createdUser);
        } catch (Exception e) {
            return null;
        }
        return Result.showMessage(Result.SUCCESS, "User saved successfully");
    }

    @Override
    public Result updateUser(Long userId, UserRequest request) {

        Optional<User> isExistUser = this.userRepository.findById(userId);

        if (isExistUser.isEmpty())
            return null;

        User updatedUser = isExistUser.get();

        updatedUser.setUserName(request.getUsername());
        updatedUser.setPassword(request.getPassword());
        updatedUser.setEmail(request.getEmail());
        updatedUser.setPhoneNumber(request.getPhoneNumber());
        updatedUser.setFirstName(request.getFirstName());
        updatedUser.setLastName(request.getLastName());
        updatedUser.setIdentityNumber(request.getIdentityNumber());

        try {
            this.userRepository.save(updatedUser);
        } catch (Exception e) {
            return null;
        }
        return Result.showMessage(Result.SUCCESS, "User updated successfully");
    }

    @Override
    public Result deleteUser(Long userId) {

        Boolean existById = this.userRepository.existsById(userId);

        if (!existById)
            return Result.showMessage(Result.SERVER_ERROR, "User not found");

        try {

            userRepository.deleteById(userId);
        } catch (Exception e) {

            return Result.showMessage(Result.SERVER_ERROR, "User delete failed");
        }

        return Result.showMessage(Result.SUCCESS, "User deleted succesfully");
    }
}
