package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.GameSettings.*;

public class StatusBar extends JPanel implements KeyListener {
    private JLabel label;

    public StatusBar() {
        this.setPreferredSize(new Dimension(BOARD_WIDTH, STATUS_BAR_HEIGHT));
        this.setBackground(Color.white);
        this.label = new JLabel(GameLogic.getHERO().toString());
        this.label.setHorizontalAlignment(SwingConstants.CENTER);
        this.label.setFont(new Font("Serif", Font.BOLD, FONT_SIZE));
        this.add(this.label);
    }

    @Override
    public void paint(Graphics graphics) {
        label.setText(GameLogic.getHERO().toString());
        super.paint(graphics);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        repaint();
    }
}
