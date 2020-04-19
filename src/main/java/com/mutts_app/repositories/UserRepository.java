package com.mutts_app.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


    public  User findByUserId (long userId);

    public User findByEmail(String email);


}
