package ls.lesm.conveter;

import org.springframework.stereotype.Component;

import ls.lesm.model.EmployeesAtClientsDetails;
import ls.lesm.payload.request.EmpClientDetailsRequest;

@Component
public class EmpAtClientToRequest {
	
	public EmpClientDetailsRequest modelToReq(EmployeesAtClientsDetails clientDetails) {
		
		EmpClientDetailsRequest dto=new EmpClientDetailsRequest();
		dto.setClientSalary(clientDetails.getClientSalary());
		dto.setDesgAtClient(clientDetails.getDesgAtClient());
		//dto.setPOEdate(clientDetails.getPOEdate());
		//dto.setPOSdate(clientDetails.getPOSdate());
		dto.setTenure(dto.getTenure());
		dto.setTotalEarningAtClient(dto.getTotalEarningAtClient());
		return dto;
	}
	
	public EmployeesAtClientsDetails reqToModel(EmpClientDetailsRequest clientDetailsReq) {
		
		EmployeesAtClientsDetails  clientDetails=new EmployeesAtClientsDetails();
		clientDetails.setClientSalary(clientDetailsReq.getClientSalary());
		clientDetails.setDesgAtClient(clientDetailsReq.getDesgAtClient());
		//clientDetails.setPOEdate(clientDetailsReq.getPOEdate());
		//clientDetails.setPOSdate(clientDetailsReq.getPOSdate());
		return clientDetails;
	}
	
	

}
