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
public class OnsiteExpensesType {
	
	@Id
	@GeneratedValue(generator = "on_exptype_gen",strategy = GenerationType.AUTO)
	private Integer expId;
	
	@Column(length=30)
	private String expType;
	private Date createdAt;//timpStamp
	
	@Column(length=30)
	private String createdBy;//principal

}
