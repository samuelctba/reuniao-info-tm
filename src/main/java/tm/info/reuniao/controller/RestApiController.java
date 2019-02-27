package tm.info.reuniao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tm.info.reuniao.Response;
import tm.info.reuniao.config.ETipoAcesso;
import tm.info.reuniao.model.Publicador;
import tm.info.reuniao.model.Usuario;
import tm.info.reuniao.repositorio.IPublicadorServico;
import tm.info.reuniao.repositorio.IUsuarioServico;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class RestApiController {

    @Autowired
    private IPublicadorServico publicadorServico;
    
    @Autowired
	private IUsuarioServico usuarioServico;


	@RequestMapping(value = "/perfil", method = RequestMethod.GET, produces = "application/json")
	public ETipoAcesso[] mostrarPerfis() {
		return ETipoAcesso.values();
	}
    
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public List<Usuario> mostrarUsuarios() {
		return usuarioServico.findAll();
	}

    @DeleteMapping("/user/deleteall")
    public ResponseEntity<String> deleteAllUsuarios() {
        System.out.println("Apagar todos os Usuarios...");
        usuarioServico.deleteAll();
        return new ResponseEntity<>("Todos os usuarios foram removidos do banco de dados com sucesso!", HttpStatus.OK);
    }

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	// public Usuario cadastrarUsuario(@RequestBody Usuario newUser) {
	public ResponseEntity<Response<Usuario>> cadastrarUsuario(@Valid @RequestParam Usuario newUser, BindingResult result) {
        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach((error)->errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new Response<>(errors));
        }
		Usuario _usuario = usuarioServico
				.save(new Usuario(newUser.getUsuario(), newUser.getPassword(), newUser.getPerfil()));

		return ResponseEntity.ok(new Response<Usuario>(_usuario));

	}


    //    @GetMapping("/{nomeCompleto}")
    @RequestMapping(value = "/api/{nomeCompleto}", method = RequestMethod.GET)
    public List<Publicador> BuscaPublicadorPrimeiroNome(@PathVariable String nomeCompleto) {
        return publicadorServico.findByNomeCompleto(nomeCompleto);
    }

    @GetMapping("/api/publicadores")
    public List<Publicador> getAllPublicadores() {
        System.out.println("Buscar todos os Publicadores...");

        List<Publicador> publicadores = new ArrayList<>();
//        repositorioPublicador.findAll().forEach(p->publicadores.add(p));
        publicadorServico.findAll().forEach(publicadores::add);

        return publicadores;
    }

    @PostMapping(value = "/api/publicadores/create",consumes = "application/json")
    public ResponseEntity<Response<Object>> postPublicador(@Valid @RequestBody Publicador publicador, BindingResult result) {
        System.out.println("Criando Publicador");
        System.out.println(publicador);

        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach((error)->errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new Response<>(errors));
        }

        List<Publicador> checkPublicadoor = publicadorServico.findByEmail(publicador.getEmail());
        System.out.println("checando publicador existente:");
        System.out.println(checkPublicadoor);
        if(checkPublicadoor.isEmpty()) {
            Publicador _publicadores = publicadorServico.save(new Publicador(
                    publicador.getEmail(),
                    publicador.getNomeCompleto(),
                    publicador.getEndereco(),
                    publicador.getTelefone(),
                    publicador.getDataNascimento(),
                    publicador.getDataBatismo(),
                    publicador.getOutros(),
                    publicador.getDetalhes()));

//            String fullName= publicador.getNomeCompleto();
//            String surName=fullName.split(" ")[fullName.split(" ").length-1];
//            String firstName = fullName.substring(0, fullName.length() - surName.length());
//            System.out.println(firstName );

            Usuario _user = usuarioServico
                    .save(new Usuario(publicador.getNomeCompleto().split(" ")[0], "campo", ETipoAcesso.PUBLICADOR));

//            return _publicadores;
            return ResponseEntity.ok(new Response<>(_publicadores));
        }
        else{
            System.out.println("Publicador com o mesmo Email ja existente! Crie novamente com email diferente!"+publicador.getEmail() );
            return new ResponseEntity("Publicador " + publicador.getEmail() + " com email existente!!!", HttpStatus.CONFLICT);
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
    public ResponseEntity<Response<Object>> updatePublicador(@Valid @PathVariable("id") String id, @RequestBody Publicador publicador, BindingResult result) {
        System.out.println("Atualizar Publicador com ID = " + id + "...");

        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach((error)->errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new Response<>(errors));
        }

        Optional<Publicador> publicadorData = publicadorServico.findById(id);

        if (publicadorData.isPresent()) {
            Publicador _publicador = publicadorData.get();
            _publicador.setDataNascimento(publicador.getDataNascimento());
            _publicador.setDataBatismo(publicador.getDataBatismo());
            _publicador.setDetalhes(publicador.getDetalhes());
            _publicador.setEmail(publicador.getEmail());
            _publicador.setEndereco(publicador.getEndereco());
            _publicador.setOutros(publicador.getOutros());
            _publicador.setNomeCompleto(publicador.getNomeCompleto());
            _publicador.setTelefone(publicador.getTelefone());
            return new ResponseEntity(publicadorServico.save(_publicador), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
