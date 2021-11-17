package logic;

import java.util.LinkedList;

import data.RolData;
import data.UserData;
import entities.Role;
import entities.User;

public class Admin {
	public static LinkedList<User> getAllUsers(){
		UserData ud = new UserData();
		return ud.getAll();
	}
	
	public static LinkedList<Role> getAllRoles(){
		RolData rd = new RolData();
		return rd.getAll();
	}
}
