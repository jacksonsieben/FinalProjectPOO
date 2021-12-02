import java.sql.*;
import java.util.*;

public class ConnectSQLCliente extends ConnectSQL{
    
    public ConnectSQLCliente() {}
    
    public int getClienteID() throws Exception{
        int pessoaID = 0;
        Connection con = mySqlConnection();
        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("SELECT max(PessoaID) FROM tb_cliente;");
            while(rs.next()) {
                pessoaID = rs.getInt("max(PessoaID)");                
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados " + e.getMessage()); }
        return pessoaID;
    }

    public void inserirTbCliente(Pessoa p) throws Exception{
        con = mySqlConnection();
        if(p.getTipo() == "Física"){
            try{    
                Statement stmt=con.createStatement();       
                String query1 = ("INSERT INTO tb_cliente (PessoaID, Nome, Sobrenome, Tipo, CPF, Email, Telefone)" +  
                                "VALUES ( '" + p.PessoaID + "', '" + p.Nome + "', '" + ((ClienteFisico)p).Sobrenome + 
                                        "', '" + p.Tipo + "', '" + ((ClienteFisico)p).CPF + "', '" + p.Email + "', '" + p.Telefone + "')");
                stmt.execute(query1);
                closeConnection(con);
            }catch(Exception e){ throw new Exception("Base de Dados " + e.getMessage());}
        }
        if(p.getTipo() == "Jurídica"){
            try{    
                Statement stmt=con.createStatement();       
                String query1 = ("INSERT INTO tb_cliente (PessoaID, Nome, CNPJ, Tipo, IE, Email, Telefone)" +  
                                "VALUES ( '" + p.PessoaID + "', '" +  p.Nome + "', '" + ((ClienteJuridico)p).CNPJ + 
                                        "', '" + p.Tipo + "', '" + ((ClienteJuridico)p).IE + "', '" + p.Email + "', '" + p.Telefone + "')");
                stmt.execute(query1);
                closeConnection(con);
            }catch(Exception e){ throw new Exception("Base de Dados " + e.getMessage());}
        }
    }

    public Pessoa getTbClientePorID(int i) throws Exception{
        Pessoa pessoa = new Pessoa();
        ClienteFisico clienteFisico = new ClienteFisico();
        ClienteJuridico clienteJuridico = new ClienteJuridico();
        con = mySqlConnection();

        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from tb_cliente where PessoaID = '" + i + "';");
            while (rs.next()){
                pessoa.setTipo(rs.getString("Tipo"));
                if(rs.getString("Tipo").equals("Física")){
                    clienteFisico.setPessoaID(rs.getInt("PessoaID")); 
                    clienteFisico.setNome(rs.getString("Nome"));
                    clienteFisico.setTipo(rs.getString("Tipo"));
                    clienteFisico.setSobrenome(rs.getString("Sobrenome"));
                    clienteFisico.setCPF(rs.getString("CPF"));
                    clienteFisico.setEmail(rs.getString("Email"));
                    clienteFisico.setTelefone(rs.getString("Telefone"));
                    clienteFisico.Endereco.setRua(rs.getString("Rua"));
                    clienteFisico.Endereco.setBairro(rs.getString("Bairro"));
                    clienteFisico.Endereco.setCidade(rs.getString("Cidade"));
                    clienteFisico.Endereco.setEstado(rs.getString("Estado"));
                    clienteFisico.Endereco.setNumero(rs.getInt("Numero"));
                    clienteFisico.Endereco.setCep(rs.getString("CEP"));

                }else if(rs.getString("Tipo").equals("Jurídica")){
                    clienteJuridico.setPessoaID(rs.getInt("PessoaID")); 
                    clienteJuridico.setNome(rs.getString("Nome"));
                    clienteJuridico.setTipo(rs.getString("Tipo"));
                    clienteJuridico.setCNPJ(rs.getString("CNPJ"));
                    clienteJuridico.setIE(rs.getString("IE"));
                    clienteJuridico.setEmail(rs.getString("Email"));
                    clienteJuridico.setTelefone(rs.getString("Telefone"));
                    clienteJuridico.Endereco.setRua(rs.getString("Rua"));
                    clienteJuridico.Endereco.setBairro(rs.getString("Bairro"));
                    clienteJuridico.Endereco.setCidade(rs.getString("Cidade"));
                    clienteJuridico.Endereco.setEstado(rs.getString("Estado"));
                    clienteJuridico.Endereco.setNumero(rs.getInt("Numero"));
                    clienteJuridico.Endereco.setCep(rs.getString("CEP"));
                }
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados ");}
        
        if(pessoa.Tipo.equals("Física")){ return (Pessoa) clienteFisico;}
        
        else {return (Pessoa) clienteJuridico;}
    }

    public List<Pessoa> getTbCliente() throws Exception{
        Pessoa pessoa = new Pessoa();
        List<Pessoa> p = new ArrayList<Pessoa>();
        ClienteFisico clienteFisico = new ClienteFisico();
        ClienteJuridico clienteJuridico = new ClienteJuridico();
        con = mySqlConnection();

        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from tb_cliente;");
            while (rs.next()){
                pessoa.setTipo(rs.getString("Tipo"));
                if(rs.getString("Tipo").equals("Física")){

                    clienteFisico = new ClienteFisico();
                    clienteFisico.setPessoaID(rs.getInt("PessoaID")); 
                    clienteFisico.setNome(rs.getString("Nome"));
                    clienteFisico.setTipo(rs.getString("Tipo"));
                    clienteFisico.setSobrenome(rs.getString("Sobrenome"));
                    clienteFisico.setCPF(rs.getString("CPF"));
                    clienteFisico.setEmail(rs.getString("Email"));
                    clienteFisico.setTelefone(rs.getString("Telefone"));
                    clienteFisico.Endereco.setRua(rs.getString("Rua"));
                    clienteFisico.Endereco.setBairro(rs.getString("Bairro"));
                    clienteFisico.Endereco.setCidade(rs.getString("Cidade"));
                    clienteFisico.Endereco.setEstado(rs.getString("Estado"));
                    clienteFisico.Endereco.setNumero(rs.getInt("Numero"));
                    clienteFisico.Endereco.setCep(rs.getString("CEP"));
                    p.add(clienteFisico);

                }else if(rs.getString("Tipo").equals("Jurídica")){

                    clienteJuridico = new ClienteJuridico();
                    clienteJuridico.setPessoaID(rs.getInt("PessoaID")); 
                    clienteJuridico.setNome(rs.getString("Nome"));
                    clienteJuridico.setTipo(rs.getString("Tipo"));
                    clienteJuridico.setCNPJ(rs.getString("CNPJ"));
                    clienteJuridico.setIE(rs.getString("IE"));
                    clienteJuridico.setEmail(rs.getString("Email"));
                    clienteJuridico.setTelefone(rs.getString("Telefone"));
                    clienteJuridico.Endereco.setRua(rs.getString("Rua"));
                    clienteJuridico.Endereco.setBairro(rs.getString("Bairro"));
                    clienteJuridico.Endereco.setCidade(rs.getString("Cidade"));
                    clienteJuridico.Endereco.setEstado(rs.getString("Estado"));
                    clienteJuridico.Endereco.setNumero(rs.getInt("Numero"));
                    clienteJuridico.Endereco.setCep(rs.getString("CEP"));
                    p.add(clienteJuridico);
                }
                                
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados ");}
        
        return p;
    }

    public void atualizarTbCliente(Pessoa pessoa) throws Exception{
        con = mySqlConnection();
        try {
            Statement stmt = con.createStatement();
            if(pessoa.Tipo.equals("Física")){ 
                String query = ("UPDATE tb_cliente SET Nome = '" + pessoa.Nome + "', Sobrenome = '" + ((ClienteFisico) pessoa).Sobrenome + "', CPF = '" 
                + ((ClienteFisico) pessoa).CPF +"', Email = '" + pessoa.Email + "', Telefone = '" + pessoa.Telefone + "', Rua = '" + pessoa.Endereco.Rua + "', Numero = '" + pessoa.Endereco.Numero 
                + "', Bairro = '" + pessoa.Endereco.Bairro + "', Cidade = '" + pessoa.Endereco.Cidade + "', Estado = '" + pessoa.Endereco.Estado + "', CEP = '" + pessoa.Endereco.Cep
                +"' WHERE PessoaID = '" + pessoa.PessoaID + "';");
                stmt.execute(query);
                closeConnection(con);
            }else if(pessoa.Tipo.equals("Jurídica")){
                String query = ("UPDATE tb_cliente SET Nome = '" + pessoa.Nome + "', CNPJ = '" + ((ClienteJuridico) pessoa).CNPJ + "', IE = '" 
                + ((ClienteJuridico) pessoa).IE +"', Email = '" + pessoa.Email + "', Telefone = '" + pessoa.Telefone + "', Rua = '" + pessoa.Endereco.Rua + "', Numero = '" + pessoa.Endereco.Numero 
                + "', Bairro = '" + pessoa.Endereco.Bairro + "', Cidade = '" + pessoa.Endereco.Cidade + "', Estado = '" + pessoa.Endereco.Estado + "', CEP = '" + pessoa.Endereco.Cep
                +"' WHERE PessoaID = '" + pessoa.PessoaID + "';");
                stmt.execute(query);
                closeConnection(con);
            }
            
        }catch(Exception e){ throw new Exception("Base de Dados ");}
    }

    public void deletarCliente(int pessoaID) throws Exception{
        con = mySqlConnection();
        try{
            Statement stmt = con.createStatement();
            String query = ("DELETE FROM tb_cliente WHERE PessoaID = '" + pessoaID + "';");
            stmt.execute(query);
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados ");}
    }

}