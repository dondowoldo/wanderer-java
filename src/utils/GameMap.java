package utils;

import gameobjects.structures.Block;
import gameobjects.structures.Tile;
import gameobjects.structures.Wall;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.DataFormatException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class GameMap {
    private static final Block[][] MAP = new Block[GameSettings.TILES_COLUMN][GameSettings.TILES_ROW];

    private static final int NUMBER_OF_LEVELS = Objects.requireNonNull(new File(String.valueOf(Paths.get("resources/levels/"))).listFiles()).length;

    public static void loadLevelMap() throws FileNotFoundException, DataFormatException {
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

        for (int y = 0; y < fileLines.size(); y++) {
            String[] values = fileLines.get(y).split("");
            for (int x = 0; x < values.length; x++) {
                if (Integer.parseInt(values[x]) == 0) {
                    MAP[y][x] = new Tile(x, y);
                } else {
                    MAP[y][x] = new Wall(x, y);
                }
            }
        }
    }

    private static boolean fileIsValid(List<String> fileLines) {
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

    public static Block[][] getMAP() {
        return MAP;
    }

    public static int getNUMBER_OF_LEVELS() {
        return NUMBER_OF_LEVELS;
    }
}