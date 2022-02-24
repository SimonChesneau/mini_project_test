package test;

import org.junit.*;
import static org.junit.Assert.*;

import controllayer.*;
import modellayer.Currency;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyDkk {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Entering 50 �re should make the display report 3 minutes parking time.
	 */
	@Test
	public void shouldDisplay3MinFor50Ore() throws IllegalCoinException {
		
		// Arrange
		int expectedParkingTime = 3;	// In minutes
		int coinValue = 50;
		String coinCurrency = "DKK";
//			^Changed the coinCurrency to String type
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
			
		// Assert
		assertEquals("Should display 3 min for 50 �re", expectedParkingTime, ps.readDisplay());
	}

	//Adding another test
	/**
	 * Entering 2 DKK and 1 DKK should make the display report 16 minutes parking time.
	 */
	@Test
	public void shouldDisplay16MinFor2DKKAnd1DKK() throws IllegalCoinException {
		
		// Arrange
		int expectedParkingTime = 16;	// In minutes
		int coinValue1 = 2;
		int coinValue2 = 1;
		String coinCurrency = "DKK";
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		
		// Act
		ps.addPayment(coinValue1, coinCurrency, coinType);
		ps.addPayment(coinValue2, coinCurrency, coinType);
			
		// Assert
		assertEquals("Should display 16 min for 2 DKK and 1 DKK", expectedParkingTime, ps.readDisplay());
	}

	/** Fixture for pay station testing. */
	@After
	public void cleanUp() {
		ps.setReady();
	}	
	
}
