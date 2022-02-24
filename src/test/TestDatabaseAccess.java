package test;

import static org.junit.Assert.*;

import org.junit.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

//import controllayer.ControlPayStation;
//import controllayer.Currency;
//import controllayer.IPayStation;
//import controllayer.IReceipt;
//import controllayer.IllegalCoinException;

import databaselayer.*;
import modellayer.*;
import controllayer.*;

//import static org.junit.Assert.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestDatabaseAccess {
	
	DBConnection con = null;
	static PBuy tempPBuy;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		con = DBConnection.getInstance();
	}
	
	
	@Test
	public void wasConnected() {
		assertNotNull("Connected - connection cannot be null", con);
		
		DBConnection.closeConnection();
		boolean wasNullified = DBConnection.instanceIsNull();
		assertTrue("Disconnected - instance set to null", wasNullified);
		
		con = DBConnection.getInstance();
		assertNotNull("Connected - connection cannot be null", con);		
	}
	
	
	@Test
	public void wasInsertedBuy() throws SQLException {
//											^ Adding a throw exception because of the try catch we added
		// Arrange
		LocalDate timeNow = java.time.LocalDate.now();
		double payedCentAmount = 100;
		
		tempPBuy = new PBuy();
		
		PPayStation pStat = new PPayStation(1, "P-423E");
		pStat.setAmount(payedCentAmount);
		tempPBuy.setAssociatedPaystation(pStat);
		tempPBuy.setBuyTime(timeNow);
		
		DatabasePBuy dbPbuy = new DatabasePBuy();
		
		// Act
		int key = 0; 
		//Adding the dbPBuy call and making the test works
		try {
			key = dbPbuy.insertParkingBuy(tempPBuy);
			
		} catch (DatabaseLayerException e) {
			System.out.println("Cannot insert the value inside the database");
		}
		
		// Assert
		assertEquals(true, key > 0);
		
	}	
	
	//Changint the test to make it work
	@Test
	public void wasRetrievedPriceDatabaselayer() {
		// Arrange
		PPrice foundPrice = null;
		int pZoneId = 2;
		DatabasePPrice dbPrice = new DatabasePPrice();

		
		// Act
		try {
			foundPrice = dbPrice.getPriceByZoneId(pZoneId);
		} catch(DatabaseLayerException ex) {
			System.out.println("Cannot retrieve the zone price");
		}

		// Assert
		assertEquals("Is the price retrieved the good one - ", foundPrice.getParkingPrice(), 25);
		
	}
	
	//changing the test to make it work
	@Test
	public void wasRetrievedPriceControllayer() {

		// Arrange
		PPrice foundPrice = null;
		ControlPrice ctrlPrice = new ControlPrice();
		int pZoneId = 2;
		
		// Act
		try {
			foundPrice = ctrlPrice.getPriceRemote(pZoneId);
		}catch(DatabaseLayerException ex) {
			System.out.println("Fail");
		}

		// Assert
		assertEquals("Is the price retrieved the good one - ", foundPrice.getParkingPrice(), 25);
		
	}	
	
	
	/** Fixture for pay station testing. */
	@After
	public void cleanUp() {
		DBConnection.closeConnection();
	}	
	
	@AfterClass
	public static void cleanUpWhenFinish() {
		// 		
		// Arrange
		DatabasePBuy dbPbuy = new DatabasePBuy();
		int numDeleted = 0;
		
		// Act
		try {
			numDeleted = dbPbuy.deleteParkingBuy(tempPBuy);
		} catch(Exception ex) { 
			System.out.println("Error: " + ex.getMessage());
		} finally {
			DBConnection.closeConnection();
		}
	
		// Assert
		assertEquals("One row deleted", 1, numDeleted );
	}	

}
