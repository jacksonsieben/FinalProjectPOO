import java.util.*;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

public class ClientePag extends JFrame implements ActionListener {
    JScrollPane Tabela;
    JTable tabela;
    JButton btnNovoCliente, btnNovoVeiculo, btnLimpar, btnBuscar, btnEditar, btnDeletar;
    JButton btnInicio, btnCliente, btnFuncionario, btnVenda, btnOrdemServico, btnProduto, btnServico;
    JTextField textoCodigo, textoNome, textoCpfCnpj, textoEmail, textoTelefone;
    JComboBox<Object> textoTipo;
    JPanel panel;
    JPanel barraNav;
    JPanel mainPanel;   
    JLabel lbl1;
    int hTb = 0;

    InicioPag inicioPag = new InicioPag();

    public ClientePag() {}

    public void inicializar() {
        criarJanela();
        adicionarNavBar();
        criarClientePag();
    }

    private void criarJanela(){
        setTitle("Chaveiro");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/imgs/key-fill.png")).getImage());
    }

    private void criarNavBar(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        barraNav = new JPanel();
        lbl1 = new JLabel("CLIENTE");
        btnInicio = new JButton("Início");
        btnCliente = new JButton("Cliente");
        btnFuncionario = new JButton("Funcionário");
        btnVenda = new JButton("Venda");
        btnOrdemServico = new JButton("Ordem de Serviço");
        btnProduto = new JButton("Produto");
        btnServico = new JButton("Serviço");
        
        barraNav.setBounds(0,0, 1920,50);
        lbl1.setBounds(880,50,500,60);
        lbl1.setFont(new Font("Verdana", Font.PLAIN, 32));
        
        btnInicio.setBounds(260,0,200,50);
        btnCliente.setBounds(460,0,200,50);
        btnFuncionario.setBounds(660,0,200,50);
        btnVenda.setBounds(860,0,200,50);
        btnOrdemServico.setBounds(1060,0,200,50);
        btnProduto.setBounds(1260,0,200,50);
        btnServico.setBounds(1460,0,200,50);

        transparente(btnInicio);
        transparente(btnCliente);
        transparente(btnFuncionario);
        transparente(btnVenda);
        transparente(btnOrdemServico);
        transparente(btnProduto);
        transparente(btnServico);
        
        Color color = new Color((float)1.0,(float)1.0,(float)1.0,(float)0.5);
        Color color2 = new Color(90,126,255);

        btnInicio.addActionListener(this);
        btnCliente.addActionListener(this);
        btnFuncionario.addActionListener(this);
        btnVenda.addActionListener(this);
        btnOrdemServico.addActionListener(this);
        btnProduto.addActionListener(this);
        btnServico.addActionListener(this);

        barraNav.setBackground(color);
        mainPanel.setBackground(color2);
    }

    private void transparente(JButton btn){
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
    }

    private void adicionarNavBar(){
        criarNavBar();

        mainPanel.add(btnInicio);
        mainPanel.add(btnCliente);
        mainPanel.add(btnFuncionario);
        mainPanel.add(btnVenda);
        mainPanel.add(btnOrdemServico);
        mainPanel.add(btnProduto);
        mainPanel.add(btnServico);
        mainPanel.add(barraNav);
        mainPanel.add(lbl1);
        mainPanel.setOpaque(true);
        
        setContentPane(mainPanel);
    }

    private void criarClientePag(){
        campoTexto();
        botaoCliente();
        adicionarTabela();
        setContentPane(mainPanel);
    }

    private void adicionarTabela(){
        IRepositorioCliente Clientes = new ClienteCRUD();
        IRepositorioVeiculo Veiculos = new VeiculoCRUD();
        DefaultTableModel model = new DefaultTableModel();
        hTb = 0;
        Object [] o = new Object [6];
        String [] colunas = {"Codigo", "Tipo", "Nome", "CPF/CNPJ", "Telefone", "Veículo"};
        tabela = new JTable(model);
        model.setColumnIdentifiers(colunas);
        tabela.setBackground(Color.LIGHT_GRAY);  
        tabela.setFont(new Font("Verdana", Font.PLAIN, 18));
        
        for (Pessoa p : Clientes.obterClientes()) {
            
            o[0]= p.getPessoaID();
            o[1]= p.getTipo();
            if(p.Tipo.equals("Física")){ 
                o[2]= p.getNome() +  " " + ((ClienteFisico) p).getSobrenome();
                o[3]= ((ClienteFisico) p).getCPF();
            }else{
                o[2]= p.getNome();
                o[3] = ((ClienteJuridico) p).getCNPJ();
            }
            o[4]= p.getTelefone();
            o[5]=null;
            List<Veiculo> veiculo = new ArrayList<Veiculo>();
            try {
                veiculo = Veiculos.obterVeiculoPorCliente(p.PessoaID);
                if(veiculo.size() > 0){
                    o[5]= veiculo.get(0).Marca + " " + veiculo.get(0).Modelo;
                }else {o[5]= null;}                
            } catch (Exception e){System.out.println("Erro - " + e.getMessage());}
            
            model.addRow(o);
            hTb++;
        }
        tabela.setDefaultEditor(Object.class, null);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 18));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        tabela.getColumnModel().getColumn(0).setMaxWidth(100);
        tabela.getColumnModel().getColumn(1).setMaxWidth(200);
        
        for (int i = 0; i < colunas.length; i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        tabela.setRowHeight(30);
        hTb += 1;
        hTb *= 30;

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
               if (me.getClickCount() == 2) {     
                    verCliente();
               }
            }
        });
        
        Tabela = new JScrollPane(tabela);
        if(hTb<=570){
            Tabela.setSize(1400, hTb);
        }else {Tabela.setSize(1400, 570);}
        
        Tabela.setLocation(260,400);
        Tabela.setVisible(true);

        Color select = new Color(0,255,148);
        tabela.setSelectionBackground(select);

        mainPanel.add(Tabela);
    }

    private void botaoCliente(){
        btnNovoCliente = new JButton("Novo Cliente");
        btnNovoVeiculo = new JButton("Novo Veículo");
        btnLimpar = new JButton("Limpar");
        btnBuscar = new JButton("Buscar");
        btnEditar = new JButton("Editar");
        btnDeletar = new JButton("Deletar");

        btnNovoCliente.setBounds(1535,100 ,120,25);
        btnNovoVeiculo.setBounds(1355,100 ,120,25);
        btnBuscar.setBounds(1535,300 ,120,25);
        btnLimpar.setBounds(1355,300 ,120,25);
        btnEditar.setBounds(1535,350 ,120,25);
        btnDeletar.setBounds(1355,350 ,120,25);

        Color green = new Color(0, 255, 33, 255);
        Color red = new Color(255, 0, 0, 255);

        btnLimpar.setBackground(red);
        btnDeletar.setBackground(red);
        btnEditar.setBackground(green);
        btnBuscar.setBackground(green);

        btnNovoCliente.addActionListener(this);
        btnNovoVeiculo.addActionListener(this);
        btnBuscar.addActionListener(this);
        btnLimpar.addActionListener(this);
        btnEditar.addActionListener(this);
        btnDeletar.addActionListener(this);

        mainPanel.add(btnNovoCliente);
        mainPanel.add(btnNovoVeiculo);
        mainPanel.add(btnLimpar);
        mainPanel.add(btnBuscar);
        mainPanel.add(btnEditar);
        mainPanel.add(btnDeletar);
    }

    private void campoTexto(){
        
        textoCodigo = new JTextField("Código");
        textoNome = new JTextField("Nome");

        textoCpfCnpj = new JTextField("CPF/CNPJ");
        textoEmail = new JTextField("Email");
        textoTelefone = new JTextField("Telefone");
        
        Object[] pessoaTipo = { "Física", "Jurídica", "Selecionar Tipo"};
        
        textoTipo = new JComboBox<>(pessoaTipo);
        textoTipo.setSelectedIndex(2);
        textoTipo.addActionListener(this);

        textoCodigo.setBounds(260,150, 300,30);
        textoNome.setBounds(810,150, 300,30);
        textoCpfCnpj.setBounds(1355,150, 300,30);

        textoTelefone.setBounds(260,250, 300,30);
        
        

        setVisibilityJText(true);

        adicionarFocusListener();
        
        mainPanel.add(textoCodigo);
        mainPanel.add(textoNome);
        
        mainPanel.add(textoCpfCnpj);
        
        mainPanel.add(textoTelefone);

    }

    private void setVisibilityJText(boolean visible) {
        textoCodigo.setVisible(visible);
        textoNome.setVisible(visible);
        
        textoCpfCnpj.setVisible(visible);
        
        textoTelefone.setVisible(visible);
    }

    private void buscarTabelaID(int codigo){
        for (int i = 0; i < (hTb/30)-1; i++) {
            if(tabela.getValueAt(i,0).equals(codigo)){
                tabela.setRowSelectionInterval(i, i);
            }
        }
    }

    private void buscarTabela(String nome, String cpf, String email, String telefone){
        for (int i = 0; i < (hTb/30)-1; i++) {
            if(tabela.getValueAt(i, 2).toString().toLowerCase().contains(nome)){
                tabela.setRowSelectionInterval(i, i);
            }
                      
            if(tabela.getValueAt(i, 3).toString().contains(cpf)){
                tabela.setRowSelectionInterval(i, i);
            }
            if(tabela.getValueAt(i, 4).equals(telefone)){
                tabela.setRowSelectionInterval(i, i);
            }
            
        }
    }

    private void buscarTabelaTipo(String tipo){
        IRepositorioCliente Clientes = new ClienteCRUD();
        Pessoa cliente = new Pessoa();

        for (Pessoa p : Clientes.obterClientes()) {
            if(p.Tipo.equals(tipo)){
                cliente = p;
                tabela.setRowSelectionInterval(cliente.PessoaID, cliente.PessoaID);
            }
        }
    }

    private void deletarCliente(int linha){
        IRepositorioCliente Clientes = new ClienteCRUD();
        Pessoa p = new Pessoa();
        NotificacaoPag notificacao = new NotificacaoPag();

        int clienteID = (int) tabela.getValueAt(linha, 0);
        
        p = Clientes.obterClientePorID(clienteID); 
        
        notificacao.deletarClientePag(p, this);
    }

    private void atualizarCliente (int linha){
        IRepositorioCliente Clientes = new ClienteCRUD();
        Pessoa p = new Pessoa();
        NovoClientePag novoCliente = new NovoClientePag();

        int clienteID = (int) tabela.getValueAt(linha, 0);
        
        p = Clientes.obterClientePorID(clienteID); 

        novoCliente.janelaAtualizarCliente(p.PessoaID, this);
    }

    private void novoVeiculo(int linha){
        IRepositorioCliente Clientes = new ClienteCRUD();
        Pessoa p = new Pessoa();
        NovoClientePag novoCliente = new NovoClientePag();

        int clienteID = (int) tabela.getValueAt(linha, 0);
        
        p = Clientes.obterClientePorID(clienteID); 

        novoCliente.janelaVeiculo(p.PessoaID, tabela.getValueAt(linha, 2).toString(), this);
    }

    private void verCliente(){
        VerCliente verCliente = new VerCliente();
        int pessoaID = (int) tabela.getValueAt(tabela.getSelectedRow(), 0);
        verCliente.janelaVerCliente(pessoaID, this);
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
        criarFocusListener(textoCodigo);
        criarFocusListener(textoNome);
        criarFocusListener(textoCpfCnpj);
        criarFocusListener(textoTelefone);
    }

    public void actionPerformed(ActionEvent e) { 
        NovoClientePag novoCliente = new NovoClientePag();
        int linha = tabela.getSelectedRow();
        tabela.getSelectionModel().clearSelection();

        
        if(e.getSource() == btnInicio){
            inicioPag.inicializar();
            dispose();
        }else if(e.getSource() == btnFuncionario){
            FuncionarioPag funcionario = new FuncionarioPag();
            funcionario.inicializar();
            dispose();
        }else if(e.getSource() == btnProduto){
            ProdutoPag produto = new ProdutoPag();
            produto.inicializar();
            dispose();
        }else if (e.getSource() == btnServico){
            ServicoPag servicoPag = new ServicoPag();
            servicoPag.inicializar();
            dispose();
        }else if (e.getSource() == btnVenda){
            VendaPag vendaPag = new VendaPag();
            vendaPag.inicializar();
            dispose();
        }else if(e.getSource() == btnOrdemServico){
            OrdemServicoPag ordemServicoPag = new OrdemServicoPag();
            ordemServicoPag.inicializar();
            dispose();
        }
        

        if(e.getSource() == btnBuscar){
            if(!textoCodigo.getText().equals("Código")){
                int codigo = Integer.parseInt(textoCodigo.getText().trim());
                buscarTabelaID(codigo);
            }if(!textoNome.getText().equals("Nome")){
                buscarTabela(textoNome.getText(), textoCpfCnpj.getText(), textoEmail.getText(), textoTelefone.getText());
            }if(textoTipo.getSelectedIndex() != 3){
                buscarTabelaTipo((String) textoTipo.getSelectedItem());
            }if(!textoCpfCnpj.getText().equals("CPF/CNPJ")){
                buscarTabela(textoNome.getText(), textoCpfCnpj.getText(), textoEmail.getText(), textoTelefone.getText());
            }
            if(!textoEmail.getText().equals("Email")){
                buscarTabela(textoNome.getText(), textoCpfCnpj.getText(), textoEmail.getText(), textoTelefone.getText());
            }
            if(!textoTelefone.getText().equals("Telefone")){
                buscarTabela(textoNome.getText(), textoCpfCnpj.getText(), textoEmail.getText(), textoTelefone.getText());
            }
            
        }else if(e.getSource() == btnLimpar){
            setVisibilityJText(false);
            campoTexto();
        } else if(e.getSource() == btnNovoCliente){
            novoCliente.janelaCadastroCliente(this);
        } else if(e.getSource() == btnDeletar){
            deletarCliente(linha);
        } else if(e.getSource() == btnEditar){
            atualizarCliente(linha);
        } else if (e.getSource() == btnNovoVeiculo){
            novoVeiculo(linha);
        }
    }
}
