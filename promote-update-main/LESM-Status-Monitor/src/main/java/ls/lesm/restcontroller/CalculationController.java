package ls.lesm.restcontroller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.repository.MasterEmployeeDetailsRepository;
import ls.lesm.service.impl.BusinessCalculation;
import ls.lesm.service.impl.CountryHeadCalculation;
import ls.lesm.service.impl.GeneralManagerCalculation;
import ls.lesm.service.impl.LeadCalculation;
import ls.lesm.service.impl.ManagerCalculation;
import ls.lesm.service.impl.ManagingDirectorCalculation;

@RestController
@RequestMapping("/Total")
public class CalculationController {
	
	@Autowired
	BusinessCalculation businessCalculation;
	
	@Autowired
	LeadCalculation leadCalculation;
	
	@Autowired
	ManagerCalculation managerCalculation;
	
	@Autowired
	GeneralManagerCalculation generalManagerCalculation;
	
	@Autowired
	CountryHeadCalculation countryHeadCalculation;
	
	@Autowired
    ManagingDirectorCalculation managingDirectorCalculation;
	
	@Autowired
	MasterEmployeeDetailsRepository masterEmployeeDetailsRepository;
	

	@PostMapping("/consultant/{id}")

	public Double s( Principal principal)
	
	{
		String emp1=principal.getName();
		MasterEmployeeDetails emp2=this.masterEmployeeDetailsRepository.findByLancesoft(emp1);
		int id=emp2.getEmpId();
		Double profit_or_loss=businessCalculation.Employee_cal(id);
		return profit_or_loss;
		
	}
	
	
	@PostMapping("/lead/{id}")

	public Double lead(@PathVariable("id") Integer ids)
	{
		Double profit_or_loss=leadCalculation.lead_cal(ids);
		
		return profit_or_loss;
		
	}
	
	
	

	@PostMapping("/managerCalculation/{id}")
	public Double managerCalculation(Principal principal)
	{
		String loggedIn=principal.getName();
		MasterEmployeeDetails employee=this.masterEmployeeDetailsRepository.findByLancesoft(loggedIn);
		int id=employee.getEmpId();
		Double profit_or_loss=managerCalculation.manager_cal(id);
		
		return profit_or_loss;
		
	}
	
	
	@PostMapping("/generalManagerCalculation/{id}")
	 
	public Double generalManagerCalculationManagerCalculation(@PathVariable("id") Integer ids)
	{
		Double profit_or_loss=generalManagerCalculation.generalManagercal(ids);
		
		return profit_or_loss;
		
	}
	
	

	@PostMapping("/countryHeadCalculation/{id}")
	 //@PreAuthorize("hasAuthority('MD')")
	public Double countryHeadCalculation(@PathVariable("id") Integer ids)
	{
		Double profit_or_loss=countryHeadCalculation.countryHeadCal(ids);
		
		return profit_or_loss;
		
	}
	
	
	
	@PostMapping("/managingDirectorCalculation/{id}")
	public Double managingDirectorCalculation(@PathVariable("id") Integer ids)
	{
		Double profit_or_loss=managingDirectorCalculation.managingDirectorCal(ids);
		
		return profit_or_loss;
		
	}
	
	
	
	

}
