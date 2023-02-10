package github.sigipeschke.StocksApp.YFinanceAPI;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import github.sigipeschke.StocksApp.Ledger.Ledger;
import github.sigipeschke.StocksApp.OwnedStock.OwnedStock;
import github.sigipeschke.StocksApp.SoldStock.SoldStock;
import github.sigipeschke.StocksApp.WatchStock.WatchStock;
import yahoofinance.YahooFinance;

// @AllArgsConstructor
@Service
@Transactional
public class StockService {
	//private final RefreshService refreshService;
	
	public StockWrapper findStock(final String ticker) {
		try {
			return new StockWrapper(YahooFinance.get(ticker));
		}
		catch (IOException e) {
			System.out.println("Error");
		}
		return null;
	}
	
	public List<StockWrapper> findStocks(final List<String> tickers) {
		return tickers.stream().map(this::findStock)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}
	
	/*
	public BigDecimal findPrice(final StockWrapper stock) throws IOException {
		return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getPrice(); //https://javadoc.io/static/de.sfuhrm/YahooFinanceAPI/3.16.3/yahoofinance/quotes/stock/StockQuote.html
	}
	
	public BigDecimal findLastChangePercent(final StockWrapper stock) throws IOException {
		return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getChangeInPercent();
	}
	
	public BigDecimal findChangeFrom200MeanPercent(final StockWrapper stock) throws IOException {
		return stock.getStock().getQuote(refreshService.shouldRefresh(stock)).getChangeFromAvg200InPercent();
	}
	
	public List<HistoricalQuote> findHistory(final StockWrapper stock) throws IOException {
		return stock.getStock().getHistory(); //Gets historical data for the past year https://javadoc.io/static/de.sfuhrm/YahooFinanceAPI/3.16.3/yahoofinance/histquotes/HistoricalQuote.html
	}
	*/
	
	
	/*		^ YFinance
	 * 
	 *		v Stock Manager
	 */	
	
	public Stock genStock(final String ticker) {
		return new Stock(ticker);
	}
	
	public Stock genStock(final String ticker, final List<Ledger> ledger) {
		return new Stock(ticker, ledger);
	}
	
	public Stock convOwned(final OwnedStock stock) {
		return new Stock(stock.getTicker());
	}
	
	public Stock convSold(final SoldStock stock) {
		return new Stock(stock.getTicker());
	}
	
	public Stock convWatch(final WatchStock stock) {
		return new Stock(stock.getTicker());
	}
}
