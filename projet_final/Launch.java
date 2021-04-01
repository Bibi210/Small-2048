package projet_final;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import projet_final.Game_Data.*;
import projet_final.Graph_Related.*;

public class Launch {
    public static void main(String[] args) {
        int gs = getGridSize();

        Game_2048 Plateau = new Game_2048(gs);

        // Lance la fenêtre
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Win win = new Win("2048", Plateau.gridSize, Plateau);
                win.grid.updateGrid(Plateau.grille);
            }
        });
    }

    public static int getGridSize() {
        String grid_size = JOptionPane.showInputDialog(null, "Select a grid size (e.g. 3)", "New game",
                JOptionPane.QUESTION_MESSAGE);
        int gs = 3;
        try {
            gs = Integer.parseInt(grid_size);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "You've typed a wrong size type, 3 has been choosed by default",
                    "New game", JOptionPane.WARNING_MESSAGE);
        }
        return gs;
    }

    
}