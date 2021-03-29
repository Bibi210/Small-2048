package projet_final.Graph_Related;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import projet_final.Game_Data.Game_2048;
import projet_final.Game_Data.Coord;

public class Win extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1L;

    static int WinSize = 600;

    public Grid grid;
    Game_2048 plt;

    public Win(String title, int gridSize, Game_2048 plateau) {
        grid = new Grid(gridSize, gridSize);
        plt = plateau;

        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        this.addKeyListener(this);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);

        this.setJMenuBar(createMenuBar());
        this.add(grid);
        this.pack();
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");

        JMenuItem new_game = new JMenuItem("New Game");

        menuBar.add(file);
        file.add(new_game);

        return menuBar;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent Key) {
        switch (Key.getKeyCode()) {
        case KeyEvent.VK_UP:
            plt.turn(Coord.UP);
            break;
        case KeyEvent.VK_DOWN:
            plt.turn(Coord.DOWN);
            break;
        case KeyEvent.VK_LEFT:
            plt.turn(Coord.LEFT);
            break;
        case KeyEvent.VK_RIGHT:
            plt.turn(Coord.RIGHT);
            break;
        default:
            break;
        }
        this.grid.updateGrid(plt.grille);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
