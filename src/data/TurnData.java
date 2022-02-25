package data;
//orig
import entities.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

public class TurnData {
	
	public LinkedList<Turn> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		UserData ud = new UserData();
		ScheduleData sd = new ScheduleData();
		ServiceData serd = new ServiceData();
		LinkedList<Turn> turns= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from turns");
			if(rs!=null) {
				while(rs.next()) {
					Turn t=new Turn();
					t.setTurnId(rs.getInt("turn_id"));
					User client = ud.getById(rs.getInt("client_id"));
					Schedule schedule = sd.getById(rs.getInt("schedule_id"));
					t.setClient(client);
					t.setSchedule(schedule);
					t.setInit_date(rs.getObject("init_date", LocalDateTime.class));
					t.setInit_date(rs.getObject("finish_date", LocalDateTime.class));
					LinkedList<Service> services = serd.getServicesByTurn(t.getTurnId());
					t.setServices(services);
					turns.add(t);
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
		return turns;
	}
	
	public Turn getById(int id) {
		Turn t=null;
		UserData ud = new UserData();
		ScheduleData sd = new ScheduleData();
		ServiceData serd = new ServiceData();
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select schedule_id,client_id,init_date,finish_date from turns where turn_id=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				t=new Turn();
				User client = ud.getById(rs.getInt("client_id"));
				Schedule schedule = sd.getById(rs.getInt("schedule_id"));
				t.setClient(client);
				t.setSchedule(schedule);
				t.setInit_date(rs.getObject("init_date", LocalDateTime.class));
				t.setInit_date(rs.getObject("finish_date", LocalDateTime.class));
				LinkedList<Service> services = serd.getServicesByTurn(t.getTurnId());
				t.setServices(services);
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
		System.out.println(t);
		return t;
	}
	
	public LinkedList<Turn> getByBarberAndLocal(int barberId, int localId){
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Turn> turns= new LinkedList<>();
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
							"select * from turns "
							+ "inner join barber_local on turns.schedule_id = barber_local.id "
							+ "where barber_local.barber_id=? and barber_local.local_id=?"
							);
			stmt.setInt(1, barberId);
			stmt.setInt(2, localId);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Turn t=new Turn();
					UserData ud = new UserData();
					ScheduleData sd = new ScheduleData();
					t.setTurnId(rs.getInt("turn_id"));
					User client = ud.getById(rs.getInt("client_id"));
					Schedule schedule = sd.getById(rs.getInt("schedule_id"));
					t.setClient(client);
					t.setSchedule(schedule);
					t.setInit_date(rs.getObject("init_date", LocalDateTime.class));
					t.setInit_date(rs.getObject("finish_date", LocalDateTime.class));
					turns.add(t);
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
		return turns;
	}
	
	public LinkedList<Turn> getBookedTurnsByBarberId(int barberId){
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Turn> turns= new LinkedList<>();
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from turns "
					+ "inner join barber_local on turns.schedule_id = barber_local.id "
					+ "where barber_local.barber_id=? and turns.init_date >= current_date"
					);
			stmt.setInt(1, barberId);
		
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Turn t=new Turn();
					UserData ud = new UserData();
					ScheduleData sd = new ScheduleData();
					ServiceData servicedata = new ServiceData();
					
					t.setTurnId(rs.getInt("turn_id"));
					User client = ud.getById(rs.getInt("client_id"));
					Schedule schedule = sd.getById(rs.getInt("schedule_id"));
					t.setClient(client);
					t.setSchedule(schedule);
					t.setInit_date(rs.getObject("init_date", LocalDateTime.class));
					t.setInit_date(rs.getObject("finish_date", LocalDateTime.class));
					LinkedList<Service> services = servicedata.getServicesByTurn(t.getTurnId());
					t.setServices(services);
					turns.add(t);
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
		return turns;
	}
	
	public int add(Turn turn) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into turns(schedule_id,client_id,date,time) values(?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, turn.getSchedule().getId());
			stmt.setInt(2, turn.getClient().getUserId());
			stmt.setDate(4, Date.valueOf(turn.getDate()));
			stmt.setTime(5, Time.valueOf(turn.getTime()));
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                turn.setTurnId(keyResultSet.getInt(1));
                
                ServiceData sd = new ServiceData();
               // sd.setServicesTurn(turn);
            }
            return turn.getTurnId();
            
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
	
	public boolean delete(Turn turn) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from turns where turn_id=?");
			stmt.setInt(1, turn.getTurnId());
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
}
