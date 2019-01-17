package tm.info.reuniao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tm.info.reuniao.model.Usuario;
import tm.info.reuniao.repositorio.IUsuarioRepo;

@RestController
@RequestMapping("/user")
public class UsuarioCtrl {

	@Autowired
	private IUsuarioRepo usuarioRepo;

	public UsuarioCtrl() {

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Usuario> mostrarUsuarios() {
		return usuarioRepo.findAll();
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
//	public Usuario cadastrarUsuario(@RequestBody Usuario newUser) {
	public Usuario cadastrarUsuario(@RequestParam Usuario newUser) {
		return usuarioRepo.save(newUser);
	}

}
