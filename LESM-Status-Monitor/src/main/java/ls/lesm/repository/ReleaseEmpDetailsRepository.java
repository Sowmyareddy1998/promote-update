package ls.lesm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.model.ReleaseEmpDetails;
@Repository
public interface ReleaseEmpDetailsRepository extends JpaRepository<ReleaseEmpDetails,Integer> {

	//MasterEmployeeDetails findBymasterEmployeeDetails_Id(int empId);
	
	@Query("FROM ReleaseEmpDetails g where g.masterEmployeeDetailsId.id = :masterEmployeeDetailsId")
    Optional<ReleaseEmpDetails>  findBymasterEmployeeDetails_Id(@Param("masterEmployeeDetailsId")Integer id);
    

}
