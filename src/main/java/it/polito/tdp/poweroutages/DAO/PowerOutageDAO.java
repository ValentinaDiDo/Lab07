package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.Outages;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	//LISTA DI TUTTI GLI OUTAGES IN QUEL NERC + DURATA
	public List<Outages> getNercOutages(int nercId){
		
		List<Outages> o = new ArrayList<Outages>();
		
		String sql = "SELECT  nerc_id,customers_affected, date_event_began, date_event_finished, HOUR(TIMEDIFF(date_event_began, date_event_finished)) AS diff, YEAR(date_event_began) as anno "
				+ "FROM PowerOutages "
				+ "WHERE nerc_id=? ";
		try {
			
	
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nercId);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Outages oo = new Outages(rs.getInt("customers_affected"),rs.getDate("date_event_began"), rs.getDate("date_event_finished"), rs.getInt("diff"), rs.getInt("anno"));
				o.add(oo);
			}
			conn.close();
			return o;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	

}
