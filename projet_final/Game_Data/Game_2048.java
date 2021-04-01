package projet_final.Game_Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

import javax.swing.JOptionPane;

/// Le jeu dans le terminal
public class Game_2048 {

    public Tuile[][] grille;
    public int gridSize;
    long score;
    long coup_jouer;
    List<Coord> Move_tried;

    public Game_2048(int gridSize) {
        this.grille = new Tuile[gridSize][gridSize];
        this.gridSize = gridSize;
        init_game();
    }

    // * Game Related Functions *//
    public void turn(Coord Sens) throws IOException {
        boolean is_turn_valid = false;
        if (move(Sens) > 0) {
            fusion_all(Sens);
            move(Sens);
            is_turn_valid = true;
        } else if (fusion_all(Sens) > 0) {
            move(Sens);
            is_turn_valid = true;
        }
        if (is_turn_valid) {
            Move_tried = new ArrayList<>();
            coup_jouer++;
            if (!addtuile(1)) {
                System.out.println(this);
                endgame();
            }
            count_score();
            System.out.println(this);
        } else {
            if (!Move_tried.contains(Sens)) {
                Move_tried.add(Sens);
            }
            if (Move_tried.size() == 4) {
                endgame();
            }
        }
    }

    private void endgame() throws IOException {
        System.out.println("Fin du jeu!");
        System.out.println("Votre Score est de :" + count_score());
        System.out.println(this);
        Files.write(Paths.get("projet_final/Game_Data/Game_Scores"), (Long.toString(score)+"\n").getBytes(), StandardOpenOption.APPEND);

        String fin = "Votre Score est de :" + Long.toString(count_score());
        JOptionPane.showMessageDialog(null, fin, "Fin De la Partie", JOptionPane.PLAIN_MESSAGE);
        init_game();
    }

    public long count_score() {
        long rst = 0;
        for (int x = 0; x < grille.length; x++) {
            for (int y = 0; y < grille[x].length; y++) {
                Coord Current_Tuile = new Coord(x, y);
                rst += getTuileValue(Current_Tuile);
            }
        }
        rst -= coup_jouer;
        rst /= gridSize;
        score = rst;
        return rst;
    }

    private void init_game() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grille[i][j] = new Tuile(i, j, 0);
            }
        }
        score = 0;
        coup_jouer = 0;
        addtuile(2);
        System.out.println(this);

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

    private int fusion_all(Coord movement) {
        int fusion_done = 0;
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
                        fusion_done++;
                    }
                }
            }
        }
        return fusion_done;
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
    // * Game Related Functions *//

    // * Quality Of Life Functions //*
    public Tuile getTuile(Coord Where) {
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
    // * Quality Of Life Functions //*

    // * Debug Display *//
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

}
