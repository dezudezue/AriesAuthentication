package com.mobilecorridor.authentication.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/***
 * basic user authentication object.
 * 
 * @author sleekwalker
 *
 */
@Entity
@Table(name = "Users")
public class User {

	/**
	 * user password
	 */

	private String password;

	private byte[] salt;

	@Id
	private String username;

	public User() {

	}

	public User(String password, byte[] salt, String username) {
		super();
		this.password = password;
		this.salt = salt;
		this.username = username;
	}

	public static void main(String... args) {
		User user = new User();
		user.setPassword("rubbish");
		user.setSalt(new byte[2]);
		user.setUsername("test");

		SessionFactory sessionFactory=null;
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures
				.build();
		try {
			sessionFactory = new MetadataSources(registry)
					.buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save( user );
		session.getTransaction().commit();
		session.close();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
