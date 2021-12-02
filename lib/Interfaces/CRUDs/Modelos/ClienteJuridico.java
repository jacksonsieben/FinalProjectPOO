import java.util.*;

public class ClienteJuridico extends Pessoa {
    public String CNPJ, IE;


    List<Veiculo> Veiculos = new ArrayList<Veiculo>();
    ConnectSQLVeiculo ConexaoVeiculo = new ConnectSQLVeiculo();
    
    public ClienteJuridico() {}

    public ClienteJuridico (String nome, String cnpj, String email, String telefone) throws Exception {
        setNome(nome);
        CNPJ = cnpj;
        Email = email;
        Telefone = telefone;
    }
    public ClienteJuridico(String nome, String cnpj, String ie, String email, String telefone, String rua, int numero, String bairro, String cidade, String estado, String cep)  throws Exception{
        setNome(nome);
        setCNPJ(cnpj);
        setIE(ie);
        Tipo = "Jurídica";
        Email = email;
        Telefone = telefone;
        Endereco.Rua=rua;
        Endereco.Numero = numero;
        Endereco.Bairro = bairro;
        Endereco.Cidade = cidade;
        Endereco.Estado = estado;
        Endereco.Cep = cep;
    }
    public ClienteJuridico(int pessoaID, String nome, String cnpj, String ie, String email, String telefone, String rua, int numero, String bairro, String cidade, String estado, String cep) throws Exception {
        PessoaID = pessoaID;
        setNome(nome);
        setCNPJ(cnpj);
        setIE(ie);
        Tipo = "Jurídica";
        Email = email;
        Telefone = telefone;
        Endereco.Rua=rua;
        Endereco.Numero = numero;
        Endereco.Bairro = bairro;
        Endereco.Cidade = cidade;
        Endereco.Estado = estado;
        Endereco.Cep = cep;
    }

    public String getCNPJ() {
        return CNPJ;
    }
    public String getIE() {
        return IE;
    }
    public List<Veiculo> getVeiculos() throws Exception {
        Veiculos = ConexaoVeiculo.getTbVeiculoPorCliente(PessoaID);
        return Veiculos;
    }

    public void setIE(String ie) {
        this.IE = Objects.requireNonNull(ie, "IE não pode ser vazia");
    }
    public void setCNPJ(String cnpj) {
        this.CNPJ = Objects.requireNonNull(cnpj, "CNPJ não pode ser vazio");;
    }

}
