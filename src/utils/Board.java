package utils;

import characters.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JComponent implements KeyListener {

    private final Hero HERO;


    public Board() {
        this.HERO = new Hero();

        // set the size of your draw board
        setPreferredSize(new Dimension(GameSettings.BOARD_WIDTH, GameSettings.BOARD_HEIGHT));
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        int[][] matrix = GameMap.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int x = j * GameSettings.BOARD_WIDTH / GameSettings.TILES_ROW;
                int y = i * GameSettings.BOARD_HEIGHT / GameSettings.TILES_COLUMN;
                if (matrix[i][j] == 0) {
                    PositionedImage image = new PositionedImage("resources/img/gif/floor.gif", x, y);
                    image.draw(graphics);
                } else {
                    PositionedImage image = new PositionedImage("resources/img/gif/wall.gif", x, y);
                    image.draw(graphics);
                }
            }
        }
        HERO.getPositionedImage().draw(graphics);
    }

    // To be a KeyListener the class needs to have these 3 methods in it
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            HERO.faceUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            HERO.faceDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            HERO.faceLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            HERO.faceRight();
        }
        repaint();
    }

    // But actually we can use just this one for our goals here
    @Override
    public void keyReleased(KeyEvent e) {
        // When the up or down keys hit, we change the position of our box
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            HERO.move("up");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            HERO.move("down");
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            HERO.move("left");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            HERO.move("right");
            // and redraw to have a new picture with the new coordinates
        }
        repaint();
    }
}