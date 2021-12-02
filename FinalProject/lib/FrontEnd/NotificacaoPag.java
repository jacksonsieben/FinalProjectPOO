import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotificacaoPag implements ActionListener {

    JButton btnConfirmarCliente, btnCancelarCliente; 
    JButton btnConfirmarFuncionario, btnCancelarFuncionario;
    JButton btnConfirmarProduto, btnCancelarProduto;
    JButton btnConfirmarServico, btnCancelarServico;
    JButton btnConfirmarVenda, btnCancelarVenda;
    JButton btnConfirmarVeiculo, btnCancelarVeiculo;
    JButton btnConfirmarOS, btnCancelarOS;
    JLabel textoNotificacao, textoPessoa;
    JFrame frame;
    JPanel mainPanel;

    Produto produto;
    Pessoa pessoa;
    Servico servico;
    Venda venda;
    OrdemServico ordemServico;
    Veiculo veiculo;

    boolean fim = true;

    ClientePag pagCliente;
    FuncionarioPag pagFuncionario;
    ProdutoPag pagProduto;
    ServicoPag pagServico;
    VendaPag pagVenda;
    OrdemServicoPag pagOrdemServico;
    VerCliente verCliente;
    

    public NotificacaoPag() {}

    private void criarJanela() {
        frame = new JFrame();
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Notificação");
        frame.setVisible(true);
        frame.setIconImage(new ImageIcon(getClass().getResource("/imgs/delete.png")).getImage());
    }

    public boolean deletarClientePag(Pessoa p, ClientePag cliente) {
        criarJanela();
        pagCliente = cliente;

        pessoa = p;

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        btnConfirmarCliente = new JButton("Confirmar");
        btnCancelarCliente = new JButton("Cancelar");

        textoNotificacao = new JLabel("Deseja deletar o cliente");
        textoPessoa = new JLabel(p.PessoaID + " - " + p.Nome + "?");
        textoNotificacao.setHorizontalAlignment(JLabel.CENTER);
        textoPessoa.setHorizontalAlignment(JLabel.CENTER);

        btnCancelarCliente.setBounds(50, 150, 100, 25);
        btnConfirmarCliente.setBounds(230, 150, 100, 25);

        textoNotificacao.setFont(new Font("Verdana", Font.PLAIN, 24));
        textoPessoa.setFont(new Font("Verdana", Font.PLAIN, 24));

        textoNotificacao.setBounds(0, 20, 390, 50);
        textoPessoa.setBounds(0, 50, 390, 50);

        btnConfirmarCliente.addActionListener(this);
        btnCancelarCliente.addActionListener(this);

        mainPanel.add(textoNotificacao);
        mainPanel.add(textoPessoa);
        mainPanel.add(btnConfirmarCliente);
        mainPanel.add(btnCancelarCliente);

        mainPanel.setOpaque(true);

        frame.setContentPane(mainPanel);

        return fim;
    }

    public void deletarFuncionarioPag(Pessoa p, FuncionarioPag funcionario) {
        criarJanela();
        pagFuncionario = funcionario;

        pessoa = p;

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        btnConfirmarFuncionario = new JButton("Confirmar");
        btnCancelarFuncionario = new JButton("Cancelar");

        textoNotificacao = new JLabel("Deseja deletar o funcionário");
        textoPessoa = new JLabel(p.PessoaID + " - " + p.Nome + "?");
        textoNotificacao.setHorizontalAlignment(JLabel.CENTER);
        textoPessoa.setHorizontalAlignment(JLabel.CENTER);

        btnCancelarFuncionario.setBounds(50, 150, 100, 25);
        btnConfirmarFuncionario.setBounds(230, 150, 100, 25);

        textoNotificacao.setFont(new Font("Verdana", Font.PLAIN, 24));
        textoPessoa.setFont(new Font("Verdana", Font.PLAIN, 24));

        textoNotificacao.setBounds(0, 20, 390, 50);
        textoPessoa.setBounds(0, 50, 390, 50);

        btnConfirmarFuncionario.addActionListener(this);
        btnCancelarFuncionario.addActionListener(this);

        mainPanel.add(textoNotificacao);
        mainPanel.add(textoPessoa);
        mainPanel.add(btnConfirmarFuncionario);
        mainPanel.add(btnCancelarFuncionario);

        mainPanel.setOpaque(true);

        frame.setContentPane(mainPanel);
    }

    public void deletarProdutoPag(Produto produtos, ProdutoPag produtoPag){
        criarJanela();
        pagProduto = produtoPag;

        produto = produtos;

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        btnConfirmarProduto = new JButton("Confirmar");
        btnCancelarProduto = new JButton("Cancelar");

        textoNotificacao = new JLabel("Deseja deletar o produto");
        textoPessoa = new JLabel(produtos.ProdutoID + " - " + produtos.Descricao + "?");
        textoNotificacao.setHorizontalAlignment(JLabel.CENTER);
        textoPessoa.setHorizontalAlignment(JLabel.CENTER);

        btnCancelarProduto.setBounds(50, 150, 100, 25);
        btnConfirmarProduto.setBounds(230, 150, 100, 25);

        textoNotificacao.setFont(new Font("Verdana", Font.PLAIN, 24));
        textoPessoa.setFont(new Font("Verdana", Font.PLAIN, 24));

        textoNotificacao.setBounds(0, 20, 390, 50);
        textoPessoa.setBounds(0, 50, 390, 50);

        btnConfirmarProduto.addActionListener(this);
        btnCancelarProduto.addActionListener(this);

        mainPanel.add(textoNotificacao);
        mainPanel.add(textoPessoa);
        mainPanel.add(btnConfirmarProduto);
        mainPanel.add(btnCancelarProduto);

        mainPanel.setOpaque(true);

        frame.setContentPane(mainPanel);
    }

    public void deletarServicoPag(Servico servicos, ServicoPag servicoPag){
        criarJanela();
        pagServico = servicoPag;

        servico = servicos;

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        btnConfirmarServico = new JButton("Confirmar");
        btnCancelarServico = new JButton("Cancelar");

        textoNotificacao = new JLabel("Deseja deletar o serviço");
        textoPessoa = new JLabel(servicos.ServicoID + " - " + servicos.Descricao + "?");
        textoNotificacao.setHorizontalAlignment(JLabel.CENTER);
        textoPessoa.setHorizontalAlignment(JLabel.CENTER);

        btnCancelarServico.setBounds(50, 150, 100, 25);
        btnConfirmarServico.setBounds(230, 150, 100, 25);

        textoNotificacao.setFont(new Font("Verdana", Font.PLAIN, 24));
        textoPessoa.setFont(new Font("Verdana", Font.PLAIN, 24));

        textoNotificacao.setBounds(0, 20, 390, 50);
        textoPessoa.setBounds(0, 50, 390, 50);

        btnConfirmarServico.addActionListener(this);
        btnCancelarServico.addActionListener(this);

        mainPanel.add(textoNotificacao);
        mainPanel.add(textoPessoa);
        mainPanel.add(btnConfirmarServico);
        mainPanel.add(btnCancelarServico);

        mainPanel.setOpaque(true);

        frame.setContentPane(mainPanel);
    }

    public void deletarVendaPag(Venda vendas, VendaPag vendaPag){
        criarJanela();
        pagVenda = vendaPag;

        venda = vendas;

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        btnConfirmarVenda = new JButton("Confirmar");
        btnCancelarVenda = new JButton("Cancelar");

        textoNotificacao = new JLabel("Deseja deletar a venda");
        textoPessoa = new JLabel(vendas.VendaID + " - Total R$" + vendas.Total + "?");
        textoNotificacao.setHorizontalAlignment(JLabel.CENTER);
        textoPessoa.setHorizontalAlignment(JLabel.CENTER);

        btnCancelarVenda.setBounds(50, 150, 100, 25);
        btnConfirmarVenda.setBounds(230, 150, 100, 25);

        textoNotificacao.setFont(new Font("Verdana", Font.PLAIN, 24));
        textoPessoa.setFont(new Font("Verdana", Font.PLAIN, 24));

        textoNotificacao.setBounds(0, 20, 390, 50);
        textoPessoa.setBounds(0, 50, 390, 50);

        btnConfirmarVenda.addActionListener(this);
        btnCancelarVenda.addActionListener(this);

        mainPanel.add(textoNotificacao);
        mainPanel.add(textoPessoa);
        mainPanel.add(btnConfirmarVenda);
        mainPanel.add(btnCancelarVenda);

        mainPanel.setOpaque(true);

        frame.setContentPane(mainPanel);
    }

    public void deletarOrdemServicoPag(OrdemServico os, OrdemServicoPag osPag){
        criarJanela();
        pagOrdemServico = osPag;

        ordemServico = os;

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        btnConfirmarOS = new JButton("Confirmar");
        btnCancelarOS = new JButton("Cancelar");

        textoNotificacao = new JLabel("Deseja deletar a OS");
        textoPessoa = new JLabel(ordemServico.OrdemServicoID  + " - Total R$" + ordemServico.Total + "?");
        textoNotificacao.setHorizontalAlignment(JLabel.CENTER);
        textoPessoa.setHorizontalAlignment(JLabel.CENTER);

        btnCancelarOS.setBounds(50, 150, 100, 25);
        btnConfirmarOS.setBounds(230, 150, 100, 25);

        textoNotificacao.setFont(new Font("Verdana", Font.PLAIN, 24));
        textoPessoa.setFont(new Font("Verdana", Font.PLAIN, 24));

        textoNotificacao.setBounds(0, 20, 390, 50);
        textoPessoa.setBounds(0, 50, 390, 50);

        btnConfirmarOS.addActionListener(this);
        btnCancelarOS.addActionListener(this);

        mainPanel.add(textoNotificacao);
        mainPanel.add(textoPessoa);
        mainPanel.add(btnConfirmarOS);
        mainPanel.add(btnCancelarOS);

        mainPanel.setOpaque(true);

        frame.setContentPane(mainPanel);
    }

    public void deletarVeiculo(Veiculo v, VerCliente vCliente){
        criarJanela();
        verCliente = vCliente;

        veiculo = v;

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        btnConfirmarVeiculo = new JButton("Confirmar");
        btnCancelarVeiculo = new JButton("Cancelar");

        textoNotificacao = new JLabel("Deseja deletar o veiculo");
        textoPessoa = new JLabel(veiculo.VeiculoID + " - " + veiculo.Marca + " " + veiculo.Modelo + "?");
        textoNotificacao.setHorizontalAlignment(JLabel.CENTER);
        textoPessoa.setHorizontalAlignment(JLabel.CENTER);

        btnCancelarVeiculo.setBounds(50, 150, 100, 25); 
        btnConfirmarVeiculo.setBounds(230, 150, 100, 25);

        textoNotificacao.setFont(new Font("Verdana", Font.PLAIN, 24));
        textoPessoa.setFont(new Font("Verdana", Font.PLAIN, 24));

        textoNotificacao.setBounds(0, 20, 390, 50);
        textoPessoa.setBounds(0, 50, 390, 50);

        btnConfirmarVeiculo.addActionListener(this);
        btnCancelarVeiculo.addActionListener(this);

        mainPanel.add(textoNotificacao);
        mainPanel.add(textoPessoa);
        mainPanel.add(btnConfirmarVeiculo);
        mainPanel.add(btnCancelarVeiculo);

        mainPanel.setOpaque(true);

        frame.setContentPane(mainPanel);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnCancelarCliente) {
            pagCliente.dispose();
            pagCliente.inicializar();
            frame.dispose();
        } else if (e.getSource() == btnConfirmarCliente) {
            IRepositorioCliente cliente = new ClienteCRUD();

            cliente.deletarCliente(pessoa.PessoaID);
            textoNotificacao.setText("Deletado com Sucesso!!");
            textoPessoa.setVisible(false);
            btnConfirmarCliente.setEnabled(false);
            btnCancelarCliente.setText("Fechar");
        } 
        
        if (e.getSource() == btnCancelarFuncionario) {
            pagFuncionario.dispose();
            pagFuncionario.inicializar();
            frame.dispose();
        } else if (e.getSource() == btnConfirmarFuncionario) {
            IRepositorioFuncionario funcionario = new FuncionarioCRUD();

            funcionario.deletarFuncionario(pessoa.PessoaID);
            textoNotificacao.setText("Deletado com Sucesso!!");
            textoPessoa.setVisible(false);
            btnConfirmarFuncionario.setEnabled(false);
            btnCancelarFuncionario.setText("Fechar");
        }
        
        if( e.getSource() == btnCancelarProduto){
            pagProduto.dispose();
            pagProduto.inicializar();
            frame.dispose();
        }else if( e.getSource() == btnConfirmarProduto){
            IRepositorioProduto Produtos = new ProdutoCRUD();

            Produtos.deletarProduto(produto.ProdutoID);
            textoNotificacao.setText("Deletado com Sucesso!!");
            textoPessoa.setVisible(false);
            btnConfirmarProduto.setEnabled(false);
            btnCancelarProduto.setText("Fechar");
        }
        
        if( e.getSource() == btnCancelarServico){
            pagServico.dispose();
            pagServico.inicializar();
            frame.dispose();
        }else if( e.getSource() == btnConfirmarServico){
            IRepositorioServico Servicos = new ServicoCRUD();

            Servicos.deletarServico(servico.ServicoID);
            textoNotificacao.setText("Deletado com Sucesso!!");
            textoPessoa.setVisible(false);
            btnConfirmarServico.setEnabled(false);
            btnCancelarServico.setText("Fechar");
        }
        
        if( e.getSource() == btnCancelarVenda){
            pagVenda.dispose();
            pagVenda.inicializar();
            frame.dispose();
        }else if( e.getSource() == btnConfirmarVenda){
            IRepositorioVenda Vendas = new VendaCRUD();

            Vendas.deletarVenda(venda.VendaID);
            textoNotificacao.setText("Deletado com Sucesso!!");
            textoPessoa.setVisible(false);
            btnConfirmarVenda.setEnabled(false);
            btnCancelarVenda.setText("Fechar");
        }
        
        if( e.getSource() == btnCancelarOS){
            pagOrdemServico.dispose();
            pagOrdemServico.inicializar();
            frame.dispose();
        }else if( e.getSource() == btnConfirmarOS){
            IRepositorioOrdemServico OrdemServicos = new OrdemServicoCRUD();

            OrdemServicos.deletarOrdemServico(ordemServico.OrdemServicoID);
            textoNotificacao.setText("Deletado com Sucesso!!");
            textoPessoa.setVisible(false);
            btnConfirmarOS.setEnabled(false);
            btnCancelarOS.setText("Fechar");
        }
        
        if( e.getSource() == btnCancelarVeiculo){
            verCliente.verCliente.dispose();
            verCliente.janelaVerCliente(veiculo.PessoaID, verCliente.clientePag);
            frame.dispose();
        }else if( e.getSource() == btnConfirmarVeiculo){
            IRepositorioVeiculo Veiculos = new VeiculoCRUD();

            try {
                Veiculos.deletarVeiculo(veiculo.VeiculoID);
            } catch (Exception e1) {}

            textoNotificacao.setText("Deletado com Sucesso!!");
            textoPessoa.setVisible(false);
            btnConfirmarVeiculo.setEnabled(false);
            btnCancelarVeiculo.setText("Fechar");
        }
    }
    
}
