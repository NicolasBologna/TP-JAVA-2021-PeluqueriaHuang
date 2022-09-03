package data;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Comment;

public class CommentData {
	
	//TODO
	public LinkedList<Comment> getAllTODO() throws IOException{
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Comment> publications= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select publication_id,barber_id,title,text,date,image from publications");
			if(rs!=null) {
				while(rs.next()) {
					Comment p=new Comment();
					p.setPublicationId(rs.getInt("publication_id"));
					p.setText(rs.getString("text"));
					p.setDate(rs.getString("date"));
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
	
	public Comment getByIdTODO(int id) throws IOException {
		Comment p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select publication_id,barber_id,title,text,date,image from publications where publication_id=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=new Comment();
				p.setPublicationId(rs.getInt("publication_id"));
				p.setText(rs.getString("text"));
				p.setDate(rs.getString("date"));		
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
	
	public LinkedList<Integer> getIdsByPublicationId(int publicationId){
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Integer> comments= new LinkedList<>();
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT comment_id FROM comments WHERE post_id = ?;"
					);
			stmt.setInt(1, publicationId);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					comments.add(rs.getInt("comment_id"));
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
		return comments;
	}
	
	public LinkedList<Comment> getByPublicationId(int publicationId){
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Comment> comments= new LinkedList<>();
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT comment_id, text, date, post_id, client_id FROM comments WHERE post_id = ? ORDER BY date ASC;"
					);
			stmt.setInt(1, publicationId);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Comment c=new Comment();
					c.setCommentId(rs.getInt("comment_id"));
					c.setPublicationId(rs.getInt("post_id"));
					int creatorId = rs.getInt("client_id");
					UserData ud = new UserData();
					c.setCreator(ud.getById(creatorId));
					c.setText(rs.getString("text"));
					c.setDate(rs.getString("date"));
					comments.add(c);
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
		return comments;
	}
	
	
	public int add(Comment comment) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into comments(text,post_id,client_id) values(?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, comment.getText());
			stmt.setInt(2, comment.getPublicationId());
			stmt.setInt(3, comment.getCreator().getUserId());
			//stmt.setString(2, comment.getDate());
			stmt.executeUpdate();
			
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	comment.setPublicationId(keyResultSet.getInt(1));
            }
            return comment.getPublicationId();
            
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
	
	public boolean deleteTODO(Comment publication) {
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
	
	public boolean updateTODO(Comment publication) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE publications SET title = ?,text = ? WHERE publication_id = ?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			//stmt.setString(1, publication.getTitle());
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
