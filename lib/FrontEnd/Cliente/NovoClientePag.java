import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class NovoClientePag implements ActionListener{
    JFrame novoCliente = new JFrame();
    Pessoa pessoa;
    JTextField textoSobrenomeIE, textoNome, textoCpfCnpj, textoEmail, textoTelefone, textoRua, textoNum, textoBairro, textoCidade, textoEstado, textoCEP;
    JTextField textoModelo, textoMarca, textoAno, textoPlaca, textoCor;
    JComboBox<Object> textoTipo;
    JPanel mainPanel;
    JLabel titulo, endereco;
    JButton btnLimpar, btnCadastrar, btnAtualizar, btnCadastrarVeiculo;
    ClientePag pagCliente;
    boolean trocaTipo = true;
    int pessoaID;

    public NovoClientePag(){}

    public void janelaCadastroCliente(ClientePag cliente){
        pagCliente = cliente;
        criarJanela();
        criarCampoTexto();
        adicionarFocusListenerCliente();
        criarBotoesCadastrar();
    }

    public void janelaAtualizarCliente(int pessoaID, ClientePag cliente){
        pagCliente = cliente;
        trocaTipo = false;
        criarJanela();
        novoCliente.setTitle("Atualizar Cliente");
        criarCampoTexto();
        completarCampoTexto(pessoaID);
        adicionarFocusListenerCliente();
        criarBotoesAtualizar();
    }

    public void janelaVeiculo(int pessoaId, String nome,ClientePag cliente){
        pagCliente = cliente;
        pessoaID = pessoaId;
        criarJanela();
        novoCliente.setTitle("Cadastrar Veículo");
        criarCampoTextoVeiculo(nome);
        adicionarFocusListenerVeiculo();
        criarBotoesVeiculo();
    }

    private void criarJanela(){
        novoCliente.setSize(1280,720);
        novoCliente.setVisible(true);

        novoCliente.setLocationRelativeTo(null);
        novoCliente.setResizable(false);
        novoCliente.setTitle("Cadastro Cliente");
        novoCliente.setIconImage(new ImageIcon(getClass().getResource("/imgs/key-fill.png")).getImage());
    }

    private void criarCampoTexto(){
        
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        titulo = new JLabel("Cadastro de Cliente");
        titulo.setFont(new Font("Verdana", Font.PLAIN, 28));
        titulo.setBounds(500, 50, 400, 50);

        endereco = new JLabel("Endereço");
        endereco.setFont(new Font("Verdana", Font.PLAIN, 20));
        endereco.setBounds(590,315, 300,50);

        textoSobrenomeIE = new JTextField("Sobrenome/IE");
        textoNome = new JTextField("Nome");

        textoCpfCnpj = new JTextField("CPF/CNPJ");
        textoEmail = new JTextField("Email");
        textoTelefone = new JTextField("Telefone");

        textoRua = new JTextField("Rua");
        textoNum = new JTextField("Número");
        textoBairro = new JTextField("Bairro");

        textoCidade = new JTextField("Cidade");
        textoEstado = new JTextField("Estado");
        textoCEP = new JTextField("CEP");
        
        Object[] pessoaTipo = { "Física", "Jurídica", "Selecionar Tipo"};
        
        textoTipo = new JComboBox<>(pessoaTipo);
        textoTipo.setSelectedIndex(2);

        textoTipo.setBounds(80,150, 300,30);
        textoNome.setBounds(490,150, 300,30);
        textoSobrenomeIE.setBounds(900,150, 300,30);

        textoCpfCnpj.setBounds(80,250, 300,30);
        textoEmail.setBounds(490,250, 300,30);
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

        textoTipo.addActionListener(this);

        mainPanel.add(titulo);
        mainPanel.add(endereco);
        
        mainPanel.add(textoSobrenomeIE);
        mainPanel.add(textoNome);
        mainPanel.add(textoTipo);
        mainPanel.add(textoCpfCnpj);
        mainPanel.add(textoEmail);
        mainPanel.add(textoTelefone);

        mainPanel.add(textoRua);
        mainPanel.add(textoNum);
        mainPanel.add(textoBairro);
        mainPanel.add(textoCidade);
        mainPanel.add(textoEstado);
        mainPanel.add(textoCEP);

        novoCliente.setContentPane(mainPanel);
    }

    private void criarCampoTextoVeiculo(String nome){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        textoModelo = new JTextField("Modelo");
        textoMarca = new JTextField("Marca");
        textoAno = new JTextField("Ano");
        textoCor = new JTextField("Cor");
        textoPlaca = new JTextField("Placa");
        textoNome = new JTextField(nome);
        
        textoNome.setBounds(80,150, 300,30);
        textoModelo.setBounds(490,150, 300,30);
        textoMarca.setBounds(900,150, 300,30);
        
        textoAno.setBounds(80,250, 300,30);
        textoCor.setBounds(490,250, 300,30);
        textoPlaca.setBounds(900,250, 300,30);

        textoNome.setEnabled(false);
        
        Color color = new Color(90,126,255);
        mainPanel.setBackground(color);

        adicionarFocusListenerVeiculo();

        mainPanel.add(textoNome);
        mainPanel.add(textoModelo);
        mainPanel.add(textoMarca);

        mainPanel.add(textoAno);
        mainPanel.add(textoCor);
        mainPanel.add(textoPlaca);

        novoCliente.setContentPane(mainPanel);
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

    private void criarBotoesVeiculo() {
        btnCadastrarVeiculo = new JButton("Cadastrar");
        
        btnCadastrarVeiculo.setBounds(590,600, 100,25);

        btnCadastrarVeiculo.addActionListener(this);

        mainPanel.add(btnCadastrarVeiculo);
    }

    private void campoTextoVisibilidade(boolean visible) {
        textoTipo.setVisible(visible);
        textoNome.setVisible(visible);
        textoSobrenomeIE.setVisible(visible);

        textoCpfCnpj.setVisible(visible);
        textoEmail.setVisible(visible);
        textoTelefone.setVisible(visible);

        textoRua.setVisible(visible);
        textoNum.setVisible(visible);
        textoBairro.setVisible(visible);

        textoCidade.setVisible(visible);
        textoEstado.setVisible(visible);
        textoCEP.setVisible(visible);
    }

    private void cadastrarCliente() throws Exception{
        IRepositorioCliente Cliente = new ClienteCRUD();
        verificarCampos();
        apagarPadrao();
        

        if(textoTipo.getSelectedItem().equals("Física")){ 
            Cliente.inserirClienteFisico(textoNome.getText(), textoSobrenomeIE.getText(), textoCpfCnpj.getText(), textoEmail.getText(), textoTelefone.getText(), textoRua.getText(), Integer.parseInt(textoNum.getText().trim()), textoBairro.getText(), textoCidade.getText(), textoEstado.getText(), textoCEP.getText());
        }else if(textoTipo.getSelectedItem().equals("Jurídica")){
            Cliente.inserirClienteJuridico(textoNome.getText(), textoCpfCnpj.getText(), textoSobrenomeIE.getText(), textoEmail.getText(), textoTelefone.getText(), textoRua.getText(), Integer.parseInt(textoNum.getText().trim()), textoBairro.getText(), textoCidade.getText(), textoEstado.getText(), textoCEP.getText());
        }
        pagCliente.dispose();
        pagCliente.inicializar();
        novoCliente.dispose(); 
    }

    private void verificarCampos() throws Exception{
        if(textoTipo.getSelectedIndex() == 2){
            throw new Exception("Selecione o Tipo");
        }
        if(textoNome.getText().equals("Nome")){
            throw new Exception("Nome não pode ser vazio");
        }
        if(textoTipo.getSelectedItem().equals("Física")){
            if(textoCpfCnpj.getText().contains(".")){
                if(textoCpfCnpj.getText().length() != 14){
                    throw new Exception("CPF inválido");
                }
            }else{
                if(textoCpfCnpj.getText().length() != 11){
                    throw new Exception("CPF inválido");
                }
            }
        }
        if(textoTipo.getSelectedItem().equals("Jurídica")){
            if(textoCpfCnpj.getText().contains(".")){
                if(textoCpfCnpj.getText().length() != 18){
                    throw new Exception("CNPJ inválido");
                }
            }else{
                if(textoCpfCnpj.getText().length() != 14){
                    throw new Exception("CNPJ inválido");
                }
            }
        }      
        if(!textoNum.getText().equals("Número")){
            try{
                Integer.parseInt(textoNum.getText().trim());
            }catch(Exception e){ throw new Exception("Número inválido");}
        }  
    }

    private void verificarCamposVeiculo() throws Exception{
        if(textoModelo.getText().equals("Modelo")){
            throw new Exception("Modelo não pode ser vazio");
        }
        if(textoMarca.getText().equals("Marca")){
            throw new Exception("Marca não pode ser vazia");
        }
        if(textoAno.getText().equals("Ano")){
            throw new Exception("Ano não pode ser vazia");
        }
        if(!textoAno.getText().equals("Ano")){
            try{
                Integer.parseInt(textoAno.getText().trim());
            }catch(Exception e){ throw new Exception("Ano inválido");}
        }
        if(textoCor.getText().equals("Cor")){
            throw new Exception("Cor não pode ser vazia");
        }
        if(textoPlaca.getText().equals("Placa")){
            throw new Exception("Placa não pode ser vazia");
        }
    }

    private void atualizarCliente(int pessoaID)throws Exception{
        IRepositorioCliente Cliente = new ClienteCRUD();
        verificarCampos();

        if(textoTipo.getSelectedItem().equals("Física")){ 
            Cliente.atualizarClienteFisico(pessoaID, textoNome.getText(), textoSobrenomeIE.getText(), textoCpfCnpj.getText(), textoEmail.getText(), textoTelefone.getText(), textoRua.getText(), Integer.parseInt(textoNum.getText().trim()), textoBairro.getText(), textoCidade.getText(), textoEstado.getText(), textoCEP.getText());
        }else if(textoTipo.getSelectedItem().equals("Jurídica")){
            Cliente.atualizarClienteJuridico(pessoaID, textoNome.getText(), textoSobrenomeIE.getText(), textoCpfCnpj.getText(), textoEmail.getText(), textoTelefone.getText(), textoRua.getText(), Integer.parseInt(textoNum.getText().trim()), textoBairro.getText(), textoCidade.getText(), textoEstado.getText(), textoCEP.getText());
        }
        pagCliente.dispose();
        pagCliente.inicializar();
        novoCliente.dispose(); 
    }

    private void cadastrarVeiculo() throws Exception{
        IRepositorioVeiculo veiculo = new VeiculoCRUD();
        verificarCamposVeiculo();
        apagarPadraoVeiculo();
        veiculo.inserirVeiculo(pessoaID, textoModelo.getText(), textoMarca.getText(), textoPlaca.getText(), textoCor.getText(), Integer.parseInt(textoAno.getText().trim()));

        pagCliente.dispose();
        pagCliente.inicializar();
        novoCliente.dispose();
    }

    private void apagarPadraoVeiculo(){
        if(textoModelo.getText().equals("Modelo")){
            textoModelo.setText(null);
        }if(textoMarca.getText().equals("Marca")){
            textoMarca.setText(null);
        }if(textoAno.getText().equals("Ano")){
            textoAno.setText("0");
        }if(textoCor.getText().equals("Cor")){
            textoCor.setText(null);
        }if(textoPlaca.getText().equals("Placa")){
            textoPlaca.setText(null);
        }
        
    }

    private void apagarPadrao(){
        if(textoNome.getText().equals("Nome")){
            textoNome.setText(null);
        }if(textoSobrenomeIE.getText().equals("Sobrenome") || textoSobrenomeIE.getText().equals("IE")){
            textoSobrenomeIE.setText(null);
        }if(textoCpfCnpj.getText().equals("CPF") || textoCpfCnpj.getText().equals("CNPJ")){
            textoCpfCnpj.setText(null);
        }if(textoEmail.getText().equals("Email")){
            textoEmail.setText(null);
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

    private void adicionarFocusListenerCliente(){
        
        criarFocusListener(textoNome);
        criarFocusListener(textoSobrenomeIE);
        criarFocusListener(textoCpfCnpj);
        criarFocusListener(textoEmail);
        criarFocusListener(textoTelefone);

        criarFocusListener(textoRua);
        criarFocusListener(textoNum);
        criarFocusListener(textoBairro);
        criarFocusListener(textoCidade);
        criarFocusListener(textoEstado);
        criarFocusListener(textoCEP);
    }

    private void adicionarFocusListenerVeiculo(){
        criarFocusListener(textoNome);
        criarFocusListener(textoModelo);
        criarFocusListener(textoMarca);
        criarFocusListener(textoAno);
        criarFocusListener(textoCor);
        criarFocusListener(textoPlaca);
    }

    private void completarCampoTexto(int pessoaID){
        IRepositorioCliente Clientes = new ClienteCRUD();
        pessoa = Clientes.obterClientePorID(pessoaID);
        
        textoNome.setText(pessoa.Nome);
        if(pessoa.Tipo.equals("Física")){ 
            textoSobrenomeIE.setText(((ClienteFisico)pessoa).Sobrenome);       
            textoCpfCnpj.setText(((ClienteFisico)pessoa).CPF);
            textoTipo.setSelectedIndex(0);
        }else{
            textoSobrenomeIE.setText(((ClienteJuridico)pessoa).IE);       
            textoCpfCnpj.setText(((ClienteJuridico)pessoa).CNPJ);
            textoTipo.setSelectedIndex(1);
        }      
        textoEmail.setText(pessoa.Email);      
        textoTelefone.setText(pessoa.Telefone);       
        textoRua.setText(pessoa.Endereco.Rua);
        textoNum.setText(String.format("%d", pessoa.Endereco.Numero));
        textoBairro.setText(pessoa.Endereco.Bairro);
        textoCidade.setText(pessoa.Endereco.Cidade); 
        textoEstado.setText(pessoa.Endereco.Estado); 
        textoCEP.setText(pessoa.Endereco.Cep);
    }

    public void actionPerformed(ActionEvent e) {
        try{
            if( e.getSource() == btnCadastrar){
                cadastrarCliente();
            }else if( e.getSource() == btnLimpar){
                campoTextoVisibilidade(false);
                criarCampoTexto();
                criarBotoesCadastrar();           
            }else if( e.getSource() == btnAtualizar){
                atualizarCliente(pessoa.PessoaID);
            }else if(e.getSource() == textoTipo && trocaTipo){
                if(textoTipo.getSelectedIndex() == 0){
                    textoSobrenomeIE.setText("Sobrenome");
                    textoCpfCnpj.setText("CPF");
                }else if(textoTipo.getSelectedIndex() == 1){
                    textoSobrenomeIE.setText("IE");
                    textoCpfCnpj.setText("CNPJ");
                }
            }else if(e.getSource() == btnCadastrarVeiculo){
                cadastrarVeiculo();
            }
        } catch (Exception ex) {
            ExcecaoPag excecao = new ExcecaoPag();
            excecao.inicializar(ex.getMessage());
        }
    }
}
