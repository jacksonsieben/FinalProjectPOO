import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

public class SelecionaClientePag implements ActionListener{
    JFrame selecionaCliente = new JFrame();
    JTextField textoCodigoCliente, textoNomeCliente, textoCpfCnpj;
    JPanel mainPanel;
    JLabel titulo;
    JButton btnLimparCliente, btnBuscarCliente, btnSalvarCliente, btnCancelarCliente;
    JScrollPane fundoTabelaCliente;
    JTable tabelaCliente;
    int hTb = 0;

    VendaPag vendaPag;
    Venda venda;

    OrdemServicoPag OsPag;
    OrdemServico ordemServico;

    public SelecionaClientePag(){}

    public void janelaSelecionarCliente(VendaPag pagVenda){
        vendaPag = pagVenda;
        criarJanelaSelecionarCliente();
        criarMainPanel();
        criarCampoTextoCliente();
        criarBotoesCliente();
        adicionarTabela();
        selecionaCliente.setContentPane(mainPanel);
    }

    public void janelaSelecionarClienteOS(OrdemServicoPag pagOS){
        OsPag = pagOS;
        criarJanelaSelecionarCliente();
        criarMainPanel();
        criarCampoTextoCliente();
        criarBotoesCliente();
        adicionarTabela();
        selecionaCliente.setContentPane(mainPanel);
    }

    private void criarJanelaSelecionarCliente() {
        selecionaCliente.setSize(854,520);
        selecionaCliente.setVisible(true);
        selecionaCliente.setLocationRelativeTo(null);
        selecionaCliente.setResizable(false);
        selecionaCliente.setTitle("Cliente");
        selecionaCliente.setIconImage(new ImageIcon(getClass().getResource("/imgs/key-fill.png")).getImage());
        
    }

    private void criarMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        titulo = new JLabel("Selecione o Cliente");
        titulo.setFont(new Font("Verdana", Font.PLAIN, 18));
        titulo.setBounds(0,10,834,50);
        titulo.setHorizontalAlignment(JLabel.CENTER);

        Color color = new Color(90,126,255);
        mainPanel.setBackground(color);

        mainPanel.add(titulo);
    }

    private void criarCampoTextoCliente(){
        textoCodigoCliente = new JTextField("Código");
        textoNomeCliente = new JTextField("Nome");
        textoCpfCnpj = new JTextField("CPF/CNPJ");

        textoCodigoCliente.setBounds(41,75, 200,30);
        textoNomeCliente.setBounds(318,75, 200,30);
        textoCpfCnpj.setBounds(595,75, 200,30);


        campoTextoClienteVisibilidade(true);

        adicionarFocusListener();

        mainPanel.add(textoCodigoCliente);
        mainPanel.add(textoNomeCliente);
        mainPanel.add(textoCpfCnpj);

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
        criarFocusListener(textoCodigoCliente);
        criarFocusListener(textoNomeCliente);
        criarFocusListener(textoCpfCnpj);
    }

    private void campoTextoClienteVisibilidade(boolean visible) {
        textoCodigoCliente.setVisible(visible);
        textoNomeCliente.setVisible(visible);
        textoCpfCnpj.setVisible(visible);        
    }

    private void criarBotoesCliente(){
        btnSalvarCliente = new JButton("Salvar");
        btnCancelarCliente = new JButton("Cancelar");
        
        btnBuscarCliente = new JButton("Buscar");
        btnLimparCliente = new JButton("Limpar");

        btnCancelarCliente.setBounds(277,435, 100,25);
        btnSalvarCliente.setBounds(477,435, 100,25);

        btnLimparCliente.setBounds(595,125, 75,25);
        btnBuscarCliente.setBounds(720,125, 75,25);

        btnLimparCliente.setBackground(Color.RED);
        btnBuscarCliente.setBackground(Color.GREEN);

        btnCancelarCliente.addActionListener(this);
        btnSalvarCliente.addActionListener(this);

        btnLimparCliente.addActionListener(this);
        btnBuscarCliente.addActionListener(this);

        mainPanel.add(btnCancelarCliente);
        mainPanel.add(btnSalvarCliente);

        mainPanel.add(btnLimparCliente);
        mainPanel.add(btnBuscarCliente);
        mainPanel.setVisible(true);
    }

    private void adicionarTabela(){
        IRepositorioCliente Clientes = new ClienteCRUD();
        DefaultTableModel model = new DefaultTableModel();
        hTb = 0;
        Object [] o = new Object [3];
        String [] colunas = {"Código", "Nome", "CPF/CNPJ"};
        tabelaCliente = new JTable(model);
        model.setColumnIdentifiers(colunas);
        tabelaCliente.setBackground(Color.LIGHT_GRAY);  
        tabelaCliente.setFont(new Font("Verdana", Font.PLAIN, 14));      
        
        for (Pessoa p : Clientes.obterClientes()) {
            
            o[0]= p.getPessoaID();
            if(p.Tipo.equals("Física")){ 
                o[1]= p.getNome() +  " " + ((ClienteFisico) p).getSobrenome();
                o[2]= ((ClienteFisico) p).getCPF();
            }else{
                o[1]= p.getNome();
                o[2] = ((ClienteJuridico) p).getCNPJ();
            }
            model.addRow(o);
            hTb++;
        }
        tabelaCliente.setDefaultEditor(Object.class, null);
        tabelaCliente.getTableHeader().setReorderingAllowed(false);
        tabelaCliente.getTableHeader().setResizingAllowed(false); 
        tabelaCliente.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        tabelaCliente.getColumnModel().getColumn(0).setMaxWidth(100);
        
        for (int i = 0; i < colunas.length; i++) {
            tabelaCliente.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        tabelaCliente.setRowHeight(25);
        hTb += 1;
        hTb *= 25;
        
        fundoTabelaCliente = new JScrollPane(tabelaCliente);
        if(hTb<=245){
            fundoTabelaCliente.setSize(754, hTb);
        }else {fundoTabelaCliente.setSize(754, 245);}
        
        fundoTabelaCliente.setLocation(41,170);
        fundoTabelaCliente.setVisible(true);

        Color select = new Color(0,255,148);
        tabelaCliente.setSelectionBackground(select);

        mainPanel.add(fundoTabelaCliente);
    }

    private void buscarTabelaClienteID(int codigoCliente){
        for (int i = 0; i < (hTb/25)-1; i++) {
            if(tabelaCliente.getValueAt(i,0).equals(codigoCliente)){
                tabelaCliente.setRowSelectionInterval(i, i);
            }
        }
    }

    private void buscarTabelaCliente(String nome, String cpfCnpj){
        for (int i = 0; i < (hTb/25)-1; i++) {
            if(tabelaCliente.getValueAt(i, 1).equals(nome)){
                tabelaCliente.setRowSelectionInterval(i, i);
            }else if(tabelaCliente.getValueAt(i, 1).toString().contains(nome)){
                tabelaCliente.setRowSelectionInterval(i, i);
            }
           
            if(tabelaCliente.getValueAt(i, 2).equals(cpfCnpj)){
                tabelaCliente.setRowSelectionInterval(i, i);
            }else if(tabelaCliente.getValueAt(i, 2).toString().contains(cpfCnpj)){
                tabelaCliente.setRowSelectionInterval(i, i);
            }
        }
    }

    private void selecionarCliente(int linhaCliente){
        IRepositorioCliente cliente = new ClienteCRUD();
        Pessoa pessoa = new Pessoa();
        NovaVendaPag novaVenda = new NovaVendaPag();
        
        int pessoaID = (int) tabelaCliente.getValueAt(linhaCliente, 0);

        pessoa = cliente.obterClientePorID(pessoaID);
        
        novaVenda.janelaCadastroVenda(vendaPag, pessoa);
        selecionaCliente.dispose();
    }

    private void selecionarClienteOS(int linhaCliente){
        IRepositorioCliente cliente = new ClienteCRUD();
        Pessoa pessoa = new Pessoa();
        NovaOrdemServicoPag novaOrdemServico = new NovaOrdemServicoPag();
        
        int pessoaID = (int) tabelaCliente.getValueAt(linhaCliente, 0);

        pessoa = cliente.obterClientePorID(pessoaID);
        
        novaOrdemServico.janelaCadastroOS(OsPag, pessoa);
        selecionaCliente.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        int linhaCliente = tabelaCliente.getSelectedRow();
        tabelaCliente.getSelectionModel().clearSelection();

        if(e.getSource() == btnBuscarCliente){
            if(!textoCodigoCliente.getText().equals("Código")){
                int codigoCliente = Integer.parseInt(textoCodigoCliente.getText().trim());
                buscarTabelaClienteID(codigoCliente);
            }if(!textoNomeCliente.getText().equals("Nome")){
                buscarTabelaCliente(textoNomeCliente.getText(), textoCpfCnpj.getText());
            }if(!textoCpfCnpj.getText().equals("CPF/CNPJ")){
                buscarTabelaCliente(textoNomeCliente.getText(), textoCpfCnpj.getText());
            }
        }else if(e.getSource() == btnLimparCliente){
            campoTextoClienteVisibilidade(false);
            criarCampoTextoCliente();
        }
        if(e.getSource() == btnCancelarCliente){
            selecionaCliente.dispose();
        }else if(e.getSource() == btnSalvarCliente){
            if(vendaPag == null){
                selecionarClienteOS(linhaCliente);
            }else{selecionarCliente(linhaCliente);}
            
        }
    }

}
