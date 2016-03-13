package com.mobilecorridor.authentication;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mobilecorridor.authentication.user.CreateUserResponse;
import com.mobilecorridor.authentication.user.IUserManager;

@Path("/authentication")
public class AuthenticationWebService {

	IUserManager iManager;

	public AuthenticationWebService(IUserManager manager) {
		this.iManager = manager;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CreateUserResult createUser(String username, String password, String email) {

		byte[] salt = Passwords.getNextSalt();
		byte[] hash = Passwords.hash(password.toCharArray(), salt);
		String hashed = new String(hash);

		CreateUserResponse result = iManager.createUser(username, hashed,salt);
		CreateUserResult response = new CreateUserResult();
		response.response = result;
		return response;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public AuthResult login(String username, String password) {

		byte[] salt = iManager.getSalt(username);
		byte[] hash = Passwords.hash(password.toCharArray(), salt);
		String hashed = new String(hash);

		AuthFlag flag = iManager.authenticate(username, hashed);
		AuthResult result = new AuthResult();
		result.flag = flag;

		return result;

	}
}
