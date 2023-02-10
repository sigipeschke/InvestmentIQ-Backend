package github.sigipeschke.StocksApp.SoldStock;

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
@RequestMapping("/soldstock")
public class SoldStockController {
	private final StockService stockService;
	private final SoldStockService soldStockService;
	
	public SoldStockController(StockService stockService, SoldStockService soldStockService) {
		this.stockService = stockService;
		this.soldStockService = soldStockService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Stock>> getSoldStocks() {
		List<SoldStock> sStocks = soldStockService.findAllSoldStocks(); //Add here
		List<Stock> stocks = new ArrayList<Stock>();
		for(SoldStock sStock : sStocks) {
			stocks.add(stockService.convSold(sStock));
		}
		return new ResponseEntity<>(stocks, HttpStatus.OK);
	}
	
	@GetMapping("/find/{ticker}")
	public ResponseEntity<Stock> getSoldStocktById(@PathVariable("ticker") String ticker) { //Add here
		Stock stock = stockService.convSold(soldStockService.findSoldStock(ticker));
		return new ResponseEntity<>(stock, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<SoldStock> addSoldStock(@RequestBody SoldStock stock) {
		SoldStock newStock = soldStockService.addSoldStock(stock);
		return new ResponseEntity<>(newStock, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{ticker}")
	public ResponseEntity<SoldStock> updateSoldStock(@PathVariable("ticker") String ticker, @RequestBody SoldStock stock) {
		SoldStock updateStock = soldStockService.updateSoldStock(stock);
		return new ResponseEntity<>(updateStock, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{ticker}")
	public ResponseEntity<?> deleteContact(@PathVariable("ticker") String ticker) {
		soldStockService.deleteSoldStock(ticker);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
