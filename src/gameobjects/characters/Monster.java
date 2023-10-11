package gameobjects.characters;


import gameobjects.GameObject;
import interfaces.Impenetrable;
import utils.GameLogic;
import utils.GameMap;


public abstract class Monster extends GameCharacter {
    public Monster(int baseHP, int baseDefense, int baseAttack) {
        super(baseHP, baseDefense, baseAttack);

    }
    public void move() {
        this.moveUp();
    }
    public boolean moveUp() {
        if (this.coordinateY != 0 &&
                !(GameMap.getMAP()[this.coordinateY - 1][this.coordinateX] instanceof Impenetrable) &&
                GameLogic.getMonstersPositions()[this.coordinateY - 1][this.coordinateX] == null) {
            this.coordinateY -= 1;
            return true;
        } else {
            return false;
        }
    }

    public boolean moveDown() {
        if (this.coordinateY != GameMap.getMAP().length - 1
                && !(GameMap.getMAP()[this.coordinateY + 1][this.coordinateX] instanceof Impenetrable) &&
                GameLogic.getMonstersPositions()[this.coordinateY + 1][this.coordinateX] == null) {
            this.coordinateY += 1;
            return true;
        } else {
            return false;
        }
    }
    public boolean moveLeft() {
        if (this.coordinateX != 0 &&
                !(GameMap.getMAP()[this.coordinateY][this.coordinateX - 1] instanceof Impenetrable) &&
                GameLogic.getMonstersPositions()[this.coordinateY][this.coordinateX - 1] == null) {
            this.coordinateX -= 1;
            return true;
        } else {
            return false;
        }
    }
    public boolean moveRight() {
        if (this.coordinateX != GameMap.getMAP()[this.coordinateY].length - 1 &&
                !(GameMap.getMAP()[this.coordinateY][this.coordinateX + 1] instanceof Impenetrable) &&
                GameLogic.getMonstersPositions()[this.coordinateY][this.coordinateX + 1] == null) {
            this.coordinateX += 1;
            return true;
        } else {
            return false;
        }
    }
}
