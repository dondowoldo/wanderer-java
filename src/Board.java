import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

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
        for (int y = 0; y < BOARD_HEIGHT; y += BOARD_HEIGHT / TILES_COLUMN) {
            for (int x = 0; x < BOARD_WIDTH; x += BOARD_WIDTH / TILES_ROW) {
                PositionedImage image = new PositionedImage("resources/img/gif/floor.gif", x, y);
                image.draw(graphics);
            }
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