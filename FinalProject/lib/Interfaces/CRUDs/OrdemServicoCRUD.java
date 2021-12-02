import java.util.*;

public class OrdemServicoCRUD implements IRepositorioOrdemServico{
    OrdemServico ordemServico = null;
    ConnectSQLOrdemServico Conexao = new ConnectSQLOrdemServico();

    public OrdemServicoCRUD() {}

    @Override
    public void inserirOrdemServico(OrdemServico ordemServico) throws Exception{
        try {
            ordemServico.finalizar();
            Conexao.inserirTbOrdemServico(ordemServico);
        } catch (Exception e) {throw new Exception(e.getMessage());}
    }
    @Override
    public void inserirItemServico(ItemServico item) throws Exception{
        try{
            Conexao.inserirTbItemServico(item);
        }catch (Exception e) {throw new Exception(e.getMessage());}
    }
    @Override
    public void deletarOrdemServico(int ordemServicoID){
        try{
            Conexao.deletarTbOrdemServico(ordemServicoID);
        }catch (Exception e) {System.out.println("Erro - " + e.getMessage());}
    }

    public int getOrdemServicoID(){
        int OrdemServicoID = 0;
        try{
            OrdemServicoID = Conexao.getOrdemServicoID();
            return OrdemServicoID;
            
        }catch (Exception e) {System.out.println("Erro - " + e.getMessage());}
        return OrdemServicoID;
    }

    @Override
    public OrdemServico obterOrdemServicoPorID(int ordemServicoID){ 
        try{
            ordemServico = Conexao.getTbOrdemServico(ordemServicoID);
        }catch (Exception e) {System.out.println("Erro - " + e.getMessage());}
        return ordemServico;
    }
    @Override
    public List<OrdemServico> obterOrdemServicos(){
        List<OrdemServico> ordemServicos = new ArrayList<OrdemServico>();
        try{
            ordemServicos = Conexao.getINNERTbOrdemServico();
        }catch (Exception e) {System.out.println("Erro - " + e.getMessage());}
        return ordemServicos;
    }
}