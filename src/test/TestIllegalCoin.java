package test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import controllayer.*;
import modellayer.Currency;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik Bærbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */
@RunWith(Parameterized.class)
public class TestIllegalCoin {
	
	@Parameterized.Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][] {
			{0, "EUR", Currency.ValidCoinType.INTEGER},
			{3, "EUR", Currency.ValidCoinType.INTEGER},
			{0, "EUR", Currency.ValidCoinType.FRACTION},
			{3, "EUR", Currency.ValidCoinType.FRACTION},
			{4, "EUR", Currency.ValidCoinType.FRACTION},
			{6, "EUR", Currency.ValidCoinType.FRACTION},
			{9, "EUR", Currency.ValidCoinType.FRACTION},
			{11, "EUR", Currency.ValidCoinType.FRACTION},
			{19, "EUR", Currency.ValidCoinType.FRACTION},
			{21, "EUR", Currency.ValidCoinType.FRACTION},
			{49, "EUR", Currency.ValidCoinType.FRACTION},
			{51, "EUR", Currency.ValidCoinType.FRACTION}
			
		});
	}
	private int amount;
	private String coinCurrency;
    private Currency.ValidCoinType coinType;
	
   
	ControlPayStation ps;
	
	public TestIllegalCoin(int amount, String coinCurrency, Currency.ValidCoinType coinType) {
		this.amount = amount;
		this.coinCurrency = coinCurrency;
		this.coinType = coinType;
	}

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Verify that illegal coins are rejected.
	 */
	
	
	//any coin other than ddk and eur
	@Test(expected = IllegalCoinException.class)
	public void shouldRejectIllegalCurrencies() throws IllegalCoinException {
		ps.addPayment(1, "NOK", Currency.ValidCoinType.INTEGER);
	}
	
	
	// unknown Euro coin value
	@Test(expected = IllegalCoinException.class)
	public void shouldRejectIllegalEuroCoin() throws IllegalCoinException {
		ps.addPayment(amount, coinCurrency, coinType);
	}
}
