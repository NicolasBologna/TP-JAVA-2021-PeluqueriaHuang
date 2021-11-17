package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.*;

public class RolData {
	
	public LinkedList<Role> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Role> roles= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from roles");
			if(rs!=null) {
				while(rs.next()) {
					Role r=new Role();
					r.setId(rs.getInt("id"));
					r.setRole(rs.getString("role"));
					roles.add(r);
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
		
		
		return roles;
	}
	
	public Role getById(Role rolToSearch) {
		Role r=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from roles where id=?"
					);
			stmt.setInt(1, rolToSearch.getId());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				r=new Role();
				r.setId(rs.getInt("id"));
				r.setRole(rs.getString("role"));
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
		
		return r;
	}
	
	public Role getByDesc(Role rolToSearch) {
		Role r=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from roles where role=?"
					);
			stmt.setString(1, rolToSearch.getRole());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				r=new Role();
				r.setId(rs.getInt("id"));
				r.setRole(rs.getString("role"));
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
		
		return r;
	}
	
	public void setRoles(User per) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select roles.* "
					+ "from roles "
					+ "inner join role_user "
					+ "on roles.id=role_user.role_id "
					+ "where user_id=?"
					);
			stmt.setInt(1, per.getUserId());
			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Role r=new Role();
					r.setId(rs.getInt("id"));
					r.setRole(rs.getString("role"));
					per.addRol(r);
				}
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
	}
	
	public void add(Role role) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into roles(role) values(?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, role.getRole());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                role.setId(keyResultSet.getInt(1));
            }

			
		} catch (SQLException e) {
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

	}
	
	public void update(Role role) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update roles set role=? where id=?");
			stmt.setString(1, role.getRole());
			stmt.setInt(2, role.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
	public void remove(Role role) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from roles where id=?");
			stmt.setInt(1, role.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
	public void setRolesDePersona(User p) {
		
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into role_user values(?,?);");
			stmt.setInt(1,p.getUserId());
			for(Role r:p.getAllRoles()) {
				stmt.setInt(2, r.getId());
				stmt.executeUpdate();
			}
			
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
}
