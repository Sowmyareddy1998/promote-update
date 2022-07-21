package ls.lesm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ls.lesm.model.InternalExpenses;

public interface InternalExpensesRepository extends JpaRepository<InternalExpenses, Integer> {


	@Query(" FROM InternalExpenses g where g.masterEmployeeDetails.id = :masterEmployeeDetailsId")
	Optional<InternalExpenses>  findByEmployeeById(@Param("masterEmployeeDetailsId")Integer id);
}
