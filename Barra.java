package Juegopong;

import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author julian
 */
class Barra {

    int X;
    int Ancho = 120;
    int Alto = 20;
    int Y;
    Rectangle Rec_Barra = new Rectangle(X, Y, Ancho, Alto);
    ImageIcon Barra;

    public Barra(int X, int Y, int Ancho, int Alto) {
        this.X = X;
        this.Y = Y;
        this.Ancho = Ancho;
        this.Alto = Alto;
        Rec_Barra = new Rectangle(X, Y, Ancho, Alto);
    }

    void MoverX(int X) {
        Rec_Barra.setLocation((X - (Ancho / 2)), Y);
    }

    void MoverY(int Y) {
        Rec_Barra.setLocation(X, (Y - (Ancho / 2)));
    }
}
