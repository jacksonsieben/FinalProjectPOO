import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class InicioPag extends JFrame implements ActionListener {
    JButton btnInicio, btnCliente, btnFuncionario, btnVenda, btnOrdemServico, btnProduto, btnServico;
    JPanel barraNav;
    JPanel mainPanel;
    JLabel lbl1;

    public InicioPag() {}

    public void inicializar() {
        criarJanela();
        adicionarNavBar();
        setVisible(true);
    }

    private void criarJanela() {
        setTitle("Chaveiro");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1280,720));
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/imgs/key-fill.png")).getImage());
    }

    private void criarNavBar(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        barraNav = new JPanel();
        lbl1 = new JLabel("CHAVEIRO");
        btnInicio = new JButton("Início");
        btnCliente = new JButton("Cliente");
        btnFuncionario = new JButton("Funcionário");
        btnVenda = new JButton("Venda");
        btnOrdemServico = new JButton("Ordem de Serviço");
        btnProduto = new JButton("Produto");
        btnServico = new JButton("Serviço");
        
        barraNav.setSize(1920,50);
        barraNav.setLocation(0,150);
        lbl1.setBounds(880,50,500,60);
        lbl1.setFont(new Font("Verdana", Font.PLAIN, 32));
        
        btnInicio.setBounds(260,150,200,50);       
        btnCliente.setBounds(460,150,200,50);
        btnFuncionario.setBounds(660,150,200,50);
        btnVenda.setBounds(860,150,200,50);
        btnOrdemServico.setBounds(1060,150,200,50);
        btnProduto.setBounds(1260,150,200,50);
        btnServico.setBounds(1460,150,200,50);

        transparente(btnInicio);
        transparente(btnCliente);
        transparente(btnFuncionario);
        transparente(btnVenda);
        transparente(btnOrdemServico);
        transparente(btnProduto);
        transparente(btnServico);
        
        Color color = new Color((float)1.0,(float)1.0,(float)1.0,(float)0.5);
        Color color2 = new Color(90,126,255);

        btnCliente.addActionListener(this);
        btnFuncionario.addActionListener(this);
        btnVenda.addActionListener(this);
        btnOrdemServico.addActionListener(this);
        btnProduto.addActionListener(this);
        btnServico.addActionListener(this);

        barraNav.setBackground(color);
        mainPanel.setBackground(color2);
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

    private void transparente(JButton btn){
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
    }

    public void actionPerformed(ActionEvent e) {
        ClientePag cliente = new ClientePag();
        FuncionarioPag funcionario = new FuncionarioPag();        
        if(e.getSource() == btnCliente){
            
            cliente.inicializar();
            
            dispose();
        }else if(e.getSource() == btnFuncionario){
            funcionario.inicializar();
            dispose();
        }
        else if(e.getSource() == btnProduto){
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
        setVisible(false);
        dispose();
    }

}