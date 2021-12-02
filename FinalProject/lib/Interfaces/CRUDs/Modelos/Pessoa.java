import java.util.Objects;

public class Pessoa {
    protected String Nome;
    protected String Tipo;
    protected String Email;
    protected String Telefone;
    protected int PessoaID = 0;
    protected Endereco Endereco = new Endereco();
    
    public int getPessoaID() {
        return PessoaID;
    }
    public String getNome() {
        return Nome;
    }
    public String getTipo() {
        return Tipo;
    }
    public String getEmail() {
        return Email;
    }
    public String getTelefone() {
        return Telefone;
    }
    
    public void setPessoaID(int pessoaID) {
        this.PessoaID = pessoaID;
    }
    public void setNome(String nome) throws Exception {
        if(nome.equals("") || nome.equals(null)){
            throw new Exception("Nome nao pode ser vazio");
        }else{this.Nome = Objects.requireNonNull(nome, "Nome não pode ser vazio");}
    }
    public void setTipo(String tipo) {
        this.Tipo = tipo;
    }
    public void setEmail(String email) {
        this.Email = email;
    }
    public void setTelefone(String telefone) {
        this.Telefone = telefone;
    }
}
    
    
    
    
    
