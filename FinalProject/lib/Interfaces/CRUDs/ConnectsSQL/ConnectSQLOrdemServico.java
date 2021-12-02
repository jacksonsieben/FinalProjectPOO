import java.sql.*;
import java.util.*;

public class ConnectSQLOrdemServico extends ConnectSQL{

    public ConnectSQLOrdemServico() {}

    public int getOrdemServicoID() throws Exception{
        int ordemservicoID = 0;
        Connection con = mySqlConnection();
        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("SELECT max(OrdemServicoID) FROM tb_ordemservico;");
            while(rs.next()) {
                ordemservicoID = rs.getInt("max(OrdemServicoID)");                
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){System.out.println(e);}
        return ordemservicoID+1;
    }

    public void inserirTbOrdemServico(OrdemServico ordemservico) throws Exception{
        con = mySqlConnection();
        try {
            Statement stmt = con.createStatement();
            String query = ("INSERT INTO tb_ordemservico (OrdemServicoID, FuncionarioID, ClienteID, Subtotal, Total, Desconto, FormaPagamento)" +  
                            "VALUES ('" + ordemservico.OrdemServicoID + "', '" + ordemservico.funcionario.PessoaID + "', '" + ordemservico.Cliente.PessoaID + 
                            "', '" + ordemservico.SubTotal + "', '" + ordemservico.Total + "', '" + ordemservico.Desconto + "', '" + ordemservico.FormaPagamento  + "')");
            stmt.execute(query);
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados 001" + e.getMessage()); }
    }

    public void inserirTbItemServico(ItemServico itemServico) throws Exception{
        con = mySqlConnection();
        try {
            Statement stmt = con.createStatement();
            String query = ("INSERT INTO tb_itemservico (OrdemServicoID, ServicoID, SubTotalItem, Quantidade)" +  
                            "VALUES ('" + itemServico.OrdemServicoID + "', '" + itemServico.servico.ServicoID + 
                            "', '" + itemServico.SubtotalItem + "', '" + itemServico.Quantidade  + "')");
            stmt.execute(query);
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados 002" + e.getMessage()); }
    }

    public OrdemServico getTbOrdemServico(int ordemservicoID) throws Exception{
        OrdemServico ordemservico = new OrdemServico();
        con = mySqlConnection();
        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("SELECT * FROM tb_ordemservico INNER JOIN tb_itemservico ON tb_ordemservico.OrdemServicoID = tb_itemservico.OrdemServicoID WHERE tb_ordemservico.OrdemServicoID = '" + ordemservicoID + "';" );
            
            while (rs.next()){       
                ordemservico.setOrdemServicoID(rs.getInt("tb_ordemservico.OrdemServicoID"));
                ordemservico.setClienteID(rs.getInt("tb_ordemservico.ClienteID"));
                ordemservico.setFuncionarioID(rs.getInt("tb_ordemservico.FuncionarioID"));
                ordemservico.setFormaPagamento(rs.getString("tb_ordemservico.FormaPagamento"));
                ordemservico.setTotal(rs.getFloat("tb_ordemservico.Total"));
                ordemservico.setSubtotal(rs.getFloat("tb_ordemservico.Subtotal"));
                ordemservico.setDesconto(rs.getFloat("tb_ordemservico.Desconto"));
                ordemservico.novoItem(rs.getInt("tb_itemservico.ServicoID"), rs.getInt("tb_itemservico.Quantidade"));
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados 003" + e.getMessage());}

        return ordemservico;
    }

    public List<OrdemServico> getINNERTbOrdemServico() throws Exception{
        List<OrdemServico> OrdemServicoList = new ArrayList<OrdemServico>();
        OrdemServico ordemServico = null;

        boolean entrou;

        con = mySqlConnection();
        try{
            Statement stmt=con.createStatement();
            ResultSet rs = null;
            for (int i = 0; i < getOrdemServicoID(); i++) {
                Funcionario funcionario = new Funcionario();

                Pessoa pessoa = new Pessoa();
                ClienteFisico clienteFisico = new ClienteFisico();
                ClienteJuridico clienteJuridico = new ClienteJuridico();
                
                entrou = false;

                rs = stmt.executeQuery("SELECT * FROM (tb_ordemservico " +
                                                            "INNER JOIN tb_cliente ON tb_ordemservico.ClienteID = tb_cliente.PessoaID)" +
                                                            "INNER JOIN tb_funcionario ON tb_ordemservico.FuncionarioID = tb_funcionario.PessoaID WHERE tb_ordemservico.OrdemServicoID = '" + i + "';" );
            
                
                ordemServico = new OrdemServico();
                
                while (rs.next()){     
                    if(!rs.getString("tb_ordemservico.FormaPagamento").equals(null) || !rs.getString("tb_ordemservico.FormaPagamento").equals("")){ 
                        
                        ordemServico.setOrdemServicoID(rs.getInt("tb_ordemservico.OrdemServicoID"));
                        ordemServico.setFormaPagamento(rs.getString("tb_ordemservico.FormaPagamento"));
                        ordemServico.setTotal(rs.getFloat("tb_ordemservico.Total"));
                        ordemServico.setSubtotal(rs.getFloat("tb_ordemservico.Subtotal"));
                        ordemServico.setDesconto(rs.getFloat("tb_ordemservico.Desconto"));

                        pessoa.setTipo(rs.getString("tb_cliente.Tipo"));
                        if(rs.getString("tb_cliente.Tipo").equals("Física")){
                            clienteFisico.setPessoaID(rs.getInt("tb_cliente.PessoaID")); 
                            clienteFisico.setNome(rs.getString("tb_cliente.Nome"));
                            clienteFisico.setTipo(rs.getString("tb_cliente.Tipo"));
                            clienteFisico.setSobrenome(rs.getString("tb_cliente.Sobrenome"));
                            clienteFisico.setCPF(rs.getString("tb_cliente.CPF"));
                            clienteFisico.setEmail(rs.getString("tb_cliente.Email"));
                            clienteFisico.setTelefone(rs.getString("tb_cliente.Telefone"));
                            clienteFisico.Endereco.setRua(rs.getString("tb_cliente.Rua"));
                            clienteFisico.Endereco.setBairro(rs.getString("tb_cliente.Bairro"));
                            clienteFisico.Endereco.setCidade(rs.getString("tb_cliente.Cidade"));
                            clienteFisico.Endereco.setEstado(rs.getString("tb_cliente.Estado"));
                            clienteFisico.Endereco.setNumero(rs.getInt("tb_cliente.Numero"));
                            clienteFisico.Endereco.setCep(rs.getString("tb_cliente.CEP"));
                            ordemServico.setCliente(clienteFisico);

                        }else if(rs.getString("tb_cliente.Tipo").equals("Jurídica")){
                            clienteJuridico.setPessoaID(rs.getInt("tb_cliente.PessoaID")); 
                            clienteJuridico.setNome(rs.getString("tb_cliente.Nome"));
                            clienteJuridico.setTipo(rs.getString("tb_cliente.Tipo"));
                            clienteJuridico.setCNPJ(rs.getString("tb_cliente.CNPJ"));
                            clienteJuridico.setIE(rs.getString("tb_cliente.IE"));
                            clienteJuridico.setEmail(rs.getString("tb_cliente.Email"));
                            clienteJuridico.setTelefone(rs.getString("tb_cliente.Telefone"));
                            clienteJuridico.Endereco.setRua(rs.getString("tb_cliente.Rua"));
                            clienteJuridico.Endereco.setBairro(rs.getString("tb_cliente.Bairro"));
                            clienteJuridico.Endereco.setCidade(rs.getString("tb_cliente.Cidade"));
                            clienteJuridico.Endereco.setEstado(rs.getString("tb_cliente.Estado"));
                            clienteJuridico.Endereco.setNumero(rs.getInt("tb_cliente.Numero"));
                            clienteJuridico.Endereco.setCep(rs.getString("tb_cliente.CEP"));
                            ordemServico.setCliente(clienteJuridico);
                        }

                        funcionario.setPessoaID(rs.getInt("tb_funcionario.PessoaID")); 
                        funcionario.setNome(rs.getString("tb_funcionario.Nome"));
                        funcionario.setSobrenome(rs.getString("tb_funcionario.Sobrenome"));
                        funcionario.setCPF(rs.getString("tb_funcionario.CPF"));
                        funcionario.setTelefone(rs.getString("tb_funcionario.Telefone"));
                        funcionario.Endereco.setRua(rs.getString("tb_funcionario.Rua"));
                        funcionario.Endereco.setBairro(rs.getString("tb_funcionario.Bairro"));
                        funcionario.Endereco.setCidade(rs.getString("tb_funcionario.Cidade"));
                        funcionario.Endereco.setEstado(rs.getString("tb_funcionario.Estado"));
                        funcionario.Endereco.setNumero(rs.getInt("tb_funcionario.Numero"));
                        funcionario.Endereco.setCep(rs.getString("tb_funcionario.CEP"));
                        funcionario.setCargo(rs.getString("tb_funcionario.Cargo"));
                        funcionario.setSalario(rs.getFloat("tb_funcionario.Salario"));
                        ordemServico.setFuncionario(funcionario);

                        entrou = true;
                    }
                }
                if(entrou){
                    OrdemServicoList.add(ordemServico);
                }
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados - 004 " + e.getMessage());}

        return OrdemServicoList;
    }


    public void deletarTbOrdemServico(int ordemservicoID) throws Exception{
        con = mySqlConnection();
        try{
            Statement stmt=con.createStatement();       
            String query1 = ("DELETE FROM tb_ordemservico where OrdemServicoID = '" + ordemservicoID + "';");
            stmt.execute(query1);

            query1 = ("DELETE FROM tb_itemservico WHERE OrdemServicoID = '" + ordemservicoID + "';");
            stmt.execute(query1);

            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados 005" + e.getMessage());}
    }
}