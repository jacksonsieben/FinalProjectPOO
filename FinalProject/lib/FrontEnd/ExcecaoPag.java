import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExcecaoPag{
    JFrame frame;
    JPanel mainPanel;

    JLabel textoErro, textoExcecao;

    public void inicializar(String e){
        criarJanela();
        criarMainPanel();
        adicionarExcecao(e);
        frame.setContentPane(mainPanel); 
    }

    private void criarJanela() {
        frame = new JFrame();
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Erro");
        frame.setVisible(true);
        frame.setIconImage(new ImageIcon(getClass().getResource("/imgs/warning.png")).getImage());
    }

    private void criarMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        textoErro = new JLabel("ERRO");
        textoErro.setHorizontalAlignment(JLabel.CENTER);
        textoErro.setFont(new Font("Verdana", Font.PLAIN, 24));

        textoErro.setBounds(0, 35, 385, 50);
        
        mainPanel.setOpaque(true);

        mainPanel.add(textoErro);
    }

    private void adicionarExcecao(String e){
        textoExcecao = new JLabel("<html><p style=\"width:280px; text-align:center;\">" + e + "</p></html>");
        textoExcecao.setHorizontalAlignment(JLabel.CENTER);
        textoExcecao.setVerticalAlignment(JLabel.TOP);
        textoExcecao.setFont(new Font("Verdana", Font.PLAIN, 24));

        textoExcecao.setBounds(0, 100, 390, 100);

        mainPanel.add(textoExcecao);
    }
    
}
