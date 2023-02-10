package github.sigipeschke.StocksApp.Ledger;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
public class Ledger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String ticker;
	@Column(nullable = false)
	private String transaction;
	@Column(nullable = false)
	private String date;
	@Column(nullable = false)
	private BigDecimal shares;
	@Column(nullable = false)
	private BigDecimal price;
	@Column(nullable = true, length = 264)
	private String notes;
	
	public Ledger() {}
	
	public Ledger(String email, String ticker, String transaction, String date, BigDecimal shares, BigDecimal price, String notes) {
		this.email = email;
		this.ticker = ticker;
		this.transaction = transaction;
		this.date = date;
		this.shares = shares;
		this.price = price;
		this.notes = notes;
	}
	
	public Long getID() {
		return id;
	}

	public String getEMail() {
		return email;
	}
	
	public String getTicker() {
		return ticker;
	}
	
	public String getTransaction() {
		return transaction;
	}

	public String getDate() {
		return date;
	}

	public BigDecimal getShares() {
		return shares;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getNotes() {
		return notes;
	}
}

	
