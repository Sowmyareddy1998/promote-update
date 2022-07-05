package ls.lesm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.lancesoft.employee")
public class LesmStatusMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LesmStatusMonitorApplication.class, args);
	}

}
