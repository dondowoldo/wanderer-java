package utils;

import gameobjects.characters.*;
import gameobjects.structures.Block;
import gameobjects.structures.Tile;
import interfaces.Impenetrable;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.DataFormatException;


public class GameLogic {
    private static final List<Monster> MONSTERS = new ArrayList<>();
    private static final Hero HERO = new Hero();
    private static int currentLevel;

    public GameLogic() {
        currentLevel = 1;
    }

    public static void nextLevel() throws DataFormatException, FileNotFoundException {
        currentLevel += 1;
        if (GameMap.getNUMBER_OF_LEVELS() >= currentLevel) {
            GameMap.loadLevelMap();
            MONSTERS.clear();
            placeMonsters();
            HERO.resetCoordinates();
            HERO.nextLevel();
        }
    }

    public static boolean isBossAlive() {
        for (Monster m : MONSTERS) {
            if (m instanceof Boss) {
                return true;
            }
        }
        return false;
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

    public static void placeMonsters() {
        List<Block> availableSpots = new ArrayList<>();
        for (Block[] row : GameMap.getMAP()) {
            for (Block block : row) {
                if (block instanceof Tile) {
                    availableSpots.add(block);
                }
            }
        }
        for (int i = 0; i < diceRoll(3); i++) {
            int position = new Random().nextInt(availableSpots.size());
            if (i == 0) {
                MONSTERS.add(new Boss(currentLevel, availableSpots.get(position).getCoordinateX(), availableSpots.get(position).getCoordinateY()));
                availableSpots.remove(position);
            } else if (i == 1) {
                MONSTERS.add(new KeyBearer(currentLevel, availableSpots.get(position).getCoordinateX(), availableSpots.get(position).getCoordinateY()));
                availableSpots.remove(position);
            } else {
                MONSTERS.add(new Skeleton(currentLevel, availableSpots.get(position).getCoordinateX(), availableSpots.get(position).getCoordinateY()));
                availableSpots.remove(position);
            }
        }
    }

    public static boolean fight() {
        return ((GameLogic.getMonstersPositions()[HERO.getCoordinateY()][HERO.getCoordinateX()]) instanceof Monster);
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