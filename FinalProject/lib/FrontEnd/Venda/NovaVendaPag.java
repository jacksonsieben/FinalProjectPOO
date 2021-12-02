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

public class NovaVendaPag implements ActionListener{
    JFrame novaVenda = new JFrame();
    JTextField textoNomeCliente;
    JTextField textoCodigoProduto, textoDescricao, textoDesconto, textoPrecoUn, textoQuantidade;
    JPanel mainPanel;
    JLabel titulo;
    JButton btnDeletarProduto, btnEditarProduto, btnAdcProduto, btnCancelar, btnSalvar;
    JComboBox<Object> textoFuncionario, textoFormaPagamento;
    JScrollPane fundoTabelaVenda;
    JTable tabelaVenda;
    int hTb = 0;

    int[] codFuncionario;

    VendaPag vendaPag;
    Venda venda;
    Pessoa pessoa;
    Funcionario funcionario;

    public NovaVendaPag(){}

    public void janelaCadastroVenda(VendaPag pagVenda, Pessoa p){
        vendaPag = pagVenda;
        pessoa = p;
        venda = new Venda();
        try {
            venda.getVendaID();
            venda.setClienteID(p.PessoaID);
        }catch (Exception ex){System.out.println("Erro - " + ex.getMessage());}
        criarJanelaNovaVenda();
        criarMainPanel();
        criarCampoTexto();
        criarBotoes();
        adicionarTabela();
        novaVenda.setContentPane(mainPanel);
    }

    public void reinicializarTela(){
        criarJanelaNovaVenda();
        criarMainPanel();
        adicionarCampoTexto();
        criarBotoes();
        adicionarTabela();
        novaVenda.setContentPane(mainPanel);
    }

    private void criarJanelaNovaVenda() {
        novaVenda.setSize(1280,720);
        novaVenda.setVisible(true);
        novaVenda.setLocationRelativeTo(null);
        novaVenda.setResizable(false);
        novaVenda.setTitle("Nova Venda");
        novaVenda.setIconImage(new ImageIcon(getClass().getResource("/imgs/key-fill.png")).getImage());
    }

    private void criarMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        VendaCRUD v = new VendaCRUD();

        int vendaNum = v.getVendaID();

        titulo = new JLabel("Venda N°" + vendaNum);
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
                    fundoTabelaVenda.setVisible(false);
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
        btnDeletarProduto = new JButton("Deletar");
        btnEditarProduto = new JButton("Editar");

        btnAdcProduto = new JButton("Adicionar Produto");

        btnCancelar = new JButton("Cancelar");
        btnSalvar = new JButton("Salvar");

        btnDeletarProduto.setBounds(1090,170 ,100,25);
        btnEditarProduto.setBounds(890,170 , 100,25);

        btnAdcProduto.setBounds(890,100, 300,25);

        btnCancelar.setBounds(480,620, 170,30);
        btnSalvar.setBounds(660,620, 170,30);

        btnDeletarProduto.setBackground(Color.RED);
        btnEditarProduto.setBackground(Color.GREEN);

        btnCancelar.addActionListener(this);
        btnSalvar.addActionListener(this);

        btnSalvar.setEnabled(false);

        btnDeletarProduto.addActionListener(this);
        btnEditarProduto.addActionListener(this);
        btnAdcProduto.addActionListener(this);

        mainPanel.add(btnDeletarProduto);
        mainPanel.add(btnAdcProduto);
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
        tabelaVenda = new JTable(model);
        model.setColumnIdentifiers(colunas);
        tabelaVenda.setBackground(Color.LIGHT_GRAY);  
        tabelaVenda.setFont(new Font("Verdana", Font.PLAIN, 14)); 
        
        adicionarDesconto();
        
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
        o[3] = "Total R$";
        o[4] = String.format("%.2f" ,venda.getTotal());
        model.addRow(o);
        hTb++;

        if(hTb>1){
            btnSalvar.setEnabled(true);
        }

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

        fundoTabelaVenda.setVisible(true);
        
        mainPanel.add(fundoTabelaVenda);
        
    }

    private void selecionarFuncionario() throws Exception{
        int funcionarioID = codFuncionario[textoFuncionario.getSelectedIndex()];
        try{
            venda.setFuncionarioID(funcionarioID);
        }catch(Exception e){throw new Exception("Funcionário Inválido");}
        
    }

    private void deletarProduto(int linha){
        
        int produtoID = (int) tabelaVenda.getValueAt(linha, 0);
        int quantidade = (int) tabelaVenda.getValueAt(linha, 3);

        venda.deletarItem(produtoID, quantidade);
        
        novaVenda.dispose();
        reinicializarTela();
    }

    private void adicionarDesconto(){    
        if(!textoDesconto.getText().equals("Desconto")){
            if(textoDesconto.getText().contains(".")){
                textoDesconto.getText().replace(",",".");
            }
            
            venda.setDesconto(Float.parseFloat(textoDesconto.getText()));
        }
    }

    private void adicionarFormaPagamento() throws Exception {
        venda.setFormaPagamento((String) textoFormaPagamento.getSelectedItem());
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
        if(venda.getTotal() == 0){
            throw new Exception("Desconto Inválido");
        }
    } 

    private void salvarVenda() throws Exception{
        IRepositorioVenda Vendas = new VendaCRUD();
        verificarCampos();
        adicionarFormaPagamento();
        Vendas.inserirVenda(venda);

        novaVenda.dispose();
        vendaPag.dispose();
        vendaPag.inicializar();
    }

    public void actionPerformed(ActionEvent e) {
        int linha = tabelaVenda.getSelectedRow();
        tabelaVenda.getSelectionModel().clearSelection();
        try{
            adicionarDesconto();
            if(e.getSource() == btnCancelar){
                novaVenda.dispose();
            }
            if(e.getSource() == textoFuncionario){
                selecionarFuncionario();
            }
            if(e.getSource() == btnAdcProduto){
                AdicionarProdutoPag adicionarProduto = new AdicionarProdutoPag();
                adicionarProduto.janelaAdicionarProduto(this);
            }
            if(e.getSource() == btnDeletarProduto){
                deletarProduto(linha);
            }
            if(e.getSource() == textoDesconto){
                adicionarDesconto();
            }
            if(e.getSource() == btnSalvar){
                salvarVenda();
            }
        } catch (Exception ex) {
            ExcecaoPag excecao = new ExcecaoPag();
            excecao.inicializar(ex.getMessage());
        }
    }
}
