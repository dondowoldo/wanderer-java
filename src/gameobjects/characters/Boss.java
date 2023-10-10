package gameobjects.characters;

import utils.GameLogic;

public class Boss extends Monster {
    public Boss(int level, int initialCoordX, int initialCoordY) {
        super(
                2 * level * GameLogic.diceRoll(1) + GameLogic.diceRoll(1),
                (level / 2) * GameLogic.diceRoll(1) + GameLogic.diceRoll(1) / 2,
                level * GameLogic.diceRoll(1) + GameLogic.diceRoll(1)
        );
        this.coordinateX = initialCoordX;
        this.coordinateY = initialCoordY;
        this.imageAddress = "resources/img/gif/boss.gif";
    }
}
