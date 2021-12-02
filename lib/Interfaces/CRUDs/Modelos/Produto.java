import java.util.Objects;

public class Produto {
    public String Descricao;
    public long CodigoBarras;
    public int ProdutoID, QuantidadeEstoque;
    public float PrecoCusto, PrecoVenda;


    public Produto() {}

    public Produto(String descricao, long codigoBarras, int quantidadeEstoque, float precoCusto, float precoVenda) throws Exception {
        setDescricao(descricao);
        setCodigoBarras(codigoBarras);
        setQuantidadeEstoque(quantidadeEstoque);
        setPrecoCusto(precoCusto);
        setPrecoVenda(precoVenda);
    }    

    public Produto(int produtoID, String descricao, long codigoBarras, int quantidadeEstoque, float precoCusto, float precoVenda) throws Exception {
        ProdutoID = produtoID;
        setDescricao(descricao);
        setCodigoBarras(codigoBarras);      
        setQuantidadeEstoque(quantidadeEstoque);
        setPrecoCusto(precoCusto);
        setPrecoVenda(precoVenda);
    }

    public int getProdutoID() {
        return ProdutoID;
    }
    public String getDescricao() {
        return Descricao;
    }
    public long getCodigoBarras() {
        return CodigoBarras;
    }
    public int getQuantidadeEstoque() {
        return QuantidadeEstoque;
    }
    public float getPrecoCusto() {
        return PrecoCusto;
    }
    public float getPrecoVenda() {
        return PrecoVenda;
    }

    public void setProdutoID(int produtoID) {
        this.ProdutoID = produtoID;
    }
    public void setDescricao(String descricao) throws Exception {
        if(descricao.equals("") || descricao.equals(null)){
            throw new Exception("Descrição não pode ser vazia");
        }else{this.Descricao = Objects.requireNonNull(descricao, "Descrição não pode ser vazia");}
    }
    public void setCodigoBarras(long codigoBarras) throws Exception {
        if(codigoBarras == 0){
            throw new Exception("Codigo de Barras não pode ser nulo");
        }else{this.CodigoBarras = codigoBarras;}
        
    }
    public void setQuantidadeEstoque(int quantidadeEstoque) throws Exception {
        if(quantidadeEstoque == 0) {
            throw new Exception("Quantidade em Estoque não pode ser 0");
        }else{this.QuantidadeEstoque = quantidadeEstoque;}        
    }
    public void setPrecoCusto(float precoCusto) throws Exception {
        if(precoCusto == 0){
            throw new Exception("Preco de Custo não pode ser 0");
        }else{this.PrecoCusto = precoCusto;}        
    }   
    public void setPrecoVenda(float precoVenda) throws Exception {
        if(precoVenda == 0){
            throw new Exception("Preco de Venda não pode ser 0");
        }else{this.PrecoVenda = precoVenda;}
    }
}
