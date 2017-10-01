package com.uskey512.testapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uskey512.testapi.model.User;

/**
 * Created by uskey512 on 2017/10/01.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
