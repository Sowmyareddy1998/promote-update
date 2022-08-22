package ls.lesm.restcontroller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.repository.MasterEmployeeDetailsRepository;
import ls.lesm.service.impl.ReleaseEmpServiceImp;

@RestController
public class ReleaseEmpController {
	
	
	@Autowired
	private ReleaseEmpServiceImp releaseEmpServiceImp;
	@Autowired
	MasterEmployeeDetailsRepository detailsRepository;
	
	
	
	@GetMapping("/getallemployees")
	public List<MasterEmployeeDetails> getEmployees()
	{
		 return releaseEmpServiceImp.getAllEmp();
		
	}
	
   @GetMapping("/getemp/{empId}")
	public ResponseEntity<MasterEmployeeDetails> emp(@PathVariable int empId) {
		MasterEmployeeDetails details =releaseEmpServiceImp.get(empId);
		return new ResponseEntity<MasterEmployeeDetails>(details,HttpStatus.CREATED );
		
	}
   
   @GetMapping("/send/{empId}/{empstatus}")
   ResponseEntity<String>  request(@PathVariable int empId, @PathVariable String empstatus,Principal principal) {
	   
	   
  releaseEmpServiceImp.approveRequest(empId,empstatus,principal);
  return new ResponseEntity<String>("request send to supervisor successfully",HttpStatus.ACCEPTED );
	
	   
   }
	
	
	
	
	
	
}

