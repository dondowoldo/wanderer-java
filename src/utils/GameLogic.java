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
    public static List<Monster> MONSTERS;

    public static Hero HERO;

    private static int currentLevel;

    public GameLogic() {
        this.MONSTERS = new ArrayList<>();
        this.HERO = new Hero();
        this.currentLevel = 1;
    }

    public Hero getHERO() {
        return HERO;
    }



    public void loadMonsters() {
        int monsterCount = diceRoll(3);
        this.MONSTERS.add(new Boss(currentLevel));
        for (int i = 0; i < monsterCount - 1; i++) {
            MONSTERS.add(new Skeleton(currentLevel));
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
        for (Monster m : MONSTERS) {
            int position = new Random().nextInt(availableSpots.size()) + 1;
            m.setCoordinateX(availableSpots.get(position).getCoordinateX());
            m.setCoordinateY(availableSpots.get(position).getCoordinateY());
            availableSpots.remove(position);
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