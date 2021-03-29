package projet_final.Game_Data;

import java.util.Objects;

/// Gestion de Coordonnées
public class Coord {
    int x, y;
    public static final Coord LEFT = new Coord(0, -1);
    public static final Coord RIGHT = new Coord(0, 1);
    public static final Coord UP = new Coord(-1, 0);
    public static final Coord DOWN = new Coord(1, 0);

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coord() {
        x = -1;
        y = -1;
    }

    public String toString() {
        return "Coord: \n" + "X: " + x + "|Y: " + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (getClass() != o.getClass() || o == null) {
            return false;
        }
        Coord other = (Coord) o;
        return Integer.compare(x, other.x) == 0 && Integer.compare(y, other.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public boolean in_limits(int min_x, int max_x, int min_y, int max_y) {
        if (x > min_x && x < max_x) {
            if (y > min_y && y < max_y) {
                return true;
            }
        }
        return false;
    }

    public void add(Coord to_add) {
        this.x += to_add.x;
        this.y += to_add.y;
    }

    public void minus(Coord to_minus) {
        this.x -= to_minus.x;
        this.y -= to_minus.y;
    }

}
