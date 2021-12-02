public class ItemServico {
    public int Quantidade = 0;
    public float SubtotalItem = 0;
    public int OrdemServicoID;
    Servico servico = new Servico();
    ConnectSQLServico Conexao = new ConnectSQLServico();

    public ItemServico(){}

    public ItemServico(int ordemServicoID, int servicoID, int quantidade){
        try{
            this.OrdemServicoID = ordemServicoID;
            servico = Conexao.getTbServico(servicoID);
            SubtotalItem += servico.Preco * quantidade;
            setQuantidade(quantidade);
        }catch(Exception e){}
    }

    public float getSubtotalItem(){
        return SubtotalItem;
    }
    public void setQuantidade(int quantidade) throws Exception{
        if(quantidade == 0){
            throw new Exception("Quantidade não pode ser negativa");
        }else{this.Quantidade = quantidade;}
    }
    
}
