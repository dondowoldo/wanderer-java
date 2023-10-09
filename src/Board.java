import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class Board extends JComponent implements KeyListener {

    int testBoxX;
    int testBoxY;
    private final int BOARD_WIDTH = 720;
    private final int BOARD_HEIGHT = 720;
    public static final int TILES_ROW = 10;
    public static final int TILES_COLUMN = 10;


    public Board() {

        testBoxX = 0;
        testBoxY = 0;

        // set the size of your draw board
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
//        graphics.fillRect(testBoxX, testBoxY, 100, 100);
        // here you have a 720x720 canvas
        // you can create and draw an image using the class below e.g.
        MapLoader map = new MapLoader();
        try {
            int[][] matrix = map.getLevelMatrix();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    int x = j * BOARD_WIDTH / TILES_ROW;
                    int y = i * BOARD_HEIGHT / TILES_COLUMN;
                    if (matrix[i][j] == 0) {
                        PositionedImage image = new PositionedImage("resources/img/gif/floor.gif", x, y);
                        image.draw(graphics);
                    } else {
                        PositionedImage image = new PositionedImage("resources/img/gif/wall.gif", x, y);
                        image.draw(graphics);
                    }
                }
            }
        } catch (FileNotFoundException | DataFormatException e) {
            System.err.println(e.getMessage());
        }
    }


    // To be a KeyListener the class needs to have these 3 methods in it
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    // But actually we can use just this one for our goals here
    @Override
    public void keyReleased(KeyEvent e) {
        // When the up or down keys hit, we change the position of our box
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            testBoxY -= 100;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            testBoxY += 100;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            testBoxX -= 100;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            testBoxX += 100;
        }
        // and redraw to have a new picture with the new coordinates
        repaint();

    }

}