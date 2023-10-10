package utils;

import gameobjects.structures.Block;
import gameobjects.structures.Tile;
import gameobjects.structures.Wall;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class GameMap {
    private static Block[][] map;

    public void loadLevelMatrix() throws FileNotFoundException, DataFormatException {
        Path filePath = Paths.get("resources/levels/" + GameLogic.getCurrentLevel() + ".txt");
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("level " + GameLogic.getCurrentLevel() + " does not exist. Please try again");
        }
        List<String> fileLines = new ArrayList<>();
        try {
            fileLines = Files.readAllLines(filePath, UTF_8);
        } catch (IOException e) {
            System.err.println("Unable to read file");
        }
        if (!fileIsValid(fileLines)) {
            throw new DataFormatException("File data not valid");
        }
        map = new Block[GameSettings.TILES_COLUMN][GameSettings.TILES_ROW];

        for (int i = 0; i < fileLines.size(); i++) {
            String[] values = fileLines.get(i).split("");
            for (int j = 0; j < values.length; j++) {
                if (Integer.parseInt(values[j]) == 0) {
                    map[i][j] = new Tile(j, i);
                } else {
                    map[i][j] = new Wall(j, i);
                }
            }
        }
    }

    private boolean fileIsValid(List<String> fileLines) {
        int rowLength = GameSettings.TILES_ROW;
        int colLength = GameSettings.TILES_COLUMN;
        // Check if amount of rows is correct
        if (fileLines.size() != colLength) {
            return false;
        }
        // Check if each row has required amount of elements
        for (int i = 0; i < fileLines.size(); i++) {
            String[] values = fileLines.get(i).split("");
            if (values.length != rowLength) {
                return false;
            }
            for (int j = 0; j < values.length; j++) {
                if (Integer.parseInt(values[j]) != 0 && Integer.parseInt(values[j]) != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Block[][] getMap() {
        return map;
    }
}
