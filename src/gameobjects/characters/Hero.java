package gameobjects.characters;

import interfaces.Impenetrable;
import utils.GameLogic;
import utils.GameMap;

import java.util.Random;

public class Hero extends GameCharacter implements Impenetrable {
    private int movesCount;
    private int level;

    private boolean hasKey;

    public Hero() {
        super(
                20 + 3 * GameLogic.diceRoll(1),
                2 * GameLogic.diceRoll(1),
                5 + GameLogic.diceRoll(1)
        );
        this.coordinateX = 0;
        this.coordinateY = 0;
        this.imageAddress = "resources/img/gif/hero-down.gif";
        this.movesCount = 0;
        this.level = 1;
        this.hasKey = false;
    }

    public void obtainKey() {
        this.hasKey = true;
    }

    public void resetCoordinates() {
        this.coordinateX = 0;
        this.coordinateY = 0;
        this.hasKey = false;
    }

    public boolean hasKey() {
        return hasKey;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public void nextLevel() {
        Random random = new Random();
        int chance = random.nextInt(10) + 1;
        if (chance <= 5) {
            this.currentHP += this.maxHP / 10;
        } else if (chance <= 9) {
            this.currentHP += this.maxHP / 3;
        } else {
            this.currentHP = this.maxHP;
        }
    }

    public void levelUp() {
        this.levelUpAttack();
        this.levelUpDefense();
        this.levelUpMaxHP();
        this.level += 1;
    }

    private void levelUpMaxHP() {
        int d6 = GameLogic.diceRoll(1);
        this.maxHP += d6;
    }

    private void levelUpDefense() {
        int d6 = GameLogic.diceRoll(1);
        this.defense += d6;
    }

    private void levelUpAttack() {
        int d6 = GameLogic.diceRoll(1);
        this.attack += d6;
    }

    @Override
    public String toString() {
        String key = new String(Character.toChars(0x1F511));
        String status = "Hero (Level " + level + ") HP: " + currentHP + "/" + maxHP + " | DP: " + defense + " | SP: " + attack;
        if (this.hasKey) {
            return status + " | " + key;
        } else {
            return status;
        }
    }

    public void faceLeft() {
        this.imageAddress = "resources/img/gif/hero-left.gif";
    }

    public void faceRight() {
        this.imageAddress = "resources/img/gif/hero-right.gif";
    }

    public void faceUp() {
        this.imageAddress = "resources/img/gif/hero-up.gif";
    }

    public void faceDown() {
        this.imageAddress = "resources/img/gif/hero-down.gif";
    }

    public boolean move(String direction) {
        switch (direction) {
            case "up":
                if (this.coordinateY != 0 &&
                        !(GameMap.getMAP()[this.coordinateY - 1][this.coordinateX] instanceof Impenetrable)) {
                    this.coordinateY -= 1;
                    this.movesCount += 1;
                    return true;
                }
                break;
            case "down":
                if (this.coordinateY != GameMap.getMAP().length - 1 &&
                        !(GameMap.getMAP()[this.coordinateY + 1][this.coordinateX] instanceof Impenetrable)) {
                    this.coordinateY += 1;
                    this.movesCount += 1;
                    return true;
                }
                break;
            case "left":
                if (this.coordinateX != 0 &&
                        !(GameMap.getMAP()[this.coordinateY][this.coordinateX - 1] instanceof Impenetrable)) {
                    this.coordinateX -= 1;
                    this.movesCount += 1;
                    return true;
                }
                break;
            case "right":
                if (this.coordinateX != GameMap.getMAP()[this.coordinateY].length - 1 &&
                        !(GameMap.getMAP()[this.coordinateY][this.coordinateX + 1] instanceof Impenetrable)) {
                    this.coordinateX += 1;
                    this.movesCount += 1;
                    return true;
                }
                break;
        }
        return false;
    }

    public void strike(Monster monster) {
        int strikeValue = this.attack + GameLogic.diceRoll(1) * 2;
        monster.beStriked(strikeValue);
    }

    public void beStriked(int damage) {
        if (damage > this.defense) {
            this.currentHP = Math.max(this.currentHP - (damage - this.defense), 0);
        }
    }
}