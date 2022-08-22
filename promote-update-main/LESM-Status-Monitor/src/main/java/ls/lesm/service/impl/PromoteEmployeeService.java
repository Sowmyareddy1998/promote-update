

//package ls.lesm.service.impl;
//
//import java.security.Principal;
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Optional;
//
//import javax.print.attribute.standard.Destination;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import ls.lesm.model.Designations;
//import ls.lesm.model.EmployeeStatus;
//import ls.lesm.model.HistoryOfEmp;
//import ls.lesm.model.MasterEmployeeDetails;
//import ls.lesm.model.UpdateEmpStatus;
//import ls.lesm.model.User;
//import ls.lesm.payload.request.EmployeeDetailsRequest;
//import ls.lesm.payload.request.PromoteEmployeeRequest;
//import ls.lesm.repository.DesignationsRepository;
//import ls.lesm.repository.MasterEmployeeDetailsRepository;
//import ls.lesm.repository.UserRepository;
//
//@Service
//public class PromoteEmployeeService {
//	
//	
//	
//	@Autowired
//	 private MasterEmployeeDetailsRepository  masterEmployeeDetailsRepository;
//	@Autowired
//	private DesignationsRepository designationsRepository;
//	@Autowired
//	private UserRepository userRepository;
//	
//	public MasterEmployeeDetails promoteEmployeeDetails( Designations designations, int emp, int  superviserId,Principal principal) {
//		
//		 User user=userRepository.findByUsername(principal.getName());
//		System.out.println(user);
//	
//	System.out.println("\n\n\n\n"+designations+"\n\n\n\n\n");
//	
//		MasterEmployeeDetails masterEmployeeDetails=masterEmployeeDetailsRepository.findById(emp).get();
//		
//		
//		System.out.println("......................."+masterEmployeeDetails);
//		Designations design=designationsRepository.findByDesgNames(designations.getDesgNames());
//		masterEmployeeDetails.setDesignations(design);
//		MasterEmployeeDetails DetailsSupervisor=masterEmployeeDetailsRepository.findById( superviserId).get();
//		System.out.println("......................."+DetailsSupervisor);
//		masterEmployeeDetails.setSupervisor(DetailsSupervisor);
//		masterEmployeeDetails.setUpdatedAt(new Date());
//		System.out.println(masterEmployeeDetails);
//		return masterEmployeeDetailsRepository.save(masterEmployeeDetails);
//	
//	}
//	}
//
