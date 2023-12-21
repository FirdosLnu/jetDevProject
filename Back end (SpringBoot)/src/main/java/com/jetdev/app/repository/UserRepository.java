package com.jetdev.app.repository;

import com.jetdev.app.model.Employee;
import com.jetdev.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Employee,Integer> {

    @Query("SELECT u FROM User u WHERE u.name = :username")
    public User getUserByName(@Param("username") String username);
}
