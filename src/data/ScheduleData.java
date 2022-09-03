package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.LinkedList;
import java.time.LocalTime;

import entities.Local;
import entities.Publication;
import entities.Schedule;
import entities.User;
import utils.Days;

public class ScheduleData {
	public LinkedList<Schedule> getAllByBarber(int barberId){
		UserData ud = new UserData();
		LocalData ld = new LocalData();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Schedule> schedules= new LinkedList<Schedule>();		
		try {		
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from barber_local WHERE barber_local.barber_id = ?"
					);
			stmt.setInt(1, barberId);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Schedule r=new Schedule();
					r.setId(rs.getInt("id"));
					User user = ud.getById(rs.getInt("barber_id"));
					Local local = ld.getById(rs.getInt("local_id"));
					r.setBarber(user);
					r.setLocal(local);
					r.setDay_of_week(Days.valueOf(rs.getString("day_of_week")));
					r.setStart_time(rs.getObject("start_time", LocalTime.class));
					r.setEnd_time(rs.getObject("end_time", LocalTime.class));
					schedules.add(r);
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
		
		
		return schedules;
	}
	
	public static LinkedList<User> getBarbersByLocal(int localId){
		
		UserData ud = new UserData();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<User> barbers= new LinkedList<User>();		
		try {		
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select distinct barber_id from barber_local WHERE local_id = ?"
					);
			stmt.setInt(1, localId);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					
					User barber = ud.getById(rs.getInt("barber_id"));
					
					barbers.add(barber);
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
		
		
		return barbers;
	}
	
	public int add(Schedule newSchedule) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into barber_local(barber_id, local_id, day_of_week, start_time, end_time) values(?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, newSchedule.getBarber().getUserId());
			stmt.setInt(2, newSchedule.getLocal().getLocalId());
			stmt.setString(3, newSchedule.getDay_of_week_As_string());
			stmt.setTime(4, Time.valueOf(newSchedule.getStart_time()));
			stmt.setTime(5, Time.valueOf(newSchedule.getEnd_time()));
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	newSchedule.setId(keyResultSet.getInt(1));
            }
            return newSchedule.getId();
            
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
	
	public Schedule getById(int id) {
		UserData ud = new UserData();
		LocalData ld = new LocalData();
		Schedule s = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id,barber_id,local_id,day_of_week,start_time,end_time from barber_local where id=?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null) {
			while(rs.next()) {
				s= new Schedule();
				s.setId(rs.getInt("id"));
				User user = ud.getById(rs.getInt("barber_id"));
				Local local = ld.getById(rs.getInt("local_id"));
				s.setBarber(user);
				s.setLocal(local);
				s.setDay_of_week(Days.valueOf(rs.getString("day_of_week")));
				s.setStart_time(rs.getObject("start_time", LocalTime.class));
				s.setEnd_time(rs.getObject("end_time", LocalTime.class));				
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
		return s;
	}
	
	public Schedule getByBarberLocal(int localId,int barberId) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Schedule s = new Schedule();	
		UserData ud = new UserData();
		LocalData ld = new LocalData();
		try {		
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from barber_local WHERE local_id = ? and barber_id = ?;"
					);
			stmt.setInt(1, localId);
			stmt.setInt(2, barberId);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					
					s.setId(rs.getInt("id"));
					s.setStart_time(rs.getObject("start_time", LocalTime.class));
					s.setEnd_time(rs.getObject("end_time", LocalTime.class));	
					s.setDay_of_week(Days.valueOf(rs.getString("day_of_week")));
					User user = ud.getById(rs.getInt("barber_id"));
					Local local = ld.getById(rs.getInt("local_id"));
					s.setBarber(user);
					s.setLocal(local);
					
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
		
		
		return s;
		
	}
	
	public boolean update(Schedule schedule) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE barber_local SET barber_id = ?, local_id = ?, day_of_week = ?, start_time = ?, end_time = ? WHERE id = ?"
							);
			stmt.setInt(1, schedule.getBarber().getUserId());
			stmt.setInt(2, schedule.getLocal().getLocalId());
			stmt.setString(3, schedule.getDay_of_week_As_string());
			stmt.setTime(4, Time.valueOf(schedule.getStart_time()));
			stmt.setTime(5, Time.valueOf(schedule.getEnd_time()));
			stmt.setInt(6, schedule.getId());
			
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
