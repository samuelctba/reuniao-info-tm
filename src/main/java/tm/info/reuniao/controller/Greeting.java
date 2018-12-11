package tm.info.reuniao.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greeting {
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/greeting")
	public String Hello() {
		return "OLA, Seja bem vindo!";
	}

}
