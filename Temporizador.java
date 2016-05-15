package Juegopong;

import java.awt.Label;

/**
 *
 * @author julian
 */
public class Temporizador extends Thread {

    int S;
    int M;

    public Temporizador() {
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        while (true) {
            try {
                sleep(1000);
                S++;
                if (S == 60) {
                    S = 0;
                    M++;
                }
            } catch (InterruptedException ex) {
            }
        }
    }
}
