package com.example.demo.repository;

import com.example.demo.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

    @Query("SELECT d FROM UserDetail d JOIN FETCH d.user")
    List<UserDetail> findAllWithUser();

}
