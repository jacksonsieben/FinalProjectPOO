import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class ProdutoPag extends JFrame implements ActionListener{
    JScrollPane Tabela;
    JTable tabela;
    JButton btnNovoProduto, btnLimpar, btnBuscar, btnEditar, btnDeletar;
    JButton btnInicio, btnCliente, btnFuncionario, btnVenda, btnOrdemServico, btnProduto, btnServico;
    JTextField textoCodigo, textoDescricao, textoCodigoBarra, textoPrecoCusto, textoPrecoVenda, textoQuantidadeEstoque;
    JPanel barraNav;
    JPanel mainPanel;
    JLabel lbl1;
    int hTb = 0;

    public ProdutoPag(){}

    public void inicializar(){
        criarJanela();
        adicionarNavBar();
        botaoProduto();
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
        lbl1 = new JLabel("PRODUTO");
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

    private void botaoProduto(){
        btnNovoProduto = new JButton("Novo Produto");
        
        btnLimpar = new JButton("Limpar");
        btnBuscar = new JButton("Buscar");
        btnEditar = new JButton("Editar");
        btnDeletar = new JButton("Deletar");

        btnNovoProduto.setBounds(1505,100 ,150,25);
        
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

        btnNovoProduto.addActionListener(this);
        btnBuscar.addActionListener(this);
        btnLimpar.addActionListener(this);
        btnEditar.addActionListener(this);
        btnDeletar.addActionListener(this);

        mainPanel.add(btnNovoProduto);
        mainPanel.add(btnLimpar);
        mainPanel.add(btnBuscar);
        mainPanel.add(btnEditar);
        mainPanel.add(btnDeletar);
    }

    private void campoTexto(){

        textoCodigoBarra = new JTextField("Código de Barras");
        textoCodigo = new JTextField("Código");
        textoDescricao = new JTextField("Descrição");

        textoPrecoVenda = new JTextField("Preço de Venda");
        textoPrecoCusto = new JTextField("Preço de Custo");

        textoCodigo.setBounds(260,150, 300,30);
        textoDescricao.setBounds(810,150, 845,30);

        textoCodigoBarra.setBounds(260,250, 300,30);
        textoPrecoCusto.setBounds(810,250, 300,30);
        textoPrecoVenda.setBounds(1355,250, 300,30);

        setVisibilityJText(true);

        adicionarFocusListener();
        
        mainPanel.add(textoCodigoBarra);
        mainPanel.add(textoCodigo);
        mainPanel.add(textoDescricao);
        mainPanel.add(textoPrecoVenda);
        mainPanel.add(textoPrecoCusto);
    }

    private void setVisibilityJText(boolean visible) {
        textoCodigo.setVisible(visible);
        textoDescricao.setVisible(visible);
        textoCodigoBarra.setVisible(visible);
        textoPrecoVenda.setVisible(visible);
        textoPrecoCusto.setVisible(visible);
    }

    private void adicionarTabela(){
        IRepositorioProduto Produtos = new ProdutoCRUD();
        DefaultTableModel model = new DefaultTableModel();
        hTb=0;
        Object [] o = new Object [6];
        String [] colunas = {"Codigo", "Código de Barras", "Descrição", "Preço de Custo", "Preço de Venda", "Quantidade Estoque"};
        tabela = new JTable(model);
        model.setColumnIdentifiers(colunas);
        tabela.setBackground(Color.LIGHT_GRAY);  
        tabela.setFont(new Font("Verdana", Font.PLAIN, 18));      
        
        for (Produto p : Produtos.obterProdutos()) {
            
            o[0]= p.getProdutoID();
            o[1]= p.getCodigoBarras();
            o[2]= p.getDescricao();
            o[3]= String.format("%.2f" ,p.getPrecoCusto());
            o[4]= String.format("%.2f" ,p.getPrecoVenda());
            o[5]= p.getQuantidadeEstoque();
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

    private void buscarTabela(String descricao, long codigoBarras, float precoCusto, float precoVenda, int quantidadeEstoque){

        for (int i = 0; i < (hTb/30)-1; i++) {
            
            if(tabela.getValueAt(i, 1).equals(codigoBarras)){
                tabela.setRowSelectionInterval(i, i);
            }
            if(tabela.getValueAt(i, 2).toString().toLowerCase().contains(descricao)){
                tabela.setRowSelectionInterval(i, i);
            }
            if(tabela.getValueAt(i, 3).equals(precoCusto)){
                tabela.setRowSelectionInterval(i, i);
            }
            if(tabela.getValueAt(i, 4).equals(precoVenda)){
                tabela.setRowSelectionInterval(i, i);
            }
            if(tabela.getValueAt(i, 5).equals(quantidadeEstoque)){
                tabela.setRowSelectionInterval(i, i);
            }
        }
    }

    private void atualizarProduto(int linha){
        IRepositorioProduto Produtos = new ProdutoCRUD();
        Produto produto = new Produto();
        NovoProdutoPag novoProduto = new NovoProdutoPag();

        int produtoID = (int) tabela.getValueAt(linha,0);

        produto = Produtos.obterProdutoPorID(produtoID);

        novoProduto.janelaAtualizarProduto(produto.ProdutoID, this);
    }

    private void deletarProduto(int linha){
        IRepositorioProduto Produtos = new ProdutoCRUD();
        Produto produto = new Produto();
        NotificacaoPag notificacao = new NotificacaoPag();

        int produtoID = (int) tabela.getValueAt(linha, 0);

        produto = Produtos.obterProdutoPorID(produtoID);

        notificacao.deletarProdutoPag(produto, this);
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
        criarFocusListener(textoCodigoBarra);
        criarFocusListener(textoDescricao);
        criarFocusListener(textoPrecoVenda);
        criarFocusListener(textoPrecoCusto);
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
        
        if(e.getSource() == btnNovoProduto){
            NovoProdutoPag novoProduto = new NovoProdutoPag();
            novoProduto.janelaCadastroProduto(this);
        }
        if(e.getSource() == btnBuscar){
            
            if(!textoCodigo.getText().equals("Código")){
                int codigo = Integer.parseInt(textoCodigo.getText().trim());
                buscarTabelaID(codigo);
            }
            if(!textoDescricao.getText().equals("Descrição")){
                buscarTabela(textoDescricao.getText(), 0, (float) 0 , (float) 0, 0);
            }if(!textoCodigoBarra.getText().equals("Código de Barras")){
                buscarTabela(textoDescricao.getText(), Long.parseLong(textoCodigoBarra.getText().trim()), (float) 0 , (float) 0, 0);
            }if(!textoPrecoCusto.getText().equals("Preço de Custo")){
                buscarTabela(textoDescricao.getText(), 0, Float.parseFloat(textoPrecoCusto.getText().trim()), (float) 0, 0);
            }
            if(!textoPrecoVenda.getText().equals("Preço de Venda")){
                buscarTabela(textoDescricao.getText(), 0, (float) 0, Float.parseFloat(textoPrecoVenda.getText().trim()), 0);
            }
            if(!textoQuantidadeEstoque.getText().equals("Quantidade Estoque")){
                buscarTabela(textoDescricao.getText(), 0, (float) 0 , (float) 0, Integer.parseInt(textoQuantidadeEstoque.getText().trim()));
            }
            
        }else if(e.getSource() == btnLimpar){
            setVisibilityJText(false);
            campoTexto();
        }else if(e.getSource() == btnEditar){
            atualizarProduto(linha);
        }else if(e.getSource() == btnDeletar){
            deletarProduto(linha);
        }
    }
}
