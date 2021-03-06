package Juegopong;

import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author julian
 */
final class Ventana extends JFrame implements ActionListener {

    CCanvas C = new CCanvas();
    Button Lanzar = new Button("Lanzar Pelota");
    Button Parar = new Button("Parar");
    Font Letra = new Font("Calibri", Font.PLAIN, 12);

    public Ventana(int nivel) {
        setVisible(true);
        setSize(1000, 700);
        setLayout(null);

        C.setBounds(0, 0, getWidth(), getHeight() - 80);
        add(C);

        Lanzar.addActionListener(this);
        Parar.addActionListener(this);
        Lanzar.setBounds(10, C.getHeight() + 20, 140, 25);
        Parar.setBounds(Lanzar.getX() + Lanzar.getWidth(), Lanzar.getY(), 140, 25);

        add(Lanzar);
        add(Parar);

        Lanzar.setFont(Letra);
        Parar.setFont(Letra);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ControlNivel(nivel);

        C.Crear();
        C.Velocidad.start();
        C.Bola.suspend();
        C.Velocidad.suspend();
        C.Saga.suspend();
    }

    Ventana() {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Lanzar) {
            C.Bola.resume();
            C.Velocidad.resume();
            C.Saga.resume();
        } else if (ae.getSource() == Parar) {
            C.Bola.suspend();
            C.Velocidad.suspend();
            C.Saga.suspend();
        }
    }

    void ControlNivel(int nivel) {
        if (nivel == 2) {
            C.Medio = true;
        } else if (nivel == 3) {
            C.Medio = true;
            C.Dificil = true;
        } else if (nivel == 4) {
            C.Medio = true;
            C.Dificil = true;
            C.Experto = true;
        } else if (nivel == 5) {
            C.Saga.start();
        }
    }
}
