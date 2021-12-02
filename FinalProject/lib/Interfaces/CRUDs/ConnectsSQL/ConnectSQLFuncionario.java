import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectSQLFuncionario extends ConnectSQL{
    
    public ConnectSQLFuncionario() {}

    public int getFuncionarioID() throws Exception{
        int funcionarioID = 0;
        Connection con = mySqlConnection();
        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("SELECT max(PessoaID) FROM tb_funcionario;");
            while(rs.next()) {
                funcionarioID = rs.getInt("max(PessoaID)");                
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados ");}
        return funcionarioID+1;
    }

    public void inserirTbFuncionario(Pessoa p) throws Exception{
        con = mySqlConnection();
        try{    
            Statement stmt=con.createStatement();       
            String query1 = ("INSERT INTO tb_funcionario (Nome, Sobrenome, CPF, Email, Telefone, Cargo, Salario, Rua, Numero, Bairro, Cidade, Estado, CEP)" +  
                            "VALUES ( '" + p.Nome + "', '" + ((Funcionario)p).Sobrenome + "', '" + ((Funcionario)p).CPF + "', '" + p.Email + "', '" + p.Telefone + "', '" + ((Funcionario)p).Cargo + "', '" + ((Funcionario)p).Salario 
                            + "', '" + p.Endereco.Rua + "', '" + p.Endereco.Numero + "', '" + p.Endereco.Bairro + "', '" + p.Endereco.Cidade + "', '" + p.Endereco.Estado + "', '" + p.Endereco.Cep + "')");
            stmt.execute(query1);
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados ");}
    }

    public Pessoa getTbFuncionarioPorID(int i) throws Exception{
        Funcionario funcionario = new Funcionario();
        Connection con = mySqlConnection();

        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from tb_funcionario where PessoaID = '" + i + "';");
            while (rs.next()){
                funcionario.setPessoaID(rs.getInt("PessoaID")); 
                funcionario.setNome(rs.getString("Nome"));
                funcionario.setSobrenome(rs.getString("Sobrenome"));
                funcionario.setCPF(rs.getString("CPF"));
                funcionario.setTelefone(rs.getString("Telefone"));
                funcionario.Endereco.setRua(rs.getString("Rua"));
                funcionario.Endereco.setBairro(rs.getString("Bairro"));
                funcionario.Endereco.setCidade(rs.getString("Cidade"));
                funcionario.Endereco.setEstado(rs.getString("Estado"));
                funcionario.Endereco.setNumero(rs.getInt("Numero"));
                funcionario.Endereco.setCep(rs.getString("CEP"));
                funcionario.setCargo(rs.getString("Cargo"));
                funcionario.setSalario(rs.getFloat("Salario"));
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados ");}

        return (Pessoa) funcionario;
    }

    public List<Pessoa> getTbFuncionario() throws Exception{
        Funcionario funcionario = new Funcionario();
        Connection con = mySqlConnection();
        List<Pessoa> p = new ArrayList<Pessoa>();

        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from tb_funcionario;");
            while (rs.next()){
                funcionario = new Funcionario();
                funcionario.setPessoaID(rs.getInt("PessoaID")); 
                funcionario.setNome(rs.getString("Nome"));
                funcionario.setSobrenome(rs.getString("Sobrenome"));
                funcionario.setCPF(rs.getString("CPF"));
                funcionario.setTelefone(rs.getString("Telefone"));
                funcionario.Endereco.setRua(rs.getString("Rua"));
                funcionario.Endereco.setBairro(rs.getString("Bairro"));
                funcionario.Endereco.setCidade(rs.getString("Cidade"));
                funcionario.Endereco.setEstado(rs.getString("Estado"));
                funcionario.Endereco.setNumero(rs.getInt("Numero"));
                funcionario.Endereco.setCep(rs.getString("CEP"));
                funcionario.setCargo(rs.getString("Cargo"));
                funcionario.setSalario(rs.getFloat("Salario"));

                p.add(funcionario);

            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados ");}

        return p;
    }

    public void atualizarTbFuncionario(Funcionario funcionario) throws Exception{
        con = mySqlConnection();
        try {
            Statement stmt = con.createStatement();
            String query = ("UPDATE tb_funcionario SET Nome = '" + funcionario.Nome + "', Sobrenome = '" + funcionario.Sobrenome + "', CPF = '" 
                            + funcionario.CPF +"', Email = '" + funcionario.Email + "', Telefone = '" + funcionario.Telefone + "', Cargo = '" + funcionario.Cargo + "', Salario = '" + funcionario.Salario 
                            + "', Rua = '" + funcionario.Endereco.Rua + "', Numero = '" + funcionario.Endereco.Numero 
                            + "', Bairro = '" + funcionario.Endereco.Bairro + "', Cidade = '" + funcionario.Endereco.Cidade + "', Estado = '" + funcionario.Endereco.Estado + "', CEP = '" + funcionario.Endereco.Cep 
                            +"' WHERE PessoaID = '" + funcionario.PessoaID + "';");
            stmt.execute(query);
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados ");}
    }

    public void deletarFuncionario (int pessoaID) throws Exception{
        con = mySqlConnection();
        try{    
            Statement stmt=con.createStatement();       
            String query1 = ("DELETE FROM tb_funcionario where PessoaID = '" + pessoaID + "';");
            stmt.execute(query1);
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados ");}
    }
}
