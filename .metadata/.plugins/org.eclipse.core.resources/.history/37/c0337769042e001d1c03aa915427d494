package com.ead.authuser.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ead.authuser.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

	boolean existsByUserName(String userName);

	boolean existsByEmail(String email);

}
