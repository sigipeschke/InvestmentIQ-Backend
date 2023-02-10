package github.sigipeschke.StocksApp.YFinanceAPI;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.sigipeschke.StocksApp.Ledger.Ledger;
import github.sigipeschke.StocksApp.Ledger.LedgerService;


@RestController
@RequestMapping("/stock")
public class StockController {
	private final StockService stockService;
	private final LedgerService ledgerService;
	
	public StockController(StockService stockService, LedgerService ledgerService) {
		this.stockService = stockService;
		this.ledgerService = ledgerService;
	}
	
	/*
	@GetMapping("/all")
	public ResponseEntity<List<Stock>> getStocks() {
		List<Stock> Stocks = StockService.findAllStocks();
		return new ResponseEntity<>(Stocks, HttpStatus.OK);
	}
	*/
	
	@GetMapping("/all/{email}")
	public ResponseEntity<List<Stock>> getStocksByEmail(@PathVariable("email") String email) {
		List<Ledger> ledgers = ledgerService.findLedgersByEmail(email);
		List<String> tickers = new ArrayList<String>();
		for (Ledger ledger : ledgers) {
			if (!(tickers.contains(ledger.getTicker()))) {
				tickers.add(ledger.getTicker());
			}
		}
		List<Stock> stocks = new ArrayList<Stock>();
		for (String ticker : tickers) {
			stocks.add(stockService.genStock(ticker, this.ledgerService.findLedgersByTicker(email, ticker)));
		}
		return new ResponseEntity<List<Stock>>(stocks, HttpStatus.OK);
	}
}
