package github.sigipeschke.StocksApp.WatchStock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WatchStock {
	@Id
	@Column(nullable = false, updatable = false)
	private String ticker;
	
	public WatchStock() {}
	
	public WatchStock(String ticker) {
		this.ticker = ticker;
	}
	
	public String getTicker() {
		return ticker;
	}
}
