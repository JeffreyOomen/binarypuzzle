//package domain;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import domain.BinaryPuzzle.Row;
//
//public class BinaryPuzzleCracker {
//    private int binaryPuzzleSize;
//
//    public BinaryPuzzleCracker(int binaryPuzzleSize) {
//        this.binaryPuzzleSize = binaryPuzzleSize;
//    }
//
//    public List<Row> discoverUniqueRowCombinations() {
//        List<Row> discoveredRowCombinations = new ArrayList<>();
//
//        int amountOfPossibleRowCombinations = (int) Math.pow(2, this.binaryPuzzleSize);
//        for (int rowNumber = 0; rowNumber < amountOfPossibleRowCombinations; rowNumber++) {
//            discoveredRowCombinations.add(this.buildUniqueRowCombination(rowNumber));
//        }
//
//        return discoveredRowCombinations;
//    }
//
//    private Row buildUniqueRowCombination(int rowNumber) {
//        Row uniqueRow = new Row();
//
//        int currentBinaryNumber = rowNumber;
//        for (int i = 1; currentBinaryNumber != 0; i++) {
//
//            int binaryNumber = currentBinaryNumber % 2;
//            if (binaryNumber == 0) {
//                uniqueRow.addFieldZero(binaryPuzzleSize - i);
//            } else {
//                uniqueRow.addFieldOne(binaryPuzzleSize - i);
//            }
//
//            currentBinaryNumber /= 2;
//        }
//
//        return uniqueRow;
//    }
//
//    public Map<Integer, List<Row>> filterRowCombinationsBasedOnFixedPuzzleFields(BinaryPuzzle binaryPuzzle, List<Row> rowCombinations) {
//        Map<Integer, List<Row>> filteredRowCombinations = new HashMap<>();
//
//        for (int rowNumber = 0; rowNumber < this.binaryPuzzleSize; rowNumber++) {
//
//            List<Row> matchedRows = new ArrayList<>();
//            for (Row row : rowCombinations) {
//
//                boolean isMatch = true;
//                for (int fieldIndex = 0; fieldIndex < this.binaryPuzzleSize; fieldIndex++) {
//                    if (binaryPuzzle.getRows().get(rowNumber).getFieldValues()[fieldIndex] != row.getFieldValues()[fieldIndex]) {
//                        isMatch = false;
//                        break;
//                    }
//                }
//
//                if (isMatch) {
//                    matchedRows.add(row);
//                }
//            }
//
//            filteredRowCombinations.put(rowNumber, matchedRows);
//        }
//
//        return filteredRowCombinations;
//    }
//}
