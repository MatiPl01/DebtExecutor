package pl.edu.agh.debtexecutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DebtexecutorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DebtexecutorApplication.class, args);
	}

	@RequestMapping("/")
	public String greeting() {
		return "Heloooo !!!";
	}
}
