package github.sigipeschke.StocksApp.WatchStock;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchStockRepo extends JpaRepository<WatchStock, Long> {
	void deleteWatchStockByTicker(String ticker);
	
	Optional<WatchStock> findWatchStockByTicker(String ticker);
}