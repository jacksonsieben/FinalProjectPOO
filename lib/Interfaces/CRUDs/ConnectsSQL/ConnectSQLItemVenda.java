import java.sql.*;

public class ConnectSQLItemVenda extends ConnectSQL{

    public ConnectSQLItemVenda() {}

    public void inserirTbItemVenda(ItemVenda itemVenda) throws Exception{
        con = mySqlConnection();
        try {
            Statement stmt = con.createStatement();
            String query = ("INSERT INTO tb_itemvenda (VendaID, ProdutoID, SubTotalItem, Quantidade)" +  
                            "VALUES ('" + itemVenda.VendaID + "', '" + itemVenda.produto.ProdutoID + 
                            "', '" + itemVenda.SubtotalItem + "', '" + itemVenda.Quantidade  + "')");
            stmt.execute(query);
            closeConnection(con);
        }catch(Exception e){ throw new Exception("Base de Dados " + e.getMessage()); }
    }
}
