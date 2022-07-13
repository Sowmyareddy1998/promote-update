package  ls.lesm.model;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeesAtClientsDetails {
	
	@Id
	@GeneratedValue(generator = "emp_at_cdetails_gen",strategy = GenerationType.AUTO)
	private Integer EmpAtClientId;
	private Double clientSalary;
	private Date POSdate;// purchase order start date
	
	@Column(nullable=true)
	private Date POEdate;// purchase order end date
	
	@Column(length=30)
	private String desgAtClient;
	@Transient
	private Long clientTenure;// toatl months at client(posdate to poedate)
	private Double totalEarningAtClients;// clientTenure*cliendt salary
	
	@JsonIgnore
	private Date createdAt;//timpStamp
	
	@JsonIgnore
	@Column(length=30)
	private String createdBy;//principal
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="emp_id_fk")
	private MasterEmployeeDetails masterEmployeeDetails;
	
	@JsonIgnore
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="clients_fk")
	private Clients clients;

}
