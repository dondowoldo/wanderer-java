package utils;

import gameobjects.characters.Monster;
import gameobjects.structures.Block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JComponent implements KeyListener, ActionListener {

    public Board() {
        setPreferredSize(new Dimension(GameSettings.BOARD_WIDTH, GameSettings.BOARD_HEIGHT));
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        for (Block[] y : GameMap.getMAP()) {
            for (Block x : y) {
                x.getPositionedImage().draw(graphics);
            }
        }
//        for (Monster[] y : GameLogic.getMonstersPositions()) {
//            for (Monster x : y) {
//                if (x != null) {
//                    x.getPositionedImage().draw(graphics);
//                }
//            }
//        }
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
        boolean heroMoved = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            heroMoved = GameLogic.getHERO().move("up");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            heroMoved = GameLogic.getHERO().move("down");
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            heroMoved = GameLogic.getHERO().move("left");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            heroMoved = GameLogic.getHERO().move("right");
            // and redraw to have a new picture with the new coordinates
        }
        if (heroMoved && GameLogic.getHERO().getMovesCount() % 2 == 0) {
            for (Monster monster : GameLogic.getMONSTERS()) {
                monster.move();
            }
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        for (Monster monster : GameLogic.getMONSTERS()) {
//            monster.move();
//        }
//        repaint();
    }
}