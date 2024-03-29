package data;

import entities.*;
import utils.Days;
import utils.TurnStatus;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
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
					t.setDate(rs.getObject("date", LocalDate.class));
					t.setHour(rs.getObject("hour", LocalTime.class));
					LinkedList<Service> services = serd.getServicesByTurn(t.getTurnId());
					t.setServices(services);
					t.setStatus(TurnStatus.valueOf(rs.getString("status")));
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
					"select schedule_id,client_id,date,hour from turns where turn_id=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				t=new Turn();
				User client = ud.getById(rs.getInt("client_id"));
				Schedule schedule = sd.getById(rs.getInt("schedule_id"));
				t.setClient(client);
				t.setSchedule(schedule);
				t.setDate(rs.getObject("date", LocalDate.class));
				t.setHour(rs.getObject("hour", LocalTime.class));
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
					t.setDate(rs.getObject("date", LocalDate.class));
					t.setHour(rs.getObject("hour", LocalTime.class));
					t.setStatus(TurnStatus.valueOf(rs.getString("status")));


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
		//Turnos pendientes 
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Turn> turns= new LinkedList<>();
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
			"select t.turn_id,t.date,t.hour,t.client_id,t.status,bl.id from turns as t inner join barber_local as bl on t.schedule_id = bl.id where bl.barber_id=? and t.date>=CURRENT_DATE;"
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
					Schedule schedule = sd.getById(rs.getInt("id"));
					t.setClient(client);
					t.setSchedule(schedule);
					t.setDate(rs.getObject("date", LocalDate.class));
					t.setHour(rs.getObject("hour", LocalTime.class));
					LinkedList<Service> services = servicedata.getServicesByTurn(t.getTurnId());
					t.setServices(services);
					t.setStatus(TurnStatus.valueOf(rs.getString("status")));


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
	
	public LinkedList<String> getTimes(LinkedList<Turn> turns, LocalTime duration,Schedule schedule){
		
		LinkedList<String> hours= new LinkedList<>();
		LocalTime before_hour = null;
		LocalTime after_hour = null;
		Turn tinit = new Turn();
		
		tinit.setHour(schedule.getStart_time());
		tinit.setDuration(Time.valueOf("00:00:00"));
		Turn tbreak = new Turn();
		tbreak.setHour(schedule.getEnd_time());
		
		
		if(turns.isEmpty()) {
			long cant_hours = ChronoUnit.MINUTES.between(schedule.getStart_time(),schedule.getEnd_time());
					
			int k = 0;
			LocalTime start_hour = schedule.getStart_time();
			while(k <= cant_hours/30) {
				
				hours.add(start_hour.toString());
				start_hour = start_hour.plusMinutes(30);
				k++;}
			
		}else {
			turns.add(tbreak);
			turns.addFirst(tinit);
			for(int i = 0; i < turns.size()-1;i++) {
			
				Turn before_turn = turns.get(i);
				Turn after_turn = turns.get(i+1);
			
			
					before_hour = before_turn.getHour();
					after_hour = after_turn.getHour();
			
			
					Duration hour= Duration.ofHours(before_turn.getDuration().toLocalTime().getHour());
					Duration min = Duration.ofMinutes(before_turn.getDuration().toLocalTime().getMinute());
					Duration total = hour.plus(min);
					before_hour = before_hour.plus(total);
					
			
			
				long remainder = ChronoUnit.MINUTES.between(before_hour,after_hour);
				long service_duration = duration.getLong(ChronoField.MINUTE_OF_DAY);
			
			if(remainder >= service_duration){
				
				long cant = (remainder - service_duration)/30; 
				int j = 0;
				
				do {
					
					hours.add(before_hour.toString());
					before_hour = before_hour.plusMinutes(30);
					j++;
					}while(j<=cant);
			}
				}
		}
		return hours;
	}
	
	public LinkedList<String> getHoursFree(int barberId,String turnDate,int idLocal,LocalTime duration,Schedule schedule){
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<String> hours= new LinkedList<>();
		LinkedList<Turn> turns = new LinkedList<>();
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select t.hour,t.date,t.duration from turns as t inner join barber_local as bl on t.schedule_id = bl.id where bl.barber_id=? and t.date =? and bl.local_id = ? order by t.hour asc"
					);
			stmt.setInt(1, barberId);
			stmt.setDate(2, Date.valueOf(turnDate));
			stmt.setInt(3, idLocal);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Turn t=new Turn();
					
					t.setDate(rs.getObject("date", LocalDate.class));
					t.setHour(rs.getTime("hour").toLocalTime());

					t.setDuration(rs.getTime("duration"));
					turns.add(t);
				}
			}
			hours = getTimes(turns, duration,schedule);
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
		return hours;
		
		
	}
	
	public int add(Turn turn) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into turns(schedule_id,client_id,date,hour,duration,status) values(?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, turn.getSchedule().getId());
			stmt.setInt(2, turn.getClient().getUserId());
			stmt.setDate(3, Date.valueOf(turn.getDate()));
			stmt.setTime(4, Time.valueOf(turn.getHour()));
			stmt.setTime(5,turn.getDuration());
			stmt.setString(6, turn.getStatus().toString());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null){
            	while(keyResultSet.next()) {
                turn.setTurnId(keyResultSet.getInt(1));
                
                TurnServicesData sd = new TurnServicesData();
                sd.setServicesTurn(turn);
            }}
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
	public boolean switchTurnStatus(TurnStatus newStatus, int turnId) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE turns SET status=? WHERE turn_id = ?"
							);
			stmt.setString(1, newStatus.toString());
			stmt.setInt(2, turnId);
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
	public LinkedList<Turn> getBookedTurnsByUserId(int userId){
		//Turnos pendientes 
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Turn> turns= new LinkedList<>();
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
			"select t.turn_id,t.date,t.hour,t.schedule_id,bl.barber_id,bl.local_id from turns as t "
			+ "inner join barber_local as bl on t.schedule_id = bl.id "
			+ "where t.client_id = ? and t.date>=CURRENT_DATE;");
			stmt.setInt(1, userId);
		
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Turn t=new Turn();
					UserData ud = new UserData();
					ScheduleData sd = new ScheduleData();
					LocalData ld = new LocalData();
					
					t.setTurnId(rs.getInt("turn_id"));
					
					User client = ud.getById(userId);
					Schedule schedule = sd.getById(rs.getInt("schedule_id"));
					User barber = ud.getById(rs.getInt("barber_id"));
					Local local = ld.getById(rs.getInt("local_id"));
					
					schedule.setBarber(barber);
					schedule.setLocal(local);
					t.setClient(client);
					t.setSchedule(schedule);
					t.setDate(rs.getObject("date", LocalDate.class));
					t.setHour(rs.getObject("hour", LocalTime.class));
					


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
}
