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

public class VerOrdemServico implements ActionListener{
    JFrame verOrdemServico = new JFrame();
    JTextField textoCliente, textoFuncionario;
    JPanel mainPanel;
    JLabel titulo;
    JButton btnFechar;
    JScrollPane fundoTabelaOS;
    JTable tabelaOS;
    int hTb = 0;

    OrdemServico OS = new OrdemServico();
    Funcionario funcionario = new Funcionario();
    Pessoa cliente = new Pessoa();

    public VerOrdemServico(){}

    public void janelaVerOrdemServico(int osID){
        IRepositorioOrdemServico ordemServicos = new OrdemServicoCRUD();

        OS = ordemServicos.obterOrdemServicoPorID(osID);
        criarJanela();
        criarMainPanel();
        criarCampoTexto();
        criarBotoes();
        adicionarTabela();
        verOrdemServico.setContentPane(mainPanel);
    }

    private void criarJanela(){
        verOrdemServico.setSize(1280,720);
        verOrdemServico.setVisible(true);
        verOrdemServico.setLocationRelativeTo(null);
        verOrdemServico.setResizable(false);
        verOrdemServico.setTitle("Ordem de Serviço N°" + OS.OrdemServicoID);
        verOrdemServico.setIconImage(new ImageIcon(getClass().getResource("/imgs/key-fill.png")).getImage());
    }

    private void criarMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        titulo = new JLabel("Ordem de Serviço N°" + OS.OrdemServicoID);
        titulo.setFont(new Font("Verdana", Font.PLAIN, 20));
        titulo.setBounds(0,20,1250,50);
        titulo.setHorizontalAlignment(JLabel.CENTER);

        Color color = new Color(90,126,255);
        mainPanel.setBackground(color);

        mainPanel.add(titulo);
    }

    private void criarCampoTexto(){
        IRepositorioCliente Clientes = new ClienteCRUD();
        cliente = Clientes.obterClientePorID(OS.Cliente.PessoaID);

        IRepositorioFuncionario Funcionarios = new FuncionarioCRUD();
        funcionario = Funcionarios.obterFuncionarioPorID(OS.funcionario.PessoaID);
        
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
        tabelaOS = new JTable(model);
        model.setColumnIdentifiers(colunas);
        tabelaOS.setBackground(Color.LIGHT_GRAY);  
        tabelaOS.setFont(new Font("Verdana", Font.PLAIN, 14)); 
        
        for (ItemServico IT : OS.getItens()) {
            
            o[0] = IT.servico.ServicoID;
            o[1] = IT.servico.Descricao;
            o[2] = String.format("%.2f" ,IT.servico.Preco);
            o[3] = IT.Quantidade;
            o[4] = String.format("%.2f" ,IT.SubtotalItem);

            model.addRow(o);
            hTb++;
        } 
        o[0]= "";
        o[1]= "";
        o[2]= "";
        o[3] = "Desconto R$";
        o[4] = String.format("%.2f" ,OS.Desconto);
        model.addRow(o);
        hTb++;
        
        o[0]= "";
        o[1]= "";
        o[2]= "";
        o[3] = "Total R$";
        o[4] = String.format("%.2f" ,OS.getTotal());
        model.addRow(o);
        hTb++;

        tabelaOS.setDefaultEditor(Object.class, null);
        tabelaOS.getTableHeader().setReorderingAllowed(false);
        tabelaOS.getTableHeader().setResizingAllowed(false); 
        tabelaOS.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        tabelaOS.getColumnModel().getColumn(0).setMaxWidth(100);
        tabelaOS.getColumnModel().getColumn(2).setMaxWidth(150);
        tabelaOS.getColumnModel().getColumn(3).setMaxWidth(150);
        tabelaOS.getColumnModel().getColumn(4).setMaxWidth(150);
        tabelaOS.getColumnModel().getColumn(2).setMinWidth(150);
        tabelaOS.getColumnModel().getColumn(3).setMinWidth(150);
        tabelaOS.getColumnModel().getColumn(4).setMinWidth(150);
        
        for (int i = 0; i < colunas.length; i++) {
            tabelaOS.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
       
        tabelaOS.setRowHeight(25);
        hTb += 1;
        hTb *= 25;
        
        fundoTabelaOS = new JScrollPane(tabelaOS);
        if(hTb<=410){
            fundoTabelaOS.setSize(1110, hTb);
        }else {fundoTabelaOS.setSize(1110, 410);}
        
        fundoTabelaOS.setLocation(80,230);
        fundoTabelaOS.setVisible(true);
        fundoTabelaOS.setOpaque(false);
        fundoTabelaOS.getViewport().setOpaque(false);

        Color select = new Color(0,255,148);
        tabelaOS.setSelectionBackground(select);
        
        mainPanel.add(fundoTabelaOS);
        
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnFechar){
            verOrdemServico.dispose();
        }
    }

}
