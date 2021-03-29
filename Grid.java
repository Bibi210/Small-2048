import java.awt.*;
import javax.swing.*;

public class Grid extends JPanel{
    private static final long serialVersionUID = 1L;

    GridBagConstraints gbc = new GridBagConstraints();

    Cell [][] cells;
    
    // Use JPanel that containes all the cells
    public Grid(int xnbCell, int ynbCell) {
        this.cells = new Cell[xnbCell][ynbCell];
        
        this.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;

        for (int i = 0; i < xnbCell; i++) {
            for (int j = 0; j < ynbCell; j++) {
                Cell cell = new Cell(new Color(125, 125, 125));
                this.cells[j][i] = cell;
                gbc.gridx = i;
                gbc.gridy = j;
                this.add(cell, gbc);              
            }
        }
    }

    public void updateGrid(Tuile [][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                this.cells[j][i].setCellText(grid[j][i].value);
            }
        }
    }
}
