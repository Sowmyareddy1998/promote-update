package ls.lesm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Designations {
	
	@Id
	@GeneratedValue(generator = "desg_gen",strategy = GenerationType.AUTO)
	private Integer desgId;
	
	@Column(length=30)
	private String desgNames;
	private Date createdAt;//timpStamp
	
	@Column(length=30)
	private String createdBy;//principal
	


}
