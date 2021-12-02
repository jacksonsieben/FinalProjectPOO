import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NovoFuncionarioPag implements ActionListener {
    JFrame novoFuncionario = new JFrame();
    Pessoa pessoa;
    JTextField textoSobrenome, textoNome, textoCpf, textoTelefone, textoRua, textoNum, textoBairro, textoCidade, textoEstado, textoCEP, textoCargo, textoSalario;
    JPanel mainPanel;
    JLabel titulo, endereco;
    JButton btnLimpar, btnCadastrar, btnAtualizar;
    FuncionarioPag pagFuncionario;

    int pessoaID;

    public NovoFuncionarioPag(){}

    public void janelaCadastroFuncionario(FuncionarioPag funcionario){
        pagFuncionario = funcionario;
        criarJanela();
        criarCampoTexto();
        adicionarFocusListener();
        criarBotoesCadastrar();
    }

    public void janelaAtualizarFuncionario(int pessoaID, FuncionarioPag funcionario){
        pagFuncionario = funcionario;
        criarJanela();
        novoFuncionario.setTitle("Atualizar Funcionário");
        criarCampoTexto();
        completarCampoTexto(pessoaID);
        adicionarFocusListener();
        criarBotoesAtualizar();
    }

    private void criarJanela() {
        novoFuncionario.setSize(1280,720);
        novoFuncionario.setVisible(true);
        novoFuncionario.setLocationRelativeTo(null);
        novoFuncionario.setResizable(false);
        novoFuncionario.setTitle("Cadastro Funcionário");
        novoFuncionario.setIconImage(new ImageIcon(getClass().getResource("/imgs/key-fill.png")).getImage());
    }

    private void criarCampoTexto(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        titulo = new JLabel("Cadastro de Funcionário");
        titulo.setFont(new Font("Verdana", Font.PLAIN, 28));
        titulo.setBounds(500, 50, 400, 50);

        endereco = new JLabel("Endereço");
        endereco.setFont(new Font("Verdana", Font.PLAIN, 20));
        endereco.setBounds(590,315, 300,50);

        textoNome = new JTextField("Nome");
        textoSobrenome = new JTextField("Sobrenome");
        textoCpf = new JTextField("CPF");
        
        textoCargo = new JTextField("Cargo");
        textoSalario = new JTextField("Salário");
        textoTelefone = new JTextField("Telefone");

        textoRua = new JTextField("Rua");
        textoNum = new JTextField("Número");
        textoBairro = new JTextField("Bairro");

        textoCidade = new JTextField("Cidade");
        textoEstado = new JTextField("Estado");
        textoCEP = new JTextField("CEP");

        textoNome.setBounds(80,150, 300,30);
        textoSobrenome.setBounds(490,150, 300,30);
        textoCpf.setBounds(900,150, 300,30); 

        textoCargo.setBounds(80,250, 300,30);
        textoSalario.setBounds(490,250, 300,30);
        textoTelefone.setBounds(900,250, 300,30);

        textoRua.setBounds(80,400, 300,30);
        textoNum.setBounds(490,400, 300,30);
        textoBairro.setBounds(900,400, 300,30);

        textoCidade.setBounds(80,500, 300,30);
        textoEstado.setBounds(490,500, 300,30);
        textoCEP.setBounds(900,500, 300,30);

        Color color = new Color(90,126,255);
        mainPanel.setBackground(color);

        campoTextoVisibilidade(true);

        mainPanel.add(titulo);
        mainPanel.add(endereco);
        
        mainPanel.add(textoSobrenome);
        mainPanel.add(textoNome);
        mainPanel.add(textoCargo);
        mainPanel.add(textoCpf);
        mainPanel.add(textoSalario);
        mainPanel.add(textoTelefone);

        mainPanel.add(textoRua);
        mainPanel.add(textoNum);
        mainPanel.add(textoBairro);
        mainPanel.add(textoCidade);
        mainPanel.add(textoEstado);
        mainPanel.add(textoCEP);

        novoFuncionario.setContentPane(mainPanel);
    }
    
    private void criarBotoesCadastrar(){
        btnLimpar = new JButton("Limpar");
        btnCadastrar = new JButton("Cadastrar");

        btnLimpar.setBounds(490,600, 100,25);
        btnCadastrar.setBounds(690,600, 100,25);

        btnCadastrar.addActionListener(this);
        btnLimpar.addActionListener(this);

        mainPanel.add(btnLimpar);
        mainPanel.add(btnCadastrar);
    }

    private void criarBotoesAtualizar() {
        btnAtualizar = new JButton("Atualizar");

        btnAtualizar.setBounds(590,600, 100,25);

        btnAtualizar.addActionListener(this);

        mainPanel.add(btnAtualizar);
    }

    private void campoTextoVisibilidade(boolean visible) {
        textoCargo.setVisible(visible);
        textoNome.setVisible(visible);
        textoSobrenome.setVisible(visible);

        textoCpf.setVisible(visible);
        textoSalario.setVisible(visible);
        textoTelefone.setVisible(visible);

        textoRua.setVisible(visible);
        textoNum.setVisible(visible);
        textoBairro.setVisible(visible);

        textoCidade.setVisible(visible);
        textoEstado.setVisible(visible);
        textoCEP.setVisible(visible);
    }

    private void cadastrarFuncionario() throws Exception{
        IRepositorioFuncionario Funcionarios = new FuncionarioCRUD();

        verificarCampos();
        apagarPadrao();
        substituirVirgula();

        Funcionarios.inserirFuncionario(textoNome.getText(), textoSobrenome.getText(), textoCpf.getText(), null, textoTelefone.getText(), textoRua.getText(), Integer.parseInt(textoNum.getText().trim()), textoBairro.getText(), textoCidade.getText(), textoEstado.getText(), textoCEP.getText(), Float.parseFloat(textoSalario.getText()), textoCargo.getText());

        pagFuncionario.dispose();
        pagFuncionario.inicializar();
        novoFuncionario.dispose();
    }

    private void atualizarFuncionario() throws Exception{
        IRepositorioFuncionario funcionario = new FuncionarioCRUD();

        verificarCampos();
        substituirVirgula();

        funcionario.atualizarFuncionario(pessoa.PessoaID, textoNome.getText(), textoSobrenome.getText(), textoCpf.getText(), null, textoTelefone.getText(), textoRua.getText(), Integer.parseInt(textoNum.getText().trim()), textoBairro.getText(), textoCidade.getText(), textoEstado.getText(), textoCEP.getText(), Float.parseFloat(textoSalario.getText()), textoCargo.getText());
        
        pagFuncionario.dispose();
        pagFuncionario.inicializar();
        novoFuncionario.dispose();
    }

    private void apagarPadrao(){
        if(textoNome.getText().equals("Nome")){
            textoNome.setText(null);
        }if(textoSobrenome.getText().equals("Sobrenome")){
            textoSobrenome.setText(null);
        }if(textoCpf.getText().equals("CPF")){
            textoCpf.setText(null);
        }if(textoSalario.getText().equals("Salário")){
            textoSalario.setText("0");
        }if(textoTelefone.getText().equals("Telefone")){
            textoTelefone.setText(null);
        }if(textoRua.getText().equals("Rua")){
            textoRua.setText(null);
        }if(textoNum.getText().equals("Número")){
            textoNum.setText("0");
        }if(textoBairro.getText().equals("Bairro")){
            textoBairro.setText(null);
        }if(textoCidade.getText().equals("Cidade")){
            textoCidade.setText(null);
        }if(textoEstado.getText().equals("Estado")){
            textoEstado.setText(null);
        }if(textoCEP.getText().equals("CEP")){
            textoCEP.setText(null);
        }
    }

    private void verificarCampos() throws Exception{
        if(textoNome.getText().equals("Nome")){
            throw new Exception("Nome não pode ser vazio");
        }
        if(textoSobrenome.getText().equals("Sobrenome")){
            throw new Exception("Sobrenome não pode ser vazio");
        }
        if(textoCpf.getText().contains(".")){
            if(textoCpf.getText().length() != 14){
                throw new Exception("CPF inválido");
            }
        }else{
            if(textoCpf.getText().length() != 11){
                throw new Exception("CPF inválido");
            }
        }
        if(textoSalario.getText().equals("Salário")){
            throw new Exception("Salário não pode ser vazio");
        }
        try{
            Float.parseFloat(textoSalario.getText());
        } catch(Exception e){ throw new Exception("Salário inválido");}
        if(textoCargo.getText().equals("Cargo")){
            throw new Exception("Cargo não pode ser vazio");
        }
        if(!textoNum.getText().equals("Número")){
            try{
                Integer.parseInt(textoNum.getText().trim());
            }catch(Exception e){ throw new Exception("Número inválido");}
        }
    }

    private void completarCampoTexto(int pessoaID){
        IRepositorioFuncionario funcionario = new FuncionarioCRUD();
        pessoa = funcionario.obterFuncionarioPorID(pessoaID);

        textoNome.setText(pessoa.Nome);
        textoSobrenome.setText(((Funcionario)pessoa).Sobrenome);       
        textoCpf.setText(((Funcionario)pessoa).CPF);
        textoCargo.setText(((Funcionario)pessoa).Cargo);
        textoSalario.setText(String.format("%.2f", ((Funcionario)pessoa).Salario));
        textoTelefone.setText(pessoa.Telefone);       
        textoRua.setText(pessoa.Endereco.Rua);
        textoNum.setText(String.format("%d", pessoa.Endereco.Numero));
        textoBairro.setText(pessoa.Endereco.Bairro);
        textoCidade.setText(pessoa.Endereco.Cidade); 
        textoEstado.setText(pessoa.Endereco.Estado); 
        textoCEP.setText(pessoa.Endereco.Cep);
    }

    private void substituirVirgula(){
        textoSalario.setText(textoSalario.getText().replace(",","."));
    }

    private void limparTexto(JTextField texto){
        texto.setText("");
    }

    private void criarFocusListener(JTextField texto){
        String txt = texto.getText();
        texto.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e){
                limparTexto(texto);
            }
            public void focusLost(FocusEvent e){
                if(texto.getText().equals("")){
                    texto.setText(txt);
                }                
            }
        });
    }

    private void adicionarFocusListener(){
        criarFocusListener(textoCargo);
        criarFocusListener(textoNome);
        criarFocusListener(textoSobrenome);
        criarFocusListener(textoCpf);
        criarFocusListener(textoSalario);
        criarFocusListener(textoTelefone);

        criarFocusListener(textoRua);
        criarFocusListener(textoNum);
        criarFocusListener(textoBairro);
        criarFocusListener(textoCidade);
        criarFocusListener(textoEstado);
        criarFocusListener(textoCEP);
    }

    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource() == btnCadastrar){
                cadastrarFuncionario();
            }else if(e.getSource() == btnLimpar){
                campoTextoVisibilidade(false);
                criarCampoTexto();
                criarBotoesCadastrar();
            }else if (e.getSource() == btnAtualizar){
                atualizarFuncionario();
            }
        } catch (Exception ex) {
            ExcecaoPag excecao = new ExcecaoPag();
            excecao.inicializar(ex.getMessage());
        }
    }
}
