package projet_final.Graph_Related;

import java.awt.*;
import javax.swing.*;
import projet_final.Game_Data.Tuile;

public class Grid extends JPanel {
    private static final long serialVersionUID = 1L;

    GridBagConstraints gbc = new GridBagConstraints();

    Cell[][] cells;
    Color color;

    // Use JPanel that containes all the cells
    public Grid(int xnbCell, int ynbCell) {
        this.cells = new Cell[xnbCell][ynbCell];

        this.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;

        for (int i = 0; i < xnbCell; i++) {
            for (int j = 0; j < ynbCell; j++) {
                Cell cell = new Cell();
                this.cells[j][i] = cell;
                gbc.gridx = i;
                gbc.gridy = j;
                this.add(cell, gbc);
            }
        }
    }

    public void updateGrid(Tuile[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                this.cells[j][i].setCellText(grid[j][i].value);

                this.color = Cell.assignColor(grid[i][j].value);
                this.cells[i][j].setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue()));
            }
        }
    }
}
