package entities;

import java.util.HashMap;

public class User {
	private int userId;
	private String firstName;
	private String lastName;
	private int dni;
	private String phone;
	private String email;
	private String password;
	private boolean isEnable;
	private HashMap<Integer, Role> roles;
	
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
	public HashMap<Integer, Role> getRoles() {
		return roles;
	}
	public void setRoles(HashMap<Integer, Role> roles) {
		this.roles = roles;
	}
	
	public User() {
		this.roles=new HashMap<>();
	}
	
	public void addRol(Role rolToBeAdded) {
		this.roles.put(rolToBeAdded.getId(), rolToBeAdded);
	}
	
	public void removeRol(Role rolToBeRemoved) {
		this.roles.remove(rolToBeRemoved.getId());
	}
	
	public boolean hasRol(Role rolToCheck) {
		return this.roles.containsKey(rolToCheck.getId());
	}
	
	public HashMap<Integer, Role> getAllRoles(){
		return this.roles;
	}
}
