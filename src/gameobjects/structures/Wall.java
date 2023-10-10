package gameobjects.structures;

import interfaces.Impenetrable;

public class Wall extends Block implements Impenetrable {
    public Wall(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.imageAddress = "resources/img/gif/wall.gif";
    }
}
