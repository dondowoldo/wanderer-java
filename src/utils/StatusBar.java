package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.GameSettings.*;

public class StatusBar extends JComponent implements KeyListener {
    private JLabel label;

    public StatusBar() {
        this.setPreferredSize(new Dimension(BOARD_WIDTH, STATUS_BAR_HEIGHT));
        this.setBackground(Color.white);
        this.label = new JLabel(GameLogic.getHERO().toString());
        this.label.setHorizontalAlignment(SwingConstants.CENTER);
        this.setFont(new Font("Serif", Font.BOLD, FONT_SIZE));
        this.add(this.label);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
