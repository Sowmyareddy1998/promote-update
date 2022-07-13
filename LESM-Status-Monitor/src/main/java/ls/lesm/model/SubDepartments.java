package  ls.lesm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubDepartments  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sub_depart_gen",strategy = GenerationType.AUTO)
	private Integer subDepartId;
	
	@Column(length=30)
	private String subDepartmentNames;
	
/*
	private Date createdAt;//timpStamp
*/
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "created_at", nullable = false, updatable = false)
	    @CreatedDate
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date createdAt;

	   /* @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "updated_at", nullable=true,updatable = true )
	    @LastModifiedDate
	    @DateTimeFormat(pattern = "yyyy-MM-dd")*/
	   // private Date updatedAt;
	    
	@Column(length=30)
	private String createdBy;//principal
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="depart_fk")
	private Departments departments;

}
