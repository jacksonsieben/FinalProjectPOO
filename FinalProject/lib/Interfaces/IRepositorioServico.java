import java.util.*;

public interface IRepositorioServico {
    public void inserirServico(String descricao, float preco) throws Exception;
    public void atualizarServico(int servicoID, String descricao, float preco) throws Exception;
    public void deletarServico(int servicoID);
    public Servico obterServicoPorID(int servicoID);
    public List<Servico> obterServicos();
}
