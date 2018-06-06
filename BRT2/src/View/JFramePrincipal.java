package View;

import Controller.ControllerBRT;
import Exception.RotaJaExisteException;
import Exception.SemTransbordosException;
import Exception.TransbordoJaExisteException;
import Exception.TransbordoNaoExisteException;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Esta classe tem como função conter as ações e opções no painel da interface
 * gráfica para a tela principal.
 *
 */
public class JFramePrincipal extends JFrame {

    private JLabel titulo;
    private JPanel painelCentro;
    private CardLayout card;
    private ControllerBRT controller;
    private Listener listener;
    private ListenerBarra listenerBar;

    public JFramePrincipal(ControllerBRT controller) {
        super("BRT (Transporte Rápido de Ônibus)");
        this.controller = controller;
        listenerBar = new ListenerBarra();
        listener = new Listener();
        criarMenu();
        criarFormulario();
    }

    /**
     * Método responsável por criar uma tela gráfica onde são desenvolvidos
     * informações de uma barra de tarefa.
     *
     */
    private void criarMenu() {

        JMenu menuArquivo = new JMenu("Arquivo");

        JMenuItem menuItemArquivo = new JMenuItem("Abrir Arquivo");
        menuItemArquivo.addActionListener(listenerBar);

        JMenuItem menuItemNovo = new JMenuItem("Novo");
        menuItemNovo.addActionListener(listenerBar);

        JMenuItem menuItemFechar = new JMenuItem("Fechar");
        menuItemFechar.addActionListener(listenerBar);

        menuArquivo.add(menuItemArquivo);
        menuArquivo.add(menuItemNovo);
        menuArquivo.add(menuItemFechar);

        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem menuItemSobre = new JMenuItem("Sobre...");
        menuItemSobre.addActionListener(listenerBar);

        menuAjuda.add(menuItemSobre);

        JMenuBar barra = new JMenuBar();
        barra.add(menuArquivo);
        barra.add(menuAjuda);
        setJMenuBar(barra);
    }

    /**
     * Método responsável por criar um formulário de modo a ser utilizado na
     * tela da interface gráfica.
     *
     */
    private void criarFormulario() {

        Container c = new JPanel(new BorderLayout());
        setContentPane(c);

        Container painelTitulo = new JPanel();
        painelTitulo.setLayout(new FlowLayout());

        titulo = new JLabel("Transporte Rápido de Ônibus");
        //titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Verdana", Font.BOLD, 16));

        painelTitulo.add(titulo);

        Container painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());

        JButton butaoVisualizar = new JButton("Visualizar BRT");
        butaoVisualizar.addActionListener(listener);

        JButton butaoIdentificarRotas = new JButton("Identificar Rotas");
        butaoIdentificarRotas.addActionListener(listener);

        JButton butaoIdentificarMenorCaminho = new JButton("Identificar Menor Rota");
        butaoIdentificarMenorCaminho.addActionListener(listener);

        JButton butaoEditarBRT = new JButton("Editar BRT");
        butaoEditarBRT.addActionListener(listener);

        painelBotoes.add(butaoVisualizar);
        painelBotoes.add(butaoIdentificarRotas);
        painelBotoes.add(butaoIdentificarMenorCaminho);
        painelBotoes.add(butaoEditarBRT);

        painelCentro = new JPanel();
        card = new CardLayout();
        painelCentro.setLayout(card);
        painelCentro.add(new JPaneImagem(), "Principal");
        painelCentro.add(new JPanelVisualizarBrt(controller), "Painel1");
        painelCentro.add(new JPanelMenorCaminho(controller), "Painel2");
        painelCentro.add(new JPanelIdentificarRotas(controller), "Painel3");
        painelCentro.add(new JPanelEditarBrt(controller), "Painel4");
        painelCentro.add(new JPaneArquivo(controller), "abrirArquivo");

        card.show(painelCentro, "Principal");

        c.add(painelTitulo, BorderLayout.NORTH);
        c.add(painelCentro, BorderLayout.CENTER);
        c.add(painelBotoes, BorderLayout.SOUTH);
    }

    /**
     * Método responsável por exibir uma tela/barra da interface grafica.
     *
     */
    private class ListenerBarra implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem menu = (JMenuItem) e.getSource();
            if (menu.getText().equals("Sobre...")) {
                JOptionPane.showMessageDialog(null, "Programa criado por:\n"
                        + "- Leandro Pereira Sampaio", "Sobre...", JOptionPane.PLAIN_MESSAGE);
            } else if (menu.getText().equals("Fechar")) {
                try {
                    controller.salvarArquivo();
                    System.exit(0);
                } catch (RotaJaExisteException ex) {
                    Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TransbordoJaExisteException ex) {
                    Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TransbordoNaoExisteException ex) {
                    Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (menu.getText().equals("Novo")) {
                card.show(painelCentro, "Principal");
            } else if (menu.getText().equals("Abrir Arquivo")) {
                card.show(painelCentro, "abrirArquivo");
            }
        }
    }

    /**
     * Método responsável pela ação de utilização da classe na interface.
     *
     */
    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            if (btn.getText().equals("Visualizar BRT")) {
                card.show(painelCentro, "Painel1");
            } else if (btn.getText().equals("Identificar Menor Rota")) {
                card.show(painelCentro, "Painel2");
            } else if (btn.getText().equals("Identificar Rotas")) {
                card.show(painelCentro, "Painel3");
            } else if (btn.getText().equals("Editar BRT")) {
                card.show(painelCentro, "Painel4");
            }
        }
    }
}
