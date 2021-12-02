public class ItemVenda {
    public int Quantidade = 0, ItemVendaID;
    public float SubtotalItem = 0;
    Produto produto = new Produto();
    public int VendaID;
    ConnectSQLProduto Conexao = new ConnectSQLProduto();
    
    public ItemVenda (){}

    public ItemVenda(int produtoID, int quantidade){
        try{
            produto = Conexao.getTbProdutoPorID(produtoID);
            SubtotalItem += produto.PrecoVenda * quantidade;
            setQuantidade(quantidade);
        } catch (Exception e) {System.out.println("Erro - " + e.getMessage());} 
    }

    public ItemVenda(int vendaID, int produtoID, int quantidade){
        try{
            this.VendaID = vendaID;
            produto = Conexao.getTbProdutoPorID(produtoID);
            produto.QuantidadeEstoque -= quantidade;
            if(produto.QuantidadeEstoque == 0){
                throw new Exception("Este item está negativo");
            }else{Conexao.atualizarTbProduto(produto);}
            SubtotalItem += produto.PrecoVenda * quantidade;
            setQuantidade(quantidade);
        } catch (Exception e) {System.out.println("Erro - " + e.getMessage());}       
    }
    
    public int getQuantidade() {
        return Quantidade;
    }
    public float getSubtotalItem() {
        return SubtotalItem;
    }


    public void setVendaID(int vendaID) {
        this.VendaID = vendaID;
    }
    public void setQuantidade(int quantidade) throws Exception {
        if (quantidade == 0){
            throw new Exception("Quantidade não pode ser negativa");
        }else{this.Quantidade = quantidade;}        
    }
    public void setSubtotalItem(float subtotalItem) {
        this.SubtotalItem = subtotalItem;
    }    
}
