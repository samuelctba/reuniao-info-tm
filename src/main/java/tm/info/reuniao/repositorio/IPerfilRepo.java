package tm.info.reuniao.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import tm.info.reuniao.model.Perfil;

public interface IPerfilRepo extends MongoRepository<Perfil, String> {
	
}
