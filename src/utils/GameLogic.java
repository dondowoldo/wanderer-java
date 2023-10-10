package utils;

import gameobjects.characters.Boss;
import gameobjects.characters.Monster;
import gameobjects.characters.Skeleton;
import gameobjects.structures.Block;
import gameobjects.structures.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameLogic {
    private List<Monster> monsters;
    private static int currentLevel;

    public GameLogic() {
        this.monsters = new ArrayList<>();
        this.currentLevel = 1;
    }

    public void loadMonsters() {
        int monsterCount = diceRoll(3);
        this.monsters.add(new Boss(currentLevel));
        for (int i = 0; i < monsterCount - 1; i++) {
            monsters.add(new Skeleton(currentLevel));
        }
    }

    public void deployMonsters() {
        List<Block> availableSpots = new ArrayList<>();
        for (Block[] row : GameMap.getMap()) {
            for (Block block : row) {
                if (block instanceof Tile) {
                    availableSpots.add(block);
                }
            }
        }

        for (Monster m : monsters) {
            // TODO SET MONSTERS IN POSITIONS
            //  m.setCoordinateX();
        }
    }

    public static int diceRoll(int lowerBound) throws IllegalArgumentException {
        if (lowerBound < 1 || lowerBound > 3) {
            throw new IllegalArgumentException("Invalid lower bound. Must be >= 1 and <= 3");
        }
        return new Random().nextInt(7 - lowerBound) + lowerBound;
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }
}