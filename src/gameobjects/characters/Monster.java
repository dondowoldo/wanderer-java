package gameobjects.characters;

import interfaces.Impenetrable;

public abstract class Monster extends GameCharacter implements Impenetrable {
    public Monster(int baseHP, int baseDefense, int baseAttack) {
        super(baseHP, baseDefense, baseAttack);
    }
    public void move() {

    }
}
