package ls.lesm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ls.lesm.model.EmployeesAtClientsDetails;
import ls.lesm.payload.response.DataResponse;
@Repository
@Transactional
public interface EmployeesAtClientsDetailsRepository extends JpaRepository<EmployeesAtClientsDetails, Integer> {
	@Query(nativeQuery = true, value="Select employees_at_clients_details.emp_at_client_id,employees_at_clients_details.desg_at_client, clients.clients_names, master_emp_details.employee_id"
			+ " FROM ("
			+ "(employees_at_clients_details "
			+ "INNER JOIN clients ON employees_at_clients_details.clients_fk=clients.clients_id)"
			+ "INNER JOIN master_emp_details ON employees_at_clients_details.emp_id_fk=master_emp_details.emp_id) limit 1")
			
	//@Query(nativeQuery = true)
	public DataResponse findByDataResponseAll();
	
}
