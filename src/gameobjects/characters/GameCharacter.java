package gameobjects.characters;

import gameobjects.GameObject;
import interfaces.Impenetrable;

public abstract class GameCharacter extends GameObject implements Impenetrable {

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


    public boolean isAlive() {
        return currentHP > 0;
    }
}
