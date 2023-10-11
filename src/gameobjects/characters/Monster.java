package gameobjects.characters;


import interfaces.Impenetrable;
import utils.GameLogic;
import utils.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public abstract class Monster extends GameCharacter {
    private String lastMoveDirection = "none";

    public Monster(int baseHP, int baseDefense, int baseAttack) {
        super(baseHP, baseDefense, baseAttack);

    }

    public void move() {
        List<String> possibleDirections = new ArrayList<>();
        if (canMoveUp()) {
            possibleDirections.add("up");
        }
        if (canMoveDown()) {
            possibleDirections.add("down");
        }
        if (canMoveLeft()) {
            possibleDirections.add("left");
        }
        if (canMoveRight()) {
            possibleDirections.add("right");
        }
        if (possibleDirections.isEmpty()) {
            return;
        }
        if (possibleDirections.size() <= 2) {
            switch (this.lastMoveDirection) {
                case "up":
                    if (canMoveUp()) {
                        this.moveUp();
                        return;
                    }
                    break;
                case "down":
                    if (canMoveDown()) {
                        this.moveDown();
                        return;
                    }
                    break;
                case "left":
                    if (canMoveLeft()) {
                        this.moveLeft();
                        return;
                    }
                    break;
                case "right":
                    if (canMoveRight()) {
                        this.moveRight();
                        return;
                    }
                    break;
                default:
                    break;
            }
        }
        if (possibleDirections.size() > 1) {
            if (this.lastMoveDirection.equals("up")) {
                possibleDirections.remove("down");
            } else if (this.lastMoveDirection.equals("down")) {
                possibleDirections.remove("up");
            } else if (this.lastMoveDirection.equals("left")) {
                possibleDirections.remove("right");
            } else if (this.lastMoveDirection.equals("right")) {
                possibleDirections.remove("left");
            }
        }
        int pickRandom = new Random().nextInt(possibleDirections.size());
        this.lastMoveDirection = possibleDirections.get(pickRandom);
        switch (possibleDirections.get(pickRandom)) {
            case "up":
                this.moveUp();
                break;
            case "down":
                this.moveDown();
                break;
            case "left":
                this.moveLeft();
                break;
            case "right":
                this.moveRight();
                break;
        }
    }

    //        boolean monsterMoved = false;
//        do {
//            switch (GameLogic.diceRoll(2)) {
//                case 3:
//                    monsterMoved = this.moveUp();
//                    break;
//                case 4:
//                    monsterMoved = this.moveDown();
//                    break;
//                case 5:
//                    monsterMoved = this.moveLeft();
//                    break;
//                case 6:
//                    monsterMoved = this.moveRight();
//                    break;
//            }
//        } while (!monsterMoved);
//    }
    private boolean canMoveUp() {
        return this.coordinateY != 0 &&
                !((GameMap.getMAP()[this.coordinateY - 1][this.coordinateX]) instanceof Impenetrable) &&
                !((GameLogic.getMonstersPositions()[this.coordinateY - 1][this.coordinateX]) instanceof Impenetrable);
    }

    private void moveUp() {
        this.coordinateY -= 1;
    }

    private boolean canMoveDown() {
        return this.coordinateY != GameMap.getMAP().length - 1
                && !(GameMap.getMAP()[this.coordinateY + 1][this.coordinateX] instanceof Impenetrable) &&
                !((GameLogic.getMonstersPositions()[this.coordinateY + 1][this.coordinateX]) instanceof Impenetrable);
    }

    private void moveDown() {
        this.coordinateY += 1;
    }

    private boolean canMoveLeft() {
        return this.coordinateX != 0 &&
                !(GameMap.getMAP()[this.coordinateY][this.coordinateX - 1] instanceof Impenetrable) &&
                !((GameLogic.getMonstersPositions()[this.coordinateY][this.coordinateX - 1]) instanceof Impenetrable);
    }

    private void moveLeft() {
        this.coordinateX -= 1;
    }

    private boolean canMoveRight() {
        return this.coordinateX != GameMap.getMAP()[this.coordinateY].length - 1 &&
                !(GameMap.getMAP()[this.coordinateY][this.coordinateX + 1] instanceof Impenetrable) &&
                !((GameLogic.getMonstersPositions()[this.coordinateY][this.coordinateX + 1]) instanceof Impenetrable);
    }

    private void moveRight() {
        this.coordinateX += 1;
    }
}
