package tm.info.reuniao.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import tm.info.reuniao.config.ETipoAcesso;

@Document(collection = "perfil")
public class Perfil {

	@Id
	private ObjectId id;
	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)

	private ETipoAcesso perfil;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getPerfil() {
		return perfil.toString();
	}

	public void setPerfil(ETipoAcesso perfil) {
		this.perfil = perfil;
	}

}