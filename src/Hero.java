import java.util.List;
import java.util.Random;

public class Hero extends GameCharacter {
    public Hero() {
        super(
                20 + 3 * new Random().nextInt(6) + 1,
                2 * new Random().nextInt(6) + 1,
                5 + new Random().nextInt(6) + 1
        );
        this.coordinateX = 0;
        this.coordinateY = 0;
        this.imageAddress = "resources/img/gif/hero-down.gif";
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
}
