package ls.lesm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ls.lesm.model.HistoryOfEmp;
import ls.lesm.model.MasterEmployeeDetails;

public interface HistoryRepository extends JpaRepository<HistoryOfEmp, Integer> {

}
