import java.util.*;

public interface IRepositorioProduto {
    public void inserirProduto(String descricao, long codigoBarras, int quantidadeEstoque, float precoCusto, float precoVenda) throws Exception;
    public void atualizarProduto(int produtoID, String descricao, long codigoBarras, int quantidadeEstoque, float precoCusto, float precoVenda) throws Exception;
    public void deletarProduto(int produtoID);
    public Produto obterProdutoPorID(int produtoID);
    public List<Produto> obterProdutos();
}
