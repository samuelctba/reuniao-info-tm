package tm.info.reuniao.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import tm.info.reuniao.model.Usuario;

//@RepositoryRestResourse from JPA
public interface IUsuarioServico extends MongoRepository<Usuario, String> {
	
	Usuario findByUsuario(String usuario);

}
