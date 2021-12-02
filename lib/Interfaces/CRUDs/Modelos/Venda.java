import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Venda {
    ConnectSQLVenda ConexaoVenda = new ConnectSQLVenda();
    public int VendaID;
    public int QuantidadeItem;
    public float SubTotal, Desconto = 0, Total;
    public String FormaPagamento; 
    Pessoa Cliente = new Pessoa();
    Funcionario funcionario = new Funcionario();
    ItemVenda Item = new ItemVenda();
    List<ItemVenda> itens = new ArrayList<ItemVenda>();
    ConnectSQLCliente ConexaoCliente = new ConnectSQLCliente();
    ConnectSQLFuncionario ConexaoFuncionario = new ConnectSQLFuncionario();
    
    public Venda(){}   

    public void novoItem(int produtoID, int quantidadeItem){
        Item = new ItemVenda(VendaID, produtoID, quantidadeItem);
        itens.add(Item);
    }

    public void deletarItem(int produtoID, int quantidadeItem){
        for (int i = 0; i < itens.size(); i++) {
            if(itens.get(i).produto.ProdutoID == produtoID && itens.get(i).Quantidade == quantidadeItem){
                itens.remove(i);
            }
        }
    }

    public void finalizar() throws Exception{
        VendaCRUD venda = new VendaCRUD();
        Item.setVendaID(VendaID); 
        for (ItemVenda itemVenda : getItens()) {
            venda.inserirItemVenda(itemVenda);
        }
    }

    public float getTotal() {
        Total = 0;
        for (ItemVenda Item : itens) {
            Total += Item.getSubtotalItem();
        }
        this.SubTotal = Total;
        this.Total -= Desconto;
        return Total;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public int getVendaID() throws Exception {
        this.VendaID = ConexaoVenda.getVendaID();
        return VendaID;        
    }

    public void setDesconto(float desconto){
        this.Desconto = desconto;
    }
    public void setFormaPagamento(String formaPagamento) throws Exception{
        if(formaPagamento.equals("") || formaPagamento.equals(null)){
            throw new Exception("Forma de Pagamento não pode ser vazia");
        }else{this.FormaPagamento = Objects.requireNonNull(formaPagamento, "Forma de Pagamento não pode ser vazia");}
    }
    public void setVendaID(int vendaID){
        this.VendaID = vendaID;
    }
    public void setClienteID(int clienteID) throws Exception{
        Cliente = ConexaoCliente.getTbClientePorID(clienteID);
    }
    public void setFuncionarioID(int funcionarioID) throws Exception{
        funcionario = (Funcionario) ConexaoFuncionario.getTbFuncionarioPorID(funcionarioID);
    }
    public void setTotal(float total) throws Exception{
        if(total == 0){
            throw new Exception("Total Inválido");
        }else{this.Total = total;}
    }
    public void setSubtotal(float subtotal) throws Exception{
        if(subtotal == 0){
            throw new Exception("Subtotal Inválido");
        }else{ this.SubTotal = subtotal;}
    }

    public void setCliente(Pessoa cliente){
        this.Cliente = cliente;
    }

    public void setFuncionario(Funcionario F){
        this.funcionario = F;
    }
}
