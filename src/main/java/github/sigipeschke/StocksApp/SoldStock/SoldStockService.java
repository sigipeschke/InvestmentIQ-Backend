package github.sigipeschke.StocksApp.SoldStock;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.sigipeschke.StocksApp.StockNotFoundException;

@Service
@Transactional
public class SoldStockService {
	private final SoldStockRepo soldStockRepo;
	
	@Autowired
	public SoldStockService(SoldStockRepo soldStockRepo) {
		this.soldStockRepo = soldStockRepo;
	}
	
	public SoldStock addSoldStock(SoldStock soldStock) {
		return soldStockRepo.save(soldStock);
	}
	
	public List<SoldStock> findAllSoldStocks() {
		return soldStockRepo.findAll();
	}
	
	public SoldStock updateSoldStock(SoldStock soldStock) {
		return soldStockRepo.save(soldStock);
	}
	
	public SoldStock findSoldStock(String ticker) {
		return soldStockRepo.findSoldStockByTicker(ticker)
				.orElseThrow(() -> new StockNotFoundException("Stock by ticker " + ticker + " was not found"));
	}
	
	public void deleteSoldStock(String ticker) {
		soldStockRepo.deleteSoldStockByTicker(ticker);
	}
}