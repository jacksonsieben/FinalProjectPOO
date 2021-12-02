import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class NovoServicoPag implements ActionListener {
    JFrame novoServico = new JFrame();
    Pessoa pessoa;
    JTextField textoDescricao, textoPreco;
    JPanel mainPanel;
    JLabel titulo;
    JButton btnLimpar, btnCadastrar, btnAtualizar;

    ServicoPag pagServico;
    Servico servico;

    public NovoServicoPag(){}

    public void janelaCadastroServico(ServicoPag servicoPag){
        pagServico = servicoPag;
        criarJanela();
        criarMainPanel();
        criarCampoTexto();
        adicionarFocusListener();
        criarBotoesCadastrar();
        novoServico.setContentPane(mainPanel);
    }

    public void janelaAtualizarServico(int servicoID, ServicoPag servicoPag){
        pagServico = servicoPag;
        criarJanela();
        novoServico.setTitle("Atualizar Servico");
        criarMainPanel();
        criarCampoTexto();
        completarCampoTexto(servicoID);
        adicionarFocusListener();
        criarBotoesAtualizar();
        novoServico.setContentPane(mainPanel);
    }

    private void criarJanela() {
        novoServico.setSize(1280,720);
        novoServico.setVisible(true);
        novoServico.setLocationRelativeTo(null);
        novoServico.setResizable(false);
        novoServico.setTitle("Cadastro Serviço");
        novoServico.setIconImage(new ImageIcon(getClass().getResource("/imgs/key-fill.png")).getImage());
    }

    private void criarMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        Color color = new Color(90,126,255);
        mainPanel.setBackground(color);
    }

    private void criarCampoTexto(){
        titulo = new JLabel("Cadastro de Serviço");
        titulo.setFont(new Font("Verdana", Font.PLAIN, 28));
        titulo.setBounds(0, 50, 1280, 50);
        titulo.setHorizontalAlignment(JLabel.CENTER);

        textoDescricao = new JTextField("Descrição");
        textoPreco = new JTextField("Preço");

        textoDescricao.setBounds(80,150, 710,30);
        textoPreco.setBounds(900,150, 300,30);

        campoTextoVisibilidade(true);

        mainPanel.add(titulo);

        mainPanel.add(textoDescricao);
        mainPanel.add(textoPreco);
    }

    private void campoTextoVisibilidade(boolean visible) {
        textoDescricao.setVisible(visible);
        textoPreco.setVisible(visible);
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

    private void cadastrarServico() throws Exception{
        IRepositorioServico Servicos = new ServicoCRUD();
        
        substituirVirgula();
        verificarCampos();

        Servicos.inserirServico(textoDescricao.getText(), Float.parseFloat(textoPreco.getText().trim()));
        
        pagServico.dispose();
        pagServico.inicializar();
        novoServico.dispose();
    }

    private void atualizarServico() throws Exception{
        IRepositorioServico Servicos = new ServicoCRUD();

        substituirVirgula();
        verificarCampos();

        Servicos.atualizarServico(servico.ServicoID, textoDescricao.getText(), Float.parseFloat(textoPreco.getText().trim()));
        
        pagServico.dispose();
        pagServico.inicializar();
        novoServico.dispose();
    }

    private void substituirVirgula(){
        textoPreco.setText(textoPreco.getText().replace(",","."));
    }

    private void completarCampoTexto(int servicoID){
        IRepositorioServico Servicos = new ServicoCRUD();
        servico = Servicos.obterServicoPorID(servicoID);

        textoDescricao.setText(servico.Descricao);
        textoPreco.setText(String.format("%.2f", servico.Preco));        
    }

    private void verificarCampos() throws Exception{
        if(textoDescricao.getText().equals("Descrição")){
            throw new Exception("Descrição não pode ser vazia");
        }

        try{
            Float.parseFloat(textoPreco.getText().trim());
        }catch(Exception e){ throw new Exception("Preço Inválido");}
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
        criarFocusListener(textoDescricao);
        criarFocusListener(textoPreco);
    }
    
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource() == btnCadastrar){
                cadastrarServico();
            }else if(e.getSource() == btnLimpar){
                campoTextoVisibilidade(false);
                criarCampoTexto();
                criarBotoesCadastrar();
            }else if (e.getSource() == btnAtualizar){
                atualizarServico();
            }
        } catch (Exception ex) {
            ExcecaoPag excecao = new ExcecaoPag();
            excecao.inicializar(ex.getMessage());
        }
    }

}
