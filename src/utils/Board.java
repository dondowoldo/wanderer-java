package utils;

import gameobjects.characters.Monster;
import gameobjects.structures.Block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.GameLogic.HERO;
import static utils.GameLogic.MONSTERS;

public class Board extends JComponent implements KeyListener {

    public Board() {
        setPreferredSize(new Dimension(GameSettings.BOARD_WIDTH, GameSettings.BOARD_HEIGHT));
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Block[][] map = GameMap.getMap();
        for (Block[] y : map) {
            for (Block x : y) {
                x.getPositionedImage().draw(graphics);
            }
        }
        for (Monster monster : MONSTERS) {
            monster.getPositionedImage().draw(graphics);
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