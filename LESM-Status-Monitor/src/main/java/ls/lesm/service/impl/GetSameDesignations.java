package ls.lesm.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ls.lesm.model.HistoryOfEmployee;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.repository.MasterEmployeeDetailsRepository;


@Component
@Data
@NoArgsConstructor
@ToString
public class GetSameDesignations {
	
	@Autowired
	MasterEmployeeDetailsRepository masterEmployeeDetailsRepository;
	
	
	public List<MasterEmployeeDetails> getSameDesignations(int DesignationId)
	 {
		 
		List<MasterEmployeeDetails>EmployeeDetails =masterEmployeeDetailsRepository.findByDesignations_Id( DesignationId );
		
		
		
		
		
		return EmployeeDetails;
	 }
	
	
	
	public HistoryOfEmployee promoteEmployeeDetails(int oldsuperviserId, int  newsuperviserId) {
		
		
		 List<MasterEmployeeDetails> g=masterEmployeeDetailsRepository.findBymasterEmployeeDetails_Id(oldsuperviserId);
	
		 MasterEmployeeDetails newemployee= masterEmployeeDetailsRepository.findById(newsuperviserId).get();
		 
		 System.out.println(newemployee);
		 
		 MasterEmployeeDetails empdetails=new MasterEmployeeDetails();
		 HistoryOfEmployee empDto=new HistoryOfEmployee();
		 ModelMapper modelMapper = new ModelMapper();
		 modelMapper.map(modelMapper, empDto);
		 
		 
//		
		 
		 for(MasterEmployeeDetails m:g)
		 {
			 
			 System.out.println(m);
			 m.setSupervisor(newemployee);
			 
			 masterEmployeeDetailsRepository.save(m);
			 
			 
			 
		 }
		 
		
		 
		 return empDto;
		
	}

}
