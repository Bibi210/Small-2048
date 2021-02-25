import java.awt.*;
import java.util.Random;

public class Cell {
    Point position;
    String value;
    public static int size = 80;
    int speed = 1;
    Color color;
    
    public Cell(Point position) {
        this.position = position;
        this.value = "";

        // Set a random color to the cell (a modifié)
        Random random = new Random();
        float hue = random.nextFloat();
        float saturation = (random.nextInt(2000) + 1000) / 10000f;
        float luminance = 0.9f; 
        this.color = Color.getHSBColor(hue, saturation, luminance);;
    }

    // Function call by Grid to draw each cells of the grid
    public void drawCell(Graphics g, int fontSize){
        g.drawString(this.value, this.position.x + 22, this.position.y + 50);
    }

    // Test
    public void moveCell() {
        this.position.x += speed;
        this.position.y += speed;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
