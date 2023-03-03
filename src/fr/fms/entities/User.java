package fr.fms.entities;

import java.util.ArrayList;
import java.util.List;

public class User {

	private long id;
	private String login;
	private String password;
	private List<Customer> customers;

	public User(String login, String password) {

		this.login = login;
		this.password = password;
	}

	public User(long id, String login, String password, List<Customer> customers) {

		this.id = id;
		this.login = login;
		this.password = password;
		this.customers = new ArrayList<>();
	}

	public User(long id, String login, String password) {

		this.id = id;
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "User " + id + " [Login : " + login + ",  Password : " + password + "]";
	}

}
