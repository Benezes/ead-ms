package com.ead.authuser.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ead.authuser.models.UserModel;
import com.ead.authuser.repository.UserRepository;
import com.ead.authuser.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public List<UserModel> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<UserModel> findById(UUID uuid) {
		return userRepository.findById(uuid);
	}

	@Override
	public void delete(UserModel userModel) {
		userRepository.delete(userModel);
	}

	@Override
	public void save(UserModel userModel) {
		userRepository.save(userModel);
	}

	@Override
	public boolean existsByUserName(String userName) {
		return userRepository.existsByUserName(userName);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
}
