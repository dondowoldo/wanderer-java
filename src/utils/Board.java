package utils;

import gameobjects.characters.Monster;
import gameobjects.structures.Block;

import javax.swing.*;
import java.awt.*;

public class Board extends JComponent {

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
        for (Monster monster : GameLogic.getMONSTERS()) {
            monster.getPositionedImage().draw(graphics);
        }
        GameLogic.getHERO().getPositionedImage().draw(graphics);
    }
}


