import gameobjects.characters.Hero;
import gameobjects.characters.KeyBearer;
import gameobjects.characters.Monster;
import utils.Board;
import utils.GameLogic;
import utils.GameMap;
import utils.StatusBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class Game implements KeyListener, ActionListener {
    private final JFrame frame = new JFrame("Wanderer Game");
    private final Board board = new Board();
    private final StatusBar statusBar = new StatusBar();
    private final GameLogic gameLogic = new GameLogic();
    private GameMap gameMap;
    {
        try {
            gameMap = new GameMap();
        } catch (DataFormatException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        frame.add(board, BorderLayout.NORTH);
        frame.add(statusBar, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.addKeyListener(statusBar);
        try {
            GameMap gameMap = new GameMap();
        } catch (DataFormatException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        GameLogic.placeMonsters();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        for (Monster monster : GameLogic.getMONSTERS()) {
//            monster.move();
//        }
//        repaint();
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
        this.board.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Hero hero = GameLogic.getHERO();
        if (!hero.isAlive()) {
            this.frame.dispose();
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
        this.board.repaint();
    }
}
