package ls.lesm.payload.request;

import lombok.Data;
import ls.lesm.model.Address;
import ls.lesm.model.EmployeesAtClientsDetails;
import ls.lesm.model.MasterEmployeeDetails;
@Data

public class EmployeeDetailsRequest {
	
	private MasterEmployeeDetails masterEmployeeDetails;
	private Address address;
	private EmployeesAtClientsDetails employeesAtClientsDetails;

}
