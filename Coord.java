import java.util.Objects;

public class Coord {
    int x, y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
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

}
