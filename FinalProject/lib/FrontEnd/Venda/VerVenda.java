import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class VerVenda implements ActionListener {
    JFrame verVenda = new JFrame();
    JTextField textoCliente, textoFuncionario;
    JPanel mainPanel;
    JLabel titulo;
    JButton btnFechar;
    JScrollPane fundoTabelaVenda;
    JTable tabelaVenda;
    int hTb = 0;

    Venda venda = new Venda();
    Funcionario funcionario = new Funcionario();
    Pessoa cliente = new Pessoa();

    public VerVenda() {}

    public void janelaVerVenda(int vendaID){
        IRepositorioVenda vendas = new VendaCRUD();
        
        venda = vendas.obterVendaPorID(vendaID);
        criarJanela();
        criarMainPanel();
        criarCampoTexto();
        criarBotoes();
        adicionarTabela();
        verVenda.setContentPane(mainPanel);
    }

    private void criarJanela(){
        verVenda.setSize(1280,720);
        verVenda.setVisible(true);
        verVenda.setLocationRelativeTo(null);
        verVenda.setResizable(false);
        verVenda.setTitle("Venda N°" + venda.VendaID);
        verVenda.setIconImage(new ImageIcon(getClass().getResource("/imgs/key-fill.png")).getImage());
    }

    private void criarMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        titulo = new JLabel("Venda N°" + venda.VendaID);
        titulo.setFont(new Font("Verdana", Font.PLAIN, 20));
        titulo.setBounds(0,20,1250,50);
        titulo.setHorizontalAlignment(JLabel.CENTER);

        Color color = new Color(90,126,255);
        mainPanel.setBackground(color);

        mainPanel.add(titulo);
    }

    private void criarCampoTexto(){
        IRepositorioCliente Clientes = new ClienteCRUD();
        cliente = Clientes.obterClientePorID(venda.Cliente.PessoaID);

        IRepositorioFuncionario Funcionarios = new FuncionarioCRUD();
        funcionario = Funcionarios.obterFuncionarioPorID(venda.funcionario.PessoaID);
        
        if(cliente.Tipo.equals("Física")){ 
            textoCliente = new JTextField(cliente.Nome + " " +((ClienteFisico) cliente).getSobrenome());
        }else{textoCliente = new JTextField(cliente.Nome);}
        
        textoFuncionario = new JTextField(funcionario.Nome + " " + funcionario.Sobrenome);

        textoCliente.setBounds(80,100, 300,30);
        textoCliente.setEditable(false);
        textoCliente.setFont(new Font("Verdana", Font.BOLD, 12));
        textoCliente.setHorizontalAlignment(JTextField.CENTER);

        textoFuncionario.setBounds(890,100, 300,30);
        textoFuncionario.setEditable(false);
        textoFuncionario.setFont(new Font("Verdana", Font.BOLD, 12));
        textoFuncionario.setHorizontalAlignment(JTextField.CENTER);

        mainPanel.add(textoCliente);
        mainPanel.add(textoFuncionario);
    }

    private void criarBotoes(){
        btnFechar = new JButton("Fechar");

        btnFechar.setBounds(555,620 , 170,30);

        btnFechar.addActionListener(this);

        mainPanel.add(btnFechar);
    }

    private void adicionarTabela(){
        DefaultTableModel model = new DefaultTableModel();
        hTb = 0;
        Object [] o = new Object [5];
        String [] colunas = {"Código", "Descrição", "Preço Un", " Quantidade", "Total Item"};
        tabelaVenda = new JTable(model);
        model.setColumnIdentifiers(colunas);
        tabelaVenda.setBackground(Color.LIGHT_GRAY);  
        tabelaVenda.setFont(new Font("Verdana", Font.PLAIN, 14)); 
        
        for (ItemVenda IT : venda.getItens()) {
            
            o[0] = IT.produto.ProdutoID;
            o[1] = IT.produto.Descricao;
            o[2] = String.format("%.2f" ,IT.produto.PrecoVenda);
            o[3] = IT.Quantidade;
            o[4] = String.format("%.2f" ,IT.SubtotalItem);

            model.addRow(o);
            hTb++;
        } 
        o[0]= "";
        o[1]= "";   
        o[2]= "";
        o[3] = "Desconto R$";
        o[4] = String.format("%.2f" ,venda.Desconto);
        model.addRow(o);
        hTb++;
        
        o[0]= "";
        o[1]= "";
        o[2]= "";
        o[3] = "Total R$";
        o[4] = String.format("%.2f" ,venda.getTotal());
        model.addRow(o);
        hTb++;

        tabelaVenda.setDefaultEditor(Object.class, null);
        tabelaVenda.getTableHeader().setReorderingAllowed(false);
        tabelaVenda.getTableHeader().setResizingAllowed(false); 
        tabelaVenda.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        tabelaVenda.getColumnModel().getColumn(0).setMaxWidth(100);
        tabelaVenda.getColumnModel().getColumn(2).setMaxWidth(150);
        tabelaVenda.getColumnModel().getColumn(3).setMaxWidth(150);
        tabelaVenda.getColumnModel().getColumn(4).setMaxWidth(150);
        tabelaVenda.getColumnModel().getColumn(2).setMinWidth(150);
        tabelaVenda.getColumnModel().getColumn(3).setMinWidth(150);
        tabelaVenda.getColumnModel().getColumn(4).setMinWidth(150);
        
        for (int i = 0; i < colunas.length; i++) {
            tabelaVenda.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
       
        tabelaVenda.setRowHeight(25);
        hTb += 1;
        hTb *= 25;
        
        fundoTabelaVenda = new JScrollPane(tabelaVenda);
        if(hTb<=410){
            fundoTabelaVenda.setSize(1110, hTb);
        }else {fundoTabelaVenda.setSize(1110, 410);}
        
        fundoTabelaVenda.setLocation(80,230);
        fundoTabelaVenda.setVisible(true);
        fundoTabelaVenda.setOpaque(false);
        fundoTabelaVenda.getViewport().setOpaque(false);

        Color select = new Color(0,255,148);
        tabelaVenda.setSelectionBackground(select);
        
        mainPanel.add(fundoTabelaVenda);
        
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnFechar){
            verVenda.dispose();
        }
    }

}
