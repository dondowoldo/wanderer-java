import java.util.List;
import java.util.Random;

public class Hero extends GameCharacter {
    public Hero() {
        super(
                20 + 3 * new Random().nextInt(6) + 1,
                2 * new Random().nextInt(6) + 1,
                5 + new Random().nextInt(6) + 1
        );
        this.coordinates = new int[] {0,0};
    }
}
