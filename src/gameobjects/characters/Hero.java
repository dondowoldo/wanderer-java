package gameobjects.characters;

import interfaces.Impenetrable;
import utils.GameLogic;
import utils.GameMap;

public class Hero extends GameCharacter {
    private int movesCount;

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
    }

    public int getMovesCount() {
        return movesCount;
    }

    public void levelUpMaxHP() {
        int d6 = GameLogic.diceRoll(1);
        this.maxHP += d6;
    }

    public void levelUpDefense() {
        int d6 = GameLogic.diceRoll(1);
        this.defense += d6;
    }

    public void levelUpAttack() {
        int d6 = GameLogic.diceRoll(1);
        this.attack += d6;
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
}