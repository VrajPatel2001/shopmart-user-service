package com.shopmart.userservice.source;

import com.shopmart.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
    public User findByPhone(String phone);
}
