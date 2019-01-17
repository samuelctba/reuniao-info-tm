package tm.info.reuniao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tm.info.reuniao.dao.IPublicadorDao;
import tm.info.reuniao.model.Publicador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PublicadorCtrl {

    @Autowired
    private IPublicadorDao repositorioPublicador;

    //    @GetMapping("/{sobreNome}")
    @RequestMapping(value = "/{sobreNome}", method = RequestMethod.GET)
    public List<Publicador> BuscaPublicador(@PathVariable String sobreNome) {
        return repositorioPublicador.findBySobreNome(sobreNome);
    }
    //    @GetMapping("/{sobreNome}")
    @RequestMapping(value = "/{primeiroNome}", method = RequestMethod.GET)
    public List<Publicador> BuscaPublicadorPrimeiroNome(@PathVariable String primeiroNome) {
        return repositorioPublicador.findByPrimeiroNome(primeiroNome);
    }

    @GetMapping("/publicadores")
    public List<Publicador> getAllPublicadores() {
        System.out.println("Buscar todos os Publicadores...");

        List<Publicador> publicadores = new ArrayList<>();
//        repositorioPublicador.findAll().forEach(p->publicadores.add(p));
        repositorioPublicador.findAll().forEach(publicadores::add);

        return publicadores;
    }

    @PostMapping("/publicadores/create")
    public ResponseEntity<String> postPublicador(@RequestBody Publicador publicador) {
        System.out.println("Criando Publicador");
        System.out.println(publicador);

        List<Publicador> checkPublicadoor = repositorioPublicador.findByEmail(publicador.getEmail());
        if(checkPublicadoor.isEmpty()) {
            Publicador _publicadores = repositorioPublicador.save(new Publicador(
                    publicador.getEmail(),
                    publicador.getPrimeiroNome(),
                    publicador.getSobreNome(),
                    publicador.getEndereco(),
                    publicador.getTelefone(),
                    publicador.getDataNascimento(),
                    publicador.getDataBatismo(),
                    publicador.getOutros(),
                    publicador.getDetalhes()));

//            return _publicadores;
            return new ResponseEntity<>("Publicador " + _publicadores.getId() + " foi criado com sucesso!!!", HttpStatus.OK);
        }
        else{
            System.out.println("Publicador com o mesmo Email ja existente! Crie novamente com email diferente!");
            return new ResponseEntity<>("Publicador " + publicador.getEmail() + " com email existente!!!", HttpStatus.CONFLICT);
        }


    }

    @DeleteMapping("/publicadores/{id}")
    public ResponseEntity<String> deletePublicadores(@PathVariable("id") String id) {
        System.out.println("Apagar Publicador com ID = " + id + "...");

        repositorioPublicador.deleteById(id);

        return new ResponseEntity<>("Publicador " + id + " foi removido com sucesso!!!", HttpStatus.OK);
    }

    @DeleteMapping("/publicadores/deleteall")
    public ResponseEntity<String> deleteAllPublicadores() {
        System.out.println("Apagar todos os Publicadores...");

        repositorioPublicador.deleteAll();

        return new ResponseEntity<>("Todos os publicadores foram removidos do banco de dados com sucesso!", HttpStatus.OK);
    }

    @GetMapping("publicadores/email/{email}")
    public List<Publicador> findByEmail(@PathVariable String email) {
        return repositorioPublicador.findByEmail(email);
    }

    @PutMapping("/publicadores/{id}")
    public ResponseEntity<Publicador> updatePublicador(@PathVariable("id") String id, @RequestBody Publicador publicador) {
        System.out.println("Atualizar Publicador com ID = " + id + "...");

        Optional<Publicador> publicadorData = repositorioPublicador.findById(id);

        if (publicadorData.isPresent()) {
            Publicador _publicador = publicadorData.get();
            _publicador.setDataNascimento(publicador.getDataNascimento());
            _publicador.setDataBatismo(publicador.getDataBatismo());
            _publicador.setDetalhes(publicador.getDetalhes());
            _publicador.setEmail(publicador.getEmail());
            _publicador.setEndereco(publicador.getEndereco());
            _publicador.setOutros(publicador.getOutros());
            _publicador.setPrimeiroNome(publicador.getPrimeiroNome());
            _publicador.setSobreNome(publicador.getSobreNome());
            _publicador.setTelefone(publicador.getTelefone());
            return new ResponseEntity<>(repositorioPublicador.save(_publicador), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
