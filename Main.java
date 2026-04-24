public class Main {
    public static void main(String[] args) {
        GameState  state  = new GameState();
        GameWindow window = new GameWindow(state);
        window.setVisible(true);
        new GameLoop(state, window).start();
    }
}
