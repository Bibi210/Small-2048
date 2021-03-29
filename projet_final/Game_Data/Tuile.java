package projet_final.Game_Data;

import java.util.StringJoiner;

/// Une extention de Coord avec un affichage et une valeur
public class Tuile extends Coord {

    public int value;

    // Couleur
    public Tuile(int x, int y, int value) {
        super(x, y);
        this.value = value;
    }

    public String toString() {
        StringJoiner output = new StringJoiner("");
        output.add("______________\n");
        output.add("Tuile :\n");
        output.add(super.toString() + "\n");
        output.add("value : " + value);
        return output.toString();
    }

}
