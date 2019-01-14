package tm.info.reuniao.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import tm.info.reuniao.model.Publicador;

import java.util.List;

public interface IPublicadorDao extends MongoRepository<Publicador, String> {

    List<Publicador> findByPrimeiroNome(String primeiroNome);
    List<Publicador> findBySobreNome(String sobreNome);
    List<Publicador> findByEmail(String email);
}
