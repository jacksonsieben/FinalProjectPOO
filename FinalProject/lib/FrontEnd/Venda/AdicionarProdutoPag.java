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

public class AdicionarProdutoPag implements ActionListener{
    JFrame adicionarProduto = new JFrame();
    JTextField textoCodigo, textoDescricao, textoPreco, textoQuantidade, textoTotalItem;
    JPanel mainPanel;
    JLabel titulo;
    JButton btnLimparProduto, btnBuscarProduto, btnAdicionar, btnCancelar;
    JScrollPane fundoTabelaProduto;
    JTable tabelaProduto;
    int hTb = 0;

    NovaVendaPag novaVendaPag;
    Venda venda = new Venda();
    Produto produto = new Produto();

    public AdicionarProdutoPag(){}

    public void janelaAdicionarProduto(NovaVendaPag pagNovaVenda){
        novaVendaPag = pagNovaVenda;
        criarJanelaAdicionarProduto();
        criarMainPanel();
        criarCampoTexto();
        criarBotoes();
        adicionarTabela();
        adicionarProduto.setContentPane(mainPanel);

    }

    private void criarJanelaAdicionarProduto() {
        adicionarProduto.setSize(854,520);
        adicionarProduto.setVisible(true);
        adicionarProduto.setLocationRelativeTo(null);
        adicionarProduto.setResizable(false);
        adicionarProduto.setTitle("Produto");
        adicionarProduto.setIconImage(new ImageIcon(getClass().getResource("/imgs/key-fill.png")).getImage());
    }

    private void criarMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        titulo = new JLabel("Adicionar Produto");
        titulo.setFont(new Font("Verdana", Font.PLAIN, 18));
        titulo.setBounds(0,10,834,50);
        titulo.setHorizontalAlignment(JLabel.CENTER);

        Color color = new Color(90,126,255);
        
        mainPanel.setBackground(color);

        mainPanel.add(titulo);
    }

    private void criarCampoTexto(){
        textoCodigo = new JTextField("Código");
        textoDescricao = new JTextField("Descrição");
        textoQuantidade = new JTextField("Quantidade");

        textoPreco = new JTextField("Preço");
        textoTotalItem = new JTextField("Total");

        textoCodigo.setBounds(41,75, 200,30);
        textoDescricao.setBounds(318,75, 200,30);
        textoQuantidade.setBounds(595,75, 200,30);

        textoPreco.setBounds(41,120, 200,30);
        textoTotalItem.setBounds(318,120, 200,30);

        textoPreco.setEnabled(false);
        textoTotalItem.setEnabled(false);

        textoQuantidade.addActionListener(this);

        campoTextoVisibilidade(true);

        adicionarFocusListener();

        mainPanel.add(textoCodigo);
        mainPanel.add(textoDescricao);
        mainPanel.add(textoQuantidade);

        mainPanel.add(textoPreco);
        mainPanel.add(textoTotalItem);

    }

    private void campoTextoVisibilidade(boolean visible) {
        textoCodigo.setVisible(visible);
        textoDescricao.setVisible(visible);
        textoQuantidade.setVisible(visible);
        
        textoPreco.setVisible(visible);
        textoTotalItem.setVisible(visible);
    }

    private void criarBotoes(){
        btnAdicionar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        
        btnBuscarProduto = new JButton("Buscar");
        btnLimparProduto = new JButton("Limpar");

        btnCancelar.setBounds(277,435, 100,25);
        btnAdicionar.setBounds(477,435, 100,25);

        btnLimparProduto.setBounds(595,125, 75,25);
        btnBuscarProduto.setBounds(720,125, 75,25);

        btnLimparProduto.setBackground(Color.RED);
        btnBuscarProduto.setBackground(Color.GREEN);

        btnAdicionar.setEnabled(false);

        btnCancelar.addActionListener(this);
        btnAdicionar.addActionListener(this);

        btnLimparProduto.addActionListener(this);
        btnBuscarProduto.addActionListener(this);

        mainPanel.add(btnCancelar);
        mainPanel.add(btnAdicionar);

        mainPanel.add(btnLimparProduto);
        mainPanel.add(btnBuscarProduto);
        mainPanel.setVisible(true);
    }

    private void adicionarTabela(){
        IRepositorioProduto Produto = new ProdutoCRUD();
        DefaultTableModel model = new DefaultTableModel();
        hTb = 0;
        Object [] o = new Object [3];
        String [] colunas = {"Código", "Descrição", "Preço Un"};
        tabelaProduto = new JTable(model);
        model.setColumnIdentifiers(colunas);
        tabelaProduto.setBackground(Color.LIGHT_GRAY);  
        tabelaProduto.setFont(new Font("Verdana", Font.PLAIN, 14));      
        
        for (Produto p : Produto.obterProdutos()) {
            
            o[0] = p.getProdutoID();
            o[1] = p.getDescricao();
            o[2] = String.format("%.2f" ,p.getPrecoVenda());

            model.addRow(o);
            hTb++;
        }
        tabelaProduto.setDefaultEditor(Object.class, null);
        tabelaProduto.getTableHeader().setReorderingAllowed(false);
        tabelaProduto.getTableHeader().setResizingAllowed(false); 
        tabelaProduto.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        tabelaProduto.getColumnModel().getColumn(0).setMaxWidth(100);
        
        for (int i = 0; i < colunas.length; i++) {
            tabelaProduto.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        tabelaProduto.setRowHeight(25);
        hTb += 1;
        hTb *= 25;

        tabelaProduto.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
               if (me.getClickCount() == 1) {
                    completarCampoTexto();
                    if(tabelaProduto.getSelectedRowCount() > 0) {
                        btnAdicionar.setEnabled(true);
                    }
               }
            }
        });

        fundoTabelaProduto = new JScrollPane(tabelaProduto);
        if(hTb<=245){
            fundoTabelaProduto.setSize(754, hTb);
        }else {fundoTabelaProduto.setSize(754, 245);}
        
        fundoTabelaProduto.setLocation(41,170);
        fundoTabelaProduto.setVisible(true);

        Color select = new Color(0,255,148);
        tabelaProduto.setSelectionBackground(select);

        mainPanel.add(fundoTabelaProduto);
    }

    private void buscarTabelaID(int codigo){
        boolean linhaSelecionada = false;
        for (int i = 0; i < (hTb/25)-1; i++) {
            if(tabelaProduto.getValueAt(i,0).equals(codigo)){
                tabelaProduto.setRowSelectionInterval(i, i);
                completarCampoTexto();
                linhaSelecionada = true;
                btnAdicionar.setEnabled(true);
            }
        }
        if(!linhaSelecionada){
            tabelaProduto.getSelectionModel().clearSelection();
        }
        
    }

    private void buscarTabela(String descricao){

        for (int i = 0; i < (hTb/25)-1; i++) {
            
            if(((String)tabelaProduto.getValueAt(i, 1)).toLowerCase().contains(descricao.toLowerCase())){
                tabelaProduto.setRowSelectionInterval(i, i);
                completarCampoTexto();
            }
        }
        
    }

    private void completarCampoTexto(){
        IRepositorioProduto Produtos = new ProdutoCRUD();

        int produtoID = tabelaProduto.getSelectedRow();

        produto = Produtos.obterProdutoPorID(produtoID);

        textoPreco.setText(String.format("%.2f", produto.PrecoVenda));
        if(textoQuantidade.getText().equals("Quantidade")){
            textoQuantidade.setText("1");
        }
        float total = produto.PrecoVenda * Integer.parseInt(textoQuantidade.getText().trim());
        textoTotalItem.setText(String.format("%.2f", total));
        textoDescricao.setText(produto.Descricao);
        textoCodigo.setText(String.format("%d", produto.ProdutoID));
    }

    private void adicionarProduto() throws Exception{
        int linha = tabelaProduto.getSelectedRow();
        int produtoID = (int) tabelaProduto.getValueAt(linha, 0);
        
        
        int quantidade = Integer.parseInt(textoQuantidade.getText());

        verificarCampos();
        
        novaVendaPag.venda.novoItem(produtoID, quantidade);
    }

    private void verificarCampos() throws Exception{
       
        if(tabelaProduto.getSelectedRowCount() == 0) {
            throw new Exception("Selecione um produto");
        }
        try{
            Integer.parseInt(textoCodigo.getText());
        }catch(Exception e){throw new Exception("Código Inválido");}

        if(textoDescricao.getText().equals("Descrição")){
            throw new Exception("Descrição Inválida");
        }
        try{
            int quantidade = Integer.parseInt(textoQuantidade.getText());
            if(quantidade == 0){
                throw new Exception("Quantidade não pode ser zero");
            }
        }catch(Exception e){throw new Exception("Quantidade Inválida");}

        if(textoQuantidade.getText().equals("Quantidade")){
            throw new Exception("Quantidade Inválida");
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
        criarFocusListener(textoDescricao);
        criarFocusListener(textoQuantidade);
    }

    public void actionPerformed(ActionEvent e) {        
        try{
            if(e.getSource() == btnBuscarProduto){
                if(!textoCodigo.getText().equals("Código")){
                    int codigo = Integer.parseInt(textoCodigo.getText().trim());
                    buscarTabelaID(codigo);
                }
                if(!textoDescricao.getText().equals("Descrição")){
                    buscarTabela(textoDescricao.getText());
                }
            }
            if(e.getSource() == btnLimparProduto){
                tabelaProduto.getSelectionModel().clearSelection();
                campoTextoVisibilidade(false);
                criarCampoTexto();
                btnAdicionar.setEnabled(false);
            }
            if(e.getSource() == btnCancelar){
                adicionarProduto.dispose();
            }
            if(e.getSource() == textoQuantidade){
                completarCampoTexto();
            }
            if(e.getSource() == btnAdicionar){
                adicionarProduto();

                novaVendaPag.novaVenda.dispose();
                novaVendaPag.reinicializarTela();
                adicionarProduto.dispose();
            }
        } catch (Exception ex) {
            ExcecaoPag excecao = new ExcecaoPag();
            excecao.inicializar(ex.getMessage());
        }
    }

}
