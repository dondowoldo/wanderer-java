import utils.Board;
import utils.GameLogic;
import utils.GameMap;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class Main {
    public static void main(String[] args) {
        // Here is how you set up a new window and adding our board to it
        JFrame frame = new JFrame("RPG Game");
        Board board = new Board();
        GameMap gameMap = new GameMap();
        GameLogic gameLogic = new GameLogic();
        try {
            gameMap.loadLevelMatrix();
        } catch (FileNotFoundException | DataFormatException e) {
            System.err.println(e.getMessage());
        }
        gameLogic.placeMonsters();
        frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        // Here is how you can add a key event listener
        // The board object will be notified when hitting any key
        // with the system calling one of the below 3 methods
        frame.addKeyListener(board);
        // Notice (at the top) that we can only do this
        // because this utils.Board class (the type of the board object) is also a KeyListener
    }
}
