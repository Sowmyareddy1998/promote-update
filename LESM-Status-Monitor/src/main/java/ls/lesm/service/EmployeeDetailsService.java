package ls.lesm.service;

import java.security.Principal;

import ls.lesm.model.Address;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.payload.request.EmpDetailsRequest;

public interface EmployeeDetailsService {
	
	Address insertEmpAddress(Address address, Principal principal, Integer addTypeId);
	
	MasterEmployeeDetails insetEmpDetails(MasterEmployeeDetails empDetails, Principal principal, int req );

}
