package ca.bc.gov.ag.courts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EFaxMsGraphProtoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EFaxMsGraphProtoApplication.class, args);
	}

}
