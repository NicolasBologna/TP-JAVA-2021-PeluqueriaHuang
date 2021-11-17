package entities;

import java.util.HashMap;
import java.util.LinkedList;

public class User {
	private int userId;
	private String firstName;
	private String lastName;
	private int dni;
	private String phone;
	private String email;
	private String password;
	private boolean isEnable;
	private LinkedList<Role> roles;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}
	public LinkedList<Role> getRoles() {
		return roles;
	}
	public void setRoles(LinkedList<Role> roles) {
		this.roles = roles;
	}
	
	public User() {
		this.roles=new LinkedList<Role>();
	}
	
	public void addRol(Role rolToBeAdded) {
		this.roles.add(rolToBeAdded);
	}
	
	public void removeRol(Role rolToBeRemoved) {
		this.roles.remove(rolToBeRemoved.getId());
	}
	
	public boolean hasRol(Role rolToCheck) {
		
		return this.roles.stream().anyMatch(r -> rolToCheck.getId() == r.getId());
	}
	
	public LinkedList<Role> getAllRoles(){
		return this.roles;
	}
}
