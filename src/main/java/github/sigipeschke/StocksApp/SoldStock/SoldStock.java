package github.sigipeschke.StocksApp.SoldStock;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SoldStock {
	@Id
	@Column(nullable = false, updatable = false)
	private String ticker;
	private BigDecimal soldPrice;
	private BigDecimal soldShares;
	private String notes;
	
	public SoldStock() {}
	
	public SoldStock(String ticker, BigDecimal soldPrice, BigDecimal soldShares, String notes) {
		this.ticker = ticker;
		this.soldPrice = soldPrice;
		this.soldShares = soldShares;
		this.notes = notes;
	}

	public String getTicker() {
		return ticker;
	}

	public BigDecimal getSoldPrice() {
		return soldPrice;
	}

	public BigDecimal getSoldShares() {
		return soldShares;
	}
	
	public String getNotes() {
		return notes;
	}
}
