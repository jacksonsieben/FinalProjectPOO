import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class NovaOrdemServicoPag implements ActionListener{
    JFrame novaOS = new JFrame();
    JTextField textoNomeCliente;
    JTextField textoCodigoServico, textoDescricao, textoDesconto, textoPrecoUn, textoQuantidade;
    JPanel mainPanel;
    JLabel titulo;
    JButton btnDeletarServico, btnEditarServico, btnAdcServico, btnCancelar, btnSalvar;
    JComboBox<Object> textoFuncionario, textoFormaPagamento;
    JScrollPane fundoTabelaOS;
    JTable tabelaOS;
    int hTb = 0;

    int[] codFuncionario;

    OrdemServicoPag OsPag;
    OrdemServico OS;
    Pessoa pessoa;
    Funcionario funcionario;

    public NovaOrdemServicoPag(){}

    public void janelaCadastroOS(OrdemServicoPag pagOS, Pessoa p){
        OsPag = pagOS;
        pessoa = p;
        OS = new OrdemServico();
        try {
            OS.getOrdemServicoID();
            OS.setClienteID(p.PessoaID);
        }catch (Exception ex){System.out.println("Erro - " + ex.getMessage());}
        criarJanelaNovaOS();
        criarMainPanel();
        criarCampoTexto();
        criarBotoes();
        adicionarTabela();
        novaOS.setContentPane(mainPanel);
    }

    public void reinicializarTela(){
        criarJanelaNovaOS();
        criarMainPanel();
        adicionarCampoTexto();
        criarBotoes();
        adicionarTabela();
        novaOS.setContentPane(mainPanel);
    }

    private void criarJanelaNovaOS() {
        novaOS.setSize(1280,720);
        novaOS.setVisible(true);
        novaOS.setLocationRelativeTo(null);
        novaOS.setResizable(false);
        novaOS.setTitle("Nova Ordem de Serviço");
        novaOS.setIconImage(new ImageIcon(getClass().getResource("/imgs/key-fill.png")).getImage());
    }

    private void criarMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        OrdemServicoCRUD os = new OrdemServicoCRUD();

        int OSnum = os.getOrdemServicoID();

        titulo = new JLabel("Ordem de Serviço N°" + OSnum);
        titulo.setFont(new Font("Verdana", Font.PLAIN, 20));
        titulo.setBounds(0,20,1250,50);
        titulo.setHorizontalAlignment(JLabel.CENTER);

        Color color = new Color(90,126,255);
        mainPanel.setBackground(color);

        mainPanel.add(titulo);
    }

    private void criarComboBox(){
        IRepositorioFuncionario Funcionarios = new FuncionarioCRUD();
    
        Object [] opcaoFuncionarios = new Object[Funcionarios.obterFuncionarios().size()+1];
        codFuncionario = new int[Funcionarios.obterFuncionarios().size()+1]; 
        Object[] opcaoFormaPagamento = {"À Vista", "À Prazo"};
        int i = 1;

        opcaoFuncionarios[0] = "Funcionários";
        
        for (Pessoa p : Funcionarios.obterFuncionarios()) {
            opcaoFuncionarios[i] = p.Nome + " " + ((Funcionario)p).Sobrenome;
            codFuncionario[i++] = p.PessoaID;
        }

        textoFuncionario = new JComboBox<>(opcaoFuncionarios);
        textoFuncionario.setSelectedIndex(0);
        textoFuncionario.addActionListener(this);

        textoFormaPagamento = new JComboBox<>(opcaoFormaPagamento);
        textoFormaPagamento.setSelectedIndex(0);


        textoFuncionario.setBounds(480,100, 300,30);
        textoFormaPagamento.setBounds(480,170, 300,30);

        mainPanel.add(textoFuncionario);
        mainPanel.add(textoFormaPagamento);
    }
    
    private void criarCampoTexto(){
        if(pessoa.Tipo.equals("Física")){ 
            textoNomeCliente = new JTextField(pessoa.Nome + " " +((ClienteFisico) pessoa).getSobrenome());
        }else{textoNomeCliente = new JTextField(pessoa.Nome);}
        
        textoDesconto = new JTextField("Desconto");

        criarComboBox();

        textoNomeCliente.setBounds(80,100, 300,30);
        textoNomeCliente.setEditable(false);
        textoNomeCliente.setFont(new Font("Verdana", Font.BOLD, 12));
        textoNomeCliente.setHorizontalAlignment(JTextField.CENTER);

        textoDesconto.setBounds(80,170, 300,30);
        textoDesconto.addActionListener(this);
        textoDesconto.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e){
                limparTexto(textoDesconto);
            }
            public void focusLost(FocusEvent e){
                if(textoDesconto.getText().equals("")){
                    textoDesconto.setText("0");
                }else{
                    adicionarDesconto();
                    fundoTabelaOS.setVisible(false);
                    adicionarTabela();
                }
            }
        });

        mainPanel.add(textoNomeCliente);
        mainPanel.add(textoDesconto);
    }

    private void limparTexto(JTextField texto){
        texto.setText("");
    }

    private void criarBotoes(){
        btnDeletarServico = new JButton("Deletar");
        btnEditarServico = new JButton("Editar");

        btnAdcServico = new JButton("Adicionar Servico");

        btnCancelar = new JButton("Cancelar");
        btnSalvar = new JButton("Salvar");

        btnDeletarServico.setBounds(1090,170 ,100,25);
        btnEditarServico.setBounds(890,170 , 100,25);

        btnAdcServico.setBounds(890,100, 300,25);

        btnCancelar.setBounds(480,620, 170,30);
        btnSalvar.setBounds(660,620, 170,30);

        btnDeletarServico.setBackground(Color.RED);
        btnEditarServico.setBackground(Color.GREEN);

        btnCancelar.addActionListener(this);
        btnSalvar.addActionListener(this);

        btnSalvar.setEnabled(false);

        btnDeletarServico.addActionListener(this);
        btnEditarServico.addActionListener(this);
        btnAdcServico.addActionListener(this);

        mainPanel.add(btnDeletarServico);
        mainPanel.add(btnAdcServico);
        mainPanel.add(btnCancelar);
        mainPanel.add(btnSalvar);
    }

    private void adicionarCampoTexto(){
        mainPanel.add(textoNomeCliente);
        mainPanel.add(textoDesconto);
        mainPanel.add(textoFuncionario);
        mainPanel.add(textoFormaPagamento);
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
        
        adicionarDesconto();
        
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
        o[3] = "Total R$";
        o[4] = String.format("%.2f" ,OS.getTotal());
        model.addRow(o);
        hTb++;

        if(hTb>1){
            btnSalvar.setEnabled(true);
        }

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

    private void selecionarFuncionario(){
        int funcionarioID = codFuncionario[textoFuncionario.getSelectedIndex()];
        try{
            OS.setFuncionarioID(funcionarioID);
        }catch(Exception e){System.out.println("Erro - " + e.getMessage());}
        
    }

    private void deletarServico(int linha){
        
        int servicoID = (int) tabelaOS.getValueAt(linha, 0);
        int quantidade = (int) tabelaOS.getValueAt(linha, 3);

        OS.deletarItem(servicoID, quantidade);
        
        novaOS.dispose();
        reinicializarTela();
    }

    private void adicionarDesconto(){    
        if(!textoDesconto.getText().equals("Desconto")){
            if(textoDesconto.getText().contains(".")){
                textoDesconto.getText().replace(",",".");
            }
            OS.setDesconto(Float.parseFloat(textoDesconto.getText()));
        }
    }

    private void adicionarFormaPagamento() throws Exception{
        OS.setFormaPagamento((String) textoFormaPagamento.getSelectedItem());
    }

    private void verificarCampos() throws Exception{
        if (textoFuncionario.getSelectedIndex() == 0){
            throw new Exception("Selecione o Funcionário");
        }
        try{
            if(!textoDesconto.getText().equals("Desconto")){
                Float.parseFloat(textoDesconto.getText());
            }
        }catch(Exception e){ throw new Exception("Desconto Inválido");}
        if(OS.getTotal() == 0){
            throw new Exception("Desconto Inválido");
        }
    } 

    private void salvarOS() throws Exception{
        IRepositorioOrdemServico OrdemServicos = new OrdemServicoCRUD();
        verificarCampos();
        adicionarFormaPagamento();

        OrdemServicos.inserirOrdemServico(OS);

        novaOS.dispose();
        OsPag.dispose();
        OsPag.inicializar();
    }

    public void actionPerformed(ActionEvent e) {
        int linha = tabelaOS.getSelectedRow();
        tabelaOS.getSelectionModel().clearSelection();
        try{
            adicionarDesconto();
            if(e.getSource() == btnCancelar){
                novaOS.dispose();
            }
            if(e.getSource() == textoFuncionario){
                selecionarFuncionario();
            }
            if(e.getSource() == btnAdcServico){
                AdicionarServicoPag adicionarServico = new AdicionarServicoPag();
                adicionarServico.janelaAdicionarServico(this);
            }
            if(e.getSource() == btnDeletarServico){
                deletarServico(linha);
            }
            if(e.getSource() == textoDesconto){
                adicionarDesconto();
            }
            if(e.getSource() == btnSalvar){
                salvarOS();
            }
        } catch (Exception ex) {
            ExcecaoPag excecao = new ExcecaoPag();
            excecao.inicializar(ex.getMessage());
        }
    }
}
