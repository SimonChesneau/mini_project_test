package test;

import org.junit.*;
import static org.junit.Assert.*;

import controllayer.*;
import modellayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestReset {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	//Modification of the test to make it works
	/**
	 * Verify that the pay station is cleared and display shows 0 after a buy scenario
	 */
	@Test
	public void shouldClearAfterBuy() throws IllegalCoinException, Exception {
		// Arrange
		int coinValue = 1;
        String coinCurrency = "EUR";
        Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
        int expectedResult = 0;
		
        // Act
        ps.addPayment(coinValue, coinCurrency, coinType);
        ps.buy();	//With this method, the pay station will be cleared
		
        // Assert
		assertEquals(expectedResult, ps.readDisplay());		//We use the minute amount. Because we round up this amount, if his value is 0, then the pay station have not payment
	}

	//Modification of the test to make it works
	/**
	 * Verify that cancel() clears the pay station
	 */
	@Test
	public void shouldClearAfterCancel() throws IllegalCoinException {
		// Arrange
		int coinValue = 1;
		String coinCurrency = "EUR";
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		int expectedResult = 0;
				
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
		ps.cancel();
		
        // Assert
		assertEquals(expectedResult, ps.readDisplay());		//We use the minute amount. Because we round up this amount, if his value is 0, then the pay station have not payment

	}
}
