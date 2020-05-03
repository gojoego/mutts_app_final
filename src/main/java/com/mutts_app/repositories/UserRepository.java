package com.mutts_app.repositories;


import com.mutts_app.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    public  User findByUserId (long userId);

    public User findByEmail(String email);


}
