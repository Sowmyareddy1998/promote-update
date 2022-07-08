package ls.lesm.service.impl;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ls.lesm.model.Address;
import ls.lesm.model.AddressType;
import ls.lesm.model.Designations;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.payload.request.EmpDetailsRequest;
import ls.lesm.repository.AddressRepositoy;
import ls.lesm.repository.AddressTypeRepository;
import ls.lesm.repository.DepartmentsRepository;
import ls.lesm.repository.DesignationsRepository;
import ls.lesm.repository.MasterEmployeeDetailsRepository;
import ls.lesm.repository.SubDepartmentsRepository;
import ls.lesm.service.EmployeeDetailsService;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {
	
	@Autowired
	private AddressRepositoy addressRepositoy;
	
	@Autowired
	private AddressTypeRepository addressTypeRepository;
	
	@Autowired
	private MasterEmployeeDetailsRepository masterEmployeeDetailsRepository;
	@Autowired
	private DepartmentsRepository departmentsRepository;
	@Autowired
	private SubDepartmentsRepository subDepartmentsRepositorye;
	@Autowired
	private DesignationsRepository designationsRepository;
	
	@Override
	public Address insertEmpAddress(Address address, Principal principal, Integer addTypeId) {
		address.setCreatedAt(new Date());
		address.setCreatedBy(principal.getName());
		Optional<Object> optional=addressTypeRepository.findById(addTypeId).map(type->{
		address.setAdressType(type);
		return addressRepositoy.save(address);
		});
		return address;
	}

	@Override
	public MasterEmployeeDetails insetEmpDetails(MasterEmployeeDetails empDetails,  Principal principal, int req) {
		empDetails.setCreatedBy(principal.getName());
		/* this.designationsRepository.findById(req).map(desg->{
			empDetails.setDesignations(desg);
			return desg;
		});
		 this.subDepartmentsRepositorye.findById(req.getSubDepartId()).map(subD->{
			 empDetails.setSubDepartments(subD);
			 return subD;
		 });*/
		 this.masterEmployeeDetailsRepository.findById(req).map(sup->{
			 empDetails.setMasterEmployeeDetails(sup);
			 return sup;
		 }).orElseThrow();
	/*	 this.masterEmployeeDetailsRepository.findById(req.getVerticalId()).map(ver->{
			 empDetails.setMasterEmployeeDetails(ver);
			 return ver;
		 });*/
		 
		return this.masterEmployeeDetailsRepository.save(empDetails);
	}
	
	

}
