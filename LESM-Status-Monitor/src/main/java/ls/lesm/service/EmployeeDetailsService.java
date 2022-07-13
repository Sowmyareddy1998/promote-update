package ls.lesm.service;

import java.security.Principal;

import ls.lesm.model.Address;
import ls.lesm.model.EmployeesAtClientsDetails;
import ls.lesm.model.MasterEmployeeDetails;

public interface EmployeeDetailsService {
	
	Address insertEmpAddress(Address address, Principal principal, Integer addTypeId);
	
	MasterEmployeeDetails insetEmpDetails(MasterEmployeeDetails empDetails, Principal principal );
	
	EmployeesAtClientsDetails insertClientsDetails(EmployeesAtClientsDetails clientDetails, Principal principal);

}
