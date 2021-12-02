import java.util.*;

public class ClienteCRUD implements IRepositorioCliente {
    ClienteFisico clienteFisico = new ClienteFisico();
    ClienteJuridico clienteJuridico = new ClienteJuridico();
    Pessoa pessoa = new Pessoa();
    ConnectSQLCliente Conexao = new ConnectSQLCliente();

    public ClienteCRUD() {}

    @Override
    public void inserirClienteFisico(String nome, String sobrenome, String cpf, String email, String telefone,
            String rua, int numero, String bairro, String cidade, String estado, String cep) throws Exception {

        clienteFisico = new ClienteFisico(nome, sobrenome, cpf, email, telefone, rua, numero, bairro, cidade, estado, cep);
        Conexao.inserirTbCliente(clienteFisico);

    }

    @Override
    public void inserirClienteJuridico(String nome, String cnpj, String ie, String email, String telefone, String rua,
                                        int numero, String bairro, String cidade, String estado, String cep) throws Exception {

        clienteJuridico = new ClienteJuridico(nome, cnpj, ie, email, telefone, rua, numero, bairro, cidade, estado, cep);
        Conexao.inserirTbCliente(clienteJuridico);

    }

    @Override
    public void atualizarClienteFisico(int pessoaID, String nome, String sobrenome, String cpf, String email,
                                        String telefone, String rua, int numero, String bairro, String cidade, String estado, String cep)
                                        throws Exception {

        pessoa = new ClienteFisico(pessoaID, nome, sobrenome, cpf, email, telefone, rua, numero, bairro, cidade, estado, cep);
        Conexao.atualizarTbCliente(pessoa);

    }

    @Override
    public void atualizarClienteJuridico(int pessoaID, String nome, String cnpj, String ie, String email,
                                            String telefone, String rua, int numero, String bairro, String cidade, String estado, String cep)
                                            throws Exception {

        pessoa = new ClienteFisico(pessoaID, nome, cnpj, ie, email, telefone, rua, numero, bairro, cidade, estado, cep);
        Conexao.atualizarTbCliente(pessoa);

    }

    @Override
    public void deletarCliente(int pessoaID) {
        try {
            Conexao.deletarCliente(pessoaID);
        } catch (Exception e) {
            System.out.println("Erro - " + e.getMessage());
        }
    }

    @Override
    public Pessoa obterClientePorID(int pessoaID) {
        try {
            pessoa = Conexao.getTbClientePorID(pessoaID);
        } catch (Exception e) {
            System.out.println("Erro - " + e.getMessage());
        }

        return pessoa;
    }

    @Override
    public List<Pessoa> obterClientes() {
        List<Pessoa> PessoaList = new ArrayList<Pessoa>();
        try {
            PessoaList = Conexao.getTbCliente();
        } catch (Exception e) {
            System.out.println("Erro - " + e.getMessage());
        }

        return PessoaList;
    }
}
