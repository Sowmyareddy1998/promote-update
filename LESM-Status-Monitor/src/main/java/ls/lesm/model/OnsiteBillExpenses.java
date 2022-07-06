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
public class OnsiteBillExpenses {
	
	@Id
	@GeneratedValue(generator = "site_bexp_gen",strategy = GenerationType.AUTO)
	private Integer billExpId;
	private Double cab;
	private Double travel;
	private Double accommodation;
	private Double food;
	
	@JsonIgnore
	private Date createdAt;//timpStamp
	
	@JsonIgnore
	@Column(length=30)
	private String createdBy;//principal
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="exp_type_fk")
	private OnsiteExpensesType onsiteExpensesType;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="emp_id_fk")
	private MasterEmployeeDetails masterEmployeeDetails;
	

}
