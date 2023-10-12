package gameobjects.characters;

import utils.GameLogic;

public class Skeleton extends Monster {

    public Skeleton(int level, int initialCoordX, int initialCoordY) {
        super(
                2 * level * GameLogic.diceRoll(1),
                (level / 2) * GameLogic.diceRoll(1),
                level * GameLogic.diceRoll(1)
        );
        this.coordinateX = initialCoordX;
        this.coordinateY = initialCoordY;
        this.imageAddress = "resources/img/gif/skeleton.gif";
    }
}
