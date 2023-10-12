import utils.Board;
import utils.GameLogic;
import utils.GameMap;
import utils.StatusBar;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import static utils.GameSettings.*;

public class Main {
    public static void main(String[] args) {
        // Here is how you set up a new window and adding our board to it
        JFrame frame = new JFrame("RPG Game");
        Board board = new Board(frame);
        GameMap gameMap = new GameMap();
        GameLogic gameLogic = new GameLogic();
        StatusBar statusBar = new StatusBar();
        Timer timer = new Timer(MONSTERS_SPEED, board);
        try {
            gameMap.loadLevelMap();
        } catch (FileNotFoundException | DataFormatException e) {
            System.err.println(e.getMessage());
        }
        gameLogic.placeMonsters();
        frame.add(board, BorderLayout.NORTH);
        frame.add(statusBar, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(board);
        frame.addKeyListener(statusBar);
//        timer.start();
    }
}
