package co.edu.cun.pany;

<<<<<<< HEAD
import org.springframework.boot.SpringApplication;
=======
>>>>>>> 14b631ca7e762996af8a874ece38b74eea1b80bd
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PanyApplication {


	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PanyApplication.class);

	}

	public static void main(String[] args) {
<<<<<<< HEAD
		SpringApplication.run(PanyApplication.class, args);
=======
		
>>>>>>> 14b631ca7e762996af8a874ece38b74eea1b80bd
	}

	@RequestMapping(value = "/app")
	public String hello() {
		return "app running";
	}


}
