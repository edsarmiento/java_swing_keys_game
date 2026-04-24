
//AUTOR
/*************Eleazar David Sarmiento Torres************/

import javax.swing.*;
import java.awt.*;

public class Teclasgame extends JFrame {

    static final Color BG      = new Color(22, 22, 35);
    static final Color PANEL   = new Color(38, 38, 58);
    static final Color ACCENT  = new Color(90, 190, 255);
    static final Color YELLOW  = new Color(255, 228, 70);
    static final Color GREEN   = new Color(80, 220, 140);
    static final Color RED     = new Color(255, 80, 80);
    static final Color PURPLE  = new Color(190, 120, 255);
    static final Color FG      = new Color(210, 210, 230);

    public Teclasgame() {
        setTitle("Keys Game — David Sarmiento");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel(new BorderLayout(0, 0));
        root.setBackground(BG);
        root.setBorder(BorderFactory.createEmptyBorder(14, 20, 16, 20));

        // ── Header: title ── level ─────────────────────────────
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BG);
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JLabel title = new JLabel("KEYS  GAME");
        title.setFont(new Font("Arial", Font.BOLD, 15));
        title.setForeground(ACCENT);
        header.add(title, BorderLayout.WEST);

        nivelLabel.setText("NIVEL  1");
        nivelLabel.setFont(new Font("Arial", Font.BOLD, 15));
        nivelLabel.setForeground(ACCENT);
        header.add(nivelLabel, BorderLayout.EAST);
        root.add(header, BorderLayout.NORTH);

        // ── Center: big letter + feedback icon + time bar ─────
        JPanel center = new JPanel(new BorderLayout(0, 10));
        center.setBackground(BG);

        // Letter + feedback icon side by side
        JPanel letterArea = new JPanel(new BorderLayout(0, 0));
        letterArea.setBackground(BG);

        letra.setFont(new Font("Arial", Font.BOLD, 130));
        letra.setText("A");
        letra.setHorizontalAlignment(SwingConstants.CENTER);
        letra.setForeground(YELLOW);
        letterArea.add(letra, BorderLayout.CENTER);

        // Feedback icon (✓ / ✗) shown briefly after each key press
        feedback.setFont(new Font("Arial", Font.BOLD, 40));
        feedback.setText("  ");
        feedback.setHorizontalAlignment(SwingConstants.LEFT);
        feedback.setForeground(GREEN);
        feedback.setPreferredSize(new Dimension(55, 60));
        letterArea.add(feedback, BorderLayout.EAST);

        center.add(letterArea, BorderLayout.CENTER);

        // Time bar + countdown number
        JPanel timeRow = new JPanel(new BorderLayout(8, 0));
        timeRow.setBackground(BG);
        JLabel timeLbl = styledLabel("Tiempo", 11);
        timeLbl.setPreferredSize(new Dimension(55, 14));
        timeRow.add(timeLbl, BorderLayout.WEST);
        styleBar(timeBar, ACCENT, 0, 1000, 1000);
        timeRow.add(timeBar, BorderLayout.CENTER);
        tiempoNum.setFont(new Font("Arial", Font.BOLD, 11));
        tiempoNum.setForeground(ACCENT);
        tiempoNum.setText("1000");
        tiempoNum.setPreferredSize(new Dimension(40, 14));
        tiempoNum.setHorizontalAlignment(SwingConstants.RIGHT);
        timeRow.add(tiempoNum, BorderLayout.EAST);
        center.add(timeRow, BorderLayout.SOUTH);
        root.add(center, BorderLayout.CENTER);

        // ── Footer ─────────────────────────────────────────────
        JPanel footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBackground(BG);

        JSeparator sep = new JSeparator();
        sep.setForeground(PANEL);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        footer.add(Box.createVerticalStrut(8));
        footer.add(sep);
        footer.add(Box.createVerticalStrut(8));

        // Energy row
        JPanel energyRow = new JPanel(new BorderLayout(8, 0));
        energyRow.setBackground(BG);
        energyRow.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        JLabel eLbl = styledLabel("♥  Energia", 12);
        eLbl.setForeground(RED);
        eLbl.setPreferredSize(new Dimension(80, 16));
        energyRow.add(eLbl, BorderLayout.WEST);
        styleBar(energyBar, GREEN, 0, 50, 50);
        energyBar.setStringPainted(true);
        energyBar.setString("50 / 50");
        energyBar.setPreferredSize(new Dimension(0, 18));
        energyRow.add(energyBar, BorderLayout.CENTER);
        footer.add(energyRow);

        // Score row (big, prominent)
        JPanel scoreRow = new JPanel(new BorderLayout(8, 0));
        scoreRow.setBackground(BG);
        scoreRow.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        score.setText("★  Puntaje:  0");
        score.setFont(new Font("Arial", Font.BOLD, 16));
        score.setForeground(GREEN);
        scoreRow.add(score, BorderLayout.WEST);
        nextLevel.setText("Próximo nivel en  100 pts");
        nextLevel.setFont(new Font("Arial", Font.PLAIN, 12));
        nextLevel.setForeground(FG);
        scoreRow.add(nextLevel, BorderLayout.EAST);
        footer.add(scoreRow);

        // Level progress bar
        JPanel lvlRow = new JPanel(new BorderLayout(8, 0));
        lvlRow.setBackground(BG);
        lvlRow.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));
        JLabel lvlLbl = styledLabel("Avance", 11);
        lvlLbl.setPreferredSize(new Dimension(55, 14));
        lvlRow.add(lvlLbl, BorderLayout.WEST);
        styleBar(levelBar, PURPLE, 0, 100, 0);
        lvlRow.add(levelBar, BorderLayout.CENTER);
        footer.add(lvlRow);

        // Input row
        JPanel inputRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 2));
        inputRow.setBackground(BG);
        etiqueta.setText("Escribe la letra mostrada →");
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 12));
        etiqueta.setForeground(FG);
        inputRow.add(etiqueta);

        campo.setColumns(2);
        campo.setFont(new Font("Arial", Font.BOLD, 22));
        campo.setHorizontalAlignment(JTextField.CENTER);
        campo.setBackground(PANEL);
        campo.setForeground(YELLOW);
        campo.setCaretColor(YELLOW);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT, 2),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        campo.setText("A");
        campo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char upper = Character.toUpperCase(evt.getKeyChar());
                campo.setText("" + upper);
                tecla = (int) upper;
                evt.consume();
            }
        });
        inputRow.add(campo);
        footer.add(inputRow);

        root.add(footer, BorderLayout.SOUTH);

        getContentPane().setBackground(BG);
        getContentPane().add(root);
        pack();
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });

        new Hilo(this).start();
    }

    // ── Helpers ───────────────────────────────────────────────

    private JLabel styledLabel(String text, int size) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Arial", Font.PLAIN, size));
        l.setForeground(FG);
        return l;
    }

    private void styleBar(JProgressBar bar, Color fg, int min, int max, int val) {
        bar.setMinimum(min);
        bar.setMaximum(max);
        bar.setValue(val);
        bar.setStringPainted(false);
        bar.setForeground(fg);
        bar.setBackground(PANEL);
        bar.setPreferredSize(new Dimension(0, 11));
        bar.setBorderPainted(false);
    }

    public static void main(String args[]) {
        Teclasgame t = new Teclasgame();
        t.setVisible(true);
    }

    // ── Game state ────────────────────────────────────────────
    public int puntaje   = 0;
    public int energia   = 50;
    public int velocidad = 1000;
    public int tecla     = 0;
    public int numero    = 0;

    // ── UI components ─────────────────────────────────────────
    public JLabel       nivelLabel = new JLabel();
    public JLabel       etiqueta   = new JLabel();
    public JLabel       score      = new JLabel();
    public JLabel       nextLevel  = new JLabel();
    public JLabel       feedback   = new JLabel();
    public JLabel       tiempoNum  = new JLabel();
    public JTextField   campo      = new JTextField();
    public JLabel       letra      = new JLabel();
    public JProgressBar timeBar    = new JProgressBar();
    public JProgressBar energyBar  = new JProgressBar();
    public JProgressBar levelBar   = new JProgressBar();
}

class Hilo extends Thread {
    public Hilo(Teclasgame tg) { tgame = tg; }

    public void run() {
        int contador = 0;
        try {
            while (tgame.energia >= 0) {
                tgame.numero = (int)(Math.random() * 26) + 65;
                tgame.letra.setText("" + (char) tgame.numero);
                tgame.letra.setForeground(Teclasgame.YELLOW);
                tgame.campo.setBackground(Teclasgame.PANEL);

                int nivel = (1000 - tgame.velocidad) / 200 + 1;
                tgame.nivelLabel.setText("NIVEL  " + nivel);
                tgame.timeBar.setMaximum(tgame.velocidad);

                for (int i = tgame.velocidad; i > 0; i--) {
                    tgame.timeBar.setValue(i);
                    tgame.tiempoNum.setText("" + i);
                    // Turn time bar red in last 20%
                    tgame.timeBar.setForeground(
                        i < tgame.velocidad * 0.2 ? Teclasgame.RED : Teclasgame.ACCENT);
                    tgame.repaint();
                    sleep(1);
                }

                boolean hit = (tgame.numero == tgame.tecla);
                if (hit) {
                    tgame.puntaje += 10;
                    contador     += 10;
                    // Flash green
                    tgame.feedback.setText("✓");
                    tgame.feedback.setForeground(Teclasgame.GREEN);
                    tgame.letra.setForeground(Teclasgame.GREEN);
                    tgame.campo.setBackground(new Color(30, 80, 50));
                } else {
                    tgame.energia -= 5;
                    // Flash red
                    tgame.feedback.setText("✗");
                    tgame.feedback.setForeground(Teclasgame.RED);
                    tgame.letra.setForeground(Teclasgame.RED);
                    tgame.campo.setBackground(new Color(80, 25, 25));
                }
                tgame.repaint();
                sleep(300); // hold feedback visible briefly

                if (contador >= 100) {
                    contador = 0;
                    if (tgame.velocidad > 200) tgame.velocidad -= 200;
                }

                tgame.score.setText("★  Puntaje:  " + tgame.puntaje);
                tgame.nextLevel.setText("Próximo nivel en  " + (100 - contador) + " pts");
                tgame.levelBar.setValue(contador);
                tgame.energyBar.setValue(tgame.energia);
                tgame.energyBar.setString(tgame.energia + " / 50");
                tgame.energyBar.setForeground(
                    tgame.energia <= 20 ? Teclasgame.RED : Teclasgame.GREEN);
                tgame.feedback.setText("  ");
                tgame.repaint();
                sleep(10);
            }
        } catch (InterruptedException e) { e.printStackTrace(); }

        // Game over
        tgame.letra.setText("✗");
        tgame.letra.setForeground(Teclasgame.RED);
        tgame.etiqueta.setText("JUEGO TERMINADO  —  Puntaje final: " + tgame.puntaje);
        tgame.etiqueta.setForeground(Teclasgame.RED);
        tgame.nextLevel.setText("");
        tgame.feedback.setText("  ");
        tgame.timeBar.setValue(0);
        tgame.tiempoNum.setText("0");
        tgame.campo.setEditable(false);
        tgame.repaint();
    }

    private Teclasgame tgame;
}
