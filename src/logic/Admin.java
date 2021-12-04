package logic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


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
	
	public static LinkedList<Role> getRolesByIds(String[] rolesToSearch) {
		RolData rd = new RolData();
		LinkedList<Role> roles= rd.getAll();
		roles.removeIf(r -> !Arrays.asList(rolesToSearch).contains(Integer.toString(r.getId())));
		return roles;
		
		
	}
		
	public static boolean switchUserStatus(int id){
		UserData ud = new UserData();
		User user =  ud.getById(id);
		return ud.switchUserStatus((byte)(user.getIsEnable()?0:1), user.getUserId());
	}
}
