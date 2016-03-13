package com.mobilecorridor.authentication.user;

import com.mobilecorridor.authentication.AuthFlag;

public interface IUserManager {

	public CreateUserResponse createUser(String usernmae, String passwordHash,byte[]salt);

	public byte[]  getSalt(String username);

	public AuthFlag authenticate(String username, String hashed);
}
