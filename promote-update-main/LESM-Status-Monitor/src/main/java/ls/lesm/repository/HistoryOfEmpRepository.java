package ls.lesm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ls.lesm.model.HistoryOfEmp;
import ls.lesm.model.MasterEmployeeDetails;

@Repository
public interface HistoryOfEmpRepository extends JpaRepository<HistoryOfEmpRepository, Integer> {

HistoryOfEmp save(MasterEmployeeDetails details);



	



	




	

}
