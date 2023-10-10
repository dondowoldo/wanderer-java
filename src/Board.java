import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JComponent implements KeyListener {

    private final int BOARD_WIDTH = 720;
    private final int BOARD_HEIGHT = 720;
    public static final int TILES_ROW = 10;
    public static final int TILES_COLUMN = 10;
    private final Hero HERO;


    public Board() {
        this.HERO = new Hero();

        // set the size of your draw board
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        int[][] matrix = GameMap.getMatrix();
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
        PositionedImage heroImage = new PositionedImage(HERO.getImageAddress(),
                HERO.getCoordinateX() * BOARD_WIDTH / TILES_ROW,
                HERO.getCoordinateY() * BOARD_HEIGHT / TILES_COLUMN
        );
        heroImage.draw(graphics);
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
        if (e.getKeyCode() == KeyEvent.VK_UP && GameMap.getMatrix()[HERO.getCoordinateY() - 1][HERO.getCoordinateX()] != 1) {
            HERO.setCoordinateY(HERO.getCoordinateY() - 1);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && GameMap.getMatrix()[HERO.getCoordinateY() + 1][HERO.getCoordinateX()] != 1) {
            HERO.setCoordinateY(HERO.getCoordinateY() + 1);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && GameMap.getMatrix()[HERO.getCoordinateY()][HERO.getCoordinateX() - 1] != 1) {
            HERO.setCoordinateX(HERO.getCoordinateX() - 1);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && GameMap.getMatrix()[HERO.getCoordinateY()][HERO.getCoordinateX() + 1] != 1) {
            HERO.setCoordinateX(HERO.getCoordinateX() + 1);
        }
        // and redraw to have a new picture with the new coordinates
        repaint();

    }

}