package github.sigipeschke.StocksApp.Ledger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LedgerRepo extends JpaRepository<Ledger, Long> {
	@Query(" SELECT l FROM Ledger l WHERE LOWER(l.email) = LOWER(:email) ")
	List<Ledger> findLedgersByEmail(@Param("email") String email);
	
	@Query(" SELECT l FROM Ledger l WHERE LOWER(l.email) = LOWER(:email) AND LOWER(l.ticker) = LOWER(:ticker) ")
	List<Ledger> findLedgersByTicker(@Param("email") String email, @Param("ticker") String ticker);
	
	Optional<Ledger> findLedgerById(Long id);
	
	void deleteLedgerById(Long id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(" UPDATE Ledger l SET l.transaction = :transaction, l.date = :date, l.shares = :shares, l.price = :price, l.notes = :notes WHERE l.id = :id ")
	void updateLedger(@Param("id") Long id, @Param("transaction") String transaction, @Param("date") String date, @Param("shares") BigDecimal shares, @Param("price") BigDecimal price, @Param("notes") String notes);
}

