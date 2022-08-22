package ls.lesm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.repository.MasterEmployeeDetailsRepository;

@Service
public class ManagingDirectorCalculation {
	
	Long total=0l;
	@Autowired
	BusinessCalculation bc;
	
	@Autowired
	MasterEmployeeDetailsRepository masterEmployeeDetailsRepository;
	
	@Autowired
	CountryHeadCalculation countryHeadCalculation;
	
	
	
	public Double managingDirectorCal(int managingdirector)
	{

		List<MasterEmployeeDetails> ls = masterEmployeeDetailsRepository.findBymasterEmployeeDetails_Id(managingdirector);

		Double profit_or_loss=0.0;
		Double sub_profit=0.0;
		
		if(!ls.isEmpty())
		{
		
		for (MasterEmployeeDetails Employeeid : ls) {

			System.out.println(Employeeid);

//			Optional<MasterEmployeeDetails> id = masterEmployeeDetailsRepository.findById(Employeeid.getEmpId());
//
//			MasterEmployeeDetails epm = null;
//
//			if (id.isPresent()) {
//				epm = id.get();
//			}

			int a = Employeeid.getEmpId();

			profit_or_loss = (Double)countryHeadCalculation.countryHeadCal(a);
			sub_profit += profit_or_loss;

		}
		}
		return (Double)(sub_profit - bc.Employee_cal(managingdirector));


	}
	
	

}
