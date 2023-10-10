import java.util.Random;

public class Boss extends Monster {
    public Boss(int level) {
        super(
                2 * level * (new Random().nextInt(6) + 1) + (new Random().nextInt(6) + 1),
                (level / 2) * (new Random().nextInt(6) + 1) + (new Random().nextInt(6) + 1) / 2,
                level * (new Random().nextInt(6) + 1) + (new Random().nextInt(6) + 1)
        );
        this.imageAddress = "resources/img/gif/boss.gif";
    }
}
