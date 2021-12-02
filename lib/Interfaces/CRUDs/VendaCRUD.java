import java.util.*;

public class VendaCRUD implements IRepositorioVenda{
    Venda venda = null;
    ConnectSQLVenda ConexaoVenda = new ConnectSQLVenda();
    ConnectSQLItemVenda ConexaoItem = new ConnectSQLItemVenda();

    public VendaCRUD() {}

    @Override
    public void inserirVenda(Venda venda) throws Exception{
        try { 
            venda.getTotal();
            ConexaoVenda.inserirTbVenda(venda);
            venda.setVendaID((ConexaoVenda.getVendaID()-1));

            venda.finalizar();
        } catch (Exception e) {throw new Exception(e.getMessage());}
    }
    @Override
    public void inserirItemVenda(ItemVenda item) throws Exception{
        try{
            ConexaoItem.inserirTbItemVenda(item);
        }catch (Exception e) {throw new Exception(e.getMessage());}
    }
    @Override
    public void deletarVenda(int vendaID){
        try{
            ConexaoVenda.deletarTbVenda(vendaID);
        }catch (Exception e) {System.out.println("Erro - " + e.getMessage());}
    }
    @Override
    public Venda obterVendaPorID(int vendaID){ 
        try{
            venda = ConexaoVenda.getTbVendaPorID(vendaID);
        }catch (Exception e) {System.out.println("Erro - " + e.getMessage());}
        return venda;
    }

    @Override
    public Venda obterSomenteVendaPorID(int vendaID){ 
        try{
            venda = ConexaoVenda.getOnlyTbVendaPorID(vendaID);
        }catch (Exception e) {System.out.println("Erro - " + e.getMessage());}
        return venda;
    }

    public int getVendaID(){
        int VendaID = 0;
        try{
            VendaID = ConexaoVenda.getVendaID();
            return VendaID;
            
        }catch (Exception e) {System.out.println("Erro - " + e.getMessage());}
        return VendaID;
    }

    @Override
    public List<Venda> obterVendas(){
        List<Venda> vendas = new ArrayList<Venda>();
        try{
            vendas = ConexaoVenda.getINNERTbVenda();
        }catch (Exception e) {System.out.println("Erro - " + e.getMessage());}
        return vendas;
    }
}
