package Juegopong;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author julian
 */
public class Bienvenida extends JFrame implements ActionListener, MouseListener {

    JButton Facil = new JButton("Facil");
    JButton Medio = new JButton("Medio");
    JButton Dificil = new JButton("Dificil");
    JButton Experto = new JButton("Experto");
    JButton Saga = new JButton("Saga");
    Font Letra = new Font("Georgia", Font.BOLD, 13);
    Font LetraAvisos = new Font("URW Chancery L", Font.ITALIC, 17);
    Font LetraBotones = new Font("Century Schoolbook L", Font.ITALIC, 15);
    JLabel Sele = new JLabel("Seleccione un nivel a jugar:");
    JLabel Nota = new JLabel("");
    JLabel Nota2 = new JLabel("");
    ImageIcon Port;
    JButton Portada = new JButton("");

    Bienvenida() {
        setVisible(true);
        setSize(300, 430);
        setLayout(null);
//        Port = new ImageIcon(getClass().getResource("Imagenes/Portada.jpg"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Portada.setIcon(Port);

        int xx = 70;
        Portada.setBounds(0, 5, getWidth(), 140);
        Sele.setBounds(xx-20, Portada.getHeight() + 20, 250, 30);
        Facil.setBounds(xx, Sele.getY() + 30, 150, 30);
        Medio.setBounds(xx, Facil.getY() + 30, 150, 30);
        Dificil.setBounds(xx, Medio.getY() + 30, 150, 30);
        Experto.setBounds(xx, Dificil.getY() + 30, 150, 30);
        Saga.setBounds(xx, Experto.getY() + 30, 150, 30);
        Nota.setBounds(xx, Saga.getY() + 30, 400, 30);
        Nota2.setBounds(xx, Nota.getY() + 20, 400, 30);

        Facil.setFont(LetraBotones);
        Medio.setFont(LetraBotones);
        Dificil.setFont(LetraBotones);
        Experto.setFont(LetraBotones);
        Saga.setFont(LetraBotones);
        Sele.setFont(Letra);
        Nota.setFont(LetraAvisos);
        Nota2.setFont(LetraAvisos);

        Facil.addMouseListener(this);
        Medio.addMouseListener(this);
        Dificil.addMouseListener(this);
        Experto.addMouseListener(this);
        Saga.addMouseListener(this);

        Facil.addActionListener(this);
        Medio.addActionListener(this);
        Dificil.addActionListener(this);
        Experto.addActionListener(this);
        Saga.addActionListener(this);
        Portada.addActionListener(this);

        add(Facil);
        add(Medio);
        add(Dificil);
        add(Experto);
        add(Sele);
        add(Saga);
        add(Nota);
        add(Nota2);
        add(Portada);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Ventana V;
        if (ae.getSource() == Facil) {
            V = new Ventana(1);
        } else if (ae.getSource() == Medio) {
            V = new Ventana(2);
        } else if (ae.getSource() == Dificil) {
            V = new Ventana(3);
        } else if (ae.getSource() == Experto) {
            V = new Ventana(4);
        } else if (ae.getSource() == Saga) {
            V = new Ventana(5);
        } else if (ae.getSource() == Portada) {
            JOptionPane.showMessageDialog(null, "Autor: Julian David Mora Ramos\nAño:2013", "", WIDTH, Port);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == Facil) {
            Nota.setText("Este es el modo facil.");
            Nota.setText("Jugar como principiante.");
        } else if (me.getSource() == Medio) {
            Nota.setText("Este es el modo Medio.");
            Nota.setText("Modo de aprendizaje.");
        } else if (me.getSource() == Dificil) {
            Nota.setText("Este es el modo Dificil.");
            Nota.setText("Gana muchos puntos.");
        } else if (me.getSource() == Experto) {
            Nota.setText("Este es el modo Experto.");
            Nota2.setText("Preparate para ganar");
        } else if (me.getSource() == Saga) {
            Nota.setText("Este es el modo Saga");
            Nota2.setText("Se jugara de una forma especial");
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        Nota.setText("");
        Nota2.setText("");
    }
}
