import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

public class VendaPag extends JFrame implements ActionListener{
    JScrollPane Tabela;
    JTable tabela;
    JButton btnNovaVenda, btnLimpar, btnBuscar, btnEditar, btnDeletar;
    JButton btnInicio, btnCliente, btnFuncionario, btnProduto, btnOrdemServico, btnVenda, btnServico;
    JTextField textoCodigo, textoCliente, textoFuncionario, textoFormaPagamento, textoTotal, textoSubtotal;
    JPanel barraNav;
    JPanel mainPanel;
    JLabel lbl1;
    int hTb = 0;


    public VendaPag() {}

    public void inicializar(){
        criarJanela();
        adicionarNavBar();
        botaoVenda();
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
        lbl1 = new JLabel("VENDA");
        btnInicio = new JButton("Início");
        btnCliente = new JButton("Cliente");
        btnFuncionario = new JButton("Funcionário");
        btnProduto = new JButton("Produto");
        btnOrdemServico = new JButton("Ordem de Serviço");
        btnVenda = new JButton("Venda");
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
        transparente(btnProduto);
        transparente(btnOrdemServico);
        transparente(btnVenda);
        transparente(btnServico);
        
        Color color = new Color((float)1.0,(float)1.0,(float)1.0,(float)0.5);
        Color color2 = new Color(90,126,255);

        btnInicio.addActionListener(this);
        btnCliente.addActionListener(this);
        btnFuncionario.addActionListener(this);
        btnProduto.addActionListener(this);
        btnOrdemServico.addActionListener(this);
        btnVenda.addActionListener(this);
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
        mainPanel.add(btnProduto);
        mainPanel.add(btnOrdemServico);
        mainPanel.add(btnVenda);
        mainPanel.add(btnServico);
        mainPanel.add(barraNav);
        mainPanel.add(lbl1);
        mainPanel.setOpaque(true);
        
        setContentPane(mainPanel);
    }

    private void botaoVenda(){
        btnNovaVenda = new JButton("Nova Venda");
        
        btnLimpar = new JButton("Limpar");
        btnBuscar = new JButton("Buscar");
        btnEditar = new JButton("Editar");
        btnDeletar = new JButton("Deletar");

        btnNovaVenda.setBounds(1505,100 ,150,25);
        
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

        btnNovaVenda.addActionListener(this);
        btnBuscar.addActionListener(this);
        btnLimpar.addActionListener(this);
        btnEditar.addActionListener(this);
        btnDeletar.addActionListener(this);

        mainPanel.add(btnNovaVenda);
        mainPanel.add(btnLimpar);
        mainPanel.add(btnBuscar);
        mainPanel.add(btnDeletar);
    }

    private void campoTexto(){
        
        textoCodigo = new JTextField("Código");
        textoCliente = new JTextField("Cliente");
        textoFuncionario = new JTextField("Funcionário");

        textoFormaPagamento = new JTextField("Forma de Pagamento");
        textoTotal = new JTextField("Total");
        textoSubtotal = new JTextField("Subtotal");

        textoCodigo.setBounds(260,150, 300,30);
        textoCliente.setBounds(810,150, 300,30);
        textoFuncionario.setBounds(1355,150, 300,30);

        textoFormaPagamento.setBounds(260,250, 300,30);
        textoSubtotal.setBounds(810,250, 300,30);
        textoTotal.setBounds(1355,250, 300,30);

        setVisibilityJText(true);

        adicionarFocusListener();
        
        mainPanel.add(textoCodigo);
        mainPanel.add(textoCliente);
        mainPanel.add(textoFuncionario);

        mainPanel.add(textoFormaPagamento);
        mainPanel.add(textoTotal);
        mainPanel.add(textoSubtotal);
    }

    private void setVisibilityJText(boolean visible) {
        textoCodigo.setVisible(visible);
        textoCliente.setVisible(visible);
        textoFuncionario.setVisible(visible);

        textoFormaPagamento.setVisible(visible);
        textoTotal.setVisible(visible);
        textoSubtotal.setVisible(visible);
    }

    private void adicionarTabela(){
        IRepositorioVenda Vendas = new VendaCRUD();
        DefaultTableModel model = new DefaultTableModel();
        hTb=0;
        Object [] o = new Object [6];
        String [] colunas = {"Código", "Funcionário", "Cliente", "Forma de Pagamento", "Subtotal", "Total"};
        tabela = new JTable(model);
        model.setColumnIdentifiers(colunas);
        tabela.setBackground(Color.LIGHT_GRAY);  
        tabela.setFont(new Font("Verdana", Font.PLAIN, 18));      
        
        for (Venda venda : Vendas.obterVendas()) {
            
            o[0]= venda.VendaID;
            o[1]= venda.funcionario.Nome + " " + venda.funcionario.Sobrenome;
            if(venda.Cliente.Tipo.equals("Física")){
                Pessoa p = new Pessoa();
                p = venda.Cliente;
                o[2]= venda.Cliente.Nome + " " + ((ClienteFisico)p).getSobrenome();
            }else{
                o[2]= venda.Cliente.Nome;
            }
          
            o[3]= venda.FormaPagamento;
            o[4]= String.format("%.2f" ,venda.SubTotal);
            o[5]= String.format("%.2f" ,venda.Total);
            model.addRow(o);
            hTb++;
        }
        tabela.setDefaultEditor(Object.class, null);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.getTableHeader().setResizingAllowed(false);
        tabela.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 18));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        tabela.getColumnModel().getColumn(0).setMaxWidth(100);
        tabela.getColumnModel().getColumn(5).setMaxWidth(200);
        tabela.getColumnModel().getColumn(4).setMaxWidth(100);
        tabela.getColumnModel().getColumn(4).setMinWidth(100);

        for (int i = 0; i < colunas.length; i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        tabela.setRowHeight(30);
        hTb += 1;
        hTb *= 30;

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
               if (me.getClickCount() == 2) {
                    VerVenda verVenda = new VerVenda();
                    int vendaID = (int) tabela.getValueAt(tabela.getSelectedRow(), 0);
                    verVenda.janelaVerVenda(vendaID);
               }
            }
        });
        
        Tabela = new JScrollPane(tabela);
        if(hTb<=570){
            Tabela.setSize(1400, hTb);
        }else{Tabela.setSize(1400, 570);}

        Tabela.setLocation(260,400);
        Tabela.setVisible(true);
        Tabela.setOpaque(false);
        Tabela.getViewport().setOpaque(false);

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

    private void buscarTabela(String cliente, String funcionario, String formaPagamento, float subtotal, float total){

        for (int i = 0; i < (hTb/30)-1; i++) {
            if(((String)tabela.getValueAt(i,1)).contains(funcionario)){
                tabela.setRowSelectionInterval(i, i);
            }
            if(((String)tabela.getValueAt(i,2)).contains(cliente)){
                tabela.setRowSelectionInterval(i, i);
            }
            if(tabela.getValueAt(i, 1).equals(funcionario)){
                tabela.setRowSelectionInterval(i, i);
            }
            if(tabela.getValueAt(i, 2).equals(cliente)){
                tabela.setRowSelectionInterval(i, i);
            }
            if(tabela.getValueAt(i, 3).equals(formaPagamento)){
                tabela.setRowSelectionInterval(i, i);
            }
            if(tabela.getValueAt(i, 4).equals(subtotal)){
                tabela.setRowSelectionInterval(i, i);
            }
            if(tabela.getValueAt(i, 5).equals(total)){
                tabela.setRowSelectionInterval(i, i);
            }
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

    private void adicionarFocusListener(){
        criarFocusListener(textoCodigo);
        criarFocusListener(textoCliente);
        criarFocusListener(textoFuncionario);

        criarFocusListener(textoFormaPagamento);
        criarFocusListener(textoTotal);
        criarFocusListener(textoSubtotal);
    }

    private void deletarVenda(int linha){
        IRepositorioVenda Vendas = new VendaCRUD();
        Venda venda = new Venda();
        NotificacaoPag notificacao = new NotificacaoPag();

        int vendaID = (int) tabela.getValueAt(linha, 0);
        
        venda = Vendas.obterVendaPorID(vendaID);
        

        notificacao.deletarVendaPag(venda, this);
    }

    public void actionPerformed(ActionEvent e) {
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
        }else if (e.getSource() == btnProduto){
            ProdutoPag produtoPag = new ProdutoPag();
            produtoPag.inicializar();
            dispose();
        }else if(e.getSource() == btnOrdemServico){
            OrdemServicoPag ordemServicoPag = new OrdemServicoPag();
            ordemServicoPag.inicializar();
            dispose();
        }

        if(e.getSource() == btnNovaVenda){
            SelecionaClientePag selecionaClientePag = new SelecionaClientePag();
            selecionaClientePag.janelaSelecionarCliente(this);
        }

        if(e.getSource() == btnBuscar){
            if(!textoCodigo.getText().equals("Código")){
                int codigo = Integer.parseInt(textoCodigo.getText().trim());
                buscarTabelaID(codigo);
            }
            if(!textoCliente.getText().equals("Cliente") || textoFuncionario.getText().equals("Funcionário")|| textoFormaPagamento.getText().equals("Forma de Pagamento")){
                buscarTabela(textoCliente.getText(), textoFuncionario.getText(), textoFormaPagamento.getText(), (float) 0, (float) 0);
            }
            if(!textoSubtotal.getText().equals("Subtotal")){
                buscarTabela(textoCliente.getText(), textoFuncionario.getText(), textoFormaPagamento.getText(), Float.parseFloat(textoSubtotal.getText().trim()), (float) 0);
            }
            if(!textoTotal.getText().equals("Total")){
                buscarTabela(textoCliente.getText(), textoFuncionario.getText(), textoFormaPagamento.getText(), (float) 0, Float.parseFloat(textoTotal.getText().trim()));
            }
        }else if(e.getSource() == btnLimpar){
            setVisibilityJText(false);
            campoTexto();
        }else if(e.getSource() == btnDeletar){
            deletarVenda(linha);
        }
    }

}
