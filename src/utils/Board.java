package utils;

import gameobjects.characters.Hero;
import gameobjects.characters.KeyBearer;
import gameobjects.characters.Monster;
import gameobjects.structures.Block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class Board extends JComponent implements KeyListener, ActionListener {
    public JFrame frame;

    public Board(JFrame frame) {
        this.frame = frame;
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
        for (Monster monster : GameLogic.getMONSTERS()) {
            monster.getPositionedImage().draw(graphics);
        }
        GameLogic.getHERO().getPositionedImage().draw(graphics);
    }

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

    @Override
    public void keyReleased(KeyEvent e) {
        Hero hero = GameLogic.getHERO();
        if (!hero.isAlive()) {
            frame.dispose();
        }
        if (hero.hasKey() && !GameLogic.isBossAlive()) {
            try {
                GameLogic.nextLevel();
            } catch (DataFormatException | FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            return;
        }
        Monster monster = GameLogic.getMonstersPositions()[GameLogic.getHERO().getCoordinateY()][GameLogic.getHERO().getCoordinateX()];
        if (!GameLogic.fight()) {
            boolean heroMoved = false;
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                heroMoved = GameLogic.getHERO().move("up");
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                heroMoved = GameLogic.getHERO().move("down");
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                heroMoved = GameLogic.getHERO().move("left");
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                heroMoved = GameLogic.getHERO().move("right");
            }
            if (heroMoved && GameLogic.getHERO().getMovesCount() % 2 == 0) {
                for (Monster m : GameLogic.getMONSTERS()) {
                    m.move();
                }
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                hero.strike(monster);
                if (monster.isAlive()) {
                    monster.strike(hero);
                } else {
                    if (monster instanceof KeyBearer) {
                        hero.obtainKey();
                    }
                    GameLogic.getMONSTERS().remove(monster);
                    hero.levelUp();
                }
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