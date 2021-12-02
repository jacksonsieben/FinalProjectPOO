import java.util.*;

public class ClienteFisico extends Pessoa{
    public String CPF, Sobrenome;

    List<Veiculo> Veiculos = new ArrayList<Veiculo>();
    ConnectSQLVeiculo ConexaoVeiculo = new ConnectSQLVeiculo();
    
    public ClienteFisico() {}

    public ClienteFisico(String nome, String sobrenome, String cpf, String email, String telefone) throws Exception {
        Nome = nome;
        Sobrenome = sobrenome;
        CPF = cpf;
        Email = email;
        Telefone = telefone;
    }
    public ClienteFisico(String nome, String sobrenome, String cpf, String email, String telefone, String rua, int numero, String bairro, String cidade, String estado, String cep) throws Exception{
        setNome(nome);
        setSobrenome(sobrenome);
        setCPF(cpf);   
        Tipo = "Física";
        Email = email;
        Telefone = telefone;
        Endereco.Rua=rua;
        Endereco.Numero = numero;
        Endereco.Bairro = bairro;
        Endereco.Cidade = cidade;
        Endereco.Estado = estado;
        Endereco.Cep = cep;
    }
    public ClienteFisico(int pessoaID, String nome, String sobrenome, String cpf, String email, String telefone, String rua, int numero, String bairro, String cidade, String estado, String cep) throws Exception{
        PessoaID = pessoaID;
        setNome(nome);
        setSobrenome(sobrenome);
        setCPF(cpf);        
        Tipo = "Física";
        Email = email;
        Telefone = telefone;
        Endereco.Rua=rua;
        Endereco.Numero = numero;
        Endereco.Bairro = bairro;
        Endereco.Cidade = cidade;
        Endereco.Estado = estado;
        Endereco.Cep = cep;
    }

    public void novoVeiculo(Veiculo veiculo){
        Veiculos.add(veiculo);
    }

    public String getSobrenome() {
        return Sobrenome;
    }
    public String getCPF() {
        return CPF;
    }
    public List<Veiculo> getVeiculos() throws Exception {
        Veiculos = ConexaoVeiculo.getTbVeiculoPorCliente(PessoaID);
        return Veiculos;
    }

    public void setCPF(String cpf) {
        this.CPF = Objects.requireNonNull(cpf, "CPF não pode ser vazio");
    }
    public void setSobrenome(String sobrenome) {
        this.Sobrenome = Objects.requireNonNull(sobrenome, "Sobrenome não pode ser vazio");
    }
    public void setVeiculos(Veiculo veiculo){
        Veiculos.add(veiculo);
    }
    
}
