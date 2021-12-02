import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class FuncionarioPag extends JFrame implements ActionListener {
    JScrollPane Tabela;
    JTable tabela;
    JButton btnNovoFuncionario, btnLimpar, btnBuscar, btnEditar, btnDeletar;
    JButton btnInicio, btnCliente, btnFuncionario, btnVenda, btnOrdemServico, btnProduto, btnServico;
    JTextField textoCodigo, textoNome, textoCpf, textoSalario, textoTelefone, textoCargo;
    JPanel panel;
    JPanel barraNav;
    JPanel mainPanel;
    JLabel lbl1;
    int hTb = 0;

    public FuncionarioPag() {}

    public void inicializar() {
        criarJanela();
        adicionarNavBar();
        campoTexto();
        botaoFuncionario();
        adicionarTabela();
    }

    private void criarJanela() {
        setTitle("Chaveiro");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/imgs/key-fill.png")).getImage());
    }

    private void criarNavBar() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        barraNav = new JPanel();
        lbl1 = new JLabel("FUNCIONÁRIO");
        btnInicio = new JButton("Início");
        btnCliente = new JButton("Cliente");
        btnFuncionario = new JButton("Funcionário");
        btnVenda = new JButton("Venda");
        btnOrdemServico = new JButton("Ordem de Serviço");
        btnProduto = new JButton("Produto");
        btnServico = new JButton("Serviço");

        barraNav.setBounds(0, 0, 1920, 50);
        lbl1.setBounds(840, 50, 500, 60);
        lbl1.setFont(new Font("Verdana", Font.PLAIN, 32));

        btnInicio.setBounds(260, 0, 200, 50);
        btnCliente.setBounds(460, 0, 200, 50);
        btnFuncionario.setBounds(660, 0, 200, 50);
        btnVenda.setBounds(860, 0, 200, 50);
        btnOrdemServico.setBounds(1060, 0, 200, 50);
        btnProduto.setBounds(1260, 0, 200, 50);
        btnServico.setBounds(1460, 0, 200, 50);

        transparente(btnInicio);
        transparente(btnCliente);
        transparente(btnFuncionario);
        transparente(btnVenda);
        transparente(btnOrdemServico);
        transparente(btnProduto);
        transparente(btnServico);

        Color color = new Color((float) 1.0, (float) 1.0, (float) 1.0, (float) 0.5);
        Color color2 = new Color(90, 126, 255);

        btnInicio.addActionListener(this);
        btnCliente.addActionListener(this);
        btnFuncionario.addActionListener(this);
        btnVenda.addActionListener(this);
        btnOrdemServico.addActionListener(this);
        btnProduto.addActionListener(this);
        btnServico.addActionListener(this);

        barraNav.setBackground(color);
        mainPanel.setBackground(color2);
    }

    private void transparente(JButton btn) {
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
    }

    private void adicionarNavBar() {
        criarNavBar();

        mainPanel.add(btnInicio);
        mainPanel.add(btnCliente);
        mainPanel.add(btnFuncionario);
        mainPanel.add(btnVenda);
        mainPanel.add(btnOrdemServico);
        mainPanel.add(btnProduto);
        mainPanel.add(btnServico);
        mainPanel.add(barraNav);
        mainPanel.add(lbl1);
        mainPanel.setOpaque(true);

        setContentPane(mainPanel);
    }

    private void botaoFuncionario() {
        btnNovoFuncionario = new JButton("Novo Funcionário");

        btnLimpar = new JButton("Limpar");
        btnBuscar = new JButton("Buscar");
        btnEditar = new JButton("Editar");
        btnDeletar = new JButton("Deletar");

        btnNovoFuncionario.setBounds(1505, 100, 150, 25);

        btnBuscar.setBounds(1535, 300, 120, 25);
        btnLimpar.setBounds(1355, 300, 120, 25);
        btnEditar.setBounds(1535, 350, 120, 25);
        btnDeletar.setBounds(1355, 350, 120, 25);

        Color green = new Color(0, 255, 33, 255);
        Color red = new Color(255, 0, 0, 255);

        btnLimpar.setBackground(red);
        btnDeletar.setBackground(red);
        btnEditar.setBackground(green);
        btnBuscar.setBackground(green);

        btnNovoFuncionario.addActionListener(this);
        btnBuscar.addActionListener(this);
        btnLimpar.addActionListener(this);
        btnEditar.addActionListener(this);
        btnDeletar.addActionListener(this);

        mainPanel.add(btnNovoFuncionario);
        mainPanel.add(btnLimpar);
        mainPanel.add(btnBuscar);
        mainPanel.add(btnEditar);
        mainPanel.add(btnDeletar);
    }

    private void campoTexto() {

        textoCodigo = new JTextField("Código");
        textoNome = new JTextField("Nome");
        textoCpf = new JTextField("CPF");

        textoCargo = new JTextField("Cargo");
        textoSalario = new JTextField("Salário");
        textoTelefone = new JTextField("Telefone");

        textoCodigo.setBounds(260, 150, 300, 30);
        textoNome.setBounds(810, 150, 300, 30);
        textoCpf.setBounds(1355, 150, 300, 30);

        textoCargo.setBounds(260, 250, 300, 30);
        textoSalario.setBounds(810, 250, 300, 30);
        textoTelefone.setBounds(1355, 250, 300, 30);

        setVisibilityJText(true);

        adicionarFocusListener();

        mainPanel.add(textoCargo);
        mainPanel.add(textoCodigo);
        mainPanel.add(textoNome);
        mainPanel.add(textoCpf);
        mainPanel.add(textoSalario);
        mainPanel.add(textoTelefone);
    }

    private void setVisibilityJText(boolean visible) {
        textoCodigo.setVisible(visible);
        textoNome.setVisible(visible);
        textoCargo.setVisible(visible);
        textoCpf.setVisible(visible);
        textoSalario.setVisible(visible);
        textoTelefone.setVisible(visible);
    }

    private void adicionarTabela() {
        IRepositorioFuncionario Funcionarios = new FuncionarioCRUD();
        DefaultTableModel model = new DefaultTableModel();
        hTb = 0;
        Object[] o = new Object[6];
        String[] colunas = { "Código", "Cargo", "Nome", "CPF", "Salário", "Telefone" };
        tabela = new JTable(model);
        model.setColumnIdentifiers(colunas);
        tabela.setBackground(Color.LIGHT_GRAY);
        tabela.setFont(new Font("Verdana", Font.PLAIN, 18));

        for (Pessoa p : Funcionarios.obterFuncionarios()) {

            o[0] = p.getPessoaID();
            o[1] = ((Funcionario) p).getCargo();
            o[2] = p.getNome() + " " + ((Funcionario) p).getSobrenome();
            o[3] = ((Funcionario) p).getCPF();
            o[4] = String.format("%.2f", ((Funcionario) p).getSalario());
            o[5] = p.getTelefone();
            model.addRow(o);
            hTb++;
        }
        tabela.setDefaultEditor(Object.class, null);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 18));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        tabela.getColumnModel().getColumn(0).setMaxWidth(100);

        for (int i = 0; i < colunas.length; i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tabela.setRowHeight(30);
        hTb += 1;
        hTb *= 30;

        Tabela = new JScrollPane(tabela);
        if (hTb <= 570) {
            Tabela.setSize(1400, hTb);
        } else {
            Tabela.setSize(1400, 570);
        }

        Tabela.setLocation(260, 400);
        Tabela.setVisible(true);

        Color select = new Color(0, 255, 148);
        tabela.setSelectionBackground(select);

        mainPanel.add(Tabela);

    }

    private void buscarTabelaID(int codigo) {
        for (int i = 0; i < (hTb / 30) - 1; i++) {
            if (tabela.getValueAt(i, 0).equals(codigo)) {
                tabela.setRowSelectionInterval(i, i);
            }
        }
    }

    private void buscarTabela(String nome, String cargo, String cpf, Float salario, String telefone) {
        for (int i = 0; i < (hTb / 30) - 1; i++) {
            if (tabela.getValueAt(i, 1).toString().toLowerCase().contains(cargo)) {
                tabela.setRowSelectionInterval(i, i);
            }
            if (tabela.getValueAt(i, 2).toString().toLowerCase().contains(nome)) {
                tabela.setRowSelectionInterval(i, i);
            }
            if (tabela.getValueAt(i, 3).toString().toLowerCase().contains(cpf)) {
                tabela.setRowSelectionInterval(i, i);
            }
            if (tabela.getValueAt(i, 4).equals(salario)) {
                tabela.setRowSelectionInterval(i, i);
            }
            if (tabela.getValueAt(i, 5).equals(telefone)) {
                tabela.setRowSelectionInterval(i, i);
            }
        }
    }

    private void deletarFuncionario(int linha) {
        IRepositorioFuncionario funcionario = new FuncionarioCRUD();
        Pessoa p = new Pessoa();
        NotificacaoPag notificacao = new NotificacaoPag();

        int funcionarioID = (int) tabela.getValueAt(linha, 0);

        p = funcionario.obterFuncionarioPorID(funcionarioID);

        notificacao.deletarFuncionarioPag(p, this);
    }

    private void atualizarFuncionario(int linha) {
        IRepositorioFuncionario funcionario = new FuncionarioCRUD();
        Pessoa p = new Pessoa();
        NovoFuncionarioPag novoFuncionario = new NovoFuncionarioPag();

        int funcionarioID = (int) tabela.getValueAt(linha, 0);

        p = funcionario.obterFuncionarioPorID(funcionarioID);

        novoFuncionario.janelaAtualizarFuncionario(p.PessoaID, this);
    }

    private void limparTexto(JTextField texto) {
        texto.setText("");
    }

    private void criarFocusListener(JTextField texto) {
        String txt = texto.getText();
        texto.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                limparTexto(texto);
            }

            public void focusLost(FocusEvent e) {
                if (texto.getText().equals("")) {
                    texto.setText(txt);
                }
            }
        });
    }

    private void adicionarFocusListener() {
        criarFocusListener(textoCodigo);
        criarFocusListener(textoNome);
        criarFocusListener(textoCpf);
        criarFocusListener(textoTelefone);
        criarFocusListener(textoCargo);
        criarFocusListener(textoSalario);
    }

    public void actionPerformed(ActionEvent e) {

        int linha = tabela.getSelectedRow();
        tabela.getSelectionModel().clearSelection();

        if (e.getSource() == btnInicio) {
            InicioPag inicioPag = new InicioPag();
            inicioPag.inicializar();
            dispose();
        } else if (e.getSource() == btnCliente) {
            ClientePag cliente = new ClientePag();
            cliente.inicializar();
            dispose();
        } else if (e.getSource() == btnProduto) {
            ProdutoPag produto = new ProdutoPag();
            produto.inicializar();
            dispose();
        } else if (e.getSource() == btnServico) {
            ServicoPag servicoPag = new ServicoPag();
            servicoPag.inicializar();
            dispose();
        } else if (e.getSource() == btnVenda) {
            VendaPag vendaPag = new VendaPag();
            vendaPag.inicializar();
            dispose();
        } else if (e.getSource() == btnOrdemServico) {
            OrdemServicoPag ordemServicoPag = new OrdemServicoPag();
            ordemServicoPag.inicializar();
            dispose();
        }

        if (e.getSource() == btnNovoFuncionario) {
            NovoFuncionarioPag novoFuncionario = new NovoFuncionarioPag();
            novoFuncionario.janelaCadastroFuncionario(this);
        }

        if (e.getSource() == btnBuscar) {
            if (!textoCodigo.getText().equals("Código")) {
                int codigo = Integer.parseInt(textoCodigo.getText().trim());
                buscarTabelaID(codigo);
            }
            if (!textoNome.getText().equals("Nome")) {
                buscarTabela(textoNome.getText(), textoCargo.getText(), textoCpf.getText(), (float) 0,
                        textoTelefone.getText());
            }
            if (!textoCargo.getText().equals("Cargo")) {
                buscarTabela(textoNome.getText(), textoCargo.getText(), textoCpf.getText(), (float) 0,
                        textoTelefone.getText());
            }
            if (!textoCpf.getText().equals("CPF")) {
                buscarTabela(textoNome.getText(), textoCargo.getText(), textoCpf.getText(), (float) 0,
                        textoTelefone.getText());
            }
            if (!textoSalario.getText().equals("Salário")) {
                buscarTabela(textoNome.getText(), textoCargo.getText(), textoCpf.getText(),
                        Float.parseFloat(textoSalario.getText().trim()), textoTelefone.getText());
            }
            if (!textoTelefone.getText().equals("Telefone")) {
                buscarTabela(textoNome.getText(), textoCargo.getText(), textoCpf.getText(), (float) 0,
                        textoTelefone.getText());
            }

        } else if (e.getSource() == btnLimpar) {
            setVisibilityJText(false);
            campoTexto();
        } else if (e.getSource() == btnDeletar) {
            deletarFuncionario(linha);
        } else if (e.getSource() == btnEditar) {
            atualizarFuncionario(linha);
        }
    }
}
