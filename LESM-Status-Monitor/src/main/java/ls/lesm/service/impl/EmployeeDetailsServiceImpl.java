package ls.lesm.service.impl;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ls.lesm.exception.DateMissMatchException;
import ls.lesm.model.Address;
import ls.lesm.model.EmployeesAtClientsDetails;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.payload.request.EmpClientDetailsRequest;
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
		address.setCreatedAt(new Date());
		address.setCreatedBy(principal.getName());
		Optional<Object> optional=addressTypeRepository.findById(addTypeId).map(type->{
		address.setAdressType(type);
		return addressRepositoy.save(address);
		});
		return address;
	}

	@Override
	public MasterEmployeeDetails insetEmpDetails(MasterEmployeeDetails empDetails,  Principal principal) {
		empDetails.setCreatedBy(principal.getName());
		
		return this.masterEmployeeDetailsRepository.save(empDetails);
	}

	@Override
	public EmployeesAtClientsDetails insertClientsDetails(EmployeesAtClientsDetails clientDetails,
			Principal principal,
			EmpClientDetailsRequest empClientReq) {
		
		
		empClientReq.setCreatedBy(principal.getName());
		empClientReq.setCreatedAt(new Date());
		LocalDate pos=empClientReq.getPOSdate().toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
		/*LocalDate poe=clientDetails.getPOEdate().toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();*/
		
		//if(clientDetails.getPOEdate()==null) {
		long tenureInMonth= ChronoUnit.MONTHS.between(pos,LocalDate.now());
		
		empClientReq.setClientTenure(tenureInMonth);
		/*}
		else {
			Long tenureInMonth=ChronoUnit.MONTHS.between(pos,poe);

			if(clientDetails.getPOEdate().before(clientDetails.getPOSdate()))
				throw new DateMissMatchException("PO end date can not be after Po start date","301");
			
			clientDetails.setClientTenure(tenureInMonth);
		}		*/
		empClientReq.setTotalEarningAtClients(clientDetails.getClientSalary()*empClientReq.getClientTenure());
		return employeesAtClientsDetailsRepository.save(clientDetails);
	}
	
	

}
