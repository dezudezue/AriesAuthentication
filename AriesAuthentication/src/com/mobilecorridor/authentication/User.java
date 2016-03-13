package com.mobilecorridor.authentication;

/***
 * basic user authentication object.
 * 
 * @author sleekwalker
 *
 */
public class User {

	/**
	 * user password
	 */
	private String password;
	private String salt;
	private String username;

	public User(String password, String salt, String username) {
		super();
		this.password = password;
		this.salt = salt;
		this.username = username;
	}

}
