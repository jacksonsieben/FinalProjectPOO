import java.util.*;

public interface IRepositorioVeiculo {
    public void inserirVeiculo(int pessoaID, String modelo, String marca, String placa, String cor, int ano) throws Exception;
    public void atualizarVeiculo(int veiculoID, int pessoaID, String modelo, String marca, String placa, String cor, int ano) throws Exception;
    public void deletarVeiculo(int veiculoID) throws Exception;
    public Veiculo obterVeiculoPorID(int veiculoID) throws Exception;
    public List<Veiculo> obterVeiculoPorCliente (int pessoaID) throws Exception;
    public List<Veiculo> obterVeiculos() throws Exception;
}
