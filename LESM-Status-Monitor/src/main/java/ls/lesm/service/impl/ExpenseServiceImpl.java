package ls.lesm.service.impl;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ls.lesm.exception.DateMissMatchException;
import ls.lesm.exception.DuplicateEntryException;
import ls.lesm.exception.RelationNotFoundExceptions;
import ls.lesm.model.OnsiteBillExpenses;
import ls.lesm.repository.MasterEmployeeDetailsRepository;
import ls.lesm.repository.OnsiteBillExpensesRepository;
import ls.lesm.repository.OnsiteExpensesTypeRepository;
import ls.lesm.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired
	private OnsiteBillExpensesRepository onsiteBillExpensesRepository;
	
	@Autowired
	private OnsiteExpensesTypeRepository onsiteExpensesTypeRepository;
	
	@Autowired
	private MasterEmployeeDetailsRepository masterEmployeeDetailsRepository;
	
	@Override
	public OnsiteBillExpenses insertBillExp(OnsiteBillExpenses billExp, Principal principal, int expTypeId, int empId)  {
		billExp.setCreatedAt(new Date());
		billExp.setCreatedBy(principal.getName());
		
		LocalDate startDate= billExp.getTravelSDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate endDate=billExp.getTravelEDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		Integer months=Period.between(startDate,endDate).getMonths();
		Integer days=Period.between(startDate,endDate).getDays();
		//System.out.println("--------------------"+days);
		
		billExp.setTotalTravelPeriod(days+" days "+months+" Month");
		
		if(billExp.getTravelSDate().after(billExp.getTravelEDate()))
			throw new DateMissMatchException("Travel start date can not be before travel end date","201");
		
		this.onsiteExpensesTypeRepository.findById(expTypeId).map(bill->{
			billExp.setOnsiteExpensesType(bill);
			return bill;
		}).orElseThrow(()-> new RelationNotFoundExceptions("This expense Type with this id "+empId+" Not exist","501",""));
		
		Optional<Object> obj =Optional.ofNullable(masterEmployeeDetailsRepository.findById(empId).map(id->{
			billExp.setMasterEmployeeDetails(id);
			return id;
			
		}).orElseThrow(()-> new RelationNotFoundExceptions("This employee with this id "+empId+" Not exist","502","empId: "+empId)));
		
		
		return onsiteBillExpensesRepository.save(billExp);
	}

}
