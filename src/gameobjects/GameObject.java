package gameobjects;

import interfaces.Drawable;
import utils.GameSettings;
import utils.PositionedImage;

public abstract class GameObject implements Drawable {
    protected int coordinateX;
    protected int coordinateY;
    protected String imageAddress;
    @Override
    public PositionedImage getPositionedImage() {
        return new PositionedImage(this.imageAddress,
                this.coordinateX * GameSettings.BOARD_WIDTH / GameSettings.TILES_ROW,
                this.coordinateY * GameSettings.BOARD_HEIGHT / GameSettings.TILES_COLUMN);
    }
}
