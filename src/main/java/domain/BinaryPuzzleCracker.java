package domain;

import domain.rules.RuleValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryPuzzleCracker {
    private BinaryPuzzle binaryPuzzle;
    private int binaryPuzzleSize;
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
            if (this.complyToAllRules()) {
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
     * Discovers all unique row composition, e.g. suppose you have a binary puzzle of size 2,
     * the the unique row composition would be:
     * 0 0
     * 1 1
     * 0 1
     * 1 0
     * @return a list of all unique row compositions possible within the puzzle size.
     */
    public List<Row> discoverUniqueRowCompositions() {
        List<Row> uniqueRowCompositions = new ArrayList<>();

        int maximumRowCompositions = (int) Math.pow(2, this.binaryPuzzleSize);
        for (int rowNumber = 0; rowNumber < maximumRowCompositions; rowNumber++) {
            uniqueRowCompositions.add(this.buildUniqueRowComposition(rowNumber));
        }

        return uniqueRowCompositions;
    }

    /**
     * Build a Row Object for the unique row composition.
     * @param rowNumber
     * @return a Row Object containing the unique binary number combination.
     */
    private Row buildUniqueRowComposition(int rowNumber) {
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
     * - Each row and each column should contain an equal number of zeros and ones.
     * - Each row is unique and each column is unique.
     * @return true if the binary puzzle does comply to the rules, false otherwise.
     */
    public boolean complyToAllRules() {
        RuleValidator ruleValidator = new RuleValidator(this.binaryPuzzle);
        return ruleValidator.complyToAllRules();
    }
}
