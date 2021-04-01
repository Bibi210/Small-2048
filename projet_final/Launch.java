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
        String grid_size = JOptionPane.showInputDialog(null, "Select a grid size (e.g. 3x3)", "New game",
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

    // J'ai mis la fonction la par défault mais tu peux la bouger ou tu veux
    public static void showHighScore(/* Je te laisse mettre le paramètre que tu veux*/) {
        String scores = "High scores:\n46516\n6448"; // format les scores en une string de ce style
        /*
         * si t'as une meilleure idée pour présenter le score fais comme tu le sens faut
         * juste que ce soit une string au final
         */
        JOptionPane.showMessageDialog(null, scores, "High Score", JOptionPane.PLAIN_MESSAGE);
    }
    // PS: cette fonction je l'ai appelé dans Win dans la fonction "createMenuBar"
    // et dans Game_2048 dans la fonction "endgame"
}