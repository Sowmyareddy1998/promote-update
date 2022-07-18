package ls.lesm.restcontroller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ls.lesm.exception.DateMissMatchException;
import ls.lesm.model.Address;
import ls.lesm.model.EmployeesAtClientsDetails;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.model.SubDepartments;
import ls.lesm.payload.request.EmpClientDetailsRequest;
import ls.lesm.repository.ClientsRepository;
import ls.lesm.repository.DepartmentsRepository;
import ls.lesm.repository.DesignationsRepository;
import ls.lesm.repository.EmployeeTypeRepository;
import ls.lesm.repository.EmployeesAtClientsDetailsRepository;
import ls.lesm.repository.MasterEmployeeDetailsRepository;
import ls.lesm.repository.SubDepartmentsRepository;
import ls.lesm.service.EmployeeDetailsService;

@RestController
@RequestMapping("/api/v1/emp")
public class EmployeeController {
	
	@Autowired
	private EmployeeDetailsService employeeDetailsService;
	
	
	@Autowired
	private MasterEmployeeDetailsRepository masterEmployeeDetailsRepository;
	@Autowired
	private DepartmentsRepository departmentsRepository;
	@Autowired
	private SubDepartmentsRepository subDepartmentsRepositorye;
	@Autowired
	private DesignationsRepository designationsRepository;
	@Autowired
	private EmployeeTypeRepository employeeTypeRepository;
	
	@Autowired
	private EmployeesAtClientsDetailsRepository employeesAtClientsDetailsRepository;
	
	@Autowired
	private ClientsRepository clientsRepository;
	
	@PostMapping("/insert-address")
	public ResponseEntity<?> adressFieldsInsertion(@RequestParam int addTypeId, @RequestBody Address address, Principal principal ){
		this.employeeDetailsService.insertEmpAddress(address, principal, addTypeId);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	

	@PostMapping("/insert-emp-details")
	public ResponseEntity<?> empDetailsInsertion(@RequestParam int desgId,
			                                     @RequestParam int subDepartId,
			                                     @RequestParam int supVId,
			                                     @RequestParam int empTypeId,
			                                     @RequestBody MasterEmployeeDetails empDetails,
			                                                  Principal principal ){
		 this.designationsRepository.findById(desgId).map(desg->{
				empDetails.setDesignations(desg);
				return desg;
			});
			 this.subDepartmentsRepositorye.findById(subDepartId).map(subD->{
				 empDetails.setSubDepartments(subD);
				 return subD;
			 });
			 this.masterEmployeeDetailsRepository.findById(supVId).map(sup->{
				 empDetails.setMasterEmployeeDetails(sup);
				 return sup;
			 });
			 this.employeeTypeRepository.findById(empTypeId).map(type->{
				 empDetails.setEmployeeType(type);
				 return type;
			 });
			 
		this.employeeDetailsService.insetEmpDetails(empDetails, principal);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
	@PostMapping("/inser-empat-client")
	public ResponseEntity<?> insertEmpAtClient(@RequestParam int empId,
                                               @RequestParam int clientId,
                                               @RequestBody EmployeesAtClientsDetails clientDetails,
                                               EmpClientDetailsRequest req,
                                               Principal principal){
		this.masterEmployeeDetailsRepository.findById(empId).map(id->{
			clientDetails.setMasterEmployeeDetails(id);
			return id;
		});
		this.clientsRepository.findById(clientId).map(cId->{
			clientDetails.setClients(cId);
			return cId;
		});
		this.employeeDetailsService.insertClientsDetails(clientDetails, principal, req);
		return new ResponseEntity<>(HttpStatus.CREATED);
}

	@GetMapping("/get-all")
	public ResponseEntity<List<EmployeesAtClientsDetails>> allEmpDetailsAtClient(){
		
		List<EmployeesAtClientsDetails> all=this.employeesAtClientsDetailsRepository.findAll();
		return new ResponseEntity<List<EmployeesAtClientsDetails>>(all, HttpStatus.OK);
	}
	
	@GetMapping("/alll")
	public ResponseEntity<List<Object[]>> allDetails(){
	List<Object[]> all=	this.employeesAtClientsDetailsRepository.getMeAll();
		return new ResponseEntity<List<Object[]>>(all, HttpStatus.OK);
	}
}