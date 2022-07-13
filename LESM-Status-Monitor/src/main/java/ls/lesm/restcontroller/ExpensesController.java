package ls.lesm.restcontroller;

import java.security.Principal;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ls.lesm.model.EmployeesAtClientsDetails;
import ls.lesm.model.OnsiteBillExpenses;
import ls.lesm.service.impl.ExpenseServiceImpl;

@RestController
@RequestMapping("api/v1/exp")
public class ExpensesController {
	
	@Autowired
	private ExpenseServiceImpl expenseService;
	
	@PostMapping("/insert-expenses")
	public ResponseEntity<?> expensesFieldInsertion(@RequestParam int expTypeId,
			                                        @RequestParam int empId,
			                                        @RequestBody OnsiteBillExpenses billExp,
			                                        Principal principal ){
		
		this.expenseService.insertBillExp(billExp, principal, expTypeId, empId);
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
		
	}
	
	
}
