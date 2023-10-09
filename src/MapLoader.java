import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DataFormatException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MapLoader {
    private int currentLevel;
    public MapLoader(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public MapLoader() {
        this.currentLevel = 0;
    }


    public int[][] getLevelMatrix(int level) throws FileNotFoundException, DataFormatException {
        Path filePath = Paths.get("resources/levels/" + level + ".txt");
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("level " + level + " does not exist. Please try again");
        }
        List<String> fileLines = new ArrayList<>();
        try {
            fileLines = Files.readAllLines(filePath, UTF_8);
        } catch(IOException e) {
            System.err.println("Unable to read file");
        }
        if (!fileIsValid(fileLines)) {
            throw new DataFormatException("File data not valid");
        }

        int[][] levelMatrix = new int[Board.TILES_COLUMN][Board.TILES_ROW];
        for(int i = 0; i < fileLines.size(); i++) {
            String[] values = fileLines.get(i).split("");
            for (int j = 0; j < values.length; j++) {
                levelMatrix[i][j] = Integer.parseInt(values[j]);
            }
        }
        for (int i = 0; i < levelMatrix.length; i++) {
            System.out.println(Arrays.toString(levelMatrix[i]));
        }
        return levelMatrix;
    }

    private boolean fileIsValid(List<String> fileLines) {
        int rowLength = Board.TILES_ROW;
        int colLength = Board.TILES_COLUMN;

        // Check if amount of rows is correct
        if(fileLines.size() != colLength) {
            return false;
        }

        // Check if each row has required amount of elements
        for (int i = 0; i < fileLines.size(); i++) {
            String[] values = fileLines.get(i).split("");
            if (values.length != rowLength) {
                return false;
            }
            for (int j = 0; j < values.length; j++) {
                int test = Integer.parseInt(values[j]);
                if (Integer.parseInt(values[j]) != 0 && Integer.parseInt(values[j]) != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
