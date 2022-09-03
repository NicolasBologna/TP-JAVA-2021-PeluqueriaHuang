package data;

import entities.*;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;

public class PublicationData {
	
	public LinkedList<Publication> getAll() throws IOException{
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Publication> publications= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select publication_id,barber_id,title,text,date,image from publications");
			if(rs!=null) {
				while(rs.next()) {
					Publication p=new Publication();
					p.setPublicationId(rs.getInt("publication_id"));
					p.setBarberId(rs.getInt("barber_id"));
					p.setTitle(rs.getString("title"));
					p.setText(rs.getString("text"));
					p.setDate(rs.getString("date"));
					p.setImage(rs.getBlob("image").getBinaryStream());
					p.setBase64Image(rs.getBlob("image").getBinaryStream());
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
	
	public Publication getById(int id) throws IOException {
		Publication p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select publication_id,barber_id,title,text,date,image from publications where publication_id=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=new Publication();
				p.setPublicationId(rs.getInt("publication_id"));
				p.setBarberId(rs.getInt("barber_id"));
				p.setTitle(rs.getString("title"));
				p.setText(rs.getString("text"));
				p.setDate(rs.getString("date"));
				p.setImage(rs.getBlob("image").getBinaryStream());
				p.setBase64Image(rs.getBinaryStream("image"));
				
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
	
	public LinkedList<Publication> getByBarberId(int barberId){
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Publication> publications= new LinkedList<>();
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select publication_id,barber_id,title,text,date,image from publications where barber_id=?"
					);
			stmt.setInt(1, barberId);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Publication p=new Publication();
					p.setPublicationId(rs.getInt("publication_id"));
					p.setBarberId(rs.getInt("barber_id"));
					p.setTitle(rs.getString("title"));
					p.setText(rs.getString("text"));
					p.setDate(rs.getString("date"));
					p.setImage(rs.getBinaryStream("image"));
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
	
	
	public int add(Publication publication) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
            // "SET GLOBAL max_allowed_packet=104857600;";  //Para que acepte archivos de mas de un mega hay que modificar la variable en la db
            
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into publications(barber_id,title,text,image) values(?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, publication.getBarberId());
			stmt.setString(2, publication.getTitle());
			stmt.setString(3, publication.getText());
			stmt.setBlob(4, publication.getImage());
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
	
	public boolean delete(Publication publication) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from publications where publication_id=?");
			stmt.setInt(1, publication.getPublicationId());
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
	
	public boolean update(Publication publication) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE publications SET title = ?,text = ? WHERE publication_id = ?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, publication.getTitle());
			stmt.setString(2, publication.getText());
			stmt.setInt(3, publication.getPublicationId());
			
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
