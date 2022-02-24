package modellayer;

public class Coin {

	private int amount; 
	private String currency;	//Changed to String
	private Currency.ValidCoinType coinType;
	
	public Coin(int amount, String currency, Currency.ValidCoinType coinType) {
//							^Changed currency to String type
		this.amount = amount;
		this.currency = currency;
		this.coinType = coinType;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getCurrency(){
//			^Changed currency to String type
		return currency;
	}
	
	public void setCurrency(String currency){
//							^Changed currency to String type
		this.currency = currency;
	}
	
	public Currency.ValidCoinType getCoinType() {
		return coinType;
	}
	
	public void setCoinType(Currency.ValidCoinType coinType) {
		this.coinType = coinType;
	}
}
