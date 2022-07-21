	package ls.lesm.service.impl;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ls.lesm.model.Address;
import ls.lesm.model.EmployeeStatus;
import ls.lesm.model.EmployeesAtClientsDetails;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.payload.request.EmployeeDetailsRequest;
import ls.lesm.repository.AddressRepositoy;
import ls.lesm.repository.AddressTypeRepository;
import ls.lesm.repository.DepartmentsRepository;
import ls.lesm.repository.DesignationsRepository;
import ls.lesm.repository.EmployeesAtClientsDetailsRepository;
import ls.lesm.repository.MasterEmployeeDetailsRepository;
import ls.lesm.repository.SubDepartmentsRepository;
import ls.lesm.service.EmployeeDetailsService;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {
	
	@Autowired
	private AddressRepositoy addressRepositoy;
	
	@Autowired
	private AddressTypeRepository addressTypeRepository;
	
	@Autowired
	private MasterEmployeeDetailsRepository masterEmployeeDetailsRepository;
	
	@Autowired
	private DepartmentsRepository departmentsRepository;
	
	@Autowired
	private SubDepartmentsRepository subDepartmentsRepositorye;
	
	@Autowired
	private DesignationsRepository designationsRepository;
	
	@Autowired
	private EmployeesAtClientsDetailsRepository employeesAtClientsDetailsRepository;
	
		
	@Override
	public Address insertEmpAddress(Address address, Principal principal, Integer addTypeId) {
		address.setCreatedAt(LocalDate.now());
		address.setCreatedBy(principal.getName());
		Optional<Object> optional=addressTypeRepository.findById(addTypeId).map(type->{
		address.setAdressType(type);
		return type;
		});
		return addressRepositoy.save(address);
	}

	
	public EmployeeDetailsRequest insetEmpDetails(EmployeeDetailsRequest empDetails,  Principal principal) {
		empDetails.getMasterEmployeeDetails().setCreatedBy(principal.getName());
		empDetails.getMasterEmployeeDetails().setStatus(EmployeeStatus.BENCH);
		
		
		empDetails.getEmployeesAtClientsDetails().setCreatedAt(LocalDate.now());
		empDetails.getEmployeesAtClientsDetails().setCreatedBy(principal.getName());

				this.masterEmployeeDetailsRepository.save(empDetails.getMasterEmployeeDetails());

				this.employeesAtClientsDetailsRepository.save(empDetails.getEmployeesAtClientsDetails());
				return empDetails;
	}

	@Override
	public EmployeesAtClientsDetails insertClientsDetails(EmployeesAtClientsDetails clientDetails,
			Principal principal) {
		clientDetails.setCreatedBy(principal.getName());
		clientDetails.setCreatedAt(LocalDate.now());
	 
		//if(clientDetails.getPOSdate().before(clientDetails.getPOEdate()))
			//throw new DateMissMatchException("Po start date can not be before po end date","408");
		
			
		return employeesAtClientsDetailsRepository.save(clientDetails);
	}

	@Override
	public Page<EmployeesAtClientsDetails> getAllEmpClinetDetails(PageRequest pageReuquest) {
		
		
		Page<EmployeesAtClientsDetails> list = employeesAtClientsDetailsRepository.findAll(pageReuquest);		
		return  list;

	}


	
	
	

}
