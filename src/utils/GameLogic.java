package utils;

import gameobjects.characters.Boss;
import gameobjects.characters.Hero;
import gameobjects.characters.Monster;
import gameobjects.characters.Skeleton;
import gameobjects.structures.Block;
import gameobjects.structures.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameLogic {
    private static final List<Monster> MONSTERS = new ArrayList<>();

    private static final Hero HERO = new Hero();


    private static int currentLevel;

    public GameLogic() {
        this.currentLevel = 1;
    }

    public static List<Monster> getMONSTERS() {
        return MONSTERS;
    }

    public static Hero getHERO() {
        return HERO;
    }

    public static Monster[][] getMonstersPositions() {
        Monster[][] matrixMonsters = new Monster[GameSettings.TILES_COLUMN][GameSettings.TILES_ROW];
        for (Monster monster : MONSTERS) {
            matrixMonsters[monster.getCoordinateY()][monster.getCoordinateX()] = monster;
        }
        return matrixMonsters;
    }

    public void placeMonsters() {
        List<Block> availableSpots = new ArrayList<>();
        for (Block[] row : GameMap.getMAP()) {
            for (Block block : row) {
                if (block instanceof Tile) {
                    availableSpots.add(block);
                }
            }
        }
        for (int i = 0; i < GameLogic.diceRoll(3); i++) {
            int position = new Random().nextInt(availableSpots.size());
            if (i == 0) {
                MONSTERS.add(new Boss(currentLevel, availableSpots.get(position).getCoordinateX(), availableSpots.get(position).getCoordinateY()));
                availableSpots.remove(position);
            } else {
                MONSTERS.add(new Skeleton(currentLevel, availableSpots.get(position).getCoordinateX(), availableSpots.get(position).getCoordinateY()));
                availableSpots.remove(position);
            }
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