package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryPuzzleCracker {
    private int binaryPuzzleSize;
    private BinaryPuzzle binaryPuzzle;
    private boolean isCrackable;

    public BinaryPuzzleCracker(int binaryPuzzleSize) {
        this.binaryPuzzleSize = binaryPuzzleSize;
        this.binaryPuzzle = BinaryPuzzle.getInstance(binaryPuzzleSize);
    }

    public BinaryPuzzle getBinaryPuzzle() {
        return binaryPuzzle;
    }

    public boolean isCrackable() {
        return isCrackable;
    }

    public void crackBinaryPuzzle(Map<Integer, List<Row>> possibleRowCombinationsPerRowIndex, int mapIndex) {
        if (mapIndex == possibleRowCombinationsPerRowIndex.size()) { // all rows are set, validate if the puzzle is solvable
            if (this.complyToRowConstraints()) {
                this.isCrackable = true;
            }
            return;
        }

        for (int rowIndex = 0; rowIndex < possibleRowCombinationsPerRowIndex.get(mapIndex).size(); rowIndex++) {
            this.binaryPuzzle.replaceRow(mapIndex, possibleRowCombinationsPerRowIndex.get(mapIndex).get(rowIndex));
            this.crackBinaryPuzzle(possibleRowCombinationsPerRowIndex, mapIndex + 1);

            if (isCrackable) {
                return;
            }
        }
    }

    /**
     * Discovers all unique row combinations, e.g. suppose you have a binary puzzle of size 2,
     * the the unique row combinations would be:
     * 0 0
     * 1 1
     * 0 1
     * 1 0
     * @return a list of all unique row combinations possible within the puzzle size.
     */
    public List<Row> discoverUniqueRowCombinations() {
        List<Row> discoveredRowCombinations = new ArrayList<>();

        int amountOfPossibleRowCombinations = (int) Math.pow(2, this.binaryPuzzleSize);
        for (int rowNumber = 0; rowNumber < amountOfPossibleRowCombinations; rowNumber++) {
            discoveredRowCombinations.add(this.buildUniqueRowCombination(rowNumber));
        }

        return discoveredRowCombinations;
    }

    /**
     * Build a Row Object for the unique row combination.
     * @param rowNumber
     * @return a Row Object containing the unique binary number combination.
     */
    private Row buildUniqueRowCombination(int rowNumber) {
        Row row = this.binaryPuzzle.instantiateAndReturnRow();

        int currentBinaryNumber = rowNumber;
        for (int i = 1; currentBinaryNumber != 0; i++) {

            int binaryNumber = currentBinaryNumber % 2;
            if (binaryNumber == 0) {
                row.addZero(binaryPuzzleSize - i);
            } else {
                row.addOne(binaryPuzzleSize - i);
            }

            currentBinaryNumber /= 2;
        }

        return row;
    }

    public Map<Integer, List<Row>> filterRowCombinationsBasedOnFixedPuzzleFields(List<Row> rowCombinations) {
        Map<Integer, List<Row>> filteredRowCombinations = new HashMap<>();

        for (int rowNumber = 0; rowNumber < this.binaryPuzzleSize; rowNumber++) {

            List<Row> matchedRows = new ArrayList<>();
            for (Row row : rowCombinations) {

                boolean isMatch = true;
                for (int fieldIndex = 0; fieldIndex < this.binaryPuzzleSize; fieldIndex++) {
                    if (this.binaryPuzzle.getRows().get(rowNumber).getFieldValues().get(fieldIndex) != FieldValue.EMPTY && (this.binaryPuzzle.getRows().get(rowNumber).getFieldValues().get(fieldIndex) != row.getFieldValues().get(fieldIndex))) {
                        isMatch = false;
                        break;
                    }
                }

                if (isMatch) {
                    matchedRows.add(row);
                }
            }

            filteredRowCombinations.put(rowNumber, matchedRows);
        }

        return filteredRowCombinations;
    }

    /**
     * Checks if the binary puzzle does comply to the three rules:
     * - No more than two similar numbers next to or below each other are allowed.
     * - TODO Each row and each column should contain an equal number of zeros and ones.
     * - Each row is unique and each column is unique.
     * @return true if the binary puzzle does comply to the rules, false otherwise.
     */
    public boolean complyToRowConstraints() {
        // Check Consecutive Constraints
        if (!this.complyToHorizontalConsecutiveConstraint()) {
            return false;
        }

        if (!this.complyToVerticalConsecutiveConstraint()) {
            return false;
        }

        // Check Unique Constraints
        if (!this.complyToHorizontalUniqueConstraint()) {
            return false;
        }

        if (!this.complyToVerticalUniqueConstraint()) {
            return false;
        }

        if (!this.complyToHorizontalEqualAmountOfOnesAndZeros()) {
            return false;
        }

        if (!this.complyToVerticalEqualAmountOfOnesAndZeros()) {
            return false;
        }

        return true;
    }

    public boolean complyToHorizontalConsecutiveConstraint() {
        for (Row row : this.binaryPuzzle.getRows()) {
            if (!this.complyToConsecutiveConstraint(row)) {
                return false;
            }
        }

        return true;
    }

    public boolean complyToVerticalConsecutiveConstraint() {
        for (int columnIndex = 0; columnIndex < this.binaryPuzzleSize; columnIndex++) {
            Row verticalRow = new BinaryRow(false, this.binaryPuzzleSize);

            for (Row horizontalRow : this.binaryPuzzle.getRows()) {
                verticalRow.addFieldValue(horizontalRow.getFieldValues().get(columnIndex));
            }

            if (!this.complyToConsecutiveConstraint(verticalRow)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks that no more than two similar numbers next to each other are allowed.
     * @param row the row which needs to be checked.
     * @return true if the row does comply to the constraints, false if otherwise.
     */
    public boolean complyToConsecutiveConstraint(Row row) {
        for (int index = 0; index < row.getFieldValues().size(); index++) {
            if (row.getFieldValues().get(index) == FieldValue.EMPTY) {
                continue;
            }

            // Skip beginning and end, because there is nothing to be compared yet
            // if you're on the first row, same for the last row.
            if (index == 0 || index == row.getFieldValues().size() - 1) {
                continue;
            }

            FieldValue currentValue = row.getFieldValues().get(index);
            FieldValue previousValue = row.getFieldValues().get(index - 1);
            FieldValue nexValue = row.getFieldValues().get(index + 1);
            if (currentValue == previousValue && currentValue == nexValue) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if each horizontal row in the binary puzzle is unique.
     * @return true if the binary puzzle does comply to the uniqueness constraint, false otherwise.
     */
    public boolean complyToHorizontalUniqueConstraint() {
        for (int i = 0; i < this.binaryPuzzleSize; i++) {
            for (int rowIndex = 0; rowIndex < binaryPuzzleSize; rowIndex++) {
                if (i != rowIndex) {
                    if (this.binaryPuzzle.getRows().get(i).equals(this.binaryPuzzle.getRows().get(rowIndex))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Checks if each vertical row in the binary puzzle is unique.
     * @return true if the binary puzzle does comply to the uniqueness constraint, false otherwise.
     */
    public boolean complyToVerticalUniqueConstraint() {
        List<Row> verticalRows = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < this.binaryPuzzleSize; columnIndex++) {
            Row verticalRow = new BinaryRow(false, this.binaryPuzzleSize);
            for (Row row : this.binaryPuzzle.getRows()) {
                verticalRow.addFieldValue(row.getFieldValues().get(columnIndex));
            }

            verticalRows.add(verticalRow);
        }

        for (int rowIndex = 0; rowIndex < verticalRows.size(); rowIndex++) {
            for (int i = 0; i < verticalRows.size(); i++) {
                if (i != rowIndex) {
                    if (verticalRows.get(rowIndex).equals(verticalRows.get(i))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean complyToHorizontalEqualAmountOfOnesAndZeros() {
        return this.complyToEqualAmountOfOnesAndZeros(this.binaryPuzzle.getRows());
    }

    public boolean complyToVerticalEqualAmountOfOnesAndZeros() {
        // Vertical Check
        List<Row> verticalRows = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < this.binaryPuzzleSize; columnIndex++) {
            Row verticalRow = new BinaryRow(false, this.binaryPuzzleSize);
            for (Row row : this.binaryPuzzle.getRows()) {
                verticalRow.addFieldValue(row.getFieldValues().get(columnIndex));
            }

            verticalRows.add(verticalRow);
        }

        return this.complyToEqualAmountOfOnesAndZeros(verticalRows);
    }

    public boolean complyToEqualAmountOfOnesAndZeros(List<Row> rows) {
        // Horizontal Check
        for (Row row : rows) {
            int numOfOnes = 0;
            for (FieldValue fieldValue : row.getFieldValues()) {
                if (fieldValue.equals(FieldValue.ONE)) {
                    numOfOnes++;
                }
            }

            if (numOfOnes != this.binaryPuzzleSize / 2) {
                return false;
            }
        }

        return true;
    }
}
