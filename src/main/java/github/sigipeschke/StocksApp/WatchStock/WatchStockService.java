package github.sigipeschke.StocksApp.WatchStock;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.sigipeschke.StocksApp.StockNotFoundException;

@Service
@Transactional
public class WatchStockService {
	private final WatchStockRepo watchStockRepo;
	
	@Autowired
	public WatchStockService(WatchStockRepo watchStockRepo) {
		this.watchStockRepo = watchStockRepo;
	}
	
	public WatchStock addWatchStock(WatchStock watchStock) {
		return watchStockRepo.save(watchStock);
	}
	
	public List<WatchStock> findAllWatchStocks() {
		return watchStockRepo.findAll();
	}
	
	public WatchStock findWatchStock(String ticker) {
		return watchStockRepo.findWatchStockByTicker(ticker)
				.orElseThrow(() -> new StockNotFoundException("Stock by ticker " + ticker + " was not found"));
	}
	
	public void deleteWatchStock(String ticker) {
		watchStockRepo.deleteWatchStockByTicker(ticker);
	}
}