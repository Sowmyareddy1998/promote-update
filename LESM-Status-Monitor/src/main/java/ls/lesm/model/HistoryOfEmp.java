package ls.lesm.model;

import java.time.LocalDate;
import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
//@AllArgsConstructor

public class HistoryOfEmp {
	
	


	@Id
	@GeneratedValue(generator = "emp_id_gen",strategy = GenerationType.AUTO)
	private Integer empId;// AutoInc Pk
	
	public HistoryOfEmp( String lancesoft, String firstName, String lastName, LocalDate joiningDate,
		LocalDate dOB, String location, String gender, String email, EmployeeStatus status,
		UpdateEmpStatus updatestatus, Integer age, Boolean isInternal, Long phoneNo, String createdBy, LocalDate exitAt,
		SubDepartments subDepartments, Departments departments, Designations designations,
		MasterEmployeeDetails supervisor, EmployeeType employeeType) {
	super();
	
	this.lancesoft = lancesoft;
	this.firstName = firstName;
	this.lastName = lastName;
	this.joiningDate = joiningDate;
	DOB = dOB;
	Location = location;
	this.gender = gender;
	this.email = email;
	this.status = status;
	this.updatestatus = updatestatus;
	Age = age;
	this.isInternal = isInternal;
	this.phoneNo = phoneNo;
	this.createdBy = createdBy;
	this.exitAt = exitAt;
	this.subDepartments = subDepartments;
	this.departments = departments;
	this.designations = designations;
	this.supervisor = supervisor;
	this.employeeType = employeeType;
}



	

	public HistoryOfEmp(Integer empId, String lancesoft, String firstName, String lastName, LocalDate joiningDate,
			LocalDate dOB, String location, String gender, String email, EmployeeStatus status,
			UpdateEmpStatus updatestatus, Integer age, Boolean isInternal, Long phoneNo, String createdBy,
			LocalDate exitAt, SubDepartments subDepartments, Departments departments, Designations designations,
			MasterEmployeeDetails supervisor, EmployeeType employeeType) {
		super();
		this.empId = empId;
		this.lancesoft = lancesoft;
		this.firstName = firstName;
		this.lastName = lastName;
		this.joiningDate = joiningDate;
		DOB = dOB;
		Location = location;
		this.gender = gender;
		this.email = email;
		this.status = status;
		this.updatestatus = updatestatus;
		Age = age;
		this.isInternal = isInternal;
		this.phoneNo = phoneNo;
		this.createdBy = createdBy;
		this.exitAt = exitAt;
		this.subDepartments = subDepartments;
		this.departments = departments;
		this.designations = designations;
		this.supervisor = supervisor;
		this.employeeType = employeeType;
	}





	@Column(length=30,name="employee_id")
	private String lancesoft;// lancesoft Id	
	
	@Column(length=30)
	private String firstName;
	
	@Column(length=30)
	private String lastName;
	
	private LocalDate joiningDate;
	
	private LocalDate DOB;
	
	@Column(length=30)
	private String Location;
	
	@Column(length=20)
	private String gender;
	
	@Column(length=30)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private EmployeeStatus status;// active/bench/releas
	
	@Enumerated(EnumType.STRING)
	private UpdateEmpStatus updatestatus;// release/transfer/promote
	
	
	private Integer Age;// dob+sysDate
	
	private Boolean isInternal;// employee is internal or external
	
	private Long phoneNo;
	private String createdBy;
	private LocalDate exitAt;

	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sub_depart_fk")
	private SubDepartments subDepartments;
	
	
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_fk")
	private Departments departments;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST,   fetch=FetchType.LAZY)
	@JoinColumn(name="Desg_fk")
	private Designations designations;
	
	/*@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="employee_relation",
	joinColumns=@JoinColumn (name="emp_id"),
	inverseJoinColumns =@JoinColumn(name="supervisor_id"))
	private MasterEmployeeDetails masterEmployeeDetails;*/
	
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="supervisor_fk")
	private MasterEmployeeDetails supervisor;
	
/*	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	//@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="verticle_fk")
	private MasterEmployeeDetails verticle;*/
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="emp_type_fk")
	private EmployeeType employeeType;
}
