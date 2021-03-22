import javax.swing.JFrame;

public class Win extends JFrame{

    static int WinSize = 600;

    Win(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WinSize,WinSize);
        this.setVisible(true);
        this.setResizable(false);
    }
}
