/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Juegopong;

import java.awt.Rectangle;
import static java.lang.Thread.sleep;
import javax.swing.ImageIcon;

/**
 *
 * @author julian
 */
class Bola extends Thread {

    boolean Arriba;
    boolean Abajo;
    boolean Derecha;
    boolean Izquierda;
    private int gravedad;
    int Ancho;
    int Alto = 400;
    private int Radio = 40;
    int X = 200;
    int Y = 300;
    int Velocidad = 4;
    Rectangle Rec_Bola = new Rectangle(X, Y, Radio, Radio);
    ImageIcon Pelota = new ImageIcon(getClass().getResource("Imagenes/Pelota.png"));

    Bola() {
        Arriba = Izquierda = false;
        Derecha = Abajo = true;
    }

    Bola(int x, int y) {
        Arriba = Izquierda = false;
        Derecha = Abajo = true;
        X = x;
        Y = y;
        Rec_Bola = new Rectangle(X, Y, Radio, Radio);
    }

    @Override
    public void run() {
        super.run();
        long c = 0;
        gravedad = (int) (Math.random() * 3) + 1;
        while (true) {
            c++;
            try {
                if (isDerecha()) {
                    if (X < Ancho - getRadio()) {
                        X++;
                    } else {
                        setIzquierda(true);
                        setDerecha(false);
                    }
                } else if (isIzquierda()) {
                    if (X > 0) {
                        X--;
                    } else {
                        setDerecha(true);
                        setIzquierda(false);
                    }
                }
                if (isAbajo()) {
                    if (Y < Alto - getRadio()) {
                        if (c % gravedad == 0) {
                            Y++;
                        }
                    } else {
                        setAbajo(false);
                        setArriba(true);
                    }
                } else if (isArriba()) {
                    if (Y > 0) {
                        if (c % gravedad == 0) {
                            Y--;
                        }
                    } else {
                        setAbajo(true);
                        setArriba(false);
                    }
                }
                sleep(Velocidad);
                Rec_Bola.setBounds(X, Y, Radio, Radio);
            } catch (InterruptedException ex) {
            }
        }
    }

    public boolean isArriba() {
        return Arriba;
    }

    public void setArriba(boolean Arriba) {
        this.Arriba = Arriba;
    }

    public boolean isAbajo() {
        return Abajo;
    }

    public void setAbajo(boolean Abajo) {
        this.Abajo = Abajo;
    }

    public boolean isDerecha() {
        return Derecha;
    }

    public void setDerecha(boolean Derecha) {
        this.Derecha = Derecha;
    }

    public boolean isIzquierda() {
        return Izquierda;
    }

    public void setIzquierda(boolean Izquierda) {
        this.Izquierda = Izquierda;
    }

    public int getRadio() {
        return Radio;
    }

    public void setRadio(int Radio) {
        this.Radio = Radio;
    }
}
