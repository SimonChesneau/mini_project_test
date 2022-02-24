package test;

import org.junit.*;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import databaselayer.DatabaseLayerException;
import modellayer.Currency;
import modellayer.PReceipt;

import static org.junit.Assert.*;

public class TestBuy {
	ControlPayStation ps;

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
		PReceipt ticket = ps.buy();
		
        // Assert
		assertEquals(exepectedResult, ticket.getValue());
	}
}
