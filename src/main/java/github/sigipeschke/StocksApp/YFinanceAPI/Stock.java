package github.sigipeschke.StocksApp.YFinanceAPI;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import github.sigipeschke.StocksApp.Ledger.Ledger;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes2.HistoricalDividend;
import yahoofinance.quotes.stock.StockQuote;


public class Stock {
	private StockService stockService = new StockService();
	private String ticker;
	private List<Ledger> ledger;
	private List<HistoricalQuote> history;
	private List<HistoricalDividend> dividendHist;
	private BigDecimal ownedValue;
	private BigDecimal investedValue;
	private BigDecimal ownedShares;
	private String mode;
	private BigDecimal profit;
	private BigDecimal profitInPercent;
	private String notes;
	private String name;
	private BigDecimal price;
	private BigDecimal bid;
	private BigDecimal ask;
	private BigDecimal open;
	private BigDecimal previousClose;
	private BigDecimal dayHigh;
	private BigDecimal dayLow;
	private BigDecimal change;
	private BigDecimal changeInPercent;
	private BigDecimal avg50Price;
	private BigDecimal avgChange50;
	private BigDecimal avgChange50InPercent;
	private BigDecimal avg200Price;
	private BigDecimal avgChange200;
	private BigDecimal avgChange200InPercent;
	private BigDecimal yearHigh;
	private BigDecimal changeYearHigh;
	private BigDecimal changeYearHighInPercent;
	private BigDecimal yearLow;
	private BigDecimal changeYearLow;
	private BigDecimal changeYearLowInPercent;
	private Long volume;
	private Long avgVolume;
	private BigDecimal dividendAnnualYield;
	private BigDecimal dividendAnnualYieldPercent;
	private String dividendPayDate;
	private BigDecimal marketCap;
	private String stockExchange;
	private String url;
	
	public Stock() {}
	
	public Stock(String ticker) {
		this.ticker = ticker;
		construct(ticker);
	}
	
	public Stock(String ticker, List<Ledger> ledger) {
		this.ticker = ticker;
		construct(ticker);
		
		this.ledger = ledger;
		sumLedgers();
	}

	private void construct(String ticker) {
		StockWrapper stock = this.stockService.findStock(ticker);
		this.name = stock.getStock().getName();
		StockQuote quote = stock.getStock().getQuote();
		this.price = quote.getPrice();
		this.bid = quote.getBid();
		this.ask = quote.getAsk();
		this.open = quote.getOpen();
		this.previousClose = quote.getPreviousClose();
		this.dayHigh = quote.getDayHigh();
		this.dayLow = quote.getDayLow();
		this.change = quote.getChange();
		this.changeInPercent = quote.getChangeInPercent();
		this.avg50Price = quote.getPriceAvg50();
		this.avgChange50 = quote.getChangeFromAvg50();
		this.avgChange50InPercent = quote.getChangeFromAvg50InPercent();
		this.avg200Price = quote.getPriceAvg200();
		this.avgChange200 = quote.getChangeFromAvg200();
		this.avgChange200InPercent = quote.getChangeFromAvg200InPercent();
		this.yearHigh = quote.getYearHigh();
		this.changeYearHigh = quote.getChangeFromYearHigh();
		this.changeYearHighInPercent = quote.getChangeFromYearHighInPercent();
		this.yearLow = quote.getYearLow();
		this.changeYearLow = quote.getChangeFromYearLow();
		this.changeYearLowInPercent = quote.getChangeFromYearLowInPercent();
		this.volume = quote.getVolume();
		this.avgVolume = quote.getAvgVolume();
		this.dividendAnnualYield = stock.getStock().getDividend().getAnnualYield();
		this.dividendAnnualYieldPercent = stock.getStock().getDividend().getAnnualYieldPercent();
		this.dividendPayDate = ""; //stock.getStock().getDividend().getPayDate().toString();
		this.marketCap = stock.getStock().getStats().getMarketCap();
		this.stockExchange = stock.getStock().getStockExchange();
		this.url = genUrl();
		
		/*
		try {
			this.history = stock.getStock().getHistory();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.dividendHist = stock.getStock().getDividendHistory();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public List<HistoricalQuote> getHistory() {
		return history;
	}

	public List<HistoricalDividend> getDividendHist() {
		return dividendHist;
	}
	
	public BigDecimal getProfit() {
		return profit;
	}
	
	public BigDecimal getProfitInPercent() {
		return profitInPercent;
	}
	
	public BigDecimal getAvg50Price() {
		return avg50Price;
	}

	public BigDecimal getAvgChange50() {
		return avgChange50;
	}

	public BigDecimal getAvg200Price() {
		return avg200Price;
	}

	public BigDecimal getAvgChange200() {
		return avgChange200;
	}

	public BigDecimal getYearHigh() {
		return yearHigh;
	}

	public BigDecimal getChangeYearHigh() {
		return changeYearHigh;
	}

	public BigDecimal getChangeYearHighInPercent() {
		return changeYearHighInPercent;
	}

	public BigDecimal getYearLow() {
		return yearLow;
	}

	public BigDecimal getChangeYearLow() {
		return changeYearLow;
	}

	public BigDecimal getChangeYearLowInPercent() {
		return changeYearLowInPercent;
	}
	
	public String getMode() {
		return mode;
	}

	private void sumLedgers() {
		Double iv = 0.0;
		Double ov = 0.0;
		Double os = 0.0;
		
		for (Ledger l : this.ledger) {
			if (l.getTransaction().equals("purchase")) {
				ov += this.price.doubleValue() * l.getShares().doubleValue();
				iv += l.getPrice().doubleValue() * l.getShares().doubleValue();
				os += l.getShares().doubleValue();
			}
			else if (l.getTransaction().equals("sale")) {
				iv -= l.getPrice().doubleValue() * l.getShares().doubleValue();
				os -= l.getShares().doubleValue();
				
			}
		}
		this.investedValue = new BigDecimal(iv);
		this.ownedValue = new BigDecimal(ov);
		this.ownedShares = new BigDecimal(os);
		if (os > 0.001) {
			this.mode = "Owned";
		} else {
			this.mode = "Sold";
		}
		Double p = (ov-iv);
		this.profit = new BigDecimal(p);
		Double pp = (p/iv)*100;
		this.profitInPercent = new BigDecimal(pp).setScale(2, RoundingMode.HALF_UP);
	}

	public List<Ledger> getLedger() {
		return ledger;
	}

	public BigDecimal getOwnedValue() {
		return ownedValue;
	}
	
	public BigDecimal getInvestedValue() {
		return investedValue;
	}

	public Long getVolume() {
		return volume;
	}
	
	public String getStockExchange() {
		return stockExchange;
	}

	public BigDecimal getPreviousClose() {
		return previousClose;
	}

	public BigDecimal getDayHigh() {
		return dayHigh;
	}

	public BigDecimal getDayLow() {
		return dayLow;
	}

	public BigDecimal getChange() {
		return change;
	}

	public BigDecimal getChangeInPercent() {
		return changeInPercent;
	}

	public BigDecimal getMarketCap() {
		return marketCap;
	}

	public String getTicker() {
		return ticker;
	}

	public BigDecimal getOwnedShares() {
		return ownedShares;
	}

	public String getNotes() {
		return notes;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getBid() {
		return bid;
	}

	public BigDecimal getAsk() {
		return ask;
	}
	
	public BigDecimal getOpen() {
		return open;
	}

	public Long getAvgVolume() {
		return avgVolume;
	}

	public BigDecimal getAvgChange50InPercent() {
		return avgChange50InPercent;
	}

	public BigDecimal getAvgChange200InPercent() {
		return avgChange200InPercent;
	}

	public BigDecimal getDividendAnnualYield() {
		return dividendAnnualYield;
	}

	public BigDecimal getDividendAnnualYieldPercent() {
		return dividendAnnualYieldPercent;
	}

	public String getDividendPayDate() {
		return dividendPayDate;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public String genUrl() {
		return "https://ca.finance.yahoo.com/quote/" + this.ticker + "?p=" + this.ticker;
	}
}
