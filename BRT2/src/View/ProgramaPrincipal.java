package View;

import Controller.ControllerBRT;
import Exception.RotaJaExisteException;
import Exception.SemTransbordosException;
import Exception.TransbordoJaExisteException;
import Exception.TransbordoNaoExisteException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Esta classe tem como função conter as ações e opções no painel da interface
 * gráfica.
 *
 */
public class ProgramaPrincipal {

    private ControllerBRT controller = new ControllerBRT();

    // Inicialização de um novo objeto do tipo ProgramaPrincipal.
    public static void main(String[] args) throws TransbordoJaExisteException, TransbordoNaoExisteException, RotaJaExisteException, SemTransbordosException {
        ProgramaPrincipal programa = new ProgramaPrincipal();
        programa.iniciar();
    }

    /**
     * Método responsável por inicializar a interface gráfica e realizar o
     * carregamento das informações da tela inicial além de conter determinadas
     * informações de tamanho, por exemplo.
     *
     */
    public void iniciar() {
        JFramePrincipal login = new JFramePrincipal(controller);
        URL caminhoImagem = this.getClass().getClassLoader().getResource("arduino.png");
        Image icone = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        login.setIconImage(icone);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setSize(800, 500);
        login.setResizable(false);
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        login.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja Realmente Sair...", "BRT", JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.OK_OPTION) {
                    try {
                        controller.salvarArquivo();
                    } catch (Exception ex) {
                    }
                    System.exit(0);
                }
            }
        });
    }
}
