package ls.lesm.restcontroller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ls.lesm.model.Address;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.payload.request.EmpDetailsRequest;
import ls.lesm.service.EmployeeDetailsService;

@RestController
@RequestMapping("/api/v1/emp")
public class EmployeeController {
	
	@Autowired
	private EmployeeDetailsService employeeDetailsService;
	
	@PostMapping("/insert-address")
	public ResponseEntity<?> adressFieldsInsertion(@RequestParam int addTypeId, @RequestBody Address address, Principal principal ){
		this.employeeDetailsService.insertEmpAddress(address, principal, addTypeId);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	

	@PostMapping("/insert-emp-details")
	public ResponseEntity<?> empDetailsInsertion(@RequestParam int ids, @RequestBody MasterEmployeeDetails empDetails, Principal principal ){
		this.employeeDetailsService.insetEmpDetails(empDetails, principal, ids);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}

}
