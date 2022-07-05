package ls.lesm.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Salary {
	
	@Id
	@GeneratedValue(generator = "sal_gen",strategy = GenerationType.AUTO)
	private Integer salId;
	private Boolean isLatest;// 0 old salary; 1 new salary
	private Double salary;
	private Date createdAt;
	
	@Column(length=30)
	private String createdBy;//principal
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="emp_id_fk")
    private MasterEmployeeDetails masterEmployeeDetails;
	

}
