package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.LinkedList;
import java.time.LocalTime;

import entities.Local;
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
}
