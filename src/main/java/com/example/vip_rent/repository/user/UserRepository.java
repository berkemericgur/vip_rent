package com.example.vip_rent.repository.user;

import com.example.vip_rent.modal.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUserName(String userName);
    Optional<User> findUserByUserName(User user);
    Optional<User> findUserByIdentityNumber(String identityNumber);

}
