package ls.lesm.service.impl;

import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ls.lesm.model.EmployeeStatus;
import ls.lesm.model.HistoryOfEmp;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.model.UpdateEmpStatus;
import ls.lesm.model.User;
import ls.lesm.repository.HistoryOfEmpRepository;
import ls.lesm.repository.MasterEmployeeDetailsRepository;
import ls.lesm.repository.UserRepository;




@Service
public class GetSameDesignations {
	
	@Autowired
	MasterEmployeeDetailsRepository masterEmployeeDetailsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HistoryOfEmpRepository historyOfEmpRepository;
	
	
	public List<MasterEmployeeDetails> getSameDesignations(int DesignationId)
	 {
		 
		List<MasterEmployeeDetails>EmployeeDetails =masterEmployeeDetailsRepository.findByDesignations_Id( DesignationId );
		
		return EmployeeDetails;
	 }
	
	
	
	public String promoteEmployeeDetails( int oldsuperviserId, int  newsuperviserId,Principal principal) {
		
		 User user=userRepository.findByUsername(principal.getName());
		System.out.println("*****************************"+user);


		 List<MasterEmployeeDetails> g=masterEmployeeDetailsRepository.findBymasterEmployeeDetails_Id(oldsuperviserId);
	     MasterEmployeeDetails newemployee= masterEmployeeDetailsRepository.findById(newsuperviserId).get();
		 System.out.println(newemployee);
		 
//		 MasterEmployeeDetails empdetails=new MasterEmployeeDetails();
//		 HistoryOfEmployee historyOfEmployee=new HistoryOfEmployee();
//
//		 ModelMapper modelMapper = new ModelMapper();
//		 System.err.println(empdetails);
//	
//		 HistoryOfEmployee m1= modelMapper.map( empdetails, HistoryOfEmployee.class);
//	  
		// System.out.println("---------------------------------------------"+m1);
		
	
		 
		  for(MasterEmployeeDetails m:g)
		 {
			  System.out.println(m);
			 
			  m.setSupervisor(newemployee);
			 
			  masterEmployeeDetailsRepository.save(m);

			 
			 }
		   return "success";
		   }
	
	
	public  HistoryOfEmp Update(String  lancesoftid) {
		
		MasterEmployeeDetails details=masterEmployeeDetailsRepository.findByLancesoft(lancesoftid);
		return historyOfEmpRepository.save(details);
		
		
	}
	}

