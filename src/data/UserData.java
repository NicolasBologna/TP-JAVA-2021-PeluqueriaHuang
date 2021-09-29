package data;
//orig
import entities.*;

import java.sql.*;
import java.util.LinkedList;

public class UserData {
	
	public LinkedList<User> getAll(){
		DataRol dr=new DataRol();
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<User> pers= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select user_id,first_name,last_name,dni,email,phone,is_enable from users");
			if(rs!=null) {
				while(rs.next()) {
					User p=new User();
					p.setId(rs.getInt("user_id"));
					p.setFirstName(rs.getString("first_name"));
					p.setLastName(rs.getString("last_name"));
					p.setDocument(rs.getInt("dni"));
					p.setEmail(rs.getString("email"));
					p.setPhone(rs.getString("phone"));
					
					p.setEnable(rs.getBoolean("is_enable"));
					
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
		DataRol dr=new DataRol();
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
				p.setId(rs.getInt("user_id"));
				p.setFirstName(rs.getString("first_name"));
				p.setLastName(rs.getString("last_name"));
				p.setDocument(rs.getInt("dni"));
				p.setEmail(rs.getString("email"));
				p.setPhone(rs.getString("phone"));
				
				p.setEnable(rs.getBoolean("is_enable"));
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
		DataRol dr=new DataRol();
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
				p.setId(rs.getInt("user_id"));
				p.setFirstName(rs.getString("first_name"));
				p.setLastName(rs.getString("last_name"));
				p.setDocument(rs.getInt("dni"));
				p.setEmail(rs.getString("email"));
				p.setPhone(rs.getString("phone"));
				
				p.setEnable(rs.getBoolean("is_enable"));
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
	
	public int add(User p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into users(first_name, last_name, dni, phone, email, password) values(?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, p.getFirstName());
			stmt.setString(2, p.getLastName());
			stmt.setInt(3, p.getDocument());
			stmt.setString(4, p.getPhone());
			stmt.setString(5, p.getEmail());
			stmt.setString(6, p.getPassword());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setId(keyResultSet.getInt(1));
                
                DataRol dr = new DataRol();
                dr.setRolesDePersona(p);
            }
            return p.getId();


			
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

	
}
