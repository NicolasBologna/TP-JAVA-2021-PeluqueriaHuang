package data;
import entities.*;

import java.sql.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedList;

public class ServiceData {
  
  public LinkedList<Service> getAll(){
    Statement stmt=null;
    ResultSet rs=null;
    LinkedList<Service> services= new LinkedList<>();
    
    try {
      stmt= DbConnector.getInstancia().getConn().createStatement();
      rs= stmt.executeQuery("select service_id,name,description,price,duration from services");
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
  
  public Service getById(int id) {
		Service s = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select service_id,name,description,price, duration from services where service_id=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				
				s = new Service();
				s.setServiceId(rs.getInt("service_id"));
				s.setName(rs.getString("name"));
				s.setDescription(rs.getString("description"));
				s.setPrice(rs.getFloat("price"));
				s.setDuration(rs.getTime("duration"));
				
				
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
		System.out.println(s);
		return s;
	}
  
  public LinkedList<Service> getServicesByTurn(int turn_id){
	  
	  LinkedList<Service> services = new LinkedList<>();
	  PreparedStatement stmt=null;
	  ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select s.service_id,name,description,price,duration from services as s inner join turns_services as ts on s.service_id = ts.service_id where ts.turn_id= ?"
					);
			
			stmt.setInt(1, turn_id);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				Service s = new Service();
		
				s.setServiceId(rs.getInt("service_id"));
				s.setName(rs.getString("name"));
				s.setDescription(rs.getString("description"));
				s.setPrice(rs.getFloat("price"));
				s.setDuration(rs.getTime("duration"));
				services.add(s);}
				
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
              "delete from services where service_id=?");
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
      stmt.setInt(5,service.getServiceId() );
      
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
  
  public LocalTime getTotalDuration(String[] servicesId) {
	  
	Service s = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	LocalTime totalDuration = LocalTime.parse("00:00:00");
	
	for(String serviceId:servicesId) {
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select duration from services where service_id=?"
					);
			stmt.setInt(1, Integer.parseInt(serviceId));
			rs=stmt.executeQuery();
			if(rs!=null) {
				
			while(rs.next()) {	
				s = new Service();
				s.setDuration(rs.getTime("duration"));
				
				LocalTime duration = s.getDuration().toLocalTime();
				Duration hours = Duration.ofHours(duration.getHour());
				Duration mins = Duration.ofMinutes(duration.getMinute());
				
				totalDuration = totalDuration.plus(hours);

				totalDuration = totalDuration.plus(mins);
				//totalDuration = totalDuration.plus(Duration.ofHours(duration.to);
				//totalDuration = totalDuration.plus(Duration.ofMinutes(duration.getMinute()));
				//totalDuration.plusHours(duration.getHour()).plusMinutes(duration.getMinute());
				}}
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
	return totalDuration;
  }
  
  public LinkedList<Service> getServicesById(String[] servicesId){
	  PreparedStatement stmt=null;
	  ResultSet rs=null;
	  Service s = null;
	  LinkedList<Service> services = new LinkedList<>();
		
	  for(String serviceId: servicesId) {
			
			
		  try {
				stmt=DbConnector.getInstancia().getConn().prepareStatement(
						"select * from services where service_id=?"
						);
				stmt.setInt(1, Integer.parseInt(serviceId));
				rs=stmt.executeQuery();
				if(rs!=null) {
					
				while(rs.next()) {	
					s = new Service();
					
					s.setServiceId(rs.getInt("service_id"));
					s.setName(rs.getString("name"));
					s.setDescription(rs.getString("description"));
					s.setDuration(rs.getTime("duration"));
					s.setPrice(rs.getFloat("price"));
					
					services.add(s);
					}}
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
	  return services;}
  
}