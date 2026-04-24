import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

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

    public GameWindow(GameState state) {
        setTitle("Keys Game — David Sarmiento");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel(new BorderLayout(0, 0));
        root.setBackground(GameColors.BG);
        root.setBorder(BorderFactory.createEmptyBorder(14, 20, 16, 20));

        root.add(buildHeader(), BorderLayout.NORTH);
        root.add(buildCenter(), BorderLayout.CENTER);
        root.add(buildFooter(state), BorderLayout.SOUTH);

        getContentPane().setBackground(GameColors.BG);
        getContentPane().add(root);
        pack();
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(GameColors.BG);
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JLabel title = new JLabel("KEYS  GAME");
        title.setFont(new Font("Arial", Font.BOLD, 15));
        title.setForeground(GameColors.ACCENT);
        header.add(title, BorderLayout.WEST);

        nivelLabel.setText("NIVEL  1");
        nivelLabel.setFont(new Font("Arial", Font.BOLD, 15));
        nivelLabel.setForeground(GameColors.ACCENT);
        header.add(nivelLabel, BorderLayout.EAST);

        return header;
    }

    private JPanel buildCenter() {
        JPanel center = new JPanel(new BorderLayout(0, 10));
        center.setBackground(GameColors.BG);

        JPanel letterArea = new JPanel(new BorderLayout());
        letterArea.setBackground(GameColors.BG);

        letra.setFont(new Font("Arial", Font.BOLD, 130));
        letra.setText("A");
        letra.setHorizontalAlignment(SwingConstants.CENTER);
        letra.setForeground(GameColors.YELLOW);
        letterArea.add(letra, BorderLayout.CENTER);

        feedback.setFont(new Font("Arial", Font.BOLD, 40));
        feedback.setText("  ");
        feedback.setHorizontalAlignment(SwingConstants.LEFT);
        feedback.setForeground(GameColors.GREEN);
        feedback.setPreferredSize(new Dimension(55, 60));
        letterArea.add(feedback, BorderLayout.EAST);

        center.add(letterArea, BorderLayout.CENTER);
        center.add(buildTimeRow(), BorderLayout.SOUTH);

        return center;
    }

    private JPanel buildTimeRow() {
        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setBackground(GameColors.BG);

        JLabel lbl = styledLabel("Tiempo", 11);
        lbl.setPreferredSize(new Dimension(55, 14));
        row.add(lbl, BorderLayout.WEST);

        styleBar(timeBar, GameColors.ACCENT, 0, 1000, 1000);
        row.add(timeBar, BorderLayout.CENTER);

        tiempoNum.setFont(new Font("Arial", Font.BOLD, 11));
        tiempoNum.setForeground(GameColors.ACCENT);
        tiempoNum.setText("1000");
        tiempoNum.setPreferredSize(new Dimension(40, 14));
        tiempoNum.setHorizontalAlignment(SwingConstants.RIGHT);
        row.add(tiempoNum, BorderLayout.EAST);

        return row;
    }

    private JPanel buildFooter(GameState state) {
        JPanel footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBackground(GameColors.BG);

        JSeparator sep = new JSeparator();
        sep.setForeground(GameColors.PANEL);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        footer.add(Box.createVerticalStrut(8));
        footer.add(sep);
        footer.add(Box.createVerticalStrut(8));
        footer.add(buildEnergyRow());
        footer.add(buildScoreRow());
        footer.add(buildLevelRow());
        footer.add(buildInputRow(state));

        return footer;
    }

    private JPanel buildEnergyRow() {
        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setBackground(GameColors.BG);
        row.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        JLabel lbl = styledLabel("♥  Energia", 12);
        lbl.setForeground(GameColors.RED);
        lbl.setPreferredSize(new Dimension(80, 16));
        row.add(lbl, BorderLayout.WEST);

        styleBar(energyBar, GameColors.GREEN, 0, 50, 50);
        energyBar.setStringPainted(true);
        energyBar.setString("50 / 50");
        energyBar.setPreferredSize(new Dimension(0, 18));
        row.add(energyBar, BorderLayout.CENTER);

        return row;
    }

    private JPanel buildScoreRow() {
        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setBackground(GameColors.BG);
        row.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));

        score.setText("★  Puntaje:  0");
        score.setFont(new Font("Arial", Font.BOLD, 16));
        score.setForeground(GameColors.GREEN);
        row.add(score, BorderLayout.WEST);

        nextLevel.setText("Próximo nivel en  100 pts");
        nextLevel.setFont(new Font("Arial", Font.PLAIN, 12));
        nextLevel.setForeground(GameColors.FG);
        row.add(nextLevel, BorderLayout.EAST);

        return row;
    }

    private JPanel buildLevelRow() {
        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setBackground(GameColors.BG);
        row.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));

        JLabel lbl = styledLabel("Avance", 11);
        lbl.setPreferredSize(new Dimension(55, 14));
        row.add(lbl, BorderLayout.WEST);

        styleBar(levelBar, GameColors.PURPLE, 0, 100, 0);
        row.add(levelBar, BorderLayout.CENTER);

        return row;
    }

    private JPanel buildInputRow(GameState state) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 2));
        row.setBackground(GameColors.BG);

        etiqueta.setText("Escribe la letra mostrada →");
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 12));
        etiqueta.setForeground(GameColors.FG);
        row.add(etiqueta);

        campo.setColumns(2);
        campo.setFont(new Font("Arial", Font.BOLD, 22));
        campo.setHorizontalAlignment(JTextField.CENTER);
        campo.setBackground(GameColors.PANEL);
        campo.setForeground(GameColors.YELLOW);
        campo.setCaretColor(GameColors.YELLOW);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GameColors.ACCENT, 2),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        campo.setText("A");
        campo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char upper = Character.toUpperCase(evt.getKeyChar());
                campo.setText("" + upper);
                state.tecla = (int) upper;
                evt.consume();
            }
        });
        row.add(campo);

        return row;
    }

    private JLabel styledLabel(String text, int size) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Arial", Font.PLAIN, size));
        l.setForeground(GameColors.FG);
        return l;
    }

    private void styleBar(JProgressBar bar, Color fg, int min, int max, int val) {
        bar.setMinimum(min);
        bar.setMaximum(max);
        bar.setValue(val);
        bar.setStringPainted(false);
        bar.setForeground(fg);
        bar.setBackground(GameColors.PANEL);
        bar.setPreferredSize(new Dimension(0, 11));
        bar.setBorderPainted(false);
    }
}
