package ls.lesm.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErorrs {
	
	private Date timeStamp;
	private String errormessage;
	private String errorCode;
	private String fieldName;

}
