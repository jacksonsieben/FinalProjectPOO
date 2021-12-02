import java.util.*;

public interface IRepositorioCliente {
    public void inserirClienteFisico(String nome, String sobrenome, String cpf, String email, String telefone, String rua, int numero, String bairro, String cidade, String estado, String cep) throws Exception;
    public void inserirClienteJuridico (String nome, String cnpj, String ie, String email, String telefone, String rua, int numero, String bairro, String cidade, String estado, String cep) throws Exception;
    public void atualizarClienteFisico(int pessoaID, String nome, String sobrenome, String cpf, String email, String telefone, String rua, int numero, String bairro, String cidade, String estado, String cep) throws Exception;
    public void atualizarClienteJuridico(int pessoaID, String nome, String cnpj, String ie, String email, String telefone, String rua, int numero, String bairro, String cidade, String estado, String cep) throws Exception;
    public void deletarCliente(int pessoaID);
    public Pessoa obterClientePorID(int clienteID);
    public List<Pessoa> obterClientes();
}
