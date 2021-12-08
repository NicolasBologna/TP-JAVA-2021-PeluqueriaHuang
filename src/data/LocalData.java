package data;
//orig
import entities.*;

import java.sql.*;
import java.util.LinkedList;

public class LocalData {
	
	public LinkedList<Local> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Local> locals= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select local_id,name,address,coordenates from locals");
			if(rs!=null) {
				while(rs.next()) {
					Local l=new Local();
					l.setLocalId(rs.getInt("local_id"));
					l.setName(rs.getString("name"));
					l.setAddress(rs.getString("address"));
					l.setCoordenates(rs.getString("coordenates"));
					locals.add(l);
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
		return locals;
	}
	
	public Local getById(int id) {
		Local l=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select local_id,name,address,coordenates from locals where local_id=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				l=new Local();
				l.setLocalId(rs.getInt("local_id"));
				l.setName(rs.getString("name"));
				l.setAddress(rs.getString("address"));
				l.setCoordenates(rs.getString("coordenates"));
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
		System.out.println(l);
		return l;
	}
	
	public Local getByAddress(String address) {
		Local l=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select local_id,name,address,coordenates from locals where address=?"
					);
			stmt.setString(1, address);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				l=new Local();
				l.setLocalId(rs.getInt("local_id"));
				l.setName(rs.getString("name"));
				l.setAddress(rs.getString("address"));
				l.setCoordenates(rs.getString("coordenates"));
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
		System.out.println(l);
		return l;
	}
	
	public int add(Local local) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into locals(name, address, coordenates) values(?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, local.getName());
			stmt.setString(2, local.getAddress());
			stmt.setString(3, local.getCoordenates());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                local.setLocalId(keyResultSet.getInt(1));
            }
            return local.getLocalId();
            
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
	
	public boolean delete(Local local) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from locals where id=?");
			stmt.setInt(1, local.getLocalId());
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
		return false;
	}
	
	
	public boolean update(Local local) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE locals SET name = ?, address = ?, coordenates = ? WHERE local_id = ?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, local.getName());
			stmt.setString(2, local.getAddress());
			stmt.setString(3, local.getCoordenates());
			
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