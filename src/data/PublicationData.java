package data;
//orig
import entities.*;

import java.sql.*;
import java.util.LinkedList;

public class PublicationData {
	
	public LinkedList<Publication> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Publication> publications= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select publication_id,barber_id,text,image from publications");
			if(rs!=null) {
				while(rs.next()) {
					Publication p=new Publication();
					p.setPublicationId(rs.getInt("publication_id"));
					p.setBarberId(rs.getInt("barber_id"));
					p.setText(rs.getString("text"));
					p.setImage(rs.getString("image"));
					
					publications.add(p);
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
		return publications;
	}
	
	public Publication getById(int id) {
		Publication p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select publication_id,barber_id,text,images,is_enable from publications where publication_id=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=new Publication();
				p.setPublicationId(rs.getInt("publication_id"));
				p.setBarberId(rs.getInt("barber_id"));
				p.setText(rs.getString("text"));
				p.setImage(rs.getString("images"));
				p.setEnable(rs.getBoolean("is_enable"));
				
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
	
	/*public LinkedList<Publication> getByBarberId(int barberId){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Publication> publications= new LinkedList<>();
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select publication_id,barber_id,text,images,is_enable from publications where barber_id=?"
					);
			stmt.setInt(1, barberId);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Publication p=new Publication();
					p.setPublicationId(rs.getInt("publication_id"));
					p.setBarberId(rs.getInt("barber_id"));
					p.setText(rs.getString("text"));
					p.setImage(rs.getString("images"));
					p.setEnable(rs.getBoolean("is_enable"));
					publications.add(p);
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
		return publications;
	}*/
	
	
	public int add(Publication publication) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into publications(barber_id,text,image) values(?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, publication.getBarberId());
			stmt.setString(2, publication.getText());
			stmt.setString(3, publication.getImage());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                publication.setPublicationId(keyResultSet.getInt(1));
            }
            return publication.getPublicationId();
            
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
	
	/*public boolean delete(Local local) {
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
	}*/
	
	
	public boolean update(Publication publication) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE publications SET text = ? WHERE publication_id = ?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, publication.getText());
			stmt.setInt(2, publication.getPublicationId());
			
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
	
	/*public boolean switchPublicationStatus(byte isEnable, int publicationId) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE publications SET is_enable=? WHERE publication_id = ?"
							);
			stmt.setInt(1, isEnable);
			stmt.setInt(2, publicationId);
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
	
	*/
	
}
