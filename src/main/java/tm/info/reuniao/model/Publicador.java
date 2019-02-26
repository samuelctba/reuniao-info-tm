package tm.info.reuniao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;


//@Collection(name="publicador")
@Document(collection = "publicador")
public class Publicador {

    public Publicador() {
		super();
	}

	@Id
    private ObjectId _id;

    @NotNull
    @Size(min=2, max=30)
    @Pattern(regexp = "^(.+)@(.+)$")
    @Email(message = "Email should be valid")
    private String email;
    @NotNull
    @Min(6)
    private String nomeCompleto;
    @NotNull
    private String endereco;
    @NotNull
    private String telefone;
    @NotNull
    @Past
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat( pattern = "dd-MM-yyyy" )
    private LocalDate dataNascimento;
    @NotNull
    @Past
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat( pattern = "dd-MM-yyyy" )
    private LocalDate dataBatismo;
    private String outros;
    private String detalhes;

    public Publicador(@NotNull @Size(min = 2, max = 30) @Pattern(regexp = "^(.+)@(.+)$") @Email(message = "Email should be valid") String email, @NotNull @Min(6) String nomeCompleto, @NotNull String endereco, @NotNull String telefone, @NotNull @Past LocalDate dataNascimento, @NotNull @Past LocalDate dataBatismo, String outros, String detalhes) {
        this.email = email;
        this.nomeCompleto = nomeCompleto;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.dataBatismo = dataBatismo;
        this.outros = outros;
        this.detalhes = detalhes;
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataBatismo() {
        return dataBatismo;
    }

    public void setDataBatismo(LocalDate dataBatismo) {
        this.dataBatismo = dataBatismo;
    }

    public String getOutros() {
        return outros;
    }

    public void setOutros(String outros) {
        this.outros = outros;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
}
