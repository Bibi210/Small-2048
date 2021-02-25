public class Main implements Runnable {

    Window window = new Window(); 
    public static void main(String[] args) {
        new Thread(new Main()).start(); // Créer l'instance du jeu 
    }

    @Override
    // Fonction update
    public void run() {
        while (true) {
            window.repaint();
        }
    }
}