import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;   

import java.awt.Color;
import java.awt.Dimension;

public class Cell extends JLabel{
    private static final long serialVersionUID = 1L;

    Color clr;

    public Cell(Color c) {
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 3, false);
        clr = c;
       
        this.setText("");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setBackground(c);
        this.setOpaque(true);
        this.setBorder(border);
        this.setPreferredSize(new Dimension(100, 100));
    }

    public void setCellText(int value) {
        this.setText("" + value);
    }
}
