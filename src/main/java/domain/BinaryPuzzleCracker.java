package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryPuzzleCracker {
    private int binaryPuzzleSize;
    private BinaryPuzzle binaryPuzzle;

    public BinaryPuzzleCracker(int binaryPuzzleSize) {
        this.binaryPuzzleSize = binaryPuzzleSize;
        this.binaryPuzzle = BinaryPuzzle.getInstance(binaryPuzzleSize);
    }

    public BinaryPuzzle getBinaryPuzzle() {
        return binaryPuzzle;
    }

    public List<Row> discoverUniqueRowCombinations() {
        List<Row> discoveredRowCombinations = new ArrayList<>();

        int amountOfPossibleRowCombinations = (int) Math.pow(2, this.binaryPuzzleSize);
        for (int rowNumber = 0; rowNumber < amountOfPossibleRowCombinations; rowNumber++) {
            discoveredRowCombinations.add(this.buildUniqueRowCombination(rowNumber));
        }

        return discoveredRowCombinations;
    }

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
}
