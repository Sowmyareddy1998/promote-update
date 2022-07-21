package ls.lesm.restcontroller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ls.lesm.exception.RecordNotFoundException;
import ls.lesm.exception.RelationNotFoundExceptions;
import ls.lesm.model.Address;
import ls.lesm.model.EmployeeStatus;
import ls.lesm.model.EmployeesAtClientsDetails;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.model.User;
import ls.lesm.payload.request.EmployeeDetailsRequest;
import ls.lesm.payload.response.EmployeeDetailsResponse;
import ls.lesm.payload.response.Response;
import ls.lesm.repository.AddressRepositoy;
import ls.lesm.repository.ClientsRepository;
import ls.lesm.repository.DepartmentsRepository;
import ls.lesm.repository.DesignationsRepository;
import ls.lesm.repository.EmployeeTypeRepository;
import ls.lesm.repository.EmployeesAtClientsDetailsRepository;
import ls.lesm.repository.MasterEmployeeDetailsRepository;
import ls.lesm.repository.SubDepartmentsRepository;
import ls.lesm.repository.UserRepository;
import ls.lesm.service.impl.EmployeeDetailsServiceImpl;

@RestController
@RequestMapping("/api/v1/emp")
@CrossOrigin("*")
public class EmployeeController {
	
	@Autowired
	private EmployeeDetailsServiceImpl employeeDetailsService;
	
	
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
	
	@Autowired
	private AddressRepositoy addressRepositoy;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/insert-address")
	public ResponseEntity<?> adressFieldsInsertion(@RequestParam int addTypeId, @RequestBody Address address, Principal principal ){
		this.employeeDetailsService.insertEmpAddress(address, principal, addTypeId);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	

	@PostMapping("/insert-emp-details")
	public ResponseEntity<?> empDetailsInsertion(@RequestParam(required=false) Integer subVId,
			                                     @RequestParam(required=false) Integer departId,
			                                     @RequestParam(required=false) Integer subDepartId,
			                                     @RequestParam(required=false) Integer desgId,
			                                     @RequestParam(required=false, defaultValue ="0") Integer typeId,
			                                     @RequestParam Integer addressId,
			                                     @RequestBody EmployeeDetailsRequest empReq,
			                                   
			                                                  Principal principal ){
		this.masterEmployeeDetailsRepository.findById(subVId).map(id->{
			empReq.getMasterEmployeeDetails().setSupervisor(id);
			return id;
		});
		
		this.departmentsRepository.findById(departId).map(id->{
			empReq.getMasterEmployeeDetails().setDepartments(id);
			return id;
		});
		
		this.subDepartmentsRepositorye.findById(subDepartId).map(id->{
			empReq.getMasterEmployeeDetails().setSubDepartments(id);
			return id;
		});
		this.designationsRepository.findById(desgId).map(id->{
			empReq.getMasterEmployeeDetails().setDesignations(id);
			return id;
		});
		this.employeeTypeRepository.findById(typeId).map(id->{
			empReq.getMasterEmployeeDetails().setEmployeeType(id);
			return id;
		});		
		
//		this.addressRepositoy.findById(addressId).map(id->{
//			empReq.getAddress().setMasterEmployeeDetails(id);
//			return id;		
//		});
		
		
		
		this.employeeDetailsService.insetEmpDetails(empReq, principal);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
	@PostMapping("/inser-empat-client")
	public ResponseEntity<?> insertEmpAtClient(@RequestParam int empId,
                                               @RequestParam int clientId,
                                               @RequestBody  EmployeesAtClientsDetails clientDetails,
                                                             Principal principal){
		
		this.masterEmployeeDetailsRepository.findById(empId).map(id->{
			clientDetails.setMasterEmployeeDetails(id);
			return id;
		}).orElseThrow(()-> new RelationNotFoundExceptions("Employee with this id '" +empId+"' not exist","415",""));
		this.clientsRepository.findById(clientId).map(cId->{
			clientDetails.setClients(cId);
			return cId;
		}).orElseThrow(()-> new RelationNotFoundExceptions("this client with this id '"+clientId+"' not exist","416",""));
		
       /*   List<EmployeesAtClientsDetails> currentRecord=employeesAtClientsDetailsRepository.findAll();
		
		System.out.println("----------------"+currentRecord);
		
		if(currentRecord.get().getMasterEmployeeDetails().getEmpId()==clientDetails.getMasterEmployeeDetails().getEmpId() && clientDetails.getPOEdate()==null) {
			throw new RecordAlredyExistException("This employee '"+currentRecord.get().getMasterEmployeeDetails().getEmpId()+"' alreday working with '"+
		                                          currentRecord.get().getClients().getClientsNames()
		                                          +"' this client please enter Po E date to register this employee","201");
		
		}*/
	
		
		this.employeeDetailsService.insertClientsDetails(clientDetails, principal);
		return new ResponseEntity<>(HttpStatus.CREATED);
}
	
	@GetMapping("/get-all")
	public ResponseEntity<List<EmployeesAtClientsDetails>> allEmpDetailsAtClient(){
		
		List<EmployeesAtClientsDetails> all=this.employeesAtClientsDetailsRepository.findAll();
		
		return new ResponseEntity<List<EmployeesAtClientsDetails>>(all, HttpStatus.OK);
	}

	@GetMapping("/get-details-byId/{id}")
	public ResponseEntity<EmployeesAtClientsDetails> getDetailsOfEmpAtClientById(@RequestParam int id){
		
		EmployeesAtClientsDetails clientDetails=employeesAtClientsDetailsRepository.findById(id).orElseThrow(()->
		new RecordNotFoundException("Client Details with this id '"+id+"' not exist in database","51"));
		
		Optional<MasterEmployeeDetails> employee=this.masterEmployeeDetailsRepository.findById(clientDetails.getMasterEmployeeDetails().getEmpId());
		if(clientDetails.getPOEdate()==null) {
		clientDetails.setTenure(ChronoUnit.MONTHS.between(clientDetails.getPOSdate(), LocalDate.now()));
		
		employee.get().setStatus(EmployeeStatus.ACTIVE);
		masterEmployeeDetailsRepository.save(employee.get());
		}
		else 
			employee.get().setStatus(EmployeeStatus.BENCH);
		
			clientDetails.setTenure(ChronoUnit.MONTHS.between(clientDetails.getPOSdate(), clientDetails.getPOEdate()));
		
		clientDetails.setTotalEarningAtclient(clientDetails.getClientSalary()*clientDetails.getTenure());
		this.employeesAtClientsDetailsRepository.save(clientDetails);
		return new ResponseEntity<EmployeesAtClientsDetails>(clientDetails,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAll-detail-empAtClient")
	public ResponseEntity<Map<String, Object>> getAllDetailsOfEmpAtClient(
			@RequestParam(value="pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue="10", required=false)Integer pageSize){
		
		try {
			Page<EmployeesAtClientsDetails> clientDetails=employeeDetailsService.getAllEmpClinetDetails(PageRequest.of(pageNumber, pageSize));
			Map<String, Object> response = new HashMap<>();
			List<EmployeesAtClientsDetails> allEmployee=clientDetails.getContent();
			response.put("User", allEmployee);
			response.put("currentPage", clientDetails.getNumber());
			response.put("totalItems", clientDetails.getTotalElements());
			response.put("totalPages", clientDetails.getTotalPages());
			List<EmployeesAtClientsDetails> deatils=allEmployee;
			List<Integer> bdId=deatils.stream().map(EmployeesAtClientsDetails::getEmpAtClientId).collect(Collectors.toList());
			List<EmployeesAtClientsDetails> clientDetailsAll=employeesAtClientsDetailsRepository.findAllById(bdId);
			  
			//for(int i=0; i<=clientDetailsAll.size(); i++) {
				for(EmployeesAtClientsDetails i: clientDetailsAll) {
					
				Optional<EmployeesAtClientsDetails> currentRecord=employeesAtClientsDetailsRepository.findById(i.getEmpAtClientId());
				Optional<MasterEmployeeDetails> employee=this.masterEmployeeDetailsRepository.findById(currentRecord.get().getMasterEmployeeDetails().getEmpId()); 
				if(currentRecord.get().getPOEdate()==null) {
					currentRecord.get().setTenure(ChronoUnit.MONTHS.between(currentRecord.get().getPOSdate(), LocalDate.now()));
					employee.get().setStatus(EmployeeStatus.ACTIVE);
					this.masterEmployeeDetailsRepository.save(employee.get());
				}
				else {
					employee.get().setStatus(EmployeeStatus.BENCH);
				this.masterEmployeeDetailsRepository.save(employee.get());
				currentRecord.get().setTenure(ChronoUnit.MONTHS.between(currentRecord.get().getPOSdate(), currentRecord.get().getPOEdate()));
				}
				currentRecord.get().setTotalEarningAtclient(currentRecord.get().getClientSalary()*currentRecord.get().getTenure());
				this.employeesAtClientsDetailsRepository.save(currentRecord.get());
			}
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getingAll")
	public ResponseEntity<List<Response>> getEmpById(){
		
		 List<Response>all= this.employeesAtClientsDetailsRepository.findDataResponseAll();
		 
		return new ResponseEntity<List<Response>>(all,HttpStatus.OK); 
	
	}
	@GetMapping("/get-all-empDetails")
	public ResponseEntity<List<EmployeeDetailsResponse>> getAllEmp(){
		List<EmployeeDetailsResponse>all=this.masterEmployeeDetailsRepository.getAllEmpDetails();
		return new ResponseEntity<List<EmployeeDetailsResponse>>(all,HttpStatus.OK);
	}
	
	@GetMapping("/address-by-id")
	public ResponseEntity<List<Object[]>> findEmpAddById(@RequestParam int id){
		List<Object[]> add= (List<Object[]>) this.addressRepositoy.findByEmpIdFk(id);
		return new ResponseEntity<List<Object[]>>(add,HttpStatus.OK);
	}
	
	@GetMapping("/getEmps")
	public ResponseEntity<List<MasterEmployeeDetails>> getEmp( Principal principal){
		
		User loggedU=this.userRepository.findByUsername(principal.getName());
		String id=loggedU.getUsername();
		MasterEmployeeDetails employee=this.masterEmployeeDetailsRepository.findByLancesoft(id);
		int dbPk=employee.getEmpId();
		List<MasterEmployeeDetails> ls = masterEmployeeDetailsRepository.findBymasterEmployeeDetails_Id(dbPk);

		return new ResponseEntity<List<MasterEmployeeDetails>>(ls,HttpStatus.OK);
		
	}

}