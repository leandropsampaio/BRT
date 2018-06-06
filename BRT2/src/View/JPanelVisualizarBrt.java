package View;

import Controller.ControllerBRT;
import Model.Rota;
import Model.Transbordo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 * Esta classe tem como função conter as ações e opções no painel da interface
 * gráfica para a vizualição do BRT.
 *
 */
public class JPanelVisualizarBrt extends JPanel {

    private ControllerBRT controller;

    /**
     * Método responsável por criar a interface grafica.
     *
     * @param controller - ControllerBRT.
     */
    public JPanelVisualizarBrt(ControllerBRT controller) {
        this.controller = controller;
        init();
    }

    /**
     * Método responsável por iniciar a interface gráfica.
     *
     */
    private void init() {
        setLayout(new BorderLayout());
        setBackground(Color.BLUE);
    }

    /**
     * Método responsável por conter informações que estarão contidas na
     * interface.
     *
     * @param g - Graphics.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int x = 350, y = 50;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int i = 0;
        Iterator it = controller.listarTransbordos().iterator();

        while (it.hasNext()) {
            g2.setColor(Color.red);
            Transbordo transbordo = (Transbordo) it.next();
            if (i == 0) {
                transbordo.setX(x);
                transbordo.setY(y);
            } else if (i == 1) {
                x = x + 100;
                y = y + 15;
                transbordo.setX(x);
                transbordo.setY(y);
            } else if (i == 2) {
                x = x + 60;
                y = y + 60;
                transbordo.setX(x);
                transbordo.setY(y);
            } else if (i == 3) {
                y = y + 80;
                transbordo.setX(x);
                transbordo.setY(y);
            } else if (i == 4) {
                x = x - 60;
                y = y + 60;
                transbordo.setX(x);
                transbordo.setY(y);
            } else if (i == 5) {
                x = x - 100;
                y = y + 15;
                transbordo.setX(x);
                transbordo.setY(y);
            } else if (i == 6) {
                x = x - 100;
                y = y - 15;
                transbordo.setX(x);
                transbordo.setY(y);
            } else if (i == 7) {
                x = x - 60;
                y = y - 60;
                transbordo.setX(x);
                transbordo.setY(y);
            } else if (i == 8) {
                y = y - 80;
                transbordo.setX(x);
                transbordo.setY(y);
            } else if (i == 9) {
                x = x + 60;
                y = y - 60;
                transbordo.setX(x);
                transbordo.setY(y);
            }
            i++;
        }
        it = controller.listarTransbordos().iterator();
        while (it.hasNext()) {
            Transbordo transbordo = (Transbordo) it.next();
            Iterator it2 = transbordo.getRotas().iterator();
            while (it2.hasNext()) {
                g2.setColor(Color.BLACK);
                Rota rota = (Rota) it2.next();
                g2.drawLine(rota.getTransbordo1().getX() + 20, rota.getTransbordo1().getY() + 20,
                        rota.getTransbordo2().getX() + 20, rota.getTransbordo2().getY() + 20);
                g2.setColor(Color.WHITE);
                int x1 = (rota.getTransbordo1().getX() + rota.getTransbordo2().getX()) / 2;
                int y1 = (rota.getTransbordo1().getY() + rota.getTransbordo2().getY()) / 2;
                g2.drawString(String.valueOf(rota.getPeso()), Math.abs(x1) + 20, Math.abs(y1) + 20);
            }
            g2.setColor(Color.BLACK);
            Ellipse2D rec = new Ellipse2D.Double(transbordo.getX(), transbordo.getY(), 40, 40);
            g2.fill(rec);
            g2.setColor(Color.WHITE);
            g2.drawString(transbordo.toString(), transbordo.getX() + 16, transbordo.getY() + 20);
        }
    }
}
