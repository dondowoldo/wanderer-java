import java.util.Random;

public abstract class GameCharacter implements Drawable {

    protected int maxHP;
    protected int currentHP;
    protected int defense;
    protected int attack;

    protected int coordinateX;
    protected int coordinateY;

    protected String imageAddress;

    public GameCharacter(int baseHP, int baseDefense, int baseAttack) {
        this.maxHP = baseHP;
        this.currentHP = baseHP;
        this.defense = baseDefense;
        this.attack = baseAttack;

    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public boolean isAlive() {
        return currentHP > 0;
    }
    @Override
    public PositionedImage getPositionedImage() {
        return new PositionedImage(this.imageAddress,
                this.coordinateX * Board.BOARD_WIDTH / Board.TILES_ROW,
                this.coordinateY * Board.BOARD_HEIGHT / Board.TILES_COLUMN);
    }
}
