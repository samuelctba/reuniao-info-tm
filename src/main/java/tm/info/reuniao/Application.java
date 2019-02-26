package tm.info.reuniao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tm.info.reuniao.model.Publicador;
import tm.info.reuniao.model.Usuario;
import tm.info.reuniao.repositorio.IPublicadorServico;
import tm.info.reuniao.repositorio.IUsuarioServico;

import java.util.List;

@SpringBootApplication
public class Application {

	@Autowired
	private IPublicadorServico publicadorServico;

	@Autowired
	private IUsuarioServico usuarioServico;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init() {

		return args -> {

			System.out.println("************************************************************");
			System.out.println("Start printing mongo objects");
			System.out.println("************************************************************");

			System.out.println("Usuários:");
			List<Usuario> primaries = this.usuarioServico.findAll();
			for (Usuario primary : primaries) {
				System.out.println(primary.toString());
			}

			List<Publicador> secondaries = this.publicadorServico.findAll();

			System.out.println("Publicadores:");
			for (Publicador secondary : secondaries) {
				System.out.println(secondary.toString());
			}

			System.out.println("************************************************************");
			System.out.println("Ended printing mongo objects");
			System.out.println("************************************************************");

		};

	}
}