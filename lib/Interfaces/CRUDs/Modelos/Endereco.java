public class Endereco { 
    public String Rua, Bairro, Cidade, Estado, Cep;
    int Numero;
    public int EnderecoID;

    public Endereco() {}
    
    public Endereco(int enderecoID, String rua, String bairro, String cidade, String estado, String cep, int numero) {
        EnderecoID = enderecoID;
        Rua = rua;
        Bairro = bairro;
        Cidade = cidade;
        Estado = estado;
        Cep = cep;
        Numero = numero;
    }
    
    //region Get Set Endereço

    public int getEnderecoID() {
        return EnderecoID;
    }
    public String getRua() {
        return Rua;
    }
    public void setRua(String rua) {
        this.Rua = rua;
    }

    public String getBairro() {
        return Bairro;
    }
    public void setBairro(String bairro) {
        this.Bairro = bairro;
    }

    public String getCidade() {
        return Cidade;
    }
    public void setCidade(String cidade) {
        this.Cidade = cidade;
    }

    public String getEstado() {
        return Estado;
    }
    public void setEstado(String estado) {
        this.Estado = estado;
    }

    public void setEnderecoID(int enderecoID) {
        this.EnderecoID = enderecoID;
    }
    public int getNumero() {
        return Numero;
    }
    public void setNumero(int numero) {
        this.Numero = numero;
    }

    public String getCep() {
        return Cep;
    }
    public void setCep(String cep) {
        this.Cep = cep;
    }
    //endregion
 
}
