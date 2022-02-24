package test;

import org.junit.*;
import static org.junit.Assert.*;

import controllayer.*;
import modellayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyMixed {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

        //Changing th test to make it work 
	/**
	 * Entering 1 cent and 50 �re should make the display report 4 minutes parking time.
	 */
	@Test
	public void shouldDisplay4MinFor1CentAnd1Ore() throws IllegalCoinException {
		 // Arrange
        int expectedParkingTime = 4;    // In minutes
        int coinValueOre = 50;
        int coinValueCent = 1;
        String coinCurrency1 = "DKK";
        Currency.ValidCoinType coinType1 = Currency.ValidCoinType.FRACTION;
       
        String coinCurrency2 = "EUR";
        Currency.ValidCoinType coinType2 = Currency.ValidCoinType.FRACTION;
       
        // Act
        ps.addPayment(coinValueOre, coinCurrency1, coinType1);
        ps.addPayment(coinValueCent, coinCurrency2, coinType2);
       
        System.out.println(ps.readDisplay());
           
        // Assert
        assertEquals("Should display 4 min for 50 �re and 1 cent", expectedParkingTime, ps.readDisplay());   		
	}
	
        //Adding more test
	/**
	 * Entering 1 euro and 1 DKK should make the display report 46 minutes parking time.
	 */
	@Test
	public void shouldDisplay46MinFor1EuroAnd1DKK() throws IllegalCoinException {
		 // Arrange
        int expectedParkingTime = 46;    // In minutes
        int coinValueDKK = 1;
        int coinValueCent = 1;
        String coinCurrency1 = "DKK";
        Currency.ValidCoinType coinType1 = Currency.ValidCoinType.INTEGER;
       
        String coinCurrency2 = "EUR";
        Currency.ValidCoinType coinType2 = Currency.ValidCoinType.INTEGER;
       
        // Act
        ps.addPayment(coinValueDKK, coinCurrency1, coinType1);
        ps.addPayment(coinValueCent, coinCurrency2, coinType2);
       
        //System.out.println(ps.readDisplay());
           
        // Assert
        assertEquals("Should display 46 min for 1 DKK and 1 euro", expectedParkingTime, ps.readDisplay());   		
	}
	
	/** Fixture for pay station testing. */
	@After
	public void cleanUp() {
		ps.setReady();
	}
	
}
