package ls.lesm.service.impl;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ls.lesm.exception.DuplicateEntryException;
import ls.lesm.model.Clients;
import ls.lesm.model.Departments;
import ls.lesm.model.Designations;
import ls.lesm.model.EmployeeType;
import ls.lesm.model.SubDepartments;
import ls.lesm.repository.ClientsRepository;
import ls.lesm.repository.DepartmentsRepository;
import ls.lesm.repository.DesignationsRepository;
import ls.lesm.repository.EmployeeTypeRepository;
import ls.lesm.repository.SubDepartmentsRepository;
import ls.lesm.service.ConstantFieldService;

@Service
public class ConstantFieldServiceImpl implements ConstantFieldService {

	@Autowired
	private DesignationsRepository desigRepository;
	
	@Autowired
	private DepartmentsRepository departmentsRepository;
	
	@Autowired
	private SubDepartmentsRepository subDepartmentsRepository;
	
	@Autowired
	private ClientsRepository clientsRepository;
	
	@Autowired
	private EmployeeTypeRepository employeeTypeRepository;
	

	@Override
	public Designations insertDesg(Designations desig, Principal principal) {
		desig.setCreatedBy(principal.getName());
		desig.setCreatedAt(new Date());
		
		Designations local=this.desigRepository.findByDesgNames(desig.getDesgNames());
		if(local!=null)
			throw new DuplicateEntryException("101","This Designation Already exist in database");
		else
				
		this.desigRepository.save(desig);
		return desig;
	}

	@Override
	public Departments insertDepart(Departments depart, Principal principal) {
		depart.setCreatedAt(new Date());
		depart.setCreatedBy(principal.getName());
		Departments local=this.departmentsRepository.findByDepart(depart.getDepart());
		if(local!=null)
			throw new DuplicateEntryException("102","This Department Already exist in database");
		else
			this.departmentsRepository.save(depart);
		return depart;
	}

	@Override
	public SubDepartments insertSubDepart(SubDepartments subDepart, Principal principal, int departId) {
		
		subDepart.setCreatedAt(new Date());
		subDepart.setCreatedBy(principal.getName());
		Departments depart=new Departments();
		subDepart.setDepartments(new Departments(departId,"",null,""));// assigning foreign key 
	
		SubDepartments local=this.subDepartmentsRepository.findBySubDepartmentNames(subDepart.getSubDepartmentNames());
		if(local!=null)
			throw new DuplicateEntryException("103","This Sub-Department Already exist in database");
		else
			this.subDepartmentsRepository.save(subDepart);
		return subDepart;
	}

	@Override
	public Clients insertClient(Clients clients, Principal principal) {
		
		clients.setCreatedAt(new Date());
		clients.setCreatedBy(principal.getName());
		Clients local=this.clientsRepository.findByClientsNames(clients.getClientsNames());
		if(local!=null)
			throw new DuplicateEntryException("104","This Client Already exist in database");
		else
			this.clientsRepository.save(clients);
		return clients;
	}

	@Override
	public EmployeeType insertEmpTypes(EmployeeType type) {
		EmployeeType local=employeeTypeRepository.findByTypes(type.getTypes());
		if(local!=null)
			throw new DuplicateEntryException("105","This Employee Type Already exist in database");
		else
			this.employeeTypeRepository.save(type);
		return type;
	}
	
	
}
