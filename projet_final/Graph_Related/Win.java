package projet_final.Graph_Related;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import projet_final.Game_Data.Game_2048;
import projet_final.Game_Data.Coord;
import projet_final.Launch;

public class Win extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1L;

    static int WinSize = 600;

    Game_2048 plt;
    public Grid grid;
    JLabel score_label = new JLabel("Score:          ");
    int score;

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
        JMenuItem high_score = new JMenuItem("High Score");

        menuBar.add(file);
        
        file.add(new_game);
        new_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame(Launch.getGridSize());
            }
        });

        file.add(high_score);
        high_score.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Launch.showHighScore();
            }
        });

        menuBar.add(Box.createGlue());

        menuBar.add(score_label);

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
        score = (int) plt.count_score();
        this.setScore(score);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setScore(int scr) {
        this.score_label.setText("Score:  " + scr + "       ");
    }

    public void newGame(int size) {
        this.getContentPane().remove(grid);
        plt = new Game_2048(size);
        grid = new Grid(size, size);
        this.getContentPane().add(grid);
        this.grid.updateGrid(plt.grille);
        this.pack();
        this.invalidate();
        this.validate();
    }
}
