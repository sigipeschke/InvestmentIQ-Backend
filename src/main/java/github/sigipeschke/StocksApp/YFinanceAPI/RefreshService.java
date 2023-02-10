package github.sigipeschke.StocksApp.YFinanceAPI;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import static java.util.concurrent.TimeUnit.SECONDS;
import org.springframework.stereotype.Service;

@Service
public class RefreshService {
	private final Map<StockWrapper, Boolean> stocksToRefresh;
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private static final int refreshPeriod = 15;
	private static final Duration refreshDuration = Duration.ofSeconds(refreshPeriod);
	
	public RefreshService() {
		stocksToRefresh = new HashMap<>();
		setRefresh();
	}
	
	public boolean shouldRefresh(final StockWrapper stock) {
		if (!stocksToRefresh.containsKey(stock)) {
			stocksToRefresh.put(stock,  false);
			return true;
		}
		return stocksToRefresh.get(stock);
	}
	
	private void setRefresh() {
		scheduler.scheduleAtFixedRate(() -> 
											stocksToRefresh.forEach((stock, value) -> {
												if (stock.getLastAccessed().isBefore(LocalDateTime.now().minus(refreshDuration))) {
													System.out.println("Refreshing " + stock.getStock().getSymbol());
													stocksToRefresh.remove(stock);
													stocksToRefresh.put(stock.withLastAccessed(LocalDateTime.now()), true);
												}
											}), 0, refreshPeriod, SECONDS);
	}
}
