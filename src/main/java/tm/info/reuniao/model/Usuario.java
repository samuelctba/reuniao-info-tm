package tm.info.reuniao.model;

import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import tm.info.reuniao.config.ETipoAcesso;

@Document(collection = "usuario")
public class Usuario {

	@Id
	private ObjectId _id;
	@NotNull
	private String usuario;
	@NotNull
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
