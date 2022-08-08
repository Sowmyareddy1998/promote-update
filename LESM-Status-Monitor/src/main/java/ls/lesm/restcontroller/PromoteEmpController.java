package ls.lesm.restcontroller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ls.lesm.model.Designations;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.payload.request.PromoteEmployeeRequest;
import ls.lesm.service.impl.PromoteEmployeeService;



@RestController
public class PromoteEmpController {
	
	
	@Autowired
	private PromoteEmployeeService promoteEmployee; 
	
	
	
	@PutMapping("/update/{emp_id}/{sub_id}")
	public  ResponseEntity<String>  promoteEmp(@RequestBody Designations designations,@PathVariable ("emp_id") int emp,@PathVariable(" sub_id") int superviserId
			)
	{
//		MasterEmployeeDetails 	masterEmployeeDetails=new 	MasterEmployeeDetails();
//		masterEmployeeDetails.setUpdatedAt(LocalDate.now());
		promoteEmployee. promoteEmployeeDetails(designations, emp, superviserId);
		
		return new ResponseEntity<String>("updated ",HttpStatus.CREATED );
	}
	
	
	@PutMapping("/getin/{sup_id}/{updatedSupervisio_id}")
	
	public String getInfo(@PathVariable(value="updatedSupervisio_id") int updatedSupervisio_id,@PathVariable("sup_id") int  sup_id)
	{
		
		promoteEmployee.promoteEmployeeDetailsUpdate( updatedSupervisio_id,sup_id);
		
		return "Am here you Don't Here";
	}
}

