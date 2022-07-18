package  ls.lesm.model;



import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class EmployeesAtClientsDetails implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "emp_at_cdetails_gen",strategy = GenerationType.AUTO)
	private Integer EmpAtClientId;
	private Double clientSalary;
	private LocalDate POSdate;// purchase order start date
	
	@Column(nullable=true)
	private LocalDate POEdate;// purchase order end date
	
	@Column(length=30)
	private String desgAtClient;
	
	//private Long clientTenure;// toatl months at client(posdate to poedate)
	//private Double totalEarningAtClients;// clientTenure*cliendt salary
	
	@JsonIgnore
	private LocalDate createdAt;//timpStamp
	
	@JsonIgnore
	@Column(length=30)
	private String createdBy;//principal
	
	//@JsonIgnore
	//@Fetch(FetchMode.JOIN) 
	@JsonIgnoreProperties({"hibernateLazyInitializer"})
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="emp_id_fk")
	private MasterEmployeeDetails masterEmployeeDetails;
	
	//@JsonIgnore
	//@Fetch(FetchMode.JOIN) 
	@JsonIgnoreProperties({"hibernateLazyInitializer"})
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="clients_fk")
	private Clients clients;
	
	
	@Transient
	private Long tenure;
	
	private Double totalEarningAtclient;

}
