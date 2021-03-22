import java.util.StringJoiner;

public class Tuile extends Coord {
    int value;
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
