package github.sigipeschke.StocksApp.WatchStock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.sigipeschke.StocksApp.YFinanceAPI.Stock;
import github.sigipeschke.StocksApp.YFinanceAPI.StockService;


@RestController
@RequestMapping("/watchstock")
public class WatchStockController {
	private final StockService stockService;
	private final WatchStockService watchStockService;
	
	public WatchStockController(StockService stockService, WatchStockService watchStockService) {
		this.stockService = stockService;
		this.watchStockService = watchStockService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Stock>> getWatchStocks() {
		List<WatchStock> wStocks = watchStockService.findAllWatchStocks(); //Add here
		List<Stock> stocks = new ArrayList<Stock>();
		for(WatchStock wStock : wStocks) {
			stocks.add(stockService.convWatch(wStock));
		}
		return new ResponseEntity<>(stocks, HttpStatus.OK);
	}
	
	@GetMapping("/find/{ticker}")
	public ResponseEntity<Stock> getWatchStocktById(@PathVariable("ticker") String ticker) { //Add here
		Stock stock = stockService.convWatch(watchStockService.findWatchStock(ticker));
		return new ResponseEntity<>(stock, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<WatchStock> addWatchStock(@RequestBody WatchStock stock) {
		WatchStock newStock = watchStockService.addWatchStock(stock);
		return new ResponseEntity<>(newStock, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{ticker}")
	public ResponseEntity<?> deleteContact(@PathVariable("ticker") String ticker) {
		watchStockService.deleteWatchStock(ticker);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}