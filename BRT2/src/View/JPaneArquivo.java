package View;

import Controller.ControllerBRT;
import Exception.RotaJaExisteException;
import Exception.TransbordoJaExisteException;
import Exception.TransbordoNaoExisteException;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Esta classe tem como função conter as ações e opções no painel da interface
 * gráfica para a tela de leitura de arquivo.
 *
 */
public class JPaneArquivo extends JPanel {

    private JTextField nomeArquivo;
    private ControllerBRT controller;

    public JPaneArquivo(ControllerBRT controller) {
        this.controller = controller;
        init();
    }

    /**
     * Método responsável pela ação de utilização da classe na interface.
     *
     */
    private void init() {
        AbrirAction abrirAction = new AbrirAction();

        setLayout(new BorderLayout());

        JLabel label = new JLabel("Adicionar arquivo: ");
        nomeArquivo = new JTextField(40);

        Container painel = new JPanel();
        painel.setLayout(new FlowLayout());

        JButton botaoAbrir = new JButton("Abrir");
        botaoAbrir.addActionListener(abrirAction);

        painel.add(label);
        painel.add(nomeArquivo);
        painel.add(botaoAbrir);

        add(painel, BorderLayout.CENTER);
    }

    /**
     * Metodo responsável pela ação do botão abrir do painel de arquivo.
     *
     */
    public class AbrirAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                if (f != null) {
                    String filename = f.getAbsolutePath();
                    nomeArquivo.setText(filename);
                    controller.lerArquivo(nomeArquivo.getText());
                    JOptionPane.showMessageDialog(null, "Arquivo lido com Sucesso!", "BRT", JOptionPane.PLAIN_MESSAGE);
                }
            } catch (RotaJaExisteException | TransbordoJaExisteException | TransbordoNaoExisteException ex) {
                Logger.getLogger(JPaneArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
