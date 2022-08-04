package ls.lesm.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ls.lesm.model.Designations;
import ls.lesm.model.MasterEmployeeDetails;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PromoteEmployeeRequest {
	
	
private Designations designations;
private MasterEmployeeDetails masterEmployeeDetails;

}
