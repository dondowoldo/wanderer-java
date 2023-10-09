import java.util.Random;

public abstract class GameCharacter {

    private int maxHP;
    private int currentHP;
    private int defense;
    private int attack;
    protected int[] coordinates;

    public GameCharacter(int baseHP, int baseDefense, int baseAttack) {

    }


    public boolean isAlive() {
        return currentHP > 0;
    }

    public void levelUpMaxHP() {
        Random random = new Random();
        int d6 = random.nextInt(6) + 1;
        this.maxHP += d6;
    }

    public void levelUpDefense() {
        Random random = new Random();
        int d6 = random.nextInt(6) + 1;
        this.defense += d6;
    }

    public void levelUpAttack() {
        Random random = new Random();
        int d6 = random.nextInt(6) + 1;
        this.attack += d6;
    }
}
