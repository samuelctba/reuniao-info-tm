package tm.info.reuniao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tm.info.reuniao.model.Publicador;
import tm.info.reuniao.model.Usuario;
import tm.info.reuniao.repositorio.IPublicadorServico;
import tm.info.reuniao.repositorio.IUsuarioServico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RestApiController {

    @Autowired
    private IPublicadorServico publicadorServico;
    
    @Autowired
	private IUsuarioServico usuarioServico;
    
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public List<Usuario> mostrarUsuarios() {
		return usuarioServico.findAll();
	}

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	// public Usuario cadastrarUsuario(@RequestBody Usuario newUser) {
	public ResponseEntity<String> cadastrarUsuario(@RequestParam Usuario newUser) {

		Usuario _usuario = usuarioServico
				.save(new Usuario(newUser.getUsuario(), newUser.getPassword(), newUser.getPerfil()));

		return new ResponseEntity<>("Usuario " + _usuario.get_id() + " foi criado com sucesso!!!", HttpStatus.OK);

	}


    //    @GetMapping("/{sobreNome}")
    @RequestMapping(value = "/api/{sobreNome}", method = RequestMethod.GET)
    public List<Publicador> BuscaPublicador(@PathVariable String sobreNome) {
        return publicadorServico.findBySobreNome(sobreNome);
    }
    //    @GetMapping("/{sobreNome}")
    @RequestMapping(value = "/api/{primeiroNome}", method = RequestMethod.GET)
    public List<Publicador> BuscaPublicadorPrimeiroNome(@PathVariable String primeiroNome) {
        return publicadorServico.findByPrimeiroNome(primeiroNome);
    }

    @GetMapping("/api/publicadores")
    public List<Publicador> getAllPublicadores() {
        System.out.println("Buscar todos os Publicadores...");

        List<Publicador> publicadores = new ArrayList<>();
//        repositorioPublicador.findAll().forEach(p->publicadores.add(p));
        publicadorServico.findAll().forEach(publicadores::add);

        return publicadores;
    }

    @PostMapping("/api/publicadores/create")
    public ResponseEntity<String> postPublicador(@RequestBody Publicador publicador) {
        System.out.println("Criando Publicador");
        System.out.println(publicador);

        List<Publicador> checkPublicadoor = publicadorServico.findByEmail(publicador.getEmail());
        if(checkPublicadoor.isEmpty()) {
            Publicador _publicadores = publicadorServico.save(new Publicador(
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

    @DeleteMapping("/api/publicadores/{id}")
    public ResponseEntity<String> deletePublicadores(@PathVariable("id") String id) {
        System.out.println("Apagar Publicador com ID = " + id + "...");

        publicadorServico.deleteById(id);

        return new ResponseEntity<>("Publicador " + id + " foi removido com sucesso!!!", HttpStatus.OK);
    }

    @DeleteMapping("/api/publicadores/deleteall")
    public ResponseEntity<String> deleteAllPublicadores() {
        System.out.println("Apagar todos os Publicadores...");

        publicadorServico.deleteAll();

        return new ResponseEntity<>("Todos os publicadores foram removidos do banco de dados com sucesso!", HttpStatus.OK);
    }

    @GetMapping("/api/publicadores/email/{email}")
    public List<Publicador> findByEmail(@PathVariable String email) {
        return publicadorServico.findByEmail(email);
    }

    @PutMapping("/api/publicadores/{id}")
    public ResponseEntity<Publicador> updatePublicador(@PathVariable("id") String id, @RequestBody Publicador publicador) {
        System.out.println("Atualizar Publicador com ID = " + id + "...");

        Optional<Publicador> publicadorData = publicadorServico.findById(id);

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
            return new ResponseEntity<>(publicadorServico.save(_publicador), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
