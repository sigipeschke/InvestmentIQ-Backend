package github.sigipeschke.StocksApp.OwnedStock;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OwnedStock {
	@Id
	@Column(nullable = false, updatable = false)
	private String ticker;
	private BigDecimal purchasePrice;
	private BigDecimal ownedShares;
	private BigDecimal desiredSellPrice;
	private String notes;
	
	public OwnedStock() {}
	
	public OwnedStock(String ticker, BigDecimal purchasePrice, BigDecimal ownedShares, BigDecimal desiredSellPrice, String notes) {
		this.ticker = ticker;
		this.purchasePrice = purchasePrice;
		this.ownedShares = ownedShares;
		this.desiredSellPrice = desiredSellPrice;
		this.notes = notes;
	}
	
	public String getTicker() {
		return ticker;
	}
	
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}
	
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	public BigDecimal getOwnedShares() {
		return ownedShares;
	}
	
	public void setOwnedShares(BigDecimal ownedShares) {
		this.ownedShares = ownedShares;
	}
	
	public BigDecimal getDesiredSellPrice() {
		return desiredSellPrice;
	}
	
	public void setDesiredSellPrice(BigDecimal desiredSellPrice) {
		this.desiredSellPrice = desiredSellPrice;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Override
	public String toString() {
		return "Stock {" +
				" ticker = '" + ticker +
				", purchasePrice = '" + purchasePrice + '\'' +
				", ownedShares = '" + ownedShares + '\'' +
				", desiredSellPrice = '" + desiredSellPrice + '\'' +
				", notes = '" + notes + '\'' +
				'}';
	}
}
