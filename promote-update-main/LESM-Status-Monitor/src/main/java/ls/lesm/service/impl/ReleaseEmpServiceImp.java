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
	private UserRepository userRepository;
	

	@Autowired
	private HistoryRepository historyOfEmpRepository;
	//get all employees 
	public List<MasterEmployeeDetails> getAllEmp() {

		return masterEmployeeDetailsRepository.findAll();
	}
//select an employee for releasing
	public MasterEmployeeDetails get(int empId) {
		MasterEmployeeDetails employee = masterEmployeeDetailsRepository.findById(empId).get();
		System.out.println("-------------******************-----" + employee);
		ReleaseEmpDetails releaseEmpDetails = new ReleaseEmpDetails();
		releaseEmpDetails.setMasterEmployeeDetailsId(employee);
		releaseEmpDetails.setMasterEmployeeDetailssupervisor(employee.getSupervisor());
		releaseEmpDetails.setReleaseStatus(ReleaseStatus.ONHOLD);
		releaseEmpDetailsRepository.save(releaseEmpDetails);
		MasterEmployeeDetails m = employee.getSupervisor();
		System.out.println("-------------------------------" + m);
		System.err.println(m);
		return m;

	}
	
	
    // send the request to supervisor
	public void approveRequest(int empId, String empstatus,Principal principal) {

		MasterEmployeeDetails masterEmployeeDetails = masterEmployeeDetailsRepository.findById(empId).get();
		User user=userRepository.findByUsername(principal.getName());
		System.out.println(user);
 
		ReleaseEmpDetails details = releaseEmpDetailsRepository.findBymasterEmployeeDetails_Id(empId).get();

		System.out.println(details);

		if (empstatus.equals("APPROVED")) {

			HistoryOfEmp historyOfEmp = new HistoryOfEmp(masterEmployeeDetails.getLancesoft(),
					masterEmployeeDetails.getFirstName(), masterEmployeeDetails.getLastName(),
					masterEmployeeDetails.getJoiningDate(), masterEmployeeDetails.getDOB(),
					masterEmployeeDetails.getLocation(), masterEmployeeDetails.getGender(),
					masterEmployeeDetails.getEmail(), masterEmployeeDetails.getStatus(), UpdateEmpStatus.RELEASE,
					masterEmployeeDetails.getAge(), masterEmployeeDetails.getIsInternal(),
					masterEmployeeDetails.getPhoneNo(), masterEmployeeDetails.getCreatedBy(),
					masterEmployeeDetails.getExitAt(), masterEmployeeDetails.getSubDepartments(),
					masterEmployeeDetails.getDepartments(), masterEmployeeDetails.getDesignations(),
					masterEmployeeDetails.getSupervisor(), masterEmployeeDetails.getEmployeeType());

			historyOfEmpRepository.save(historyOfEmp);
			
			details.setReleaseStatus(ReleaseStatus.APPROVED);
			details.setReleaseDate(LocalDate.now());
			releaseEmpDetailsRepository.save(details);
			
			

			masterEmployeeDetails.setStatus(EmployeeStatus.Exit);
			masterEmployeeDetailsRepository.save(masterEmployeeDetails);

		} else
			details.setReleaseStatus(ReleaseStatus.DENIED);

	}
}
