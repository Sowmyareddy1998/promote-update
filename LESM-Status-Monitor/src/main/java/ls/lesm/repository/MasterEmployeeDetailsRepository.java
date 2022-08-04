
package ls.lesm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.payload.response.EmployeeDetailsResponse;
@Repository
public interface MasterEmployeeDetailsRepository extends JpaRepository<MasterEmployeeDetails, Integer> {
	
	@Query(nativeQuery=true,
			value=" SELECT master_emp_details.emp_id AS empId, master_emp_details.employee_id AS employeeId, master_emp_details.first_name AS firstName, master_emp_details.last_name AS lastName,"
					       + "master_emp_details.dob AS dob, master_emp_details.location AS location, master_emp_details.email AS email, master_emp_details.gender AS gender, "
					       + "master_emp_details.joining_date, master_emp_details.phone_no, master_emp_details.status, departments.depart, "
					       + "designations.desg_names AS designation, employee_type.type_name AS employeeType, sub_departments.sub_department_names AS subDepartName"
				+ " FROM( (("
				          + "(master_emp_details"
				+ " LEFT JOIN departments"
				+ " ON master_emp_details.department_fk=departments.depart_id) "
				+ " LEFT JOIN designations "
				+ " ON master_emp_details.desg_fk=designations.desg_id ) "
				+ " LEFT JOIN employee_type"
				+ " ON master_emp_details.emp_type_fk=employee_type.emp_type_id)"
				+ " LEFT JOIN sub_departments"
				+ " ON master_emp_details.sub_depart_fk=sub_departments.sub_depart_id);")
	public List<EmployeeDetailsResponse> getAllEmpDetails();

	

	@Query("FROM MasterEmployeeDetails g where g.supervisor.id = :supervisor")
	List<MasterEmployeeDetails>  findBymasterEmployeeDetails_Id(@Param("supervisor")Integer  id);



	public MasterEmployeeDetails findByLancesoft(String id);



public List<MasterEmployeeDetails> findAll(int superviserId);










	//public List<MasterEmployeeDetails> findAll(int superviserId);



	







	
}
