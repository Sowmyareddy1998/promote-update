package ls.lesm.service.impl;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import ls.lesm.model.Departments;
import ls.lesm.model.Designations;
import ls.lesm.model.EmployeeStatus;
import ls.lesm.model.EmployeeType;
import ls.lesm.model.HistoryOfEmp;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.model.ReleaseEmpDetails;
import ls.lesm.model.ReleaseStatus;
import ls.lesm.model.SubDepartments;
import ls.lesm.model.UpdateEmpStatus;
import ls.lesm.model.User;
import ls.lesm.repository.HistoryRepository;
import ls.lesm.repository.MasterEmployeeDetailsRepository;
import ls.lesm.repository.ReleaseEmpDetailsRepository;
import ls.lesm.repository.UserRepository;

@Service
public class ReleaseEmpServiceImp {

	@Autowired
	private MasterEmployeeDetailsRepository masterEmployeeDetailsRepository;
	
	@Autowired
	private ReleaseEmpDetailsRepository releaseEmpDetailsRepository; 
	
	@Autowired
	private HistoryRepository  historyOfEmpRepository; 
	

	public List<MasterEmployeeDetails> getAllEmp() {
		
	return masterEmployeeDetailsRepository.findAll();
	}


	
	
	public MasterEmployeeDetails  get(int empId) {
  MasterEmployeeDetails employee= masterEmployeeDetailsRepository.findById( empId).get();
  System.out.println("-------------******************-----"+employee);
  ReleaseEmpDetails  releaseEmpDetails =new ReleaseEmpDetails ();
  releaseEmpDetails.setMasterEmployeeDetailsId(employee);
  releaseEmpDetails.setMasterEmployeeDetailssupervisor(employee.getSupervisor());
  releaseEmpDetails.setReleaseStatus(ReleaseStatus.ONHOLD);
  releaseEmpDetailsRepository.save(releaseEmpDetails );
  MasterEmployeeDetails m= employee.getSupervisor();
  System.out.println("-------------------------------"+m);
  System.err.println(m);
  return m;
	
}
	
	
	
	
	    public List<MasterEmployeeDetails> approveRequest(int supervisorId,String empstatus ) {
	    	
	    List<MasterEmployeeDetails> details=masterEmployeeDetailsRepository.findBymasterEmployeeDetails_Id(supervisorId);
	    
	    System.out.println(details);
	   
	    for(MasterEmployeeDetails m:details) {
	    	
	    
	    
	    if(empstatus.equals("APPROVED")) {
	    	
	    	HistoryOfEmp historyOfEmp=new HistoryOfEmp(
	    			m.getLancesoft(),
					m.getFirstName(),
					m.getLastName(),
				    m.getJoiningDate(),
				    m.getDOB(),
					m.getLocation(),
					m.getGender(),
					m.getEmail(),
					m.getStatus(),
					UpdateEmpStatus.RELEASE,
					 m.getAge(),
				     m.getIsInternal(),
					 m.getPhoneNo(),
				     m.getCreatedBy(),
					 m.getExitAt(),
					 m.getSubDepartments(),
					 m.getDepartments(),
					 m.getDesignations(),
					 m.getSupervisor(),
					 m.getEmployeeType());
	    	
	    	
	    	historyOfEmpRepository.save(historyOfEmp);
	    	 ReleaseEmpDetails releaseEmpDetails=new ReleaseEmpDetails();
	    	
	    	releaseEmpDetails.setReleaseStatus(ReleaseStatus.APPROVED);
	    	releaseEmpDetails.setReleaseDate(LocalDate.now());
	    	releaseEmpDetailsRepository.save(releaseEmpDetails);
	    	
	    	
	    	m.setStatus(EmployeeStatus.Exit);
	    	 masterEmployeeDetailsRepository.save(m);
	    	
	    }    	

	    }
	  		return details;
	  	    }
	  }

	    	
	    	
	    	
	    	
	    	
	    	 
	    	
	    	
	    	
	   
	    
	   
		    	
		    		
	 
	
	    	
	    	
	      
	    
	  


