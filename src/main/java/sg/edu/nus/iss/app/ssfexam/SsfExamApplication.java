package sg.edu.nus.iss.app.ssfexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class SsfExamApplication {

	private static final Logger logger = LoggerFactory.getLogger(SsfExamApplication.class);

	public static void main(String[] args) {
		logger.debug("===================================APPLICATION IS ABOUT TO RUN================================");
		SpringApplication.run(SsfExamApplication.class, args);
		logger.debug("===================================APPLICATION FINISHED RUNNING================================");
	}

}
