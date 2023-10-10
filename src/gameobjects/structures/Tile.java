package gameobjects.structures;

public class Tile extends Block {
    public Tile(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.imageAddress = "resources/img/gif/floor.gif";
    }
}
