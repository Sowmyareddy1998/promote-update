package ls.lesm.service;

import java.security.Principal;

import javax.management.relation.RelationNotFoundException;

import ls.lesm.model.OnsiteBillExpenses;

public interface ExpenseService {
	
	OnsiteBillExpenses insertBillExp(OnsiteBillExpenses billExp, Principal principal, int expTypeId, int empId);

}
