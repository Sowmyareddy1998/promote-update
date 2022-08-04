package ls.lesm.restcontroller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ls.lesm.exception.NoDataException;
import ls.lesm.model.AddressType;
import ls.lesm.model.Clients;
import ls.lesm.model.Departments;
import ls.lesm.model.Designations;
import ls.lesm.model.EmployeeType;
import ls.lesm.model.OnsiteExpensesType;
import ls.lesm.model.SubDepartments;
import ls.lesm.payload.response.ForeignkeyResponse;
import ls.lesm.repository.AddressRepositoy;
import ls.lesm.repository.ClientsRepository;
import ls.lesm.repository.DesignationsRepository;
import ls.lesm.repository.EmployeeTypeRepository;
import ls.lesm.repository.OnsiteExpensesTypeRepository;
import ls.lesm.service.ConstantFieldService;

@RestController
@RequestMapping("/api/v1/fields")
@CrossOrigin("*")
public class ConstantFieldController {
	
	@Autowired
	private ConstantFieldService contantConstantFieldService;
	
	@Autowired
	private ClientsRepository clientsRepository;
	
	@Autowired
	private DesignationsRepository designationsRepository;
	
	@Autowired
	private EmployeeTypeRepository employeeTypeRepository;
	
	@Autowired
	private OnsiteExpensesTypeRepository onsiteExpensesTypeRepository;
	
	@Autowired
	private AddressRepositoy addressRepositoy;
	
	@PostMapping("/insert-desig")
    @PreAuthorize("hasAuthority('HR')")
	public ResponseEntity<?> desigFieldInsertions(@RequestBody Designations desig, Principal principal){
		this.contantConstantFieldService.insertDesg(desig, principal);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/insert-depart")
	   @PreAuthorize("hasAuthority('HR')")
	public ResponseEntity<?> departFieldInsertions(@RequestBody Departments depart, Principal principal){
		this.contantConstantFieldService.insertDepart(depart, principal);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/insert-sub-depart/{departId}")
	   @PreAuthorize("hasAuthority('HR')")
	public ResponseEntity<?> subDepartFieldInsertions(@RequestBody SubDepartments subDepart, Principal principal,@PathVariable int departId){
		this.contantConstantFieldService.insertSubDepart(subDepart, principal,departId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/insert-clients")
	   @PreAuthorize("hasAuthority('MANAGER')")
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
	   @PreAuthorize("hasAuthority('MANAGER')")
	public ResponseEntity<List<SubDepartments>> allSubDeparts(){
		List<SubDepartments> all=this.contantConstantFieldService.getAllSubDepartments();
		return new ResponseEntity<List<SubDepartments>>(all, HttpStatus.OK);
	}
	
	@GetMapping("/get-all-Depart")
	   @PreAuthorize("hasAuthority('MANAGER')")
	public ResponseEntity<List<Departments>> allDeparts(){
		List<Departments> all=this.contantConstantFieldService.getAllDepartments();
		return new ResponseEntity<List<Departments>>(all, HttpStatus.OK);
	}
	
	@GetMapping("/get-all-addType")
	public ResponseEntity<List<AddressType>> allAddType(){
		List<AddressType> addType=contantConstantFieldService.getAllAddType();
		return new ResponseEntity<List<AddressType>>(addType, HttpStatus.OK);
	}
	
	@GetMapping("/get-all-clients")
	   @PreAuthorize("hasAuthority('MANAGER')")
	public ResponseEntity<List<Clients>> getAllClients(){
		List<Clients> allC=this.clientsRepository.findAll();
		if(allC.isEmpty())
			throw new NoDataException("106","depart records not there in database");
		else
		return new ResponseEntity<List<Clients>>(allC,HttpStatus.OK);
	}
	
	@GetMapping("/get-all-desg")
	 @PreAuthorize("hasAuthority('MANAGER')")
	public ResponseEntity<List<Designations>> getAllDesgList(){
		List<Designations> allDsg=designationsRepository.findAll();
		return new ResponseEntity<List<Designations>>(allDsg,HttpStatus.OK);
	}
	
	@GetMapping("/get-all-empTypes")
	public ResponseEntity<List<EmployeeType>> getallEmpTypeList(){
		List<EmployeeType> allType=this.employeeTypeRepository.findAll();
		return new ResponseEntity<List<EmployeeType>>(allType,HttpStatus.OK);
	}
	
	@GetMapping("/get-all-expType")
	public ResponseEntity<List<OnsiteExpensesType>> getExpType(){
		List<OnsiteExpensesType> allType=this.onsiteExpensesTypeRepository.findAll();
		return new ResponseEntity<List<OnsiteExpensesType>>(allType, HttpStatus.OK);
	}
	
	
	//@GetMapping("/get-all-clients")
	   //@PreAuthorize("hasAuthority('MANAGER')")
//	public ResponseEntity List<Clients>  updateClients(@RequestBody Clients clients, Principal principal){
//		Clients g=this.clientsRepository.getById(clients.getClientsId());
//		if(clients.getClientsNames().equals(g))
//			this.contantConstantFieldService.insertClient(clients, principal);
//		
//		return new ResponseEntity<List<Clients>>(HttpStatus.OK);
//	}
  
		
	}
	
	
	
	



