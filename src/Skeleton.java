import java.util.Random;
public class Skeleton extends Monster {
    public Skeleton(int level) {
        super(
                2 * level * new Random().nextInt(6) + 1,
                (level / 2) * new Random().nextInt(6) + 1,
                level * new Random().nextInt(6) + 1
        );
//        this.coordinates = new int[] {0,0};
    }
}
