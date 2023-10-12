import utils.Board;
import utils.GameLogic;
import utils.GameMap;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import static utils.GameSettings.*;

public class Main {
    public static void main(String[] args) {
        // Here is how you set up a new window and adding our board to it
        JFrame frame = new JFrame("RPG Game");
        Board board = new Board();
        GameMap gameMap = new GameMap();
        GameLogic gameLogic = new GameLogic();
        Timer timer = new Timer(MONSTERS_SPEED, board);
        try {
            gameMap.loadLevelMap();
        } catch (FileNotFoundException | DataFormatException e) {
            System.err.println(e.getMessage());
        }
        gameLogic.placeMonsters();
        frame.add(board, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(Color.white);
        statusPanel.setPreferredSize(new Dimension(frame.getWidth(), STATUS_BAR_HEIGHT));

        JLabel statusLabel = new JLabel(GameLogic.getHERO().toString());
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Serif", Font.BOLD, FONT_SIZE));
        statusPanel.add(statusLabel);

        frame.add(statusPanel, BorderLayout.SOUTH);
        frame.pack();





        frame.setVisible(true);
        // Here is how you can add a key event listener
        // The board object will be notified when hitting any key
        // with the system calling one of the below 3 methods
        frame.addKeyListener(board);
        timer.start();
        // Notice (at the top) that we can only do this
        // because this utils.Board class (the type of the board object) is also a KeyListener
    }
}
