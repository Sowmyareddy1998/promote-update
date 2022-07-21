package ls.lesm.service;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import ls.lesm.model.Address;
import ls.lesm.model.EmployeesAtClientsDetails;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.payload.request.EmpClientDetailsRequest;

public interface EmployeeDetailsService {
	
	Address insertEmpAddress(Address address, Principal principal, Integer addTypeId);
	
	MasterEmployeeDetails insetEmpDetails(MasterEmployeeDetails empDetails, Principal principal );
	
	EmployeesAtClientsDetails insertClientsDetails(EmployeesAtClientsDetails clientDetails, Principal principal);

	Page<EmployeesAtClientsDetails> getAllEmpClinetDetails(PageRequest pageReuquest);
	
	

	
}
