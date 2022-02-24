package test;

import org.junit.*;
import static org.junit.Assert.*;

import modellayer.Coin;
import modellayer.Currency;
import modellayer.PPrice;
import utility.Calculation;

public class TestCalculation {
	private PPrice price;
	private Calculation cal;
	
	@Before
	public void SetUp() {
		price = new PPrice();
		cal = new Calculation();
	}
	
	@Test
	public void testGetEuroCoinValueInCent() {
		
		//Arrange
		double expectedResult1 = 1d;
		double expectedResult2 = 100d;
		double result1, result2;
		String currency = "EURO";
		Currency.ValidCoinType coinType1 = Currency.ValidCoinType.FRACTION;
		Currency.ValidCoinType coinType2 = Currency.ValidCoinType.INTEGER;
		Coin coin1 = new Coin(1, currency, coinType1);
		Coin coin2 = new Coin(1, currency, coinType2);
		
		//Act
		result1 = cal.getEuroCoinValueInCent(coin1);
		result2 = cal.getEuroCoinValueInCent(coin2);
		
		//Assert
		assertEquals(expectedResult1, result1, 1d);
		assertEquals(expectedResult2, result2, 100d);
	}
	
	@Test
	public void testGetDkkCoinValueInCent() {
		
		//Arrange
		double expectedResult1 = 7d;
		double expectedResult2 = 13d;
		double result1, result2;
		String currency = "DKK";
		Currency.ValidCoinType coinType1 = Currency.ValidCoinType.FRACTION;
		Currency.ValidCoinType coinType2 = Currency.ValidCoinType.INTEGER;
		Coin coin1 = new Coin(50, currency, coinType1);
		Coin coin2 = new Coin(1, currency, coinType2);
		
		//Act
		result1 = cal.getDkkCoinValueInCent(coin1, price);
		result2 = cal.getDkkCoinValueInCent(coin2, price);
		
		//Assert
		assertEquals(expectedResult1, result1, 7d);
		assertEquals(expectedResult2, result2, 13d);
	}
}
