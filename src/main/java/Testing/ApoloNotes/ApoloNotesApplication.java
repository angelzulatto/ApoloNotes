package Testing.ApoloNotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication

public class ApoloNotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApoloNotesApplication.class, args);

		
	}

}
