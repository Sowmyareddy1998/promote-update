package ls.lesm.restcontroller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ls.lesm.model.Clients;
import ls.lesm.model.Departments;
import ls.lesm.model.Designations;
import ls.lesm.model.EmployeeType;
import ls.lesm.model.SubDepartments;
import ls.lesm.service.ConstantFieldService;

@RestController
@RequestMapping("/api/v1/fields")
public class ConstantFieldController {
	
	@Autowired
	private ConstantFieldService contantConstantFieldService;
	
	@PostMapping("/insert-desig")
	public ResponseEntity<?> desigFieldInsertions(@RequestBody Designations desig, Principal principal){
		this.contantConstantFieldService.insertDesg(desig, principal);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/insert-depart")
	public ResponseEntity<?> departFieldInsertions(@RequestBody Departments depart, Principal principal){
		this.contantConstantFieldService.insertDepart(depart, principal);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/insert-sub-depart/{departId}")
	public ResponseEntity<?> subDepartFieldInsertions(@RequestBody SubDepartments subDepart, Principal principal,@PathVariable int departId){
		this.contantConstantFieldService.insertSubDepart(subDepart, principal,departId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/insert-clients")
	public ResponseEntity<?> clientsFieldInsertions(@RequestBody Clients clients, Principal principal){
		this.contantConstantFieldService.insertClient(clients, principal);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/insert-employee-type")
	public ResponseEntity<?> empTypesFieldInsertions(@RequestBody EmployeeType type){
		this.contantConstantFieldService.insertEmpTypes(type);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
