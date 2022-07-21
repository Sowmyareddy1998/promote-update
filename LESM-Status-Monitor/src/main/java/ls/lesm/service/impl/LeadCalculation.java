package ls.lesm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.repository.MasterEmployeeDetailsRepository;


@Service
public class LeadCalculation {
	

	Long total = 0l;

	@Autowired
	MasterEmployeeDetailsRepository masterEmployeeDetailsRepository;

	@Autowired
	BusinessCalculation bc;

	public Double lead_cal(int leademployeeid) {

		// masterEmployeeDetailsRepository.findById(leademployeeid);

		List<MasterEmployeeDetails> ls = masterEmployeeDetailsRepository.findBymasterEmployeeDetails_Id(leademployeeid);

		// List<MasterEmployeeDetails>
		// ls=masterEmployeeDetailsRepository.findBymasterEmployeeDetailsId(leademployeeid);

		Double profit_or_loss = 0.0;
		Double sub_profit = 0.0;

		if (!ls.isEmpty()) {

			for (MasterEmployeeDetails Employeeid : ls) {

				System.out.println(Employeeid);

//			
//			Optional<MasterEmployeeDetails> id = masterEmployeeDetailsRepository.findById(Employeeid.getEmpId());
//
//			MasterEmployeeDetails epm = null;
//
//			if (id.isPresent()) {
//				epm = id.get();
//			}

				int a = Employeeid.getEmpId();

				profit_or_loss = (Double) bc.Employee_cal(a);
				sub_profit += profit_or_loss;

			}
		}
		return (Double) (sub_profit - bc.Employee_cal(leademployeeid));

		// return 0.0;

	}

}
