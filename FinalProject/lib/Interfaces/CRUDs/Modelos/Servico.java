import java.util.Objects;

public class Servico {
    public String Descricao;
    public int ServicoID;
    public float Preco;

    public Servico() {}

    public Servico( String descricao, float preco) throws Exception {
        setDescricao(descricao);
        setPreco(preco);
    }

    public Servico(int servicoID, String descricao, float preco) throws Exception {  
        setServicoID(servicoID);
        setDescricao(descricao);
        setPreco(preco);
    }

    public int getServicoID() {
        return ServicoID;
    }
    public String getDescricao(){
        return Descricao;
    }
    public float getPreco(){
        return Preco;
    }
    
    public void setServicoID(int servicoID) {
        this.ServicoID = servicoID;
    }
    public void setDescricao(String descricao) throws Exception{
        if(descricao.equals("") || descricao.equals(null)){
            throw new Exception("Descrição não pode ser vazia");
        }else{this.Descricao = Objects.requireNonNull(descricao, "Descrição não pode ser vazia");}
    }
    public void setPreco(float preco) throws Exception{
        if (preco == 0){
            throw new Exception("Preco não pode ser 0");
        }else{this.Preco = preco;}
        
    }
    
}