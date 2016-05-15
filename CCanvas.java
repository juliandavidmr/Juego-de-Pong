package Juegopong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import static java.lang.Thread.sleep;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class CCanvas extends Canvas implements MouseMotionListener {

    Graphics2D g2;
    Image I;
    Bola Bola = new Bola();
    ImageIcon Fondo = new ImageIcon(getClass().getResource("Imagenes/Fondo2.jpg"));
    ImageIcon Franja = new ImageIcon(getClass().getResource("Imagenes/Franjas.jpg"));
    ImageIcon Franja2 = new ImageIcon(getClass().getResource("Imagenes/Franjas.jpg"));
    ImageIcon AgujeroImagen = new ImageIcon(getClass().getResource("Imagenes/Agujero.png"));
    int LimiteInferiorY = 530;
    int LimiteSuperiorY = 15;
    int LimiteIzquierdoX = 15;
    int LimiteDerechoX = 950;
    Barra BarraInf = new Barra(0, LimiteInferiorY - 20, 120, 20);
    Barra BarraSup = new Barra(0, LimiteSuperiorY + 10, 120, 20);
    Barra BarraIzq = new Barra(LimiteIzquierdoX + 20, 0, 20, 120);
    Barra BarraDer = new Barra(LimiteDerechoX - 20, 0, 20, 120);
    int Vidas = 3;
//   Niveles
    boolean Medio, Dificil, Experto, Normal;
    Font Letra = new Font("URW Chancery L", Font.BOLD, 22);
    Saga Saga = new Saga();
    Rectangle Rect_Agujero = new Rectangle();
//  Franja
    Rectangle FranjaInf = new Rectangle();
    Rectangle FranjaSup = new Rectangle();
    Rectangle FranjaDer = new Rectangle();
    Rectangle FranjaIzq = new Rectangle();
//    
    Temporizador Tiempo = new Temporizador();
    Velocidad Velocidad = new Velocidad();

    CCanvas() {
        setBackground(Color.WHITE);
        Bola.Alto = getSize().height;
        BarraInf.Barra = new ImageIcon(getClass().getResource("Imagenes/barra.png"));
        BarraSup.Barra = new ImageIcon(getClass().getResource("Imagenes/barra.png"));
        BarraIzq.Barra = new ImageIcon(getClass().getResource("Imagenes/BarraVertical.png"));
        BarraDer.Barra = new ImageIcon(getClass().getResource("Imagenes/BarraVertical.png"));
        addMouseMotionListener(this);
        Tiempo.start();
    }

    @Override
    public void paint(Graphics g) {
        Rect_Agujero.setBounds(getWidth() / 2, getHeight() / 2, 120, 100);
        FranjaInf.setBounds(0, (int) BarraInf.Rec_Barra.getMaxY(), getWidth(), 50);
        FranjaSup.setBounds(0, 0, getWidth(), (int) BarraSup.Rec_Barra.getY());
        FranjaIzq.setBounds(0, (int) FranjaSup.getMaxY(), BarraIzq.Rec_Barra.x, (int) FranjaInf.getMinY() - 25);
        FranjaDer.setBounds((int) BarraDer.Rec_Barra.getMaxX(), (int) FranjaSup.getMaxY(),
                40, (int) FranjaInf.getMinY() - 25);
        I = createImage(getWidth(), getHeight());
        Bola.Alto = getSize().height;
        Bola.Ancho = getSize().width;
        g2 = (Graphics2D) I.getGraphics();

        g2.drawImage(Fondo.getImage(), 0, 0, getWidth(), getHeight(), this);

        g2.drawImage(Franja.getImage(), FranjaInf.x, FranjaInf.y, FranjaInf.width, FranjaInf.height, this);

        g2.drawImage(Bola.Pelota.getImage(), Bola.X, Bola.Y, Bola.getRadio(), Bola.getRadio(), this);

        g2.drawImage(BarraInf.Barra.getImage(), BarraInf.Rec_Barra.x, BarraInf.Rec_Barra.y,
                BarraInf.Rec_Barra.width, BarraInf.Rec_Barra.height, this);

        if (Medio) {
            g2.drawImage(Franja.getImage(), FranjaSup.x, FranjaSup.y, FranjaSup.width, FranjaSup.height, this);
            g2.drawImage(BarraSup.Barra.getImage(), BarraSup.Rec_Barra.x, BarraSup.Rec_Barra.y,
                    BarraSup.Rec_Barra.width, BarraSup.Rec_Barra.height, this);
        }
        if (Dificil) {
            g2.drawImage(BarraIzq.Barra.getImage(), BarraIzq.Rec_Barra.x, BarraIzq.Rec_Barra.y,
                    BarraIzq.Rec_Barra.width, BarraIzq.Rec_Barra.height, this);

            g2.drawImage(BarraDer.Barra.getImage(), BarraDer.Rec_Barra.x, BarraDer.Rec_Barra.y,
                    BarraDer.Rec_Barra.width, BarraDer.Rec_Barra.height, this);

            g2.drawImage(Franja2.getImage(), FranjaIzq.x, FranjaIzq.y, FranjaIzq.width, FranjaIzq.height, this);

            g2.drawImage(Franja2.getImage(), FranjaIzq.x, FranjaIzq.y, FranjaIzq.width, FranjaIzq.height, this);

            g2.drawImage(Franja2.getImage(), FranjaDer.x, FranjaDer.y, FranjaDer.width, FranjaDer.height, this);
        }
        if (Experto) {
            g2.drawImage(AgujeroImagen.getImage(), Rect_Agujero.x, Rect_Agujero.y, Rect_Agujero.width, Rect_Agujero.height, this);
        }
        g2.setFont(Letra);
        g2.setColor(Color.yellow);
        Sensor();
        g.drawImage(I, 0, 0, this);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    void Sensor() {
        if (BarraInf.Rec_Barra.intersects(Bola.Rec_Bola)) {
            Rectangle choque = BarraInf.Rec_Barra.intersection(Bola.Rec_Bola);
            if ((choque.getBounds().y + choque.getBounds().height)
                    < BarraInf.Rec_Barra.getBounds().y + BarraInf.Rec_Barra.getBounds().height) {
                Bola.Alto = BarraInf.Alto;
            }
        }
        Abajo();
        if (Medio) {
            if (BarraSup.Rec_Barra.intersects(Bola.Rec_Bola)) {
                Rectangle choque = BarraSup.Rec_Barra.intersection(Bola.Rec_Bola);
                if ((choque.getBounds().y + choque.getBounds().height) > BarraSup.Rec_Barra.getBounds().y) {
                    Bola.Abajo = true;
                }
            }
            Arriba();
        }
        if (Dificil) {
            if (BarraIzq.Rec_Barra.intersects(Bola.Rec_Bola)) {
                Rectangle choque = BarraIzq.Rec_Barra.intersection(Bola.Rec_Bola);
                if (choque.getBounds().x > BarraIzq.Rec_Barra.getBounds().x) {
                    Bola.Derecha = true;
                }
            }
            if (BarraDer.Rec_Barra.intersects(Bola.Rec_Bola)) {
                Rectangle choque = BarraDer.Rec_Barra.intersection(Bola.Rec_Bola);
                if (choque.getBounds().x + choque.getBounds().width > BarraDer.Rec_Barra.getBounds().x) {
                    Bola.Derecha = false;
                    Bola.Izquierda = true;
                }
            }
            Izquierda();
            Derecha();
        }
        if (Experto) {
            if (Rect_Agujero.intersects(Bola.Rec_Bola)) {
                if (Rect_Agujero.getMaxX() == Bola.Rec_Bola.getMinX()) {
                    Bola.Derecha = true;
                    System.out.println("--------choque");
                }
            }
            if (Rect_Agujero.intersects(Bola.Rec_Bola)) {
                int dir = (int) (Math.random() * 5);
                if (dir == 0) {
                    Bola.setIzquierda(true);
                    Bola.setDerecha(false);
                } else if (dir == 1) {
                    Bola.setDerecha(true);
                    Bola.setIzquierda(false);
                } else if (dir == 2) {
                    Bola.setAbajo(false);
                    Bola.setArriba(true);
                } else if (dir == 3) {
                    Bola.setAbajo(true);
                    Bola.setArriba(false);
                }
            }
        }
        if (Vidas <= 0) {
            g2.setColor(Color.red);
            g2.drawString("PERDIO", 200, 605);
        } else {
            g2.setColor(Color.GREEN);
            g2.drawString("Vidas:" + Vidas, 200, 605);
        }
        g2.setColor(Color.WHITE);
        g2.drawString("Tiempo: " + Tiempo.M + ":" + Tiempo.S, 20, 605);
//        System.out.println("Vidas: " + Vidas);
        repaint();
    }

    void Abajo() {
        if (Bola.Rec_Bola.intersects(FranjaInf)) {
            System.out.println(Bola.Rec_Bola.getMaxY() + " " + getHeight());
            if (Bola.Rec_Bola.getMaxY() == getHeight()) {
                System.out.println("----abajo");
                Vidas--;
            }
        }
    }

    private void Arriba() {
        if (Bola.Rec_Bola.getMinY() == 0) {
            System.out.println("-------arriba");
            Vidas--;
        }
    }

    private void Izquierda() {
        if (Bola.Rec_Bola.getMinX() == 0) {
            System.out.println("--------izquierda");
            Vidas--;
        }
    }

    void Crear() {
//        int x = (int) (Math.random() * getSize().width - 10);
//        int y = (int) (Math.random() * (getSize().height));
        Bola = new Bola(getWidth() / 2, getHeight() / 2);
        Bola.start();
    }

    private void Derecha() {
        if (Bola.Rec_Bola.getMaxX() == getWidth()) {
            System.out.println("----derecha");
            Vidas--;
        }
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        BarraInf.MoverX(me.getX());
        BarraSup.MoverX(me.getX());
        BarraDer.MoverY(me.getY());
        BarraIzq.MoverY(me.getY());
    }

    class Saga extends Thread {
        
        int Tiempo = 10000;

        public Saga() {
        }

        @Override
        public void run() {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            int era = 0;
            while (true) {
                try {
                    sleep(Tiempo);
                    ++era;
                    if (era == 1) {
                        Bola.suspend();
                        JOptionPane.showMessageDialog(null, "Ascenso al Nivel medio");
                        Medio = true;
                        Bola.resume();
                        Bola.Velocidad = 4;
                    } else if (era == 2) {
                        Bola.suspend();
                        JOptionPane.showMessageDialog(null, "Ascenso al Nivel dificil");
                        Dificil = true;
                        Bola.Velocidad = 3;
                        Bola.resume();
                    } else if (era == 3) {
                        Bola.suspend();
                        JOptionPane.showMessageDialog(null, "Ascenso al Nivel experto");
                        Experto = true;
                        Bola.Velocidad = 2;
                        Bola.resume();
                    } else if (era == 4) {
                        JOptionPane.showMessageDialog(null, "Has recibido una vida.");
                        Vidas++;
                        Bola.Velocidad = 1;
                    }
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    class Velocidad extends Thread {

        int Duracion = 2000;

        public Velocidad() {
        }

        @Override
        public void run() {
            try {
                super.run(); //To change body of generated methods, choose Tools | Templates.
                Bola.Velocidad = 4;
                sleep(Duracion);
                Bola.Velocidad = 3;
                sleep(Duracion);
                Bola.Velocidad = 2;
                sleep(Duracion);
                Bola.Velocidad = 1;
            } catch (InterruptedException ex) {
            }
        }
    }
}
