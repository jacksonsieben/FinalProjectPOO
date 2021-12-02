import java.util.Objects;

public class Veiculo {
    public String Modelo, Marca, Placa, Cor;
    public int Ano, VeiculoID = 0;
    public int PessoaID;

    public Veiculo() {}

    public Veiculo(int pessoaID, String modelo, String marca, String placa, String cor, int ano) throws Exception{
        PessoaID = pessoaID;
        setModelo(modelo);
        setMarca(marca);
        setPlaca(placa);
        setCor(cor);
        setAno(ano);
    }

    public Veiculo(int veiculoID, int pessoaID, String modelo, String marca, String placa, String cor, int ano) throws Exception{
        VeiculoID = veiculoID;
        PessoaID = pessoaID;
        setModelo(modelo);
        setMarca(marca);
        setPlaca(placa);
        setCor(cor);
        setAno(ano);
    }

    public int getVeiculoID() {
        return VeiculoID;
    }
    public int getPessoaID() {
        return PessoaID;
    }
    public String getModelo(){
        return Modelo;
    }
    public String getMarca(){
        return Marca;
    }
    public String getPlaca(){
        return Placa;
    }
    public String getCor(){
        return Cor;
    }
    public int getAno(){
        return Ano;
    }

    public void setPessoaID (int pessoaID){
        this.PessoaID = pessoaID;
    }
    public void setVeiculoID(int veiculoID) {
        this.VeiculoID = veiculoID;
    }
    public void setModelo(String modelo) throws Exception{
        if(modelo.equals("") || modelo.equals(null)){
            throw new Exception("Modelo não pode ser vazio");
        }else{this.Modelo = Objects.requireNonNull(modelo, "Modelo não pode ser vazio");}
    }    
    public void setMarca(String marca) throws Exception{
        if(marca.equals("") || marca.equals(null)){
            throw new Exception("Marca não pode ser vazia");
        }else{this.Marca = Objects.requireNonNull(marca, "Marca não pode ser vazia");}
    }    
    public void setPlaca(String placa) throws Exception{
        if(placa.equals("") || placa.equals(null)){
            throw new Exception("Placa não pode ser vazia");
        }else{this.Placa = Objects.requireNonNull(placa, "Placa não pode ser vazia");}
    }    
    public void setCor(String cor) throws Exception{
        if(cor.equals("") || cor.equals(null)){
            throw new Exception("Cor não pode ser vazia");
        }else{this.Cor = cor;}
    }    
    public void setAno(int ano)throws Exception{
        if(ano == 0){
            throw new Exception("Ano não pode ser vazio");
        }else{this.Ano = ano;}
    }
   
}
