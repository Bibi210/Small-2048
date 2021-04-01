package projet_final.Graph_Related;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

public class Cell extends JLabel {
    private static final long serialVersionUID = 1L;

    Color clr;

    public Cell() {
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 3, false);

        this.setFont(new Font(this.getFont().getName(), Font.PLAIN, 25));
        this.setText("");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setBackground(clr);
        this.setOpaque(true);
        this.setBorder(border);
        this.setPreferredSize(new Dimension(100, 100));
    }

    public void setCellText(int value) {
        this.setText("" + value);
    }

    public static Color assignColor(int value) {
        Color color;

        switch (value) {
        case 0:
            color = new Color(255, 255, 255);
            break;
        case 2:
            color = new Color(255, 153, 255);
            break;
        case 4:
            color = new Color(204, 153, 255);
            break;
        case 8:
            color = new Color(153, 153, 255);
            break;
        case 16:
            color = new Color(153, 204, 255);
            break;
        case 32:
            color = new Color(153, 255, 255);
            break;
        case 64:
            color = new Color(153, 255, 204);
            break;
        case 128:
            color = new Color(153, 255, 153);
            break;
        case 256:
            color = new Color(204, 255, 153);
            break;
        case 512:
            color = new Color(255, 255, 153);
            break;
        case 1024:
            color = new Color(255, 204, 153);
            break;
        case 2048:
            color = new Color(255, 153, 153);
            break;
        default:
            color = new Color(255);
            break;
        }

        return color;
    }
}
