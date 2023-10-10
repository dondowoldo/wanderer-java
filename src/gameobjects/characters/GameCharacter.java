package gameobjects.characters;
//import utils.PositionedImage;


import gameobjects.GameObject;

public abstract class GameCharacter extends GameObject {

    protected int maxHP;
    protected int currentHP;
    protected int defense;
    protected int attack;


    public GameCharacter(int baseHP, int baseDefense, int baseAttack) {
        this.maxHP = baseHP;
        this.currentHP = baseHP;
        this.defense = baseDefense;
        this.attack = baseAttack;

    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public boolean isAlive() {
        return currentHP > 0;
    }
}
