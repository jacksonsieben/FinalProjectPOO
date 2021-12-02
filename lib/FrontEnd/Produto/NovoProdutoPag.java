import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NovoProdutoPag implements ActionListener {
    JFrame novoProduto = new JFrame();
    JTextField textoDescricao, textoCodigoBarras, textoPrecoCusto, textoPrecoVenda, textoQuantidadeEstoque;
    JPanel mainPanel;
    JLabel titulo;
    JButton btnLimpar, btnCadastrar, btnAtualizar;
    
    ProdutoPag PagProduto;
    Produto produto;

    public NovoProdutoPag(){}

    public void janelaCadastroProduto(ProdutoPag pagProduto){
        PagProduto = pagProduto;
        criarJanela();
        criarMainPanel();
        criarCampoTexto();
        criarBotoesCadastrar();
        adicionarFocusListener();
        novoProduto.setContentPane(mainPanel);
    }

    public void janelaAtualizarProduto(int produtoID, ProdutoPag pagProduto){
        PagProduto = pagProduto;
        criarJanela();
        novoProduto.setTitle("Atualizar Produto");
        criarMainPanel();
        criarCampoTexto();
        completarCampoTexto(produtoID);
        adicionarFocusListener();
        criarBotoesAtualizar();
        novoProduto.setContentPane(mainPanel);
    }

    private void criarJanela() {
        novoProduto.setSize(1280,720);
        novoProduto.setVisible(true);
        novoProduto.setLocationRelativeTo(null);
        novoProduto.setResizable(false);
        novoProduto.setTitle("Cadastro Produto");
        novoProduto.setIconImage(new ImageIcon(getClass().getResource("/imgs/key-fill.png")).getImage());
    }

    private void criarMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        Color color = new Color(90,126,255);
        mainPanel.setBackground(color);
    }

    private void criarCampoTexto(){
        titulo = new JLabel("Cadastro de Produto");
        titulo.setFont(new Font("Verdana", Font.PLAIN, 28));
        titulo.setBounds(0, 50, 1280, 50);
        titulo.setHorizontalAlignment(JLabel.CENTER);

        textoCodigoBarras = new JTextField("Código de Barras");
        textoDescricao = new JTextField("Descrição");
        
        textoPrecoCusto = new JTextField("Preço de Custo");
        textoPrecoVenda = new JTextField("Preço de Venda");
        textoQuantidadeEstoque = new JTextField("Quantidade em Estoque");

        textoCodigoBarras.setBounds(80,150, 300,30);
        textoDescricao.setBounds(490,150, 710,30);

        textoPrecoCusto.setBounds(80,250, 300,30);
        textoPrecoVenda.setBounds(490,250, 300,30);
        textoQuantidadeEstoque.setBounds(900,250, 300,30);

        campoTextoVisibilidade(true);

        mainPanel.add(titulo);

        mainPanel.add(textoCodigoBarras);
        mainPanel.add(textoDescricao);

        mainPanel.add(textoPrecoCusto);
        mainPanel.add(textoPrecoVenda);
        mainPanel.add(textoQuantidadeEstoque);
    }

    private void campoTextoVisibilidade(boolean visible) {
        textoCodigoBarras.setVisible(visible);
        textoDescricao.setVisible(visible);

        textoPrecoCusto.setVisible(visible);
        textoPrecoVenda.setVisible(visible);
        textoQuantidadeEstoque.setVisible(visible);        
    }

    private void criarBotoesCadastrar(){
        btnLimpar = new JButton("Limpar");
        btnCadastrar = new JButton("Cadastrar");

        btnLimpar.setBounds(490,600, 100,25);
        btnCadastrar.setBounds(690,600, 100,25);

        btnCadastrar.addActionListener(this);
        btnLimpar.addActionListener(this);

        mainPanel.add(btnLimpar);
        mainPanel.add(btnCadastrar);
    }

    private void criarBotoesAtualizar() {
        btnAtualizar = new JButton("Atualizar");

        btnAtualizar.setBounds(590,600, 100,25);

        btnAtualizar.addActionListener(this);

        mainPanel.add(btnAtualizar);
    }

    private void cadastrarProduto() throws Exception{
        IRepositorioProduto Produtos = new ProdutoCRUD();

        substituirVirgula();
        verificarCampos();

        Produtos.inserirProduto(textoDescricao.getText(), Long.parseLong(textoCodigoBarras.getText().trim()), Integer.parseInt(textoQuantidadeEstoque.getText().trim()), Float.parseFloat(textoPrecoCusto.getText().trim()), Float.parseFloat(textoPrecoVenda.getText().trim()));
        
        PagProduto.dispose();
        PagProduto.inicializar();
        novoProduto.dispose();
    }

    private void atualizarProduto() throws Exception {
        IRepositorioProduto Produtos = new ProdutoCRUD();
        
        substituirVirgula();
        verificarCampos();

        Produtos.atualizarProduto(produto.ProdutoID, textoDescricao.getText(), Long.parseLong(textoCodigoBarras.getText().trim()), Integer.parseInt(textoQuantidadeEstoque.getText().trim()), Float.parseFloat(textoPrecoCusto.getText().trim()), Float.parseFloat(textoPrecoVenda.getText().trim()));
        
        PagProduto.dispose();
        PagProduto.inicializar();
        novoProduto.dispose();
    }

    private void completarCampoTexto(int produtoID){
        IRepositorioProduto Produtos = new ProdutoCRUD();
        produto = Produtos.obterProdutoPorID(produtoID);

        textoCodigoBarras.setText(String.format("%d", produto.CodigoBarras));
        textoDescricao.setText(produto.Descricao);
        
        textoPrecoCusto.setText(String.format("%.2f", produto.PrecoCusto));
        textoPrecoVenda.setText(String.format("%.2f", produto.PrecoVenda));
        textoQuantidadeEstoque.setText(String.format("%d", produto.QuantidadeEstoque));
        
    }

    private void verificarCampos() throws Exception{
        try{
            Long.parseLong(textoCodigoBarras.getText().trim());
        }catch(Exception e){ throw new Exception("Código de Barras Inválido");}

        if(textoDescricao.getText().equals("Descrição")){
            throw new Exception("Descrição não pode ser vazia");
        }

        try{
            Float.parseFloat(textoPrecoCusto.getText().trim());
        }catch(Exception e){ throw new Exception("Preço de Custo Inválido");}

        try{
            Float.parseFloat(textoPrecoVenda.getText().trim());
        }catch(Exception e){ throw new Exception("Preço de Venda Inválido");}

        try{
            Integer.parseInt(textoQuantidadeEstoque.getText().trim());
        }catch(Exception e){ throw new Exception("Quantidade em Estoque Inválido");}
    }

    private void substituirVirgula(){
        textoPrecoCusto.setText(textoPrecoCusto.getText().replace(",","."));
        textoPrecoVenda.setText(textoPrecoVenda.getText().replace(",","."));
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
        criarFocusListener(textoCodigoBarras);
        criarFocusListener(textoDescricao);
        criarFocusListener(textoPrecoVenda);
        criarFocusListener(textoPrecoCusto);
        criarFocusListener(textoQuantidadeEstoque);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnLimpar){
            campoTextoVisibilidade(false);
            criarCampoTexto();
        }
        try{
            if(e.getSource() == btnCadastrar){
                cadastrarProduto();
            }else if (e.getSource() == btnAtualizar){
                atualizarProduto();
            }
        } catch (Exception ex) {
            ExcecaoPag excecao = new ExcecaoPag();
            excecao.inicializar(ex.getMessage());
        }
        
    }

}
