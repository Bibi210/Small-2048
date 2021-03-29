package projet_final;

import javax.swing.SwingUtilities;
import projet_final.Game_Data.*;
import projet_final.Graph_Related.*;

class Lunch{
    public static void main(String[] args) {
        int gridSize = 3;
        Game_2048 Plateau = new Game_2048(gridSize);

        // Lance la fenêtre
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Win win = new Win("2048", gridSize, Plateau);
                win.grid.updateGrid(Plateau.grille);
            }
        });
    }
}