package utils;

import gameobjects.characters.Monster;
import gameobjects.structures.Block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JComponent implements KeyListener {

    public Board() {
        setPreferredSize(new Dimension(GameSettings.BOARD_WIDTH, GameSettings.BOARD_HEIGHT));
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Block[][] map = GameMap.getMAP();
        for (Block[] y : map) {
            for (Block x : y) {
                x.getPositionedImage().draw(graphics);
            }
        }
        for (Monster monster : GameLogic.getMONSTERS()) {
            monster.getPositionedImage().draw(graphics);
        }
        GameLogic.getHERO().getPositionedImage().draw(graphics);
    }

    // To be a KeyListener the class needs to have these 3 methods in it
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            GameLogic.getHERO().faceUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            GameLogic.getHERO().faceDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            GameLogic.getHERO().faceLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            GameLogic.getHERO().faceRight();
        }
        repaint();
    }

    // But actually we can use just this one for our goals here
    @Override
    public void keyReleased(KeyEvent e) {
        // When the up or down keys hit, we change the position of our box
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            GameLogic.getHERO().move("up");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            GameLogic.getHERO().move("down");
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            GameLogic.getHERO().move("left");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            GameLogic.getHERO().move("right");
            // and redraw to have a new picture with the new coordinates
        }
        repaint();
    }
}