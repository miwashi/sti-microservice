package net.miwashi.sti;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Chinook API", version = "1.0", description = "Chinook WebServices"))
public class StiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StiApplication.class, args);
	}

	@GetMapping(path = "/")
	public RedirectView redirectWithUsingRedirectView(RedirectAttributes attributes) {
		return new RedirectView("/swagger-ui.html",true);
	}

}
