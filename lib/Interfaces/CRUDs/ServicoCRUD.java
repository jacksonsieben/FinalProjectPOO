import java.util.*;

public class ServicoCRUD implements IRepositorioServico {
    Servico servico = new Servico();
    ConnectSQLServico Conexao = new ConnectSQLServico();
    
    public ServicoCRUD() {}

    @Override
    public void inserirServico(String descricao, float preco) throws Exception{
        try{
            servico = new Servico(descricao, preco);
            Conexao.inserirTbServico(servico);
        }catch(Exception e){throw new Exception(e.getMessage());}
    }

    @Override
    public void atualizarServico(int servicoID, String descricao, float preco) throws Exception{
        try{
            servico = new Servico(servicoID, descricao, preco);
            Conexao.atualizarTbServico(servico);
        }catch(Exception e){throw new Exception(e.getMessage());}        
    }

    @Override
    public void deletarServico(int servicoID){
        try{
            Conexao.deletarServico(servicoID);
        }catch(Exception e){System.out.println("Erro - " + e.getMessage());}
    }

    @Override
    public Servico obterServicoPorID(int servicoID){
        try{
            servico = Conexao.getTbServico(servicoID);
        }catch(Exception e){System.out.println("Erro - " + e.getMessage());}
        return servico;
    }
    
    @Override
    public List<Servico> obterServicos(){
        List<Servico> ServicoList = new ArrayList<Servico>();
        try{
            for (int i = 0; i < Conexao.getServicoID(); i++) {
                servico = Conexao.getTbServico(i);
                ServicoList.add(servico);
            }
        }catch(Exception e){System.out.println("Erro - " + e.getMessage());}
        return ServicoList;
    }

}
