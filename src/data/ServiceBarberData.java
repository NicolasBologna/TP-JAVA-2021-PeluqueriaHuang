package data;
//orig
import entities.*;

import java.sql.*;
import java.util.LinkedList;

public class ServiceBarberData {
	
	/*public LinkedList<ServiceBarber> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<ServiceBarber> servicesBarbers= new LinkedList<>();
		
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
	}*/
	
	public ServiceBarber getById(int id) {
		ServiceBarber sb=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select service_barber_id,barber_id,service_id from service_barber where service_barber_id=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				sb=new ServiceBarber();
				sb.setServiceBarberId(rs.getInt("service_barber_id"));
				sb.setBarberId(rs.getInt("barber_id"));
				sb.setServiceId(rs.getInt("service_id"));
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
		System.out.println(sb);
		return sb;
	}
	
	public LinkedList<ServiceBarber> getByBarberId(int barberId){
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<ServiceBarber> serviceBarber= new LinkedList<>();
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select service_barber_id,barber_id,service_id from service_barber where barber_id=?"
					);
			stmt.setInt(1, barberId);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					ServiceBarber sb=new ServiceBarber();
					sb.setServiceBarberId(rs.getInt("service_barber_id"));
					sb.setBarberId(rs.getInt("barber_id"));
					sb.setServiceId(rs.getInt("service_id"));
					serviceBarber.add(sb);
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
		return serviceBarber;
	}
	
	public LinkedList<Integer> getServicesByBarberId(int barberId){
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Integer> serviceBarber= new LinkedList<>();
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select service_id from service_barber where barber_id=?"
					);
			stmt.setInt(1, barberId);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					serviceBarber.add(rs.getInt("service_id"));
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
		return serviceBarber;
	}
	
	public LinkedList<User> getBarberByServices(String[] idServices, int idLocal){
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<User> allBarbers = new LinkedList<>();
		LinkedList<User> barbers = new LinkedList<>();
		
		allBarbers = ScheduleData.getBarbersByLocal(idLocal);
		
		for(User barber: allBarbers) {
			
			Boolean band = false;
			
			for(String idService: idServices) {
				
				try {
					stmt=DbConnector.getInstancia().getConn().prepareStatement(
						"select barber_id from service_barber where service_id=? and barber_id = ?"
								);
					stmt.setInt(1, Integer.parseInt(idService));
					stmt.setInt(2, barber.getUserId());
					rs=stmt.executeQuery();
				
					if(rs == null) {
						band = false;
						break;
						}	
					else{
						band = true;}} 
				catch (SQLException e) {
					e.printStackTrace();}
				finally {
					try {
						if(rs!=null) {rs.close();}
						if(stmt!=null) {stmt.close();}
							DbConnector.getInstancia().releaseConn();
					} catch (SQLException e) {
						e.printStackTrace();
								}}	
				}
			if (band) {
				barbers.add(barber);
			}
			}
		return barbers;
	}
	
	
	public int add(ServiceBarber serviceBarber) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into service_barber(barber_id,service_id) values(?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, serviceBarber.getBarberId());
			stmt.setInt(2, serviceBarber.getServiceId());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	serviceBarber.setServiceBarberId(keyResultSet.getInt(1));
            }
            return serviceBarber.getServiceBarberId();
            
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
	
	public boolean delete(ServiceBarber serviceBarber) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from service_barber where service_barber_id=?");
			stmt.setInt(1, serviceBarber.getServiceBarberId());
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
	
	public boolean deleteAllByBarber(int barberId) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from service_barber where barber_id=?");
			stmt.setInt(1,barberId);
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
	
	public boolean addAll(String[] servicesBarber, int userId) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into service_barber(barber_id,service_id) values(?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, userId);
			for(String s:servicesBarber) {
				stmt.setInt(2, Integer.parseInt(s));
				stmt.executeUpdate();
			}
            
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
