package View;

import java.awt.BorderLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Esta classe tem como função conter as ações e opções no painel da ainterface
 * gráfica para uma imagem a ser exibida.
 *
 */
public class JPaneImagem extends JPanel {

    public JPaneImagem() {
        init();
    }

    /**
     * Método responsável pela ação de utilização da classe na interface.
     *
     */
    private void init() {
        setLayout(new BorderLayout());
        JPanel painelImagem = new JPanel();
        Icon icone = new ImageIcon(getClass().getResource("/brt.png"));
        painelImagem.add(new JLabel((Icon) icone));
        add(painelImagem, BorderLayout.CENTER);
    }
}
