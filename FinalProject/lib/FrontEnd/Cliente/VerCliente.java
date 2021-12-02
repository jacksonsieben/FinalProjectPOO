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

public class VerCliente implements ActionListener{
    JFrame verCliente = new JFrame();
    JTextField textoCliente, textoCpfCnpj, textoTipo;
    JPanel mainPanel;
    JLabel titulo;
    JButton btnFechar, btnDeletarVeiculo;
    JScrollPane fundoTabelaCliente;
    JTable tabelaCliente;
    int hTb = 0;

    Pessoa Cliente = new Pessoa();
    Veiculo veiculo = new Veiculo();
    ClientePag clientePag;

    public VerCliente(){}

    public void janelaVerCliente(int pessoaID, ClientePag pagCliente){
        clientePag = pagCliente;
        
        IRepositorioCliente Clientes = new ClienteCRUD();
        
        Cliente = Clientes.obterClientePorID(pessoaID);
        criarJanela();
        criarMainPanel();
        criarCampoTexto();
        criarBotoes();
        adicionarTabela();
        verCliente.setContentPane(mainPanel);
    }

    private void criarJanela(){
        verCliente.setSize(1280,720);
        verCliente.setVisible(true);
        verCliente.setLocationRelativeTo(null);
        verCliente.setResizable(false);
        verCliente.setTitle("Cliente N°" + Cliente.PessoaID);
        verCliente.setIconImage(new ImageIcon(getClass().getResource("/imgs/key-fill.png")).getImage());
    }

    private void criarMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        titulo = new JLabel("Cliente N°" + Cliente.PessoaID);
        titulo.setFont(new Font("Verdana", Font.PLAIN, 20));
        titulo.setBounds(0,20,1250,50);
        titulo.setHorizontalAlignment(JLabel.CENTER);

        Color color = new Color(90,126,255);
        mainPanel.setBackground(color);

        mainPanel.add(titulo);
    }

    private void criarCampoTexto(){
        IRepositorioCliente Clientes = new ClienteCRUD();
        Cliente = Clientes.obterClientePorID(Cliente.PessoaID);
        
        if(Cliente.Tipo.equals("Física")){ 
            textoCliente = new JTextField(Cliente.Nome + " " +((ClienteFisico) Cliente).getSobrenome());
            textoCpfCnpj = new JTextField(((ClienteFisico) Cliente).CPF);
        }else{
            textoCliente = new JTextField(Cliente.Nome);
            textoCpfCnpj = new JTextField(((ClienteJuridico) Cliente).CNPJ);
        }

        textoTipo = new JTextField(Cliente.Tipo);

        textoTipo.setBounds(80,100, 300,30);
        textoTipo.setEditable(false);
        textoTipo.setFont(new Font("Verdana", Font.BOLD, 12));
        textoTipo.setHorizontalAlignment(JTextField.CENTER);

        textoCliente.setBounds(480,100, 300,30);
        textoCliente.setEditable(false);
        textoCliente.setFont(new Font("Verdana", Font.BOLD, 12));
        textoCliente.setHorizontalAlignment(JTextField.CENTER);

        textoCpfCnpj.setBounds(890,100, 300,30);
        textoCpfCnpj.setEditable(false);
        textoCpfCnpj.setFont(new Font("Verdana", Font.BOLD, 12));
        textoCpfCnpj.setHorizontalAlignment(JTextField.CENTER);

        mainPanel.add(textoCliente);
        mainPanel.add(textoTipo);
        mainPanel.add(textoCpfCnpj);
    }

    private void criarBotoes(){
        btnFechar = new JButton("Fechar");
        btnDeletarVeiculo = new JButton("Deletar");

        btnDeletarVeiculo.setBounds(1090,170 ,100,25);
        btnFechar.setBounds(555,620 , 170,30);

        btnDeletarVeiculo.setBackground(Color.RED);

        btnFechar.addActionListener(this);
        btnDeletarVeiculo.addActionListener(this);

        mainPanel.add(btnFechar);
        mainPanel.add(btnDeletarVeiculo);
    }

    private void adicionarTabela(){
        DefaultTableModel model = new DefaultTableModel();
        hTb = 0;
        Object [] o = new Object [6];
        String [] colunas = {"Código", "Marca", "Modelo", "Placa", "Ano", "Cor"};
        tabelaCliente = new JTable(model);
        model.setColumnIdentifiers(colunas);
        tabelaCliente.setBackground(Color.LIGHT_GRAY);  
        tabelaCliente.setFont(new Font("Verdana", Font.PLAIN, 14)); 

        IRepositorioVeiculo veiculos = new VeiculoCRUD();
        
        try {
            for (Veiculo v : veiculos.obterVeiculoPorCliente(Cliente.PessoaID)) {
                
                o[0] = v.VeiculoID;
                o[1] = v.Marca;
                o[2] = v.Modelo;
                o[3] = v.Placa;
                o[4] = v.Ano;
                o[5] = v.Cor;

                model.addRow(o);
                hTb++;
            }
        } catch (Exception e) {}

        tabelaCliente.setDefaultEditor(Object.class, null);
        tabelaCliente.getTableHeader().setReorderingAllowed(false);
        tabelaCliente.getTableHeader().setResizingAllowed(false); 
        tabelaCliente.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        tabelaCliente.getColumnModel().getColumn(0).setMaxWidth(100);
        tabelaCliente.getColumnModel().getColumn(2).setMaxWidth(150);
        tabelaCliente.getColumnModel().getColumn(3).setMaxWidth(150);
        tabelaCliente.getColumnModel().getColumn(4).setMaxWidth(150);
        tabelaCliente.getColumnModel().getColumn(2).setMinWidth(150);
        tabelaCliente.getColumnModel().getColumn(3).setMinWidth(150);
        tabelaCliente.getColumnModel().getColumn(4).setMinWidth(150);
        
        for (int i = 0; i < colunas.length; i++) {
            tabelaCliente.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
       
        tabelaCliente.setRowHeight(25);
        hTb += 1;
        hTb *= 25;
        
        fundoTabelaCliente = new JScrollPane(tabelaCliente);
        if(hTb<=410){
            fundoTabelaCliente.setSize(1110, hTb);
        }else {fundoTabelaCliente.setSize(1110, 410);}
        
        fundoTabelaCliente.setLocation(80,230);
        fundoTabelaCliente.setVisible(true);
        fundoTabelaCliente.setOpaque(false);
        fundoTabelaCliente.getViewport().setOpaque(false);

        Color select = new Color(0,255,148);
        tabelaCliente.setSelectionBackground(select);
        
        mainPanel.add(fundoTabelaCliente);
        
    }

    private void deletarVeiculo(){
        IRepositorioVeiculo Veiculos = new VeiculoCRUD();
        NotificacaoPag notificacao = new NotificacaoPag();

        int veiculoID = (int) tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 0);
        
        Veiculo v;
        try {
            v = Veiculos.obterVeiculoPorID(veiculoID);
            notificacao.deletarVeiculo(v, this);
        } catch (Exception e) {}
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnFechar){
            clientePag.dispose();
            clientePag.inicializar();
            verCliente.dispose();
        }
        if(e.getSource() == btnDeletarVeiculo){
            deletarVeiculo();
        }
    }
}
