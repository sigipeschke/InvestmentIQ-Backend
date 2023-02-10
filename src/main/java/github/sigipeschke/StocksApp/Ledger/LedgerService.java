package github.sigipeschke.StocksApp.Ledger;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.sigipeschke.StocksApp.StockNotFoundException;

@Service
@Transactional
public class LedgerService {
	private final LedgerRepo ledgerRepo;
	
	@Autowired
	public LedgerService(LedgerRepo ledgerRepo) {
		this.ledgerRepo = ledgerRepo;
	}
	
	public Ledger addLedger(Ledger ledger) {
		return ledgerRepo.save(ledger);
	}
	
	public List<Ledger> findAllLedgers() {
		return ledgerRepo.findAll();
	}
	
	public void updateLedger(Ledger ledger) {
		ledgerRepo.updateLedger(ledger.getID(), ledger.getTransaction(), ledger.getDate(), ledger.getShares(), ledger.getPrice(), ledger.getNotes());
	}
	
	public Ledger findLedger(Long id) {
		return ledgerRepo.findLedgerById(id)
				.orElseThrow(() -> new StockNotFoundException("Ledger by id " + id + " was not found"));
	}
	
	public List<Ledger> findLedgersByEmail(String email) {
		return ledgerRepo.findLedgersByEmail(email);
	}
	
	public List<Ledger> findLedgersByTicker(String email, String ticker) {
		return ledgerRepo.findLedgersByTicker(email, ticker);
	}
	
	public void deleteLedger(Long id) {
		ledgerRepo.deleteLedgerById(id);
	}
}