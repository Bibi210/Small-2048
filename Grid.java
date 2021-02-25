import java.awt.*;
import javax.swing.*;

public class Grid extends JPanel {
    private static final long serialVersionUID = 8817061009732255009L; // Jsp

    int spacing = 10;
    Cell[][] cells = new Cell[4][4];
    
    public Grid() {
        this.setLayout(null);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // Set the location of the new cell
                Point location = new Point(this.spacing + i * Cell.size, this.spacing + j * Cell.size);
                cells[i][j] = new Cell(location);
            }
        }     
    }

    public void paintComponent(Graphics g) {
        // Paint the window
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, Window.winWidth, Window.winHeight);
        
        // Set the font
        int fontSize = 40;
        g.setFont(new Font("Comic Sans MS", 1, fontSize));

        // Paint the grid
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // Fill the cells
                g.setColor(cells[i][j].color);
                g.fillRect(spacing + i * Cell.size, spacing + j * Cell.size, Cell.size - spacing, Cell.size - spacing);

                // Paint the digits
                g.setColor(Color.black);
                cells[i][j].setValue("2");
                cells[i][j].drawCell(g, fontSize);
            }
        }
    }
}
