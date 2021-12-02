import java.util.*;

public class VeiculoCRUD implements IRepositorioVeiculo{
    Veiculo veiculo = new Veiculo();
    Pessoa Cliente = new Pessoa();

    ConnectSQLVeiculo Conexao = new ConnectSQLVeiculo();
    ConnectSQLCliente ConexaoCliente = new ConnectSQLCliente();
    
    public VeiculoCRUD() {}

    @Override
    public void inserirVeiculo(int pessoaID, String modelo, String marca, String placa, String cor, int ano) throws Exception{
        
        veiculo = new Veiculo(pessoaID, modelo, marca, placa, cor, ano);
        Conexao.inserirTbVeiculo(veiculo);
    }
    @Override
    public void atualizarVeiculo(int veiculoID, int pessoaID, String modelo, String marca, String placa, String cor, int ano) throws Exception{
       
        veiculo = new Veiculo(veiculoID, pessoaID, modelo, marca, placa, cor, ano);
        Conexao.atualizarTbVeiculo(veiculo);
              
    }
    @Override
    public void deletarVeiculo(int veiculoID) throws Exception{
        Conexao.deletarVeiculo(veiculoID);
    }
    
    @Override
    public List<Veiculo> obterVeiculoPorCliente(int pessoaID) throws Exception{
        List<Veiculo> VeiculoList = new ArrayList<Veiculo>();
        VeiculoList = Conexao.getTbVeiculoPorCliente(pessoaID);

        return VeiculoList;
    }
    @Override
    public Veiculo obterVeiculoPorID(int veiculoID) throws Exception{
        veiculo = Conexao.getTbVeiculo(veiculoID);
        return veiculo;
    }
    @Override
    public List<Veiculo> obterVeiculos() throws Exception{
        List<Veiculo> VeiculoList = new ArrayList<Veiculo>();
        for (int i = 0; i < Conexao.getVeiculoID(); i++) {
            veiculo = Conexao.getTbVeiculo(i);
            VeiculoList.add(veiculo);
        }
        return VeiculoList;
    }

}

