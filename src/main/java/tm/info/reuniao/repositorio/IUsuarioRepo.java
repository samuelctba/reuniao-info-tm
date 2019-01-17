package tm.info.reuniao.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import tm.info.reuniao.model.Usuario;

public interface IUsuarioRepo extends MongoRepository<Usuario, String> {
	
	Usuario findByUsuario(String usuario);

}
