import java.util.*;

public class OrdemServico {
    public int OrdemServicoID = 0, Quantidade;
    public float SubTotal, Desconto = 0, Total;
    public String FormaPagamento; 
    Pessoa Cliente = new Pessoa();
    Funcionario funcionario = new Funcionario();
    ConnectSQLCliente ConexaoCliente = new ConnectSQLCliente();
    ConnectSQLFuncionario ConexaoFuncionario = new ConnectSQLFuncionario();
    ConnectSQLOrdemServico ConexaoOS = new ConnectSQLOrdemServico();
    ItemServico Item = new ItemServico();
    List<ItemServico> itens = new ArrayList<ItemServico>();

    public OrdemServico() {}

    public void novoItem (int servicoID, int quantidade){
        Item = new ItemServico(OrdemServicoID, servicoID, quantidade);
        itens.add(Item);
    }

    public void finalizar() throws Exception{
        OrdemServicoCRUD OS = new OrdemServicoCRUD();
        getTotal();
        for (ItemServico itemServico : getItens()) {
            OS.inserirItemServico(itemServico);
        }
    }

    public void deletarItem(int servicoID, int quantidadeItem){
        for (int i = 0; i < itens.size(); i++) {
            if(itens.get(i).servico.ServicoID == servicoID && itens.get(i).Quantidade == quantidadeItem){
                itens.remove(i);
            }
        }
    }

    public float getTotal() {
        Total = 0;
        for (ItemServico Item : itens) {
            Total += Item.getSubtotalItem();
        }
        SubTotal = Total;
        Total -= Desconto;
        return Total;
    }

    public List<ItemServico> getItens() {
        return itens;
    }

    public int getOrdemServicoID() throws Exception {
        this.OrdemServicoID = ConexaoOS.getOrdemServicoID();
        return OrdemServicoID;        
    }

    public void setDesconto(float desconto){
        this.Desconto = desconto;
    }
    public void setFormaPagamento(String formaPagamento) throws Exception{
        if(formaPagamento.equals("") || formaPagamento.equals(null)){
            throw new Exception("Forma de Pagamento não pode ser vazia");
        }else{this.FormaPagamento = Objects.requireNonNull(formaPagamento, "Forma de Pagamento não pode ser vazia");}
    }
    public void setOrdemServicoID(int ordemServicoID){
        this.OrdemServicoID = ordemServicoID;
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
    public void setClienteID(int clienteID) throws Exception{
        Cliente = ConexaoCliente.getTbClientePorID(clienteID);
    }
    public void setFuncionarioID(int funcionarioID) throws Exception{
        funcionario = (Funcionario) ConexaoFuncionario.getTbFuncionarioPorID(funcionarioID);
    }
    public void setCliente(Pessoa cliente){
        this.Cliente = cliente;
    }
    public void setFuncionario(Funcionario F){
        this.funcionario = F;
    }
}
