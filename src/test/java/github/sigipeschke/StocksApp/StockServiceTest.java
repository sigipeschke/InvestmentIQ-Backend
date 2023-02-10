package github.sigipeschke.StocksApp;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import github.sigipeschke.StocksApp.OwnedStock.OwnedStock;
import github.sigipeschke.StocksApp.OwnedStock.OwnedStockService;
import github.sigipeschke.StocksApp.YFinanceAPI.Stock;
import github.sigipeschke.StocksApp.YFinanceAPI.StockService;
import github.sigipeschke.StocksApp.YFinanceAPI.StockWrapper;
import yahoofinance.histquotes.HistoricalQuote;

@SpringBootTest
class StockServiceTest {
	/*
	@Autowired
	private StockService stockService;
	private OwnedStockService ownedStockService;
	
	@Test
	void invokeTest() throws IOException {
		System.out.println("--- Commencing invoke test ---");
		final StockWrapper stock = stockService.findStock("UU.L");
		System.out.println("Stock: " + stock.getStock());
		
		final BigDecimal price = stockService.findPrice(stock);
		System.out.println("Price: " + price);
		
		final BigDecimal change = stockService.findLastChangePercent(stock);
		System.out.println("Percent Change: " + change);
		
		final BigDecimal mean200DayPercent = stockService.findChangeFrom200MeanPercent(stock);
		System.out.println("200 Day Mean Percent Change: " + mean200DayPercent);
		
		//final List<HistoricalQuote> history = stockService.findHistory(stock);
		//System.out.println("History: " + history);
		
		System.out.println("Commencing mainframe test");
		OwnedStock oStock = ownedStockService.findOwnedStock("TSLA");
		Stock s = stockService.convOwned(oStock);
		System.out.println("Test stock" + s.getName() + s.getTicker() + s.getPrice());
	}
	
	@Test
	void multiple() throws IOException, InterruptedException {
		System.out.println("--- Commencing multiple test ---");
		final String[] stocksArr = new String[] {"GOOG", "AMZN"};
		final List<String> stocksList = Arrays.asList(stocksArr);
		final List<StockWrapper> stocks = stockService.findStocks(stocksList);
		
		System.out.println("Stocks: " + stocks);
		
		findPrices(stocks);
		Thread.sleep(16000);
		
		final StockWrapper aa = stockService.findStock("UU.L");
		stocks.add(aa);
		
		System.out.println(stockService.findPrice(aa));
		
		findPrices(stocks);
	}
	
	private void findPrices(List<StockWrapper> stocks) {
		stocks.forEach(stock -> {
			try {
				System.out.println(stockService.findPrice(stock));
			}
			catch (IOException e) {
				//Ignore
			}
		});
	}
	*/
}
