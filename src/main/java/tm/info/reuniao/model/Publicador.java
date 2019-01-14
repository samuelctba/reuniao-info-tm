package tm.info.reuniao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//@Collection(name="publicador")
@Document(collection = "publicador")
public class Publicador {

    @Id
    private String id;

    @NotNull
    @Size(min=2, max=30)
    private String email;
    @NotNull
//    @Min(18)
    private String primeiroNome;
    @NotNull
    private String sobreNome;
    @NotNull
    private String endereco;
    @NotNull
    private String telefone;
    @NotNull
    private String dataNascimento;
    @NotNull
    private String dataBatismo;
    private String outros;
    private String detalhes;

    public Publicador(@NotNull @Size(min = 2, max = 30) String email, @NotNull String primeiroNome, @NotNull String sobreNome, @NotNull String endereco, @NotNull String telefone, @NotNull String dataNascimento, @NotNull String dataBatismo, String outros, String detalhes) {
        this.email = email;
        this.primeiroNome = primeiroNome;
        this.sobreNome = sobreNome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.dataBatismo = dataBatismo;
        this.outros = outros;
        this.detalhes = detalhes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDataBatismo() {
        return dataBatismo;
    }

    public void setDataBatismo(String dataBatismo) {
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

    public String getId() {
        return id;
    }
}
