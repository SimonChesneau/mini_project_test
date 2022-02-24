package test;

import org.junit.*;
import static org.junit.Assert.*;

import controllayer.*;
import modellayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyEuro {

	private ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}
	
	/**
	 * Entering no coins should make the display report 0 minutes parking time.
	 */
	@Test
	public void shouldDisplay0() throws IllegalCoinException {
		
		// Arrange
		int expectedParkingTime = 0;
		
		// Act
		// No action
		
		// Assert
		assertEquals("Should display 0 min for no coins", expectedParkingTime, ps.readDisplay());
	}	

	/**
	 * Entering 5 cents should make the display report 2 minutes parking time
	 */
	@Test
	public void shouldDisplay2MinFor5Cents() throws IllegalCoinException {
		// Arrange
        int expectedParkingTime = 2;    // In minutes        
        int coinValue = 5;
        String coinCurrency = "EUR";
        Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
        
        // Act
    
        ps.addPayment(coinValue, coinCurrency, coinType);
        
        //System.out.println(ps.readDisplay());
            
        // Assert
        assertEquals("should display 2 min for 5 cents", expectedParkingTime, ps.readDisplay());
	}

	/**
	 * Entering 1 cent should make the display report 1 minutes parking time
	 */
	@Test
	public void shouldDisplay1MinFor1Cent() throws IllegalCoinException {
		// Arrange
        int expectedParkingTime = 1;    // In minutes        
        int coinValue = 1;
        String coinCurrency = "EUR";
        Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
        
        // Act
    
        ps.addPayment(coinValue, coinCurrency, coinType);
        
        //System.out.println(ps.readDisplay());
            
        // Assert
        assertEquals("should display 1 min for 1 cent", expectedParkingTime, ps.readDisplay());
	}
	
	/**
	 * Entering 1 euro and 2 euros should make the display report 120 minutes parking time
	 */
	@Test
	public void shouldDisplay120MinFor1EuroAnd2Euros() throws IllegalCoinException {
		// Arrange
        int expectedParkingTime = 120;    // In minutes        
        int coinValue1 = 1;
        String coinCurrency = "EUR";
        Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
        int coinValue2 = 2;
        
        // Act
    
        ps.addPayment(coinValue1, coinCurrency, coinType);
        ps.addPayment(coinValue2, coinCurrency, coinType);
        
        //System.out.println(ps.readDisplay());
            
        // Assert
        assertEquals("should display 120 min for 1 euro and 2 euros", expectedParkingTime, ps.readDisplay());
	}
	
	/** Fixture for pay station testing. */
	@After
	public void cleanUp() {
		ps.setReady();
	}	
}
