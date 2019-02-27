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

    @NotNull(message="Email não pode ser nulo!")
    @Size(min=2, max=30, message = "Email tem que ter entre 2 e 30 caracteres")
    @Pattern(regexp = "^(.+)@(.+)$")
    @Email(message = "Email tem que ser Valido!")
    private String email;
    @Size(min=6)
    @NotNull(message="Nome completo não pode ser nulo!")
    @NotEmpty(message="Preencha nome completo")
    private String nomeCompleto;
    @NotNull(message="Endereço não pode ser nulo!")
    @NotEmpty(message="Preencha o endereço")
    private String endereco;
    @NotNull(message="Telefone não pode ser nulo!")
    @NotEmpty(message="Preencha telefone")
    private String telefone;
    @NotNull(message="Data de Nascimento não pode ser nulo!")
    @Past(message="Preencha data Formato dd-MM-yyyy que seja no passado.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat( pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;
    @NotNull(message="Data de Batismo não pode ser nulo!")
    @Past(message="Preencha data Formato dd-MM-yyyy que seja no passado.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat( pattern = "dd-MM-yyyy" )
    private LocalDate dataBatismo;
    private String outros;
    private String detalhes;

    public Publicador(@NotNull @Size(min = 2, max = 30) @Pattern(regexp = "^(.+)@(.+)$") @Email(message = "Email should be valid") String email, @NotNull @NotEmpty(message = "Preencha nome completo") @Min(6) String nomeCompleto, @NotNull @NotEmpty(message = "Preencha o endereço") String endereco, @NotNull @NotEmpty(message = "Preencha telefone") String telefone, @NotNull @Past(message = "Preencha data Formato dd-MM-yyyy que seja no passado.") LocalDate dataNascimento, @NotNull @Past(message = "Preencha data Formato dd-MM-yyyy que seja no passado.") LocalDate dataBatismo, String outros, String detalhes) {
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
