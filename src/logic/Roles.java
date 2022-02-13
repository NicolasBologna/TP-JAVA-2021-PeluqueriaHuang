package logic;

import entities.Role;

import data.RolData;

public class Roles {
	public static Role getRoleByName(String roleName) {
		RolData rd = new RolData();
		return rd.getByDesc(roleName);
	}
}
