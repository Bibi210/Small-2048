import javax.swing.*;

public class Window extends JFrame {
    private static final long serialVersionUID = -2285069498553778239L; // Jsp

    // Window width and height
    public static int winWidth = 1920;
    public static int winHeight = 1080;
    
    
    public Window() {
        // Intitialize the window
        this.setTitle("2048");
        this.setSize(winWidth, winHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        // Initialize the grid
        Grid grid = new Grid();
        this.setContentPane(grid);
    }
}
