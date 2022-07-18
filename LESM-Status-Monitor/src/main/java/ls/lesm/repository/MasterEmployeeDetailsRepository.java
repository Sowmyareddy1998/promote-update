package ls.lesm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ls.lesm.model.MasterEmployeeDetails;
@Repository
public interface MasterEmployeeDetailsRepository extends JpaRepository<MasterEmployeeDetails, Integer> {
	
	

}
