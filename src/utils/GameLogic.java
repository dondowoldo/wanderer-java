package utils;

import characters.Boss;
import characters.Monster;
import characters.Skeleton;

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
        for (Monster m : monsters) {
            // SET STARTING COORDINATES FOR EACH MONSTER
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
