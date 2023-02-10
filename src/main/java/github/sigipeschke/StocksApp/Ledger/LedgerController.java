package github.sigipeschke.StocksApp.Ledger;

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


@RestController
@RequestMapping("/ledger")
public class LedgerController {
	private final LedgerService ledgerService;
	
	public LedgerController(LedgerService ledgerService) {
		this.ledgerService = ledgerService;
	}
	
	/*
	@GetMapping("/all")
	public ResponseEntity<List<Ledger>> getLedgers() {
		List<Ledger> ledgers = ledgerService.findAllLedgers();
		return new ResponseEntity<>(ledgers, HttpStatus.OK);
	}
	*/
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Ledger> getLedgersByEmail(@PathVariable("id") Long id) {
		Ledger ledger = ledgerService.findLedger(id);
		return new ResponseEntity<Ledger>(ledger, HttpStatus.OK);
	}
	
	@GetMapping("/history/{email}")
	public ResponseEntity<List<Ledger>> getLedgersByEmail(@PathVariable("email") String email) {
		List<Ledger> ledgers = ledgerService.findLedgersByEmail(email);
		return new ResponseEntity<List<Ledger>>(ledgers, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Ledger> addLedger(@RequestBody Ledger ledger) {
		Ledger newLedger = ledgerService.addLedger(ledger);
		return new ResponseEntity<>(newLedger, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateLedger(@PathVariable("id") Long id, @RequestBody Ledger ledger) {
		System.out.println(ledger.getID());
		System.out.println(ledger.getTicker());
		System.out.println(ledger.getTransaction());
		System.out.println(ledger.getDate());
		System.out.println(ledger.getPrice());
		System.out.println(ledger.getNotes());
		ledgerService.updateLedger(ledger);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteLedger(@PathVariable("id") Long id) {
		ledgerService.deleteLedger(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
