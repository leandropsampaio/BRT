package View;

import Controller.ControllerBRT;
import Exception.RotaJaExisteException;
import Exception.RotaNaoExisteException;
import Exception.TransbordoJaExisteException;
import Exception.TransbordoNaoExisteException;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Esta classe tem como função conter as ações e opções no painel da interface
 * gráfica para a edição do brt.
 *
 */
public class JPanelEditarBrt extends JPanel {

    private ControllerBRT controller;
    private CardLayout card;
    private Container painel2;
    private BotoesActions listener;
    private JTextField nomeTransbordo;
    private JTextField nomeTransbordo2;
    private JTextField nomeTransbordo3;
    private JTextField nomeTransbordo4;
    private JTextField nomeTransbordo5;
    private JTextField nomeTransbordo6;
    private JTextField tamanhoRota;

    /**
     * Método responsável por inicializar a interface gráfica.
     *
     * @param controller - ControllerBRT.
     */
    public JPanelEditarBrt(ControllerBRT controller) {
        listener = new BotoesActions();
        this.controller = controller;
        criarFormulario();
    }

    /**
     * Metodo responsável por criar a interface grafica para a criação de um
     * formulário.
     *
     * @throws TransbordoNaoExisteException
     * @throws TransbordoJaExisteException
     * @throws RotaJaExisteException
     */
    private void criarFormulario() {
        setLayout(new BorderLayout());

        Container painel = new JPanel();
        painel.setLayout(new FlowLayout());

        JButton botao = new JButton("Adicionar Nova Rota");
        botao.addActionListener(listener);
        JButton botao2 = new JButton("Remover Rotas");
        botao2.addActionListener(listener);
        JButton botao3 = new JButton("Adicionar Novo Transbordo");
        botao3.addActionListener(listener);
        JButton botao4 = new JButton("Remover Transbordos");
        botao4.addActionListener(listener);

        painel.add(botao);
        painel.add(botao2);
        painel.add(botao3);
        painel.add(botao4);

        painel2 = new JPanel();
        card = new CardLayout();
        painel2.setLayout(card);

        painel2.add(new painelAddRota(), "painelAddRota");
        painel2.add(new painelRemoverRota(), "painelRemoverRota");
        painel2.add(new painelAddTransbordo(), "painelAddTransbordo");
        painel2.add(new painelRemoverTransbordo(), "painelRemoverTransbordo");

        add(painel, BorderLayout.NORTH);
        add(painel2, BorderLayout.CENTER);
    }

    /**
     * Metodo responsável ações de botões a serem utilizadas.
     *
     */
    public class BotoesActions implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            if (btn.getText().equals("Adicionar Novo Transbordo")) {
                card.show(painel2, "painelAddTransbordo");
            } else if (btn.getText().equals("Adicionar Nova Rota")) {
                card.show(painel2, "painelAddRota");
            } else if (btn.getText().equals("Remover Transbordos")) {
                card.show(painel2, "painelRemoverTransbordo");
            } else if (btn.getText().equals("Remover Rotas")) {
                card.show(painel2, "painelRemoverRota");
            } else if (btn.getText().equals("Adicionar Transbordo")) {
                if (!nomeTransbordo.getText().equals("")) {
                    try {
                        controller.inserirTransbordo(nomeTransbordo.getText());
                    } catch (TransbordoNaoExisteException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "BRT", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            } else if (btn.getText().equals("Remover Transbordo")) {
                if (!nomeTransbordo2.getText().equals("")) {
                    try {
                        controller.removerTransbordo(nomeTransbordo2.getText());
                    } catch (TransbordoJaExisteException | TransbordoNaoExisteException | RotaNaoExisteException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "BRT", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            } else if (btn.getText().equals("Adicionar Rota")) {
                if ((!tamanhoRota.getText().equals("") && !nomeTransbordo3.getText().equals(""))
                        && !nomeTransbordo4.getText().equals("")) {
                    try {
                        int rota = Integer.parseInt(tamanhoRota.getText());
                        controller.inserirRota(controller.buscarTransbordo(nomeTransbordo3.getText()),
                                controller.buscarTransbordo(nomeTransbordo4.getText()), rota);
                    } catch (RotaJaExisteException | TransbordoJaExisteException | TransbordoNaoExisteException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "BRT", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            } else if (btn.getText().equals("Remover Rota")) {
                if (!nomeTransbordo5.getText().equals("")
                        && !nomeTransbordo6.getText().equals("")) {
                    try {
                        controller.removerRota(controller.buscarTransbordo(nomeTransbordo5.getText()),
                                controller.buscarTransbordo(nomeTransbordo6.getText()));
                    } catch (TransbordoNaoExisteException | RotaNaoExisteException | TransbordoJaExisteException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "BRT", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        }
    }

    /**
     * Metodo responsável pela ação de adição de transbordo.
     *
     */
    private class painelAddTransbordo extends JPanel {

        public painelAddTransbordo() {
            setLayout(new BorderLayout());
            Container painel = new JPanel();
            painel.setLayout(new FlowLayout());

            JLabel label = new JLabel("Nome do Transbordo");
            nomeTransbordo = new JTextField(20);

            JButton botao = new JButton("Adicionar Transbordo");
            botao.addActionListener(listener);

            painel.add(label);
            painel.add(nomeTransbordo);
            painel.add(botao);

            add(painel, BorderLayout.CENTER);
        }
    }

    /**
     * Metodo responsável pela ação de remoção de transbordo.
     *
     */
    private class painelRemoverTransbordo extends JPanel {

        public painelRemoverTransbordo() {
            setLayout(new BorderLayout());
            Container painel = new JPanel();
            painel.setLayout(new FlowLayout());

            JLabel label = new JLabel("Nome do Transbordo");
            nomeTransbordo2 = new JTextField(20);

            JButton botao = new JButton("Remover Transbordo");
            botao.addActionListener(listener);

            painel.add(label);
            painel.add(nomeTransbordo2);
            painel.add(botao);

            add(painel, BorderLayout.CENTER);
        }
    }

    /**
     * Metodo responsável pela ação de adição de rota.
     *
     */
    private class painelAddRota extends JPanel {

        public painelAddRota() {
            setLayout(new BorderLayout());
            Container painel = new JPanel();
            painel.setLayout(new FlowLayout());

            JLabel label = new JLabel("Nome do 1ª Transbordo");
            nomeTransbordo3 = new JTextField(20);

            JLabel label2 = new JLabel("Nome do 2ª Transbordo");
            nomeTransbordo4 = new JTextField(20);

            JLabel label3 = new JLabel("Distância da Rota");
            tamanhoRota = new JTextField(20);

            JButton botao = new JButton("Adicionar Rota");
            botao.addActionListener(listener);

            painel.add(label);
            painel.add(nomeTransbordo3);
            painel.add(label2);
            painel.add(nomeTransbordo4);
            painel.add(label3);
            painel.add(tamanhoRota);
            painel.add(botao);

            add(painel, BorderLayout.CENTER);
        }
    }

    /**
     * Metodo responsável pela ação de remoção de rota.
     *
     */
    private class painelRemoverRota extends JPanel {

        public painelRemoverRota() {
            setLayout(new BorderLayout());
            Container painel = new JPanel();
            painel.setLayout(new FlowLayout());

            JLabel label = new JLabel("Nome do 1ª Transbordo");
            nomeTransbordo5 = new JTextField(20);

            JLabel label2 = new JLabel("Nome do 2ª Transbordo");
            nomeTransbordo6 = new JTextField(20);

            JButton botao = new JButton("Remover Rota");
            botao.addActionListener(listener);

            painel.add(label);
            painel.add(nomeTransbordo5);
            painel.add(label2);
            painel.add(nomeTransbordo6);
            painel.add(botao);

            add(painel, BorderLayout.CENTER);
        }
    }
}
