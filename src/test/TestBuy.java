package test;

import org.junit.*;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import databaselayer.DBConnection;
import databaselayer.DatabaseLayerException;
import databaselayer.DatabasePBuy;
import modellayer.Currency;
import modellayer.PBuy;
import modellayer.PReceipt;

import static org.junit.Assert.*;

//Creation of the entire TestBuy class to test the buy methode
public class TestBuy {
	ControlPayStation ps;
	static PReceipt ticket;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Verify buy() methods
	 */
	@Test
	public void shouldBuy() throws IllegalCoinException, DatabaseLayerException {
		// Arrange
		int coinValue = 1;
		int exepectedResult = 1;
		String coinCurrency = "EUR";
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
				
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
		ticket = ps.buy();
		
        // Assert
		assertEquals(exepectedResult, ticket.getValue());
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
			PBuy tempBuy = new PBuy(ticket.getBuy_id()); //setting the id retrieved to a PBuy object
			numDeleted = dbPbuy.deleteParkingBuy(tempBuy); //Delete the line added
		} catch(Exception ex) { 
			System.out.println("Error: " + ex.getMessage());
		} finally {
			DBConnection.closeConnection();
		}
	
		// Assert
		assertEquals("One row deleted", 1, numDeleted );
	}	
}
