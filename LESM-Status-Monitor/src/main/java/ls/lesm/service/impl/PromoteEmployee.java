package ls.lesm.service.impl;

import java.security.Principal;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ls.lesm.model.Designations;
import ls.lesm.model.EmployeeStatus;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.payload.request.EmployeeDetailsRequest;
import ls.lesm.payload.request.PromoteEmployeeRequest;
import ls.lesm.repository.DesignationsRepository;
import ls.lesm.repository.MasterEmployeeDetailsRepository;

@Service
public class PromoteEmployee {
	
	
	
	@Autowired
	 private MasterEmployeeDetailsRepository  masterEmployeeDetailsRepository;
	@Autowired
	private DesignationsRepository designationsRepository;
	
	
	public MasterEmployeeDetails promoteEmployeeDetails( Designations designations, int emp, int  superviserId) {
		
	//	MasterEmployeeDetails 	masterEmployeeDetails=new 	MasterEmployeeDetails();
//		empDetails.getMasterEmployeeDetails().setCreatedBy(principal.getName());
//		empDetails.getMasterEmployeeDetails().setStatus(EmployeeStatus.BENCH);
	
			
	System.out.println("\n\n\n\n"+designations+"\n\n\n\n\n");
	
		MasterEmployeeDetails Exist=masterEmployeeDetailsRepository.findById(emp).get();
		
		System.out.println("......................."+Exist);
		Designations design=designationsRepository.findByDesgNames(designations.getDesgNames());
		Exist.setDesignations(design);
		MasterEmployeeDetails Exist1=masterEmployeeDetailsRepository.findById( superviserId).get();
		System.out.println("......................."+Exist1);
		Exist.setSupervisor(Exist1);
		Exist.setUpdatedAt(new Date());
		System.out.println(Exist);
		return masterEmployeeDetailsRepository.save(Exist);
	
	}





public List<MasterEmployeeDetails> promoteEmployeeDetailsUpdate(  int  superviserId) {

	MasterEmployeeDetails masterEmployeeDetails=new MasterEmployeeDetails();
	List<MasterEmployeeDetails> masterEmployeeDetailsobj= masterEmployeeDetailsRepository.findAll(superviserId);


	for(MasterEmployeeDetails master:masterEmployeeDetailsobj) {
		System.out.println(master);
		
		master.setSupervisor(masterEmployeeDetails.getSupervisor());
		}
	
  return masterEmployeeDetailsRepository.saveAll(masterEmployeeDetailsobj);
}
}

		
		
		
		
		
		
		
//		empDetails.getMasterEmployeeDetails().setCreatedBy(principal.getName());
//		empDetails.getMasterEmployeeDetails().setStatus(EmployeeStatus.BENCH);
		
		
//		empDetails.getEmployeesAtClientsDetails().setCreatedAt(LocalDate.now());
//		empDetails.getEmployeesAtClientsDetails().setCreatedBy(principal.getName());

//				MasterEmployeeDetailsRepository.save(empDetails.getMasterEmployeeDetails());
//
//				//this.employeesAtClientsDetailsRepository.save(empDetails.getEmployeesAtClientsDetails());
//				return empDetails;
	
