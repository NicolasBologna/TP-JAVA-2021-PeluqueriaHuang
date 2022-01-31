package data;
import entities.*;

import java.sql.*;
import java.util.LinkedList;

public class ServiceData {
  
  public LinkedList<Service> getAll(){
    Statement stmt=null;
    ResultSet rs=null;
    LinkedList<Service> services= new LinkedList<>();
    
    try {
      stmt= DbConnector.getInstancia().getConn().createStatement();
      rs= stmt.executeQuery("select service_id,name,description,price,time from services");
      if(rs!=null) {
        while(rs.next()) {
          Service s=new Service();
          s.setServiceId(rs.getInt("service_id"));
          s.setName(rs.getString("name"));
          s.setDescription(rs.getString("description"));
          s.setPrice(rs.getFloat("price"));
          s.setDuration(rs.getTime("duration"));
          services.add(s);
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
    return services;
  }
  
  public int add(Service service) {
    PreparedStatement stmt= null;
    ResultSet keyResultSet=null;
    try {
      stmt=DbConnector.getInstancia().getConn().
          prepareStatement(
              "insert into services(name, description, price, duration) values(?,?,?,?)",
              PreparedStatement.RETURN_GENERATED_KEYS
              );
      stmt.setString(1, service.getName());
      stmt.setString(2, service.getDescription());
      stmt.setFloat(3, service.getPrice());
      stmt.setTime(4, service.getDuration());
      stmt.executeUpdate();
      
      keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
              service.setServiceId(keyResultSet.getInt(1));
            }
            return service.getServiceId();
            
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
  
  public boolean delete(Service service) {
    PreparedStatement stmt= null;
    try {
      stmt=DbConnector.getInstancia().getConn().
          prepareStatement(
              "delete from services where id=?");
      stmt.setInt(1, service.getServiceId());
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
  
  public boolean update(Service service) {
    PreparedStatement stmt= null;
    try {
      stmt=DbConnector.getInstancia().getConn().
          prepareStatement(
              "UPDATE services SET name = ?, description = ?, price = ?, duration = ? WHERE service_id = ?",
              PreparedStatement.RETURN_GENERATED_KEYS
              );
      stmt.setString(1, service.getName());
      stmt.setString(2, service.getDescription());
      stmt.setFloat(3, service.getPrice());
      stmt.setTime(4, service.getDuration());
      
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