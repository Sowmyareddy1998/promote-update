package ls.lesm.service.impl;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ls.lesm.exception.DateMissMatchException;
import ls.lesm.model.OnsiteBillExpenses;
import ls.lesm.repository.OnsiteBillExpensesRepository;
import ls.lesm.repository.OnsiteExpensesTypeRepository;
import ls.lesm.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired
	private OnsiteBillExpensesRepository onsiteBillExpensesRepository;
	
	@Autowired
	private OnsiteExpensesTypeRepository onsiteExpensesTypeRepository;

	@Override
	public OnsiteBillExpenses insertBillExp(OnsiteBillExpenses billExp, Principal principal, int expTypeId) {
		billExp.setCreatedAt(new Date());
		billExp.setCreatedBy(principal.getName());
		
		LocalDate startDate= billExp.getTravelSDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate EndDate=billExp.getTravelEDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		Period days=Period.between(startDate,EndDate);
		
		billExp.setTotalTravelDays(days.getDays());
		
		if(billExp.getTravelSDate().after(billExp.getTravelEDate()))
			throw new DateMissMatchException("Travel start date can not be before travel end date","201");
		
		Optional<Object> obe=onsiteExpensesTypeRepository.findById(expTypeId).map(bill->{
			billExp.setOnsiteExpensesType(bill);
			return onsiteBillExpensesRepository.save(billExp);
		});
		return billExp;
	}

}
