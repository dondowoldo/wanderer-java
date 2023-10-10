package gameobjects.characters;


public abstract class Monster extends GameCharacter {
    public Monster(int baseHP, int baseDefense, int baseAttack) {
        super(baseHP, baseDefense, baseAttack);

    }
    public void move() {
        this.coordinateX -= 1;
    }
}
