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

public class AdicionarServicoPag implements ActionListener {
    JFrame adicionarServico = new JFrame();
    JTextField textoCodigo, textoDescricao, textoPreco, textoQuantidade, textoTotalItem;
    JPanel mainPanel;
    JLabel titulo;
    JButton btnLimparServico, btnBuscarServico, btnAdicionar, btnCancelar;
    JScrollPane fundoTabelaServico;
    JTable tabelaServico;
    int hTb = 0;

    NovaOrdemServicoPag novaOSPag;
    OrdemServico OS = new OrdemServico();
    Servico servico = new Servico();

    public AdicionarServicoPag() {}

    public void janelaAdicionarServico(NovaOrdemServicoPag pagNovaOS) {
        novaOSPag = pagNovaOS;
        criarJanelaAdicionarServico();
        criarMainPanel();
        criarCampoTexto();
        criarBotoes();
        adicionarTabela();
        adicionarServico.setContentPane(mainPanel);
    }

    private void criarJanelaAdicionarServico() {
        adicionarServico.setSize(854, 520);
        adicionarServico.setVisible(true);
        adicionarServico.setLocationRelativeTo(null);
        adicionarServico.setResizable(false);
        adicionarServico.setTitle("Servico");
        adicionarServico.setIconImage(new ImageIcon(getClass().getResource("/imgs/key-fill.png")).getImage());
    }

    private void criarMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        titulo = new JLabel("Adicionar Serviço");
        titulo.setFont(new Font("Verdana", Font.PLAIN, 18));
        titulo.setBounds(0, 10, 834, 50);
        titulo.setHorizontalAlignment(JLabel.CENTER);

        Color color = new Color(90, 126, 255);

        mainPanel.setBackground(color);

        mainPanel.add(titulo);
    }

    private void criarCampoTexto() {
        textoCodigo = new JTextField("Código");
        textoDescricao = new JTextField("Descrição");
        textoQuantidade = new JTextField("Quantidade");

        textoPreco = new JTextField("Preço");
        textoTotalItem = new JTextField("Total");

        textoCodigo.setBounds(41, 75, 200, 30);
        textoDescricao.setBounds(318, 75, 200, 30);
        textoQuantidade.setBounds(595, 75, 200, 30);

        textoPreco.setBounds(41, 120, 200, 30);
        textoTotalItem.setBounds(318, 120, 200, 30);

        textoPreco.setEnabled(false);
        textoTotalItem.setEnabled(false);

        textoQuantidade.addActionListener(this);

        adicionarFocusListener();

        campoTextoVisibilidade(true);

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

    private void criarBotoes() {
        btnAdicionar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        btnBuscarServico = new JButton("Buscar");
        btnLimparServico = new JButton("Limpar");

        btnCancelar.setBounds(277, 435, 100, 25);
        btnAdicionar.setBounds(477, 435, 100, 25);

        btnLimparServico.setBounds(595, 125, 75, 25);
        btnBuscarServico.setBounds(720, 125, 75, 25);

        btnLimparServico.setBackground(Color.RED);
        btnBuscarServico.setBackground(Color.GREEN);

        btnAdicionar.setEnabled(false);

        btnCancelar.addActionListener(this);
        btnAdicionar.addActionListener(this);

        btnLimparServico.addActionListener(this);
        btnBuscarServico.addActionListener(this);

        mainPanel.add(btnCancelar);
        mainPanel.add(btnAdicionar);

        mainPanel.add(btnLimparServico);
        mainPanel.add(btnBuscarServico);
        mainPanel.setVisible(true);
    }

    private void adicionarTabela() {
        IRepositorioServico Servico = new ServicoCRUD();
        DefaultTableModel model = new DefaultTableModel();
        hTb = 0;
        Object[] o = new Object[3];
        String[] colunas = { "Código", "Descrição", "Preço Un" };
        tabelaServico = new JTable(model);
        model.setColumnIdentifiers(colunas);
        tabelaServico.setBackground(Color.LIGHT_GRAY);
        tabelaServico.setFont(new Font("Verdana", Font.PLAIN, 14));

        for (Servico p : Servico.obterServicos()) {

            o[0] = p.getServicoID();
            o[1] = p.getDescricao();
            o[2] = String.format("%.2f", p.getPreco());

            model.addRow(o);
            hTb++;
        }
        tabelaServico.setDefaultEditor(Object.class, null);
        tabelaServico.getTableHeader().setReorderingAllowed(false);
        tabelaServico.getTableHeader().setResizingAllowed(false);
        tabelaServico.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        tabelaServico.getColumnModel().getColumn(0).setMaxWidth(100);

        for (int i = 0; i < colunas.length; i++) {
            tabelaServico.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tabelaServico.setRowHeight(25);
        hTb += 1;
        hTb *= 25;

        tabelaServico.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) {
                    completarCampoTexto();
                    if (tabelaServico.getSelectedRowCount() > 0) {
                        btnAdicionar.setEnabled(true);
                    }
                }
            }
        });

        fundoTabelaServico = new JScrollPane(tabelaServico);
        if (hTb <= 245) {
            fundoTabelaServico.setSize(754, hTb);
        } else {
            fundoTabelaServico.setSize(754, 245);
        }

        fundoTabelaServico.setLocation(41, 170);
        fundoTabelaServico.setVisible(true);

        Color select = new Color(0, 255, 148);
        tabelaServico.setSelectionBackground(select);

        mainPanel.add(fundoTabelaServico);
    }

    private void buscarTabelaID(int codigo) {
        boolean linhaSelecionada = false;
        for (int i = 0; i < (hTb / 25) - 1; i++) {
            if (tabelaServico.getValueAt(i, 0).equals(codigo)) {
                tabelaServico.setRowSelectionInterval(i, i);
                completarCampoTexto();
                linhaSelecionada = true;
                btnAdicionar.setEnabled(true);
            }
        }
        if (!linhaSelecionada) {
            tabelaServico.getSelectionModel().clearSelection();
        }

    }

    private void buscarTabela(String descricao) {

        for (int i = 0; i < (hTb / 25) - 1; i++) {

            if (((String) tabelaServico.getValueAt(i, 1)).toLowerCase().contains(descricao.toLowerCase())) {
                tabelaServico.setRowSelectionInterval(i, i);
                completarCampoTexto();
            }
        }

    }

    private void completarCampoTexto() {
        IRepositorioServico Servicos = new ServicoCRUD();

        int servicoID = tabelaServico.getSelectedRow();

        servico = Servicos.obterServicoPorID(servicoID);

        textoPreco.setText(String.format("%.2f", servico.Preco));
        if (textoQuantidade.getText().equals("Quantidade")) {
            textoQuantidade.setText("1");
        }
        float total = servico.Preco * Integer.parseInt(textoQuantidade.getText().trim());
        textoTotalItem.setText(String.format("%.2f", total));
        textoDescricao.setText(servico.Descricao);
        textoCodigo.setText(String.format("%d", servico.ServicoID));

    }

    private void adicionarServico() throws Exception {
        int linha = tabelaServico.getSelectedRow();
        int servicoID = (int) tabelaServico.getValueAt(linha, 0);
        int quantidade = Integer.parseInt(textoQuantidade.getText());

        verificarCampos();

        novaOSPag.OS.novoItem(servicoID, quantidade);
    }

    private void verificarCampos() throws Exception {

        if (tabelaServico.getSelectedRowCount() == 0) {
            throw new Exception("Selecione um produto");
        }
        try {
            Integer.parseInt(textoCodigo.getText());
        } catch (Exception e) {
            throw new Exception("Código Inválido");
        }

        if (textoDescricao.getText().equals("Descrição")) {
            throw new Exception("Descrição Inválida");
        }
        try {
            int quantidade = Integer.parseInt(textoQuantidade.getText());
            if (quantidade == 0) {
                throw new Exception("Quantidade não pode ser zero");
            }
        } catch (Exception e) {
            throw new Exception("Quantidade Inválida");
        }

        if (textoQuantidade.getText().equals("Quantidade")) {
            throw new Exception("Quantidade Inválida");
        }
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
        criarFocusListener(textoDescricao);
        criarFocusListener(textoQuantidade);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == btnBuscarServico) {
                if (!textoCodigo.getText().equals("Código")) {
                    int codigo = Integer.parseInt(textoCodigo.getText().trim());
                    buscarTabelaID(codigo);
                }
                if (!textoDescricao.getText().equals("Descrição")) {
                    buscarTabela(textoDescricao.getText());
                }
            }
            if (e.getSource() == btnLimparServico) {
                tabelaServico.getSelectionModel().clearSelection();
                campoTextoVisibilidade(false);
                criarCampoTexto();
                btnAdicionar.setEnabled(false);
            }
            if (e.getSource() == btnCancelar) {
                adicionarServico.dispose();
            }
            if (e.getSource() == textoQuantidade) {
                completarCampoTexto();
            }
            if (e.getSource() == btnAdicionar) {
                adicionarServico();

                novaOSPag.novaOS.dispose();
                novaOSPag.reinicializarTela();
                adicionarServico.dispose();
            }
        } catch (Exception ex) {
            ExcecaoPag excecao = new ExcecaoPag();
            excecao.inicializar(ex.getMessage());
        }
    }
}
