package ls.lesm.restcontroller;

import java.security.Principal;
import java.time.LocalDate;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import ls.lesm.model.Designations;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.payload.request.PromoteEmployeeRequest;
import ls.lesm.service.impl.PromoteEmpServiceImp;



@Component
@RestController
public class PromoteEmpController {
	
	
	@Autowired
	PromoteEmpServiceImp promoteEmpServiceImp;
	

	
	
	
	


@GetMapping("/getSameDesignationEmployees/{id}")

List<MasterEmployeeDetails>  getSameDesignationEmployees(@PathVariable int id)
{
	
	List<MasterEmployeeDetails> EmployeeDetails= promoteEmpServiceImp.getSameDesignations(id);
	
	return  EmployeeDetails;
}




@GetMapping("/setSupervisor/{id}/{id2}")

ResponseEntity<String> promoteEmployeeDetail( @PathVariable int id,@PathVariable int id2,Principal principal)
{
promoteEmpServiceImp.promoteEmployeeDetails(id, id2, principal);
return new ResponseEntity<String>("assigned successfully",HttpStatus.ACCEPTED );

}

@PutMapping("/update/{emp_id}/{sub_id}")
public  ResponseEntity<String>  promoteEmp(@RequestBody Designations designations,@PathVariable ("emp_id") int emp,@PathVariable(" sub_id") int superviserId,Principal principal)
{
	MasterEmployeeDetails 	masterEmployeeDetails=new 	MasterEmployeeDetails();
	promoteEmpServiceImp. promoteEmployeeDetails(designations, emp, superviserId,principal);
	return new ResponseEntity<String>("updated ",HttpStatus.CREATED );
	}




















//@GetMapping("/get/{lancesoft_id}")
//public  ResponseEntity<String> promoteEmpDetails( @PathVariable("lancesoft_id") String empid) {
//	promoteEmployee.promoteEmployeeDetailsUpdate1(empid);
//return new ResponseEntity<String>("updated ",HttpStatus.CREATED );
//
//}








//@PutMapping("/getin/{sup_id}/{updatedSupervisio_id}")
//
//public String getInfo(@PathVariable(value="updatedSupervisio_id") int updatedSupervisio_id,@PathVariable("sup_id") int  sup_id)
//{
//	
//	promoteEmployee.promoteEmployeeDetailsUpdate( updatedSupervisio_id,sup_id);
//	
//	return "Am here you Don't Here";
//}
//}
}


