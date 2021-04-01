package projet_final.Graph_Related;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


import java.nio.file.Files;
import java.nio.file.Path;


import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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

    public static void showHighScore() throws IOException {
        String scores = Files.readString(Path.of("projet_final","Game_Data","Game_Scores"));
        JOptionPane.showMessageDialog(null, scores, "High Score", JOptionPane.PLAIN_MESSAGE);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("Menu");

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
                try {
                    Win.showHighScore();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
            try {
                plt.turn(Coord.UP);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        case KeyEvent.VK_DOWN:
            try {
                plt.turn(Coord.DOWN);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        case KeyEvent.VK_LEFT:
            try {
                plt.turn(Coord.LEFT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        case KeyEvent.VK_RIGHT:
            try {
                plt.turn(Coord.RIGHT);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
