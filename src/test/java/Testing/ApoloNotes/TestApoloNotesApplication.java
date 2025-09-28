package Testing.ApoloNotes;

import org.springframework.boot.SpringApplication;

public class TestApoloNotesApplication {

	public static void main(String[] args) {
		SpringApplication.from(ApoloNotesApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
