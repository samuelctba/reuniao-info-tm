package tm.info.reuniao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tm.info.reuniao.model.Usuario;
import tm.info.reuniao.repositorio.IUsuarioServico;

@RestController
@RequestMapping("/user")
public class UsuarioCtrl {

	@Autowired
	private IUsuarioServico usuarioRepo;

	public UsuarioCtrl() {

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Usuario> mostrarUsuarios() {
		return usuarioRepo.findAll();
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	// public Usuario cadastrarUsuario(@RequestBody Usuario newUser) {
	public ResponseEntity<String> cadastrarUsuario(@RequestParam Usuario newUser) {

		Usuario _usuario = usuarioRepo
				.save(new Usuario(newUser.getUsuario(), newUser.getPassword(), newUser.getPerfil()));

		return new ResponseEntity<>("Usuario " + _usuario.get_id() + " foi criado com sucesso!!!", HttpStatus.OK);

	}

}
