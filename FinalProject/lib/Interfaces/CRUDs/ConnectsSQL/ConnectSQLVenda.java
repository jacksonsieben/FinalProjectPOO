import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectSQLVenda extends ConnectSQL{

    public ConnectSQLVenda() {}

    public int getVendaID() throws Exception{
        int vendaID = 0;
        Connection con = mySqlConnection();
        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select max(VendaID) from tb_venda;");
            while(rs.next()) {
                vendaID = rs.getInt("max(VendaID)");                
            }
            
            rs.close();
            closeConnection(con);
        }catch(Exception e){throw new Exception("Base de Dados - 001");}
        return vendaID+1;
    }

    public void inserirTbVenda(Venda venda) throws Exception{
        con = mySqlConnection();
        try {
            Statement stmt = con.createStatement();
            String query = ("INSERT INTO tb_venda (VendaID, FuncionarioID, ClienteID, Subtotal, Total, Desconto, FormaPagamento)" +  
                            "VALUES ('" + venda.VendaID + "', '" + venda.funcionario.PessoaID + "', '" + venda.Cliente.PessoaID + 
                            "', '" + venda.SubTotal + "', '" + venda.Total + "', '" + venda.Desconto + "', '" + venda.FormaPagamento  + "')");
            stmt.execute(query);
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados - 002"); }
    }

    public Venda getTbVendaPorID(int vendaID) throws Exception{
        Venda venda = new Venda();
        con = mySqlConnection();
        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("SELECT * FROM tb_venda INNER JOIN tb_itemvenda ON tb_venda.VendaID = tb_itemvenda.VendaID WHERE tb_venda.VendaID = '" + vendaID + "';" );
            
            while (rs.next()){ 
                
                venda.setVendaID(rs.getInt("tb_venda.VendaID"));
                venda.setClienteID(rs.getInt("tb_venda.ClienteID"));
                venda.setFuncionarioID(rs.getInt("tb_venda.FuncionarioID"));
                venda.setFormaPagamento(rs.getString("tb_venda.FormaPagamento"));
                venda.setTotal(rs.getFloat("tb_venda.Total"));
                venda.setSubtotal(rs.getFloat("tb_venda.Subtotal"));
                venda.setDesconto(rs.getFloat("tb_venda.Desconto"));
                venda.novoItem(rs.getInt("tb_itemvenda.ProdutoID"), rs.getInt("tb_itemvenda.Quantidade"));
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados - 003");}

        return venda;
    }

    public Venda getOnlyTbVendaPorID(int vendaID) throws Exception{
        Venda venda = new Venda();
        con = mySqlConnection();
        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("SELECT * FROM tb_venda WHERE VendaID = '" + vendaID + "';" );
            
            while (rs.next()){       
                
                venda.setVendaID(rs.getInt("tb_venda.VendaID"));
                venda.setClienteID(rs.getInt("tb_venda.ClienteID"));
                venda.setFuncionarioID(rs.getInt("tb_venda.FuncionarioID"));
                venda.setFormaPagamento(rs.getString("tb_venda.FormaPagamento"));
                venda.setTotal(rs.getFloat("tb_venda.Total"));
                venda.setSubtotal(rs.getFloat("tb_venda.Subtotal"));
                venda.setDesconto(rs.getFloat("tb_venda.Desconto"));
                venda.novoItem(rs.getInt("tb_itemvenda.ProdutoID"), rs.getInt("tb_itemvenda.Quantidade"));
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados - 004");}

        return venda;
    }

    public List<Venda> getINNERTbVenda() throws Exception{
        List<Venda> VendaList = new ArrayList<Venda>();
        Venda venda = null;

        boolean entrou;

        con = mySqlConnection();
        try{
            Statement stmt=con.createStatement();
            ResultSet rs = null;
            for (int i = 1; i < getVendaID(); i++) {
                Funcionario funcionario = new Funcionario();

                Pessoa pessoa = new Pessoa();
                ClienteFisico clienteFisico = new ClienteFisico();
                ClienteJuridico clienteJuridico = new ClienteJuridico();
                
                entrou = false;

                rs = stmt.executeQuery("SELECT * FROM (tb_venda " +
                                                            "INNER JOIN tb_cliente ON tb_venda.ClienteID = tb_cliente.PessoaID)" +
                                                            "INNER JOIN tb_funcionario ON tb_venda.FuncionarioID = tb_funcionario.PessoaID WHERE tb_venda.VendaID = '" + i + "';" );
            
                
                venda = new Venda();
                
                while (rs.next()){     
                    if(!rs.getString("tb_venda.FormaPagamento").equals(null) || !rs.getString("tb_venda.FormaPagamento").equals("")){ 
                        
                        venda.setVendaID(rs.getInt("tb_venda.VendaID"));

                        venda.setFormaPagamento(rs.getString("tb_venda.FormaPagamento"));
                        venda.setTotal(rs.getFloat("tb_venda.Total"));
                        venda.setSubtotal(rs.getFloat("tb_venda.Subtotal"));
                        venda.setDesconto(rs.getFloat("tb_venda.Desconto"));
                        
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
                            venda.setCliente(clienteFisico);

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
                            venda.setCliente(clienteJuridico);
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
                        venda.setFuncionario(funcionario);

                        entrou = true;
                    }
                }
                if(entrou){
                    VendaList.add(venda);
                }
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados - 005 " + e.getMessage());}

        return VendaList;
    }

    public void deletarTbVenda(int vendaID) throws Exception{
        con = mySqlConnection();
        try{
            Statement stmt=con.createStatement();       
            String query1 = ("DELETE FROM tb_venda where VendaID = '" + vendaID + "';");
            stmt.execute(query1);

            query1 = ("DELETE FROM tb_itemvenda WHERE VendaID = '" + vendaID + "';");
            stmt.execute(query1);

            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados - 006");}
    }
}
