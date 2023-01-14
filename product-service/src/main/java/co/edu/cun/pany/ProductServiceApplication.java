package co.edu.cun.pany;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProductServiceApplication {


	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProductServiceApplication.class);

	}

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@RequestMapping(value = "/app")
	public String hello() {
		return "app running";
	}


}
