package ls.lesm.payload.request;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class EmpClientDetailsRequest {
	private Integer EmpAtClientId;
	private Double clientSalary;
	private Date POSdate;// purchase order start date
	
	@Column(nullable=true)
	private Date POEdate;// purchase order end date
	
	@Column(length=30)
	private String desgAtClient;
	
	private Long clientTenure;// toatl months at client(posdate to poedate)
	private Double totalEarningAtClients;// clientTenure*cliendt salary
	
	@JsonIgnore
	private Date createdAt;//timpStamp
	
	@JsonIgnore
	@Column(length=30)
	private String createdBy;//principal

}
