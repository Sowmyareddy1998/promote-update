package  ls.lesm.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="Master_EMP_Details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MasterEmployeeDetails {
	
	@Id
	@GeneratedValue(generator = "emp_id_gen",strategy = GenerationType.AUTO)
	private Integer empId;// AutoInc Pk
	
	@Column(length=30)
	private String employeeId;// lancesoft Id	
	
	@Column(length=30)
	private String firstName;
	
	@Column(length=30)
	private String lastName;
	
	private Date joiningDate;
	private Date DOB;
	
	@Column(length=30)
	private String Location;
	
	@Column(length=20)
	private String gender;
	
	@Column(length=30)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private EmployeeStatus status;// active/bench/releas
	
	private Integer Age;// dob+sysDate
	private Boolean isInternal;// employee is internal or external
	
	@JsonIgnore
	private Date createdAt;//timpStamp
	
	@Column(length=30)
	@JsonIgnore
	private String createdBy;//principal
	
	private Long phoneNo;
	
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="sub_depart_fk")
	private SubDepartments subDepartments;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_fk")
	private Departments departments;
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="Desg_fk")
	private Designations designations;
	
	/*@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="employee_relation",
	joinColumns=@JoinColumn (name="emp_id"),
	inverseJoinColumns =@JoinColumn(name="supervisor_id"))
	private MasterEmployeeDetails masterEmployeeDetails;*/
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="supervisor_fk")
	private MasterEmployeeDetails masterEmployeeDetails;
	

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="verticle_fk")
	private MasterEmployeeDetails verticle;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="emp_type_fk")
	private EmployeeType employeeType;
	
	

}
