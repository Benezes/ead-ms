package com.ead.authuser.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ead.authuser.validation.UsernameConstraint;

public class UsernameConstraintImpl implements ConstraintValidator<UsernameConstraint, String> {

	private static final String BLANK_SPACE = " ";

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if (username == null || username.trim().isEmpty() || username.contains(BLANK_SPACE))
			return false;
		return true;
	}

}
