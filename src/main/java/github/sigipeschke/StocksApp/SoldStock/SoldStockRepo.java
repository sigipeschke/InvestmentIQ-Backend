package github.sigipeschke.StocksApp.SoldStock;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoldStockRepo extends JpaRepository<SoldStock, Long> {
	void deleteSoldStockByTicker(String ticker);
	
	Optional<SoldStock> findSoldStockByTicker(String ticker);
}
