import java.sql.*;
import java.util.*;

public class ConnectSQLVeiculo extends ConnectSQL{
    
    public ConnectSQLVeiculo() {}

    public int getVeiculoID() throws Exception {
        int veiculoID = 0;
        Connection con = mySqlConnection();
        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("SELECT max(VeiculoID) FROM 'tb_veiculo';");
            while(rs.next()) {
                veiculoID = rs.getInt("max(VeiculoID)");                
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados " + e.getMessage());}
        return veiculoID+1;
    }

    public void inserirTbVeiculo(Veiculo veiculo) throws Exception{
        con = mySqlConnection();
        try {
            Statement stmt = con.createStatement();
            String query = ("INSERT INTO tb_veiculo (PessoaID, Marca, Modelo, Placa, Cor, Ano)" +  
                            "VALUES ('" + veiculo.PessoaID + "', '" + veiculo.Marca + "', '" + veiculo.Modelo + "', '" + veiculo.Placa  
                                        + "', '" + veiculo.Cor + "', '" + veiculo.Ano + "')");
            stmt.execute(query);
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados " + e.getMessage());}
    }

    public void atualizarTbVeiculo(Veiculo veiculo) throws Exception{
        con = mySqlConnection();
        try {
            Statement stmt = con.createStatement();
            String query = ("UPDATE tb_veiculo SET PessoaID = '" + veiculo.PessoaID + "', Marca = '" + veiculo.Marca + "', Modelo = '" + veiculo.Modelo 
                            + "', Placa = '" + veiculo.Placa + "', Cor = '" + veiculo.Cor + "', Ano = '" + veiculo.Ano
                            +"' WHERE VeiculoID = '" + veiculo.VeiculoID + "';");
            stmt.execute(query);
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados " + e.getMessage());}
    }

    public List<Veiculo> getTbVeiculoPorCliente(int pessoaID) throws Exception{
        List<Veiculo> VeiculoList = new ArrayList<Veiculo>();
        Veiculo veiculo;
        con = mySqlConnection();

        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from tb_veiculo where PessoaID = '" + pessoaID + "';");
            while (rs.next()){
                if(!rs.getString("Modelo").equals(null)){
                    veiculo = new Veiculo(rs.getInt("VeiculoID"), pessoaID, rs.getString("Modelo"), rs.getString("Marca"), rs.getString("Placa"), rs.getString("Cor"), rs.getInt("Ano"));
                    VeiculoList.add(veiculo);
                }
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados " + e.getMessage());}

        return VeiculoList;
    }

    public Veiculo getTbVeiculo(int i) throws Exception{
        Veiculo veiculo = new Veiculo();
        con = mySqlConnection();

        try{
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from tb_veiculo where VeiculoID = '" + i + "';");
            while (rs.next()){
                veiculo.setVeiculoID(rs.getInt("VeiculoID")); 
                veiculo.setPessoaID(rs.getInt("PessoaID"));
                veiculo.setMarca(rs.getString("Marca"));
                veiculo.setModelo(rs.getString("Modelo"));
                veiculo.setPlaca(rs.getString("Placa"));
                veiculo.setCor(rs.getString("Cor"));
                veiculo.setAno(rs.getInt("Ano"));
            }
            rs.close();
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados " + e.getMessage());}

        return veiculo;
    }

    public void deletarVeiculo (int veiculoID) throws Exception{
        con = mySqlConnection();
        try{    
            Statement stmt=con.createStatement();       
            String query1 = ("DELETE FROM tb_veiculo where VeiculoID = '" + veiculoID + "';");
            stmt.execute(query1);
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados " + e.getMessage());}
    }
}

