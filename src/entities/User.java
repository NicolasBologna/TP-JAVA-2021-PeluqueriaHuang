package entities;

import java.util.HashMap;

public class User {
	private int user_id;
	private String firstName;
	private String lastName;
	private int dni;
	private String phone;
	private String email;
	private String password;
	private boolean isEnable;
	private HashMap<Integer, Rol> roles;
	
	public int getId() {
		return user_id;
	}
	public void setId(int id) {
		this.user_id = id;
	}
	public int getDocument() {
		return dni;
	}
	public void setDocument(int dni) {
		this.dni = dni;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String name) {
		this.firstName = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String last_name) {
		this.lastName = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isEnable() {
		return isEnable;
	}
	public void setEnable(boolean enable) {
		this.isEnable = enable;
	}
	
	public User() {
		this.roles=new HashMap<>();
	}
	
	public void addRol(Rol rolToBeAdded) {
		this.roles.put(rolToBeAdded.getId(), rolToBeAdded);
	}
	
	public void removeRol(Rol rolToBeRemoved) {
		this.roles.remove(rolToBeRemoved.getId());
	}
	
	public boolean hasRol(Rol rolToCheck) {
		return this.roles.containsKey(rolToCheck.getId());
	}
	
	public HashMap<Integer, Rol> getAllRoles(){
		return this.roles;
	}
	
	@Override
	public String toString() {
		return "\nPersona [user_id =" + user_id  + ", dni=" + dni + ", first_name=" + firstName + ", last_name=" + lastName
				+ ", email=" + email + ", phone=" + phone + ", is_enable=" + isEnable + ", roles=" + roles + "]";
	}
	
	

}
