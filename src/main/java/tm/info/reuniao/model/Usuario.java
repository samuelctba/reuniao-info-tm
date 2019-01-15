package tm.info.reuniao.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "usuario")
public class Usuario {

    @Id
    private ObjectId _id;
    @NotNull
    private String usuario;
    @NotNull
    private String password;

    private ETipoAcesso tipoAcesso;



}
