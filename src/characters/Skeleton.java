package characters;

import utils.GameLogic;

import java.util.Random;

public class Skeleton extends Monster {
    private boolean hasKey;

    public Skeleton(int level) {
        super(
                2 * level * GameLogic.diceRoll(1),
                (level / 2) * GameLogic.diceRoll(1),
                level * GameLogic.diceRoll(1)
        );
        this.imageAddress = "resources/img/gif/skeleton.gif";
    }
}
