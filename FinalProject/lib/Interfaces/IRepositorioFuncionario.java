import java.util.*;

public interface IRepositorioFuncionario {
    public void inserirFuncionario(String nome, String sobrenome, String cpf, String email, String telefone, String rua, int numero, String bairro, String cidade, String estado, String cep, float salario, String cargo) throws Exception;
    public void atualizarFuncionario(int pessoaID, String nome, String sobrenome, String cpf, String email, String telefone, String rua, int numero, String bairro, String cidade, String estado, String cep, float salario, String cargo) throws Exception;
    public void deletarFuncionario(int pessoaID);
    public Funcionario obterFuncionarioPorID(int pessoaID);
    public List<Pessoa> obterFuncionarios();
}
