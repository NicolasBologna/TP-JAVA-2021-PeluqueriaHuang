package data;
//orig
import entities.*;

import java.sql.*;
import java.util.LinkedList;

public class UserData {
	
	public LinkedList<User> getAll(){
		RolData dr=new RolData();
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<User> pers= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select user_id,first_name,last_name,dni,email,phone,is_enable from users");
			if(rs!=null) {
				while(rs.next()) {
					User p=new User();
					p.setUserId(rs.getInt("user_id"));
					p.setFirstName(rs.getString("first_name"));
					p.setLastName(rs.getString("last_name"));
					p.setDni(rs.getInt("dni"));
					p.setEmail(rs.getString("email"));
					p.setPhone(rs.getString("phone"));
					
					p.setIsEnable(rs.getBoolean("is_enable"));
					
					dr.setRoles(p);
					
					pers.add(p);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return pers;
	}
	
	public User getByUser(User per) {
		RolData dr=new RolData();
		User p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select user_id,first_name,last_name,dni,email,phone,is_enable from users where email=? and password=?"
					);
			stmt.setString(1, per.getEmail());
			stmt.setString(2, per.getPassword());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=new User();
				p.setUserId(rs.getInt("user_id"));
				p.setFirstName(rs.getString("first_name"));
				p.setLastName(rs.getString("last_name"));
				p.setDni(rs.getInt("dni"));
				p.setEmail(rs.getString("email"));
				p.setPhone(rs.getString("phone"));
				
				p.setIsEnable(rs.getBoolean("is_enable"));
				//
				dr.setRoles(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(p);
		return p;
	}
	
	
	public User getByEmail(String email) {
		RolData dr=new RolData();
		User p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select user_id,first_name,last_name,dni,email,phone,is_enable from users where email=?"
					);
			stmt.setString(1, email);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=new User();
				p.setUserId(rs.getInt("user_id"));
				p.setFirstName(rs.getString("first_name"));
				p.setLastName(rs.getString("last_name"));
				p.setDni(rs.getInt("dni"));
				p.setEmail(rs.getString("email"));
				p.setPhone(rs.getString("phone"));
				
				p.setIsEnable(rs.getBoolean("is_enable"));
				//
				dr.setRoles(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(p);
		return p;
	}
	
	public User getById(int id) {
		RolData dr=new RolData();
		User p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select user_id,first_name,last_name,dni,email,phone,is_enable from users where user_id=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=new User();
				p.setUserId(rs.getInt("user_id"));
				p.setFirstName(rs.getString("first_name"));
				p.setLastName(rs.getString("last_name"));
				p.setDni(rs.getInt("dni"));
				p.setEmail(rs.getString("email"));
				p.setPhone(rs.getString("phone"));
				
				p.setIsEnable(rs.getBoolean("is_enable"));
				//
				dr.setRoles(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(p);
		return p;
	}
	
	/*public User getByDocumento(User per) {
		DataRol dr=new DataRol();
		User p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre,apellido,tipo_doc,nro_doc,email,tel,habilitado from persona where tipo_doc=? and nro_doc=?"
					);
			stmt.setString(1, per.getDocumento().getTipo());
			stmt.setString(2, per.getDocumento().getNro());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=new User();
				p.setDocumento(new Documento());
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.getDocumento().setTipo(rs.getString("tipo_doc"));
				p.getDocumento().setNro(rs.getString("nro_doc"));
				p.setEmail(rs.getString("email"));
				p.setTel(rs.getString("tel"));
				p.setHabilitado(rs.getBoolean("habilitado"));
				//
				dr.setRoles(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return p;
	}*/
	
	
	
	public int add(User user) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into users(first_name, last_name, dni, phone, email, password) values(?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setInt(3, user.getDni());
			stmt.setString(4, user.getPhone());
			stmt.setString(5, user.getEmail());
			stmt.setString(6, user.getPassword());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                user.setUserId(keyResultSet.getInt(1));
                
                RolData dr = new RolData();
                dr.setRolesDePersona(user);
            }
            return user.getUserId();


			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }            
		}
		//if error return -1?
		return -1;
    }
	
	public boolean switchUserStatus(byte isEnable, int userId) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE users SET is_enable=? WHERE user_id = ?"
							);
			stmt.setInt(1, isEnable);
			stmt.setInt(2, userId);
			stmt.executeUpdate();

            return stmt.executeUpdate() > 0;
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }            
		}
		//if error return false
		return false;	
	}
	
	
	public boolean update(User user) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE users SET first_name = ?, last_name = ?, dni = ?, phone = ?, email = ?, is_enable = ? WHERE user_id = ?"
							);
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setInt(3, user.getDni());
			stmt.setString(4, user.getPhone());
			stmt.setString(5, user.getEmail());
			stmt.setBoolean(6, user.getIsEnable());
			stmt.setInt(7, user.getUserId());
			stmt.executeUpdate();
			
			if(stmt.executeUpdate()>0){
				 RolData dr = new RolData();
				 dr.removeRolesUser(user);
	             dr.setRolesDePersona(user);
               
            }
			return stmt.executeUpdate() > 0;
			
            
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }            
		}
		//if error return false
		return false;
    }
	
	
	
}
