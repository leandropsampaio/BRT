package View;

import Controller.ControllerBRT;
import Exception.SemTransbordosException;
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
 * gráfica para a identificação de menor caminho entre transbordos.
 * 
 */
public class JPanelMenorCaminho extends JPanel {

    private ControllerBRT controller;
    private Transbordo transbordo1, transbordo2;
    private DefaultListModel listaModel;
    private final JList jlista;
    private JTextField origem;
    private JTextField destino;
    private Container ctn;

    /**
     * Método responsável por inicializar a interface gráfica.
     *
     * @param controller - Controller
     */
    public JPanelMenorCaminho(ControllerBRT controller) {
        this.controller = controller;
        jlista = new JList();
        init();
    }

    /**
     * Metodo responsável por dar origem e organizar funções e opções da
     * interface.
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
     * Método responsável pela acão do botão de busca do menor caminho.
     *
     */
    private class BuscarAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                transbordo1 = controller.getTransbordos().buscarTransbordo(origem.getText());
                transbordo2 = controller.getTransbordos().buscarTransbordo(destino.getText());
                System.out.println("A-" + transbordo1.getNome() + "B-" + transbordo2.getNome());
                Iterator it = controller.encontrarMenorCaminho(transbordo1, transbordo2);
                listaModel = new DefaultListModel();
                listaModel.clear();

                int i = 0;
                while (it.hasNext()) {
                    if (i == 0) {
                        listaModel.addElement("                                     "
                                + "                                              "
                                + "******************  MENOR CAMINHO  ******************");
                        listaModel.addElement("_________________________________"
                                + "_____________________________________________"
                                + "___________________________________"
                        );
                    }
                    i++;
                    Transbordo local = (Transbordo) it.next();
                    listaModel.addElement("                                     "
                            + "                                                 "
                            + "                              "
                            + i + "- Transbordo: " + local.getNome());
                    System.out.println("LOCAIS-------- " + local.getNome());
                }
                jlista.setModel(listaModel);
                ctn.add(jlista);
                revalidate();
            } catch (SemTransbordosException ex) {
                Logger.getLogger(JPanelMenorCaminho.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransbordoNaoExisteException ex) {
                Logger.getLogger(JPanelMenorCaminho.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
