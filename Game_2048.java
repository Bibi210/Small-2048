import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game_2048 implements KeyListener {

    Tuile[][] grille;
    int gridSize;

    public String toString() {
        StringJoiner output = new StringJoiner("");
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Coord EnCours = new Coord(i, j);
                output.add(String.format("[%3d]", getTuileValue(EnCours)));
            }
            output.add("\n");
        }
        return output.toString();
    }

    private boolean addtuile(int nbtuiles) {
        for (int i = 0; i < nbtuiles; i++) {
            // Liste de possibiliter
            List<Coord> possibiliter = new ArrayList<Coord>();
            Coord OnePosition;
            // Ajout de toutes les tuile vide dans la liste
            for (Tuile[] Colonne : grille) {
                for (Tuile tuile : Colonne) {
                    if (tuile.value == 0) {
                        OnePosition = new Coord(tuile.x, tuile.y);
                        possibiliter.add(OnePosition);
                    }
                }
            }
            // ? Si il n'y a pas de possibliter fin du jeu ?
            if (possibiliter.size() == 0)
                return false;
            Random rand = new Random();
            int newvalue = rand.nextInt(4) >= 2 ? 2 : 4;
            OnePosition = possibiliter.get(rand.nextInt(possibiliter.size()));
            setTuileValue(OnePosition, newvalue);
        }
        return true;
    }

    public Game_2048(int gridSize) {
        this.grille = new Tuile[gridSize][gridSize];
        this.gridSize = gridSize;
        init_game();
    }

    private void init_game() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grille[i][j] = new Tuile(i, j, 0);
            }
        }
        addtuile(2);
        System.out.println(this);

    }

    private void turn(Coord Sens) {
        if (move(Sens) > 0) {
            fusion_all(Sens);
            move(Sens);
            if (!addtuile(1)) {
                endgame();
            }
            System.out.println(this);
        }
    }

    private void endgame() {
        System.out.println("Fin du jeu!");
        System.out.println(this);
        System.exit(0);
    }

    private int move(Coord movement) {
        int move_made = 0;
        boolean moved = true;
        while (moved) {
            moved = false;
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    Coord EnCours = grille[i][j];
                    Coord NewCoord = new Coord(EnCours.x, EnCours.y);
                    NewCoord.add(movement);
                    if (NewCoord.in_limits(-1, gridSize, -1, gridSize)) {
                        if (getTuileValue(EnCours) == 0) {
                            continue;
                        }
                        if (getTuileValue(NewCoord) == 0) {
                            switchTuileValue(EnCours, NewCoord);
                            moved = true;
                            move_made++;
                        }
                    }
                }
            }
        }
        return move_made;
    }

    private void fusion_all(Coord movement) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Coord EnCours = grille[i][j];
                Coord NewCoord = new Coord(EnCours.x, EnCours.y);
                NewCoord.add(movement);
                if (NewCoord.in_limits(-1, gridSize, -1, gridSize)) {
                    if (getTuileValue(EnCours) == 0) {
                        continue;
                    }
                    if (getTuileValue(NewCoord) == getTuileValue(EnCours)) {
                        fusionTuile_B_into_A(NewCoord, EnCours);
                    }
                }
            }
        }
    }

    private Tuile getTuile(Coord Where) {
        return grille[Where.x][Where.y];
    }

    private int getTuileValue(Coord Where) {
        return grille[Where.x][Where.y].value;
    }

    private void setTuileValue(Coord Where, int Value) {
        grille[Where.x][Where.y].value = Value;
    }

    private void switchTuileValue(Coord TuileA, Coord TuileB) {
        int valueA = getTuileValue(TuileA);
        int valueB = getTuileValue(TuileB);
        setTuileValue(TuileA, valueB);
        setTuileValue(TuileB, valueA);
    }

    private void fusionTuile_B_into_A(Coord TuileA, Coord TuileB) {
        int newvalue = getTuileValue(TuileA) + getTuileValue(TuileB);
        setTuileValue(TuileA, newvalue);
        setTuileValue(TuileB, 0);
    }

    @Override
    public void keyReleased(KeyEvent Key) {
        switch (Key.getKeyCode()) {
        case KeyEvent.VK_UP:
            turn(Coord.UP);
            break;
        case KeyEvent.VK_DOWN:
            turn(Coord.DOWN);
            break;
        case KeyEvent.VK_LEFT:
            turn(Coord.LEFT);
            break;
        case KeyEvent.VK_RIGHT:
            turn(Coord.RIGHT);
            break;
        default:
            break;
        }

    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }

    public static void main(String[] args) {
        Game_2048 Plateau = new Game_2048(4);

    }
}