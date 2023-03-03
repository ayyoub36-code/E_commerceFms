package fr.fms.entities;

public class Customer {

	private long id;
	private String lastName;
	private String firstName;
	private String email;
	private String address;
	private long idUser;

	public Customer(long id, String lastName, String firstName, String email, String address, long idUser) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.address = address;
		this.idUser = idUser;
	}

	public Customer(String lastName, String firstName, String email, String address, long idUser) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.address = address;
		this.idUser = idUser;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Customer " + id + "[" + lastName + " " + firstName + " " + email + " " + address + ", idUser=" + idUser
				+ "]";
	}

}
