package  ls.lesm.model;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class OnsiteBillExpenses {
	
	@Id
	@GeneratedValue(generator = "site_bexp_gen",strategy = GenerationType.AUTO)
	@NonNull
	private Integer billExpId;
	private Double cab;
	private Double travel;
	private Double accommodation;
	private Double food;
	private Date travelSDate;
	private Date travelEDate;
	
	@Enumerated
	private PaidStatus paidStatus;
	
	private Integer totalTravelDays;
	
	@JsonIgnore
	private Date createdAt;//timpStamp
	
	@JsonIgnore
	@Column(length=30)
	private String createdBy;//principal
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="exp_type_fk")
	private OnsiteExpensesType onsiteExpensesType;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="emp_id_fk")
	private MasterEmployeeDetails masterEmployeeDetails;
	

}
