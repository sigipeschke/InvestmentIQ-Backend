package github.sigipeschke.StocksApp.OwnedStock;

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
@RequestMapping("/ownedstock")
public class OwnedStockController {
	private final StockService stockService;
	private final OwnedStockService ownedStockService;
	
	public OwnedStockController(StockService stockService, OwnedStockService ownedStockService) {
		this.stockService = stockService;
		this.ownedStockService = ownedStockService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Stock>> getOwnedStocks() {
		List<OwnedStock> oStocks = ownedStockService.findAllOwnedStocks(); //Add here
		List<Stock> stocks = new ArrayList<Stock>();
		for(OwnedStock oStock : oStocks) {
			stocks.add(stockService.convOwned(oStock));
		}
		return new ResponseEntity<>(stocks, HttpStatus.OK);
	}
	
	@GetMapping("/find/{ticker}")
	public ResponseEntity<Stock> getOwnedStocktById(@PathVariable("ticker") String ticker) { //Add here
		Stock stock = stockService.convOwned(ownedStockService.findOwnedStock(ticker));
		return new ResponseEntity<>(stock, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<OwnedStock> addOwnedStock(@RequestBody OwnedStock stock) {
		OwnedStock newStock = ownedStockService.addOwnedStock(stock);
		return new ResponseEntity<>(newStock, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{ticker}")
	public ResponseEntity<OwnedStock> updateOwnedStock(@PathVariable("ticker") String ticker, @RequestBody OwnedStock stock) {
		OwnedStock updateStock = ownedStockService.updateOwnedStock(stock);
		return new ResponseEntity<>(updateStock, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{ticker}")
	public ResponseEntity<?> deleteOwnedStock(@PathVariable("ticker") String ticker) {
		ownedStockService.deleteOwnedStock(ticker);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
