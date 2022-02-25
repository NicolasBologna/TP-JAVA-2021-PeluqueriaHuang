package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Role;
import entities.Service;
import entities.Turn;
import entities.User;

public class TurnServicesData {

public void setServicesTurn(Turn t) {
		
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into turn_services values(?,?);");
			stmt.setInt(1,t.getTurnId());
			for(Service s:t.getServices()) {
				stmt.setInt(2, s.getServiceId());
				stmt.executeUpdate();
			}
			
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
	}
}
