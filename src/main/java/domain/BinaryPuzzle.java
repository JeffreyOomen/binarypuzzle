package domain;

import java.util.ArrayList;
import java.util.List;

public class BinaryPuzzle {
    private static BinaryPuzzle binaryPuzzle;
    private int size;
    private List<Row> rows;
    private PuzzleValidator puzzleValidator;

    // Private Constructor
    private BinaryPuzzle(int size) {
        this.size = size;
        this.rows = new ArrayList<>();
        this.puzzleValidator = new PuzzleValidator();
    }

    public static BinaryPuzzle getInstance(int size) {
        if (binaryPuzzle == null) {
            binaryPuzzle = new BinaryPuzzle(size);
        }

        return binaryPuzzle;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setRows(List<Row> rows) {
        this.puzzleValidator.validateRows(rows);
        this.rows = rows;
    }

    public List<Row> getRows() {
        return this.rows;
    }

    public void printPuzzle() {
        for (Row row : this.rows) {
            row.printRow();
        }
    }

    class PuzzleValidator {
        public void validateRows(List<Row> rows) {
            if (rows == null) {
                throw new NullPointerException("The rows variable is NULL, but should contain " + size + " rows.");
            }

            if (rows.size() > size) {
                throw new IllegalArgumentException("Too many rows, there should be " + size + " rows.");
            }

            if (rows.size() < size) {
                throw new IllegalArgumentException("Too little rows, there should be " + size + " rows.");
            }

            for (int rowIndex = 0; rowIndex < size; rowIndex++) {
                if (rows.get(rowIndex).getFieldValues().size() != size) {
                    throw new IllegalArgumentException("Row #" + (rowIndex + 1) + " should contain " + size + " field values, but there are " + rows.get(rowIndex).getFieldValues().size() + " found.");
                }
            }
        }
    }
}
