package com.ead.authuser.dto;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // tudo que for nulo não sera exibido!
public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;

	public interface UserView {
		public static interface RegistrationPost{}
		public static interface UserPut{}
		public static interface PasswordPut{}
		public static interface ImagePut{}
	}
	
	private UUID userId;
	
	@JsonView(UserView.RegistrationPost.class)
	private String userName;
	
	@JsonView(UserView.RegistrationPost.class)
	private String email;
	
	@JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
	private String password;
	
	@JsonView(UserView.PasswordPut.class)
	private String oldPassword;
	
	@JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
	private String fullName;
	
	@JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
	private String phoneNumber;
	
	@JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
	private String cpf;
	
	@JsonView(UserView.ImagePut.class)
	private String imageUrl;
}
