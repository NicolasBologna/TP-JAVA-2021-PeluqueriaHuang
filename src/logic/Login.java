package logic;

import java.util.LinkedList;

import data.*;
import entities.*;
import utils.Encrypters;


public class Login {
	private UserData dp;
	
	public Login() {
		dp=new UserData();
	}
	
	public User validate(User p) {
		p.setPassword(Encrypters.encriptadoMD5(p.getPassword()));
		return dp.getByUser(p);
	}

	public LinkedList<User> getAll(){
		return dp.getAll();
	}

	/*public User getByDocumento(User per) {
		return dp.getByDocumento(per);
		
	}*/
	
        
}
