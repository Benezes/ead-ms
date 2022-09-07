package com.ead.authuser.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", maxAge = 4000)
@RequiredArgsConstructor
public class UserController {

	private static final String USER_NOT_FOUND = "User not found";
	private static final String ZONE_ID = "UTC";

	private final UserService userService;

	@GetMapping
	public ResponseEntity<List<UserModel>> getAllUsers() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<Object> getOneUser(@PathVariable(value = "userId") UUID uuid) {
		Optional<UserModel> user = userService.findById(uuid);
		if (user.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);

		return ResponseEntity.ok(user.get());
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable(value = "userId") UUID uuid) {
		Optional<UserModel> user = userService.findById(uuid);
		if (user.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);

		userService.delete(user.get());
		return ResponseEntity.ok("USER DELETED SUCCESSFULLY");
	}

	@PutMapping("/{userId}")
	public ResponseEntity<Object> updateUser(@PathVariable(value = "userId") UUID uuid,
			@RequestBody @JsonView(UserDto.UserView.UserPut.class) UserDto userDto) {

		Optional<UserModel> user = userService.findById(uuid);
		if (user.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);

		var userModel = user.get();
		userModel.setFullName(userDto.getFullName());
		userModel.setPhoneNumber(userDto.getPhoneNumber());
		userModel.setCpf(userDto.getCpf());
		userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of(ZONE_ID)));
		userService.save(userModel);

		return ResponseEntity.ok(userModel);
	}

	@PutMapping("/{userId}/password")
	public ResponseEntity<Object> updatePassword(@PathVariable(value = "userId") UUID uuid,
			@RequestBody @JsonView(UserDto.UserView.PasswordPut.class) UserDto userDto) {

		Optional<UserModel> user = userService.findById(uuid);
		if (user.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);

		if (user.get().getPassword().equals(userDto.getOldPassword()))
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Mismatched old password");

		var userModel = user.get();
		userModel.setPassword(userDto.getOldPassword());
		userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of(ZONE_ID)));
		userService.save(userModel);

		return ResponseEntity.ok(userModel);
	}

	@PutMapping("/{userId}/image")
	public ResponseEntity<Object> updateImage(@PathVariable(value = "userId") UUID uuid,
			@RequestBody @JsonView(UserDto.UserView.ImagePut.class) UserDto userDto) {

		Optional<UserModel> user = userService.findById(uuid);
		if (user.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);

		var userModel = user.get();
		userModel.setImageUrl(userDto.getImageUrl());
		userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of(ZONE_ID)));
		userService.save(userModel);

		return ResponseEntity.ok(userModel);
	}
}
