import java.util.*;

public interface IRepositorioVenda {
    public void inserirVenda(Venda venda) throws Exception;
    public void inserirItemVenda(ItemVenda item) throws Exception;
    public void deletarVenda(int vendaID); 
    public Venda obterVendaPorID(int VendaID);
    public Venda obterSomenteVendaPorID(int vendaID);
    public List<Venda> obterVendas();
}
