package ls.lesm.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Departments {
	@Id
	@GeneratedValue(generator = "depart_gen",strategy = GenerationType.AUTO)
	private Integer departId;
	
	@Column(length=30)
	private String depart;
	@JsonIgnore
	private Date createdAt;//timpStamp
	@JsonIgnore
	@Column(length=30)
	private String createdBy;//principal
	

}
