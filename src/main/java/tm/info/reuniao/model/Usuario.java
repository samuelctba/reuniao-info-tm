package tm.info.reuniao.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tm.info.reuniao.config.ETipoAcesso;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document(collection = "usuario")
public class Usuario {

	@Id
	private ObjectId _id;
	@NotNull
	@NotEmpty(message="Usuario não pode ser vazio. Digite um email!")
	private String usuario;
	@NotNull
	@NotEmpty(message="Password não pode ser vazio!")
	private String password;

	private ETipoAcesso perfil;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ObjectId get_id() {
		return _id;
	}

	public ETipoAcesso getPerfil() {
		return perfil;
	}

	public void setPerfil(ETipoAcesso perfil) {
		this.perfil = perfil;
	}

	public Usuario(@NotNull String usuario, @NotNull String password, ETipoAcesso perfil) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.perfil = perfil;
	}

}
