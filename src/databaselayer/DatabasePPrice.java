package databaselayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.sql.SQLException;

import modellayer.*;

public class DatabasePPrice implements IDbPPrice {
	
	//Hardcoded for now. TODO: Use database
	public PPrice getCurrentPrice() {
		return new PPrice();
	}
	
	public PPrice getPriceByZoneId(int zoneId) throws DatabaseLayerException {
		PPrice foundPrice = null;
		
		Calendar calendar = Calendar.getInstance();
		java.sql.Date dateNow = new java.sql.Date(calendar.getTime().getTime());
		
		Connection con = DBConnection.getInstance().getDBcon();

		String baseSelect = "select top 1 price, pZone_id, PZone.name from PPrice";
		//Added code										^-added data
		baseSelect += " INNER JOIN PZone ON PZone.id = PPrice.pZone_id ";
		//
		baseSelect += "where pZone_id = " + zoneId + " and starttime < '" + dateNow + "' ";
		baseSelect += "order by starttime desc";
		System.out.println(baseSelect);
	
		//ResultSet rs = null; 
		int price, pZoneId;
		String name;
		PZone pZone; 
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			// Todo: Get PPrice object
			
			ResultSet rs = stmt.executeQuery(baseSelect);
			while(rs.next()) {
				price = rs.getInt("price");
				pZoneId = rs.getInt("pZone_id");
				name = rs.getString("name");
				pZone = new PZone(pZoneId, name);
				foundPrice = new PPrice (price, pZone);
			}
			
			
			stmt.close();
		} catch (SQLException ex) {
			foundPrice = null;
			DatabaseLayerException dle = new DatabaseLayerException("Error retrieving data");
			dle.setStackTrace(ex.getStackTrace());
			System.out.println(ex.getMessage());
			throw dle;
		} catch (NullPointerException ex) {
			foundPrice = null;
			DatabaseLayerException dle = new DatabaseLayerException("Null pointer exception - possibly Connection object");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} catch (Exception ex) {
			foundPrice = null;
			DatabaseLayerException dle = new DatabaseLayerException("Data not retrieved! Technical error");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} finally {
			DBConnection.closeConnection();
		}
				
		return foundPrice;
	}
	

}
