public class GameLoop extends Thread {

    private final GameState  state;
    private final GameWindow window;

    public GameLoop(GameState state, GameWindow window) {
        this.state  = state;
        this.window = window;
    }

    public void run() {
        int contador = 0;
        try {
            while (state.energia >= 0) {
                state.numero = (int)(Math.random() * 26) + 65;
                window.letra.setText("" + (char) state.numero);
                window.letra.setForeground(GameColors.YELLOW);
                window.campo.setBackground(GameColors.PANEL);

                int nivel = (1000 - state.velocidad) / 200 + 1;
                window.nivelLabel.setText("NIVEL  " + nivel);
                window.timeBar.setMaximum(state.velocidad);

                for (int i = state.velocidad; i > 0; i--) {
                    window.timeBar.setValue(i);
                    window.tiempoNum.setText("" + i);
                    window.timeBar.setForeground(
                        i < state.velocidad * 0.2 ? GameColors.RED : GameColors.ACCENT);
                    window.repaint();
                    sleep(1);
                }

                if (state.numero == state.tecla) {
                    state.puntaje += 10;
                    contador      += 10;
                    window.feedback.setText("✓");
                    window.feedback.setForeground(GameColors.GREEN);
                    window.letra.setForeground(GameColors.GREEN);
                    window.campo.setBackground(GameColors.HIT_BG);
                } else {
                    state.energia -= 5;
                    window.feedback.setText("✗");
                    window.feedback.setForeground(GameColors.RED);
                    window.letra.setForeground(GameColors.RED);
                    window.campo.setBackground(GameColors.MISS_BG);
                }
                window.repaint();
                sleep(300);

                if (contador >= 100) {
                    contador = 0;
                    if (state.velocidad > 200) state.velocidad -= 200;
                }

                window.score.setText("★  Puntaje:  " + state.puntaje);
                window.nextLevel.setText("Próximo nivel en  " + (100 - contador) + " pts");
                window.levelBar.setValue(contador);
                window.energyBar.setValue(state.energia);
                window.energyBar.setString(state.energia + " / 50");
                window.energyBar.setForeground(
                    state.energia <= 20 ? GameColors.RED : GameColors.GREEN);
                window.feedback.setText("  ");
                window.repaint();
                sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        window.letra.setText("✗");
        window.letra.setForeground(GameColors.RED);
        window.etiqueta.setText("JUEGO TERMINADO  —  Puntaje final: " + state.puntaje);
        window.etiqueta.setForeground(GameColors.RED);
        window.nextLevel.setText("");
        window.feedback.setText("  ");
        window.timeBar.setValue(0);
        window.tiempoNum.setText("0");
        window.campo.setEditable(false);
        window.repaint();
    }
}
