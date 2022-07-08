package ls.lesm.restcontroller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ls.lesm.model.AddressType;
import ls.lesm.model.Clients;
import ls.lesm.model.Departments;
import ls.lesm.model.Designations;
import ls.lesm.model.EmployeeType;
import ls.lesm.model.OnsiteExpensesType;
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
	
	@PostMapping("/insert-expens-types")
	public ResponseEntity<?> expTypesFieldInsertions(@RequestBody OnsiteExpensesType exptype, Principal principal){
		this.contantConstantFieldService.insertExpType(exptype, principal);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/insert-adsress-types")
	public ResponseEntity<?> addTypeFieldInsertions(@RequestBody AddressType addType){
		this.contantConstantFieldService.insertAddressTyp(addType);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/get-all-subDepart")
	public ResponseEntity<List<SubDepartments>> allSubDeparts(){
		List<SubDepartments> all=this.contantConstantFieldService.getAllSubDepartments();
		return new ResponseEntity<List<SubDepartments>>(all, HttpStatus.OK);
	}
	
	@GetMapping("/get-all-Depart")
	public ResponseEntity<List<Departments>> allDeparts(){
		List<Departments> all=this.contantConstantFieldService.getAllDepartments();
		return new ResponseEntity<List<Departments>>(all, HttpStatus.OK);
	}

}
