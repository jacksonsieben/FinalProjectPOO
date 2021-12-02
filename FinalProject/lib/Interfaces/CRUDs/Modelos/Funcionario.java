import java.util.Objects;

public class Funcionario extends Pessoa {
    
    float Salario;
    public String Cargo, Sobrenome, CPF;

    public Funcionario(){}

    public Funcionario(String nome, String sobrenome, String cpf, String email, String telefone, 
            String rua, int numero, String bairro, String cidade, String estado, String cep,
            float salario, String cargo) throws Exception {
        setNome(nome);
        setSobrenome(sobrenome);
        setCPF(cpf);   
        Email = email;
        Telefone = telefone;
        Endereco.Rua=rua;
        Endereco.Numero = numero;
        Endereco.Bairro = bairro;
        Endereco.Cidade = cidade;
        Endereco.Estado = estado;
        Endereco.Cep = cep;
        setSalario(salario);
        setCargo(cargo);
    }

    public Funcionario(int pessoaID, String nome, String sobrenome, String cpf, String email, String telefone, 
                        String rua, int numero, String bairro, String cidade, String estado, String cep,
                        float salario, String cargo) throws Exception {
        PessoaID = pessoaID;
        setNome(nome);
        setSobrenome(sobrenome);
        setCPF(cpf);   
        Email = email;
        Telefone = telefone;
        Endereco.Rua=rua;
        Endereco.Numero = numero;
        Endereco.Bairro = bairro;
        Endereco.Cidade = cidade;
        Endereco.Estado = estado;
        Endereco.Cep = cep;
        setSalario(salario);
        setCargo(cargo);
    }

    public String getCPF() {
        return CPF;
    }
    public String getSobrenome() {
        return Sobrenome;
    }
    public float getSalario() {
        return Salario;
    }
    public String getCargo(){
        return Cargo;
    }


    public void setCPF(String cpf) throws Exception{
        this.CPF = Objects.requireNonNull(cpf, "CPF não pode ser vazio");
    }
    public void setSobrenome(String sobrenome) throws Exception {
        if(sobrenome.equals("") || sobrenome.equals(null)){
            throw new Exception("Sobrenome não pode ser vazio");
        }else{this.Sobrenome = Objects.requireNonNull(sobrenome, "Sobrenome não pode ser vazio");}
    }
    public void setSalario(float salario) throws Exception{
        if(salario == 0){
            throw new Exception("Salario não pode ser vazio");
        }else{this.Salario = salario;}
    }
    public void setCargo(String cargo) throws Exception{
        if(cargo.equals("") || cargo.equals(null)){
            throw new Exception("Cargo não pode ser vazio");
        }else{this.Cargo = Objects.requireNonNull(cargo, "Cargo não pode ser vazio");}
        
    }
}
