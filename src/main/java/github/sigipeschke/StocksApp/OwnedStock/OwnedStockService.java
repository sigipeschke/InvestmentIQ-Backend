package github.sigipeschke.StocksApp.OwnedStock;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.sigipeschke.StocksApp.StockNotFoundException;

@Service
@Transactional
public class OwnedStockService {
	private final OwnedStockRepo ownedStockRepo;
	
	@Autowired
	public OwnedStockService(OwnedStockRepo ownedStockRepo) {
		this.ownedStockRepo = ownedStockRepo;
	}
	
	public OwnedStock addOwnedStock(OwnedStock OwnedStock) {
		return ownedStockRepo.save(OwnedStock);
	}
	
	public List<OwnedStock> findAllOwnedStocks() {
		return ownedStockRepo.findAll();
	}
	
	public OwnedStock updateOwnedStock(OwnedStock OwnedStock) {
		return ownedStockRepo.save(OwnedStock);
	}
	
	public OwnedStock findOwnedStock(String ticker) {
		return ownedStockRepo.findOwnedStockByTicker(ticker)
				.orElseThrow(() -> new StockNotFoundException("Stock by ticker " + ticker + " was not found"));
	}
	
	public void deleteOwnedStock(String ticker) {
		ownedStockRepo.deleteOwnedStockByTicker(ticker);
	}
}