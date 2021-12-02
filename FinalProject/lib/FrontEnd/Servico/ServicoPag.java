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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ServicoPag extends JFrame implements ActionListener{
    JScrollPane Tabela;
    JTable tabela;
    JButton btnNovoServico, btnLimpar, btnBuscar, btnEditar, btnDeletar;
    JButton btnInicio, btnCliente, btnFuncionario, btnVenda, btnOrdemServico, btnProduto, btnServico;
    JTextField textoCodigo, textoDescricao, textoPreco;
    JPanel barraNav;
    JPanel mainPanel;
    JLabel lbl1;
    int hTb = 0; 

    public ServicoPag(){}

    public void inicializar(){
        criarJanela();
        adicionarNavBar();
        botaoServico();
        campoTexto();
        adicionarTabela();
    }

    private void criarJanela() {
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
        lbl1 = new JLabel("SERVIÇO");
        btnInicio = new JButton("Início");
        btnCliente = new JButton("Cliente");
        btnFuncionario = new JButton("Funcionário");
        btnVenda = new JButton("Venda");
        btnOrdemServico = new JButton("Ordem de Serviço");
        btnProduto = new JButton("Produto");
        btnServico = new JButton("Serviço");
        
        barraNav.setBounds(0,0, 1920,50);
        lbl1.setBounds(0,50,1920,60);
        lbl1.setFont(new Font("Verdana", Font.PLAIN, 32));
        lbl1.setHorizontalAlignment(JLabel.CENTER);

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

    private void botaoServico(){
        btnNovoServico = new JButton("Novo Serviço");
        
        btnLimpar = new JButton("Limpar");
        btnBuscar = new JButton("Buscar");
        btnEditar = new JButton("Editar");
        btnDeletar = new JButton("Deletar");

        btnNovoServico.setBounds(1505,100 ,150,25);
        
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

        btnNovoServico.addActionListener(this);
        btnBuscar.addActionListener(this);
        btnLimpar.addActionListener(this);
        btnEditar.addActionListener(this);
        btnDeletar.addActionListener(this);

        mainPanel.add(btnNovoServico);
        mainPanel.add(btnLimpar);
        mainPanel.add(btnBuscar);
        mainPanel.add(btnEditar);
        mainPanel.add(btnDeletar);
    }

    private void campoTexto(){

        textoCodigo = new JTextField("Código");
        textoDescricao = new JTextField("Descrição");
        textoPreco = new JTextField("Preço");

        textoCodigo.setBounds(260,150, 300,30);
        textoDescricao.setBounds(810,150, 300,30);
        textoPreco.setBounds(1355,150, 300,30);

        setVisibilityJText(true);

        adicionarFocusListener();
        
        mainPanel.add(textoCodigo);
        mainPanel.add(textoDescricao);
        mainPanel.add(textoPreco);

    }

    private void setVisibilityJText(boolean visible) {
        textoCodigo.setVisible(visible);
        textoDescricao.setVisible(visible);
        textoPreco.setVisible(visible);
    }

    private void adicionarTabela(){
        IRepositorioServico Servicos = new ServicoCRUD();
        DefaultTableModel model = new DefaultTableModel();
        hTb=0;
        Object [] o = new Object [3];
        String [] colunas = {"Código", "Descrição", "Preço"};
        tabela = new JTable(model);
        model.setColumnIdentifiers(colunas);
        tabela.setBackground(Color.LIGHT_GRAY);  
        tabela.setFont(new Font("Verdana", Font.PLAIN, 18));      
        
        for (Servico s : Servicos.obterServicos()) {
            
            o[0]= s.getServicoID();
            o[1]= s.getDescricao();
            o[2]= String.format("%.2f" ,s.getPreco());
            
            model.addRow(o);
            hTb++;
        }
        tabela.setDefaultEditor(Object.class, null);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 18));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        tabela.getColumnModel().getColumn(0).setMaxWidth(100);
        
        for (int i = 0; i < colunas.length; i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        tabela.setRowHeight(30);
        hTb += 1;
        hTb *= 30;
        
        
        Tabela = new JScrollPane(tabela);
        if(hTb<=570){
            Tabela.setSize(1400, hTb);
        }else{Tabela.setSize(1400, 570);}

        Tabela.setLocation(260,400);
        Tabela.setVisible(true);

        Color select = new Color(0,255,148);
        tabela.setSelectionBackground(select);

        mainPanel.add(Tabela);
    }

    private void buscarTabelaID(int codigo){
        for (int i = 0; i < (hTb/30)-1; i++) {
            if(tabela.getValueAt(i,0).equals(codigo)){
                tabela.setRowSelectionInterval(i, i);
            }
        }
    }

    private void buscarTabela(String descricao, float preco){

        for (int i = 0; i < (hTb/30)-1; i++) {
            
            if(tabela.getValueAt(i, 1).toString().toLowerCase().contains(descricao)){
                tabela.setRowSelectionInterval(i, i);
            }
            if(tabela.getValueAt(i, 2).equals(preco)){
                tabela.setRowSelectionInterval(i, i);
            }
        }
    }

    private void atualizarServico(int linha){
        IRepositorioServico Servicos = new ServicoCRUD();
        Servico servico = new Servico();
        NovoServicoPag novoServico = new NovoServicoPag();

        int servicoID = (int) tabela.getValueAt(linha,0);

        servico = Servicos.obterServicoPorID(servicoID);

        novoServico.janelaAtualizarServico(servico.ServicoID, this);
    }

    private void deletarServico(int linha){
        IRepositorioServico Servicos = new ServicoCRUD();
        Servico servico = new Servico();
        NotificacaoPag notificacao = new NotificacaoPag();

        int servicoID = (int) tabela.getValueAt(linha, 0);

        servico = Servicos.obterServicoPorID(servicoID);

        notificacao.deletarServicoPag(servico, this);
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
        criarFocusListener(textoDescricao);
        criarFocusListener(textoPreco);
    }

    public void actionPerformed(ActionEvent e){

        int linha = tabela.getSelectedRow();
        tabela.getSelectionModel().clearSelection();

        if(e.getSource() == btnInicio){
            InicioPag inicioPag = new InicioPag();
            inicioPag.inicializar();
            dispose();
        }else if(e.getSource() == btnCliente){
            ClientePag cliente = new ClientePag();
            cliente.inicializar();
            dispose();
        }else if(e.getSource() == btnFuncionario){
            FuncionarioPag funcionario = new FuncionarioPag();
            funcionario.inicializar();
            dispose();
        }else if(e.getSource() == btnProduto){
            ProdutoPag produto = new ProdutoPag();
            produto.inicializar();
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
        
        if(e.getSource() == btnNovoServico){
            NovoServicoPag novoServico = new NovoServicoPag();
            novoServico.janelaCadastroServico(this);
        }
        if(e.getSource() == btnBuscar){
            
            if(!textoCodigo.getText().equals("Código")){
                int codigo = Integer.parseInt(textoCodigo.getText().trim());
                buscarTabelaID(codigo);
            }
            if(!textoDescricao.getText().equals("Descrição")){
                buscarTabela(textoDescricao.getText(), (float) 0);
            }if(!textoPreco.getText().equals("Preço")){
                buscarTabela(textoDescricao.getText(), Float.parseFloat(textoPreco.getText().trim()));
            }
        }else if(e.getSource() == btnLimpar){
            setVisibilityJText(false);
            campoTexto();
        }else if(e.getSource() == btnEditar){
            atualizarServico(linha);
        }else if(e.getSource() == btnDeletar){
            deletarServico(linha);
        }
    }

}
