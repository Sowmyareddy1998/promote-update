package ls.lesm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ls.lesm.model.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {

}
