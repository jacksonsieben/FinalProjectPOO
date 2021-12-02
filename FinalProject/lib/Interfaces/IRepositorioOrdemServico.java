import java.util.*;

public interface IRepositorioOrdemServico {
    public void inserirOrdemServico(OrdemServico ordemServico) throws Exception;
    public void inserirItemServico(ItemServico item) throws Exception;
    public void deletarOrdemServico(int ordemServicoID);
    public OrdemServico obterOrdemServicoPorID(int ordemServicoID);
    public List<OrdemServico> obterOrdemServicos();
}
