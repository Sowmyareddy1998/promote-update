package ls.lesm.service.impl;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Call.UpdateStatus;

import ls.lesm.model.Departments;
import ls.lesm.model.Designations;
import ls.lesm.model.EmployeeStatus;
import ls.lesm.model.EmployeeType;
import ls.lesm.model.HistoryOfEmp;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.model.SubDepartments;
import ls.lesm.model.UpdateEmpStatus;
import ls.lesm.model.User;
import ls.lesm.repository.DesignationsRepository;
import ls.lesm.repository.HistoryRepository;
import ls.lesm.repository.MasterEmployeeDetailsRepository;
import ls.lesm.repository.UserRepository;




@Service
public class PromoteEmpServiceImp {
	
	@Autowired
	MasterEmployeeDetailsRepository masterEmployeeDetailsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HistoryRepository historyRepository;
	
	@Autowired
	private DesignationsRepository designationsRepository;
	
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
		 System.out.println("*********************"+newemployee);
		 
		 
		 
		 HistoryOfEmp historyOfEmployee=new HistoryOfEmp(
				 newemployee.getLancesoft(),
				 newemployee.getFirstName(),
				 newemployee.getLastName(),
				 newemployee.getJoiningDate(),
				 newemployee.getDOB(),
				 newemployee.getLocation(),
				 newemployee.getGender(),
				 newemployee.getEmail(),
				 newemployee.getStatus(),
				UpdateEmpStatus.PROMOTE,
				 newemployee.getAge(),
			     newemployee.getIsInternal(),
				 newemployee.getPhoneNo(),
				 newemployee.getCreatedBy(),
				 newemployee.getExitAt(),
				 newemployee.getSubDepartments(),
				 newemployee.getDepartments(),
				 newemployee.getDesignations(),
				 newemployee.getSupervisor(),
				 newemployee.getEmployeeType());
				 System.out.println( "--------------------------------------------"+historyOfEmployee);
		        historyRepository.save(historyOfEmployee);
		      for(MasterEmployeeDetails m:g)
		      {
			  System.out.println(m);
			 
			  m.setSupervisor(newemployee);
			 
			  masterEmployeeDetailsRepository.save(m);

			 
			 }
		   return "success";
		   }
	
	
	
	
	
	
	
	public MasterEmployeeDetails promoteEmployeeDetails( Designations designations, int emp, int  superviserId,Principal principal) {
		
		 User user=userRepository.findByUsername(principal.getName());
		System.out.println(user);
	
	System.out.println("\n\n\n\n"+designations+"\n\n\n\n\n");
	
		MasterEmployeeDetails masterEmployeeDetails=masterEmployeeDetailsRepository.findById(emp).get();
		
		
		System.out.println("......................."+masterEmployeeDetails);
		Designations design=designationsRepository.findByDesgNames(designations.getDesgNames());
		masterEmployeeDetails.setDesignations(design);
		MasterEmployeeDetails DetailsSupervisor=masterEmployeeDetailsRepository.findById( superviserId).get();
		System.out.println("......................."+DetailsSupervisor);
		masterEmployeeDetails.setSupervisor(DetailsSupervisor);
		masterEmployeeDetails.setUpdatedAt(new Date());
		System.out.println(masterEmployeeDetails);
		return masterEmployeeDetailsRepository.save(masterEmployeeDetails);
	
	}
	
	}

