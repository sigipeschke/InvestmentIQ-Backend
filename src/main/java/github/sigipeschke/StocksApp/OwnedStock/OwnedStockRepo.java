package github.sigipeschke.StocksApp.OwnedStock;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedStockRepo extends JpaRepository<OwnedStock, Long> {
	void deleteOwnedStockByTicker(String ticker);
	
	Optional<OwnedStock> findOwnedStockByTicker(String ticker);
}

