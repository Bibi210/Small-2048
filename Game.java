import java.util.*;

public class Game {

    int taille;
    HashMap<Coord, Integer> Grille;
    private int max_X, max_Y;

    public Game(int grille_size) {
        init(grille_size, grille_size);
    }

    public Game(int max_X, int max_Y) {
        init(max_X, max_Y);
    }

    /// * Fusion de A dans B delete of A return la tuile qui glisse
    public int fusion(Coord A, Coord B) {
        int value_A = Grille.get(A);
        int value_B = Grille.get(B);

        Grille.put(B, value_A + value_B);
        Grille.put(A, 0);
        return value_A;
    }

    private void init(int x, int y) {
        this.max_X = x;
        this.max_Y = y;
        taille = max_X * max_Y;
        Grille = new HashMap<Coord, Integer>();
        for (int i = 0; i < max_X; i++) {
            for (int j = 0; j < max_Y; j++) {
                Coord Encours = new Coord(i,j);
                Grille.put(Encours,2);
            }
        }
    }

    private void spawn() {
    }

    public String toString() {
        StringJoiner output = new StringJoiner("");
        for (int i = 0; i < max_X; i++) {
            for (int j = 0; j < max_Y; j++) {
                Coord EnCours = new Coord(i, j);
                int value = Grille.getOrDefault(EnCours, 0);
                output.add(String.format("[%3d]", value));
            }
            output.add("\n");
        }
        return output.toString();
    }

    // Seul une des value peut être utiliser soit pas_x soit pas_y
    public boolean move(Coord Test, int pas_x, int pas_y) {
        int value = Grille.getOrDefault(Test, -1);
        if (value == -1 && value == 0) {
            return false;
        }
        if (pas_x != 0 && pas_y != 0) {
            System.err.println("Unaccepted Movement");
            System.exit(-1);
        }
        if (pas_y != 0) {
            for (int i = Test.y + pas_y; i < max_Y && i >= 0; i += pas_y) {
                Coord Encours = new Coord(Test.x, i);
                int Encours_value = Grille.getOrDefault(Encours, 0);
                if (Encours_value != 0) {
                    if (Encours_value == value) {
                        fusion(Test, Encours);
                        return true;
                    } else {
                        Encours.y -= pas_y;
                        if (!Test.equals(Encours) && Encours.in_limits(-1, max_X, -1, max_Y)) {
                            fusion(Test, Encours);
                            return true;
                        } else {

                            return false;
                        }

                    }
                }
            }
        } else {
            for (int i = Test.x + pas_x; i < max_X && i >= 0; i += pas_x) {
                Coord Encours = new Coord(i, Test.y);
                int Encours_value = Grille.get(Encours);
                if (Encours_value != 0) {
                    if (Encours_value == value) {
                        fusion(Test, Encours);
                        return true;
                    } else {
                        Encours.x -= pas_x;
                        if (!Test.equals(Encours) && Encours.in_limits(-1, max_X, -1, max_Y)) {
                            fusion(Test, Encours);
                            return true;
                        } else {
                            return false;
                        }

                    }
                }
            }
        }

        return false;
    }

    // -1 Gauche/Haut
    // 1 Bas/Droite
    // 0 Rien
    // Movement a droite Start a droite vice versa
    public static void main(String[] args) {

        Game game = new Game(4);
        for (int i = 0; i < 5; i++) {
            Coord testing = new Coord(2, 3);
            System.out.println(game);
            System.out.println("\n" + game.move(testing, 0, 1));
            testing.y--;
            System.out.println("\n" + game.move(testing, 0, 1));
            testing.y--;
            System.out.println("\n" + game.move(testing, 0, 1));
            testing.y--;
            System.out.println("\n" + game.move(testing, 0, 1));
            testing.y--;
        }

        System.out.println(game);
    }

}
