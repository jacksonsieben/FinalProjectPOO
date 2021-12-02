import java.sql.*;

public class ConnectSQLServico extends ConnectSQL{
    
    public ConnectSQLServico() {}

    public int getServicoID() throws Exception {
        int servicoID = 0;
        Connection con = mySqlConnection();
        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("SELECT max(ServicoID) FROM tb_servico;");
            while(rs.next()) {
                servicoID = rs.getInt("max(ServicoID)");                
            }
            rs.close();
            closeConnection(con);
            return servicoID+1;
        }catch(Exception e){ throw new Exception("Base de Dados");}
        
    }

    public void inserirTbServico(Servico servico) throws Exception{
        con = mySqlConnection();
        try {
            Statement stmt = con.createStatement();
            String query = ("INSERT INTO tb_servico (Descricao, Preco)" +  
                            "VALUES ('" + servico.Descricao + "', '" + servico.Preco + "')");
            stmt.execute(query);
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados");}
    }

    public void atualizarTbServico(Servico servico) throws Exception{
        con = mySqlConnection();
        try {
            Statement stmt = con.createStatement();
            String query = ("UPDATE tb_servico SET Descricao = '" + servico.Descricao + "', Preco = '" + servico.Preco
                            +"' WHERE ServicoID = '" + servico.getServicoID() + "';");
            stmt.execute(query);
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados ");}
    }

    public Servico getTbServico(int i) throws Exception{
        Servico servico = new Servico();
        con = mySqlConnection();

        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from tb_servico where ServicoID = '" + i + "';");
            while (rs.next()){
                servico.setServicoID(rs.getInt("ServicoID")); 
                servico.setDescricao(rs.getString("Descricao"));
                servico.setPreco(rs.getFloat("Preco"));
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados ");}

        return servico;
    }

    public void deletarServico (int servicoID) throws Exception{
        con = mySqlConnection();
        try{    
            Statement stmt=con.createStatement();       
            String query1 = ("DELETE FROM tb_servico where ServicoID = '" + servicoID + "';");
            stmt.execute(query1);
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados ");}
    }
}
