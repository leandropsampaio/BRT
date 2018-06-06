package View;

import Controller.ControllerBRT;
import Exception.TransbordoNaoExisteException;
import Model.Transbordo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Esta classe tem como função conter as ações e opções no painel da interface
 * gráfica para a identificação de todas as rotas possíveis.
 *
 */
public class JPanelIdentificarRotas extends JPanel {

    private ControllerBRT controller;
    private Transbordo transbordo1, transbordo2;
    private DefaultListModel listaModel;
    private final JList jlista;
    private JTextField origem;
    private JTextField destino;
    private Container ctn;

    /**
     * Contrutor responsável por inicializar o painel da interface gráfica.
     *
     * @param controller - ControllerBRT.
     */
    public JPanelIdentificarRotas(ControllerBRT controller) {
        this.controller = controller;
        jlista = new JList();
        init();
    }

    /**
     * Método responsável por definições básicas da interface.
     *
     */
    public void init() {
        ctn = new JPanel(new BorderLayout());
        setLayout(new BorderLayout());
        setBackground(Color.RED);

        BuscarAction cadastrarAction = new BuscarAction();

        Container ctn2 = new JPanel(new FlowLayout());
        JLabel transbordo1Label = new JLabel("Origem: ");
        origem = new JTextField(10);
        JLabel transbordo2Label = new JLabel("Destino: ");
        destino = new JTextField(10);
        JButton butao = new JButton("Buscar");
        butao.addActionListener(cadastrarAction);

        ctn2.add(transbordo1Label);
        ctn2.add(origem);
        ctn2.add(transbordo2Label);
        ctn2.add(destino);
        ctn2.add(butao);

        add(ctn, BorderLayout.CENTER);
        add(ctn2, BorderLayout.SOUTH);
    }

    /**
     * Método responsável pela acão do botão de busca de todas as rotas
     * possíveis entre do transbordos.
     *
     */
    private class BuscarAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                transbordo1 = controller.getTransbordos().buscarTransbordo(origem.getText());
                transbordo2 = controller.getTransbordos().buscarTransbordo(destino.getText());
                System.out.println("A-" + transbordo1.getNome() + "B-" + transbordo2.getNome());
                Iterator it = controller.IdentificarPossiveisRotas(transbordo1.getNome(), transbordo2.getNome());
                listaModel = new DefaultListModel();
                listaModel.clear();

                int i = 0;
                while (it.hasNext()) {
                    if (i == 0) {
                        listaModel.addElement("                                     "
                                + "                                              "
                                + "******************  IDENTIFICAR TODAS AS ROTAS  ******************");
                        listaModel.addElement("_________________________________"
                                + "_____________________________________________"
                                + "___________________________________"
                        );
                    }
                    i++;
                    listaModel.addElement("                                     "
                            + "                                                 "
                            + "                         "
                            + i + "- Rota: " + it.next());
                }
                jlista.setModel(listaModel);
                ctn.add(jlista);
                revalidate();
            } catch (TransbordoNaoExisteException ex) {
                Logger.getLogger(JPanelMenorCaminho.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
