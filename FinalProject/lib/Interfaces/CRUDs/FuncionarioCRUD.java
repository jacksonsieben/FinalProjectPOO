import java.util.*;

public class FuncionarioCRUD implements IRepositorioFuncionario {
    Funcionario funcionario = new Funcionario();
    Pessoa pessoa = new Pessoa();
    ConnectSQLFuncionario Conexao = new ConnectSQLFuncionario();

    public FuncionarioCRUD() {}

    @Override
    public void inserirFuncionario(String nome, String sobrenome, String cpf, String email, String telefone, String rua,
                                    int numero, String bairro, String cidade, String estado, String cep, float salario, String cargo)
                                    throws Exception {

        funcionario = new Funcionario(nome, sobrenome, cpf, email, telefone, rua, numero, bairro, cidade, estado, cep, salario, cargo);
        Conexao.inserirTbFuncionario(funcionario);

    }

    @Override
    public void atualizarFuncionario(int pessoaID, String nome, String sobrenome, String cpf, String email,
                                        String telefone, String rua, int numero, String bairro, String cidade, String estado, String cep,
                                        float salario, String cargo) throws Exception {

        funcionario = new Funcionario(pessoaID, nome, sobrenome, cpf, email, telefone, rua, numero, bairro, cidade, estado, cep, salario, cargo);
        Conexao.atualizarTbFuncionario(funcionario);

    }

    @Override
    public void deletarFuncionario(int pessoaID) {
        try {
            Conexao.deletarFuncionario(pessoaID);
        } catch (Exception e) {
            System.out.println("Erro - " + e.getMessage());
        }
    }

    @Override
    public Funcionario obterFuncionarioPorID(int pessoaID) {
        try {
            funcionario = (Funcionario) Conexao.getTbFuncionarioPorID(pessoaID);
        } catch (Exception e) {
            System.out.println("Erro - " + e.getMessage());
        }

        return funcionario;
    }

    @Override
    public List<Pessoa> obterFuncionarios() {
        List<Pessoa> p = new ArrayList<Pessoa>();
        try {

            p = Conexao.getTbFuncionario();

        } catch (Exception e) {
            System.out.println("Erro - " + e.getMessage());
        }

        return p;
    }

}
