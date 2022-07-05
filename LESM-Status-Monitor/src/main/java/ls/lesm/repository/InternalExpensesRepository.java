package ls.lesm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ls.lesm.model.InternalExpenses;

public interface InternalExpensesRepository extends JpaRepository<InternalExpenses, Integer> {

}
