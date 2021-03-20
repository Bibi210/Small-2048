import java.util.*;

public class GameV2 {

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

    public GameV2(int gridSize) {
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
    }
    

    private void move(Coord movement) {
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
                        }
                    }
                }
            }
        }
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
                        fusionTuile_B_into_A(NewCoord,EnCours);
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

    public static void main(String[] args) {
        GameV2 Plateau = new GameV2(4);
        Plateau.addtuile(5);
        System.out.println(Plateau);
        Plateau.move(Coord.LEFT);
        Plateau.fusion_all(Coord.LEFT);
        System.out.println(Plateau);
    }
}
