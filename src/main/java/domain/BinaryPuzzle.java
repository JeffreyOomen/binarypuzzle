package domain;

import java.util.ArrayList;
import java.util.List;

public class BinaryPuzzle implements Puzzle {
    private static BinaryPuzzle binaryPuzzle;
    private int size;
    private List<Row> rows;
    private Row temporaryRow;

    // Private Constructor
    private BinaryPuzzle(int size) {
        this.size = size;
        this.rows = new ArrayList<>();
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

    public List<Row> getRows() {
        return this.rows;
    }

    public void printPuzzle() {
        for (Row row : this.rows) {
            row.printRow();
        }
    }

    public BinaryPuzzle instantiateRow() {
        this.temporaryRow = new BinaryRow();
        this.rows.add(this.temporaryRow);
        return this;
    }

    public BinaryPuzzle addEmpty() {
        this.temporaryRow.addEmpty();
        return this;
    }

    public BinaryPuzzle addZero() {
        this.temporaryRow.addZero();
        return this;
    }

    public BinaryPuzzle addOne() {
        this.temporaryRow.addOne();
        return this;
    }

    private class BinaryRow implements Row {
        private List<FieldValue> fieldValues;
        private int nextFieldValuesIndex;

        public BinaryRow() {
            this.fieldValues = new ArrayList<>(getSize());
        }

        public BinaryRow(List<FieldValue> fieldValues) {
            this.fieldValues = fieldValues;
            this.nextFieldValuesIndex = 0;
        }

        public List<FieldValue> getFieldValues() {
            return fieldValues;
        }

        public void setFieldValues(List<FieldValue> fieldValues) {
            this.fieldValues = fieldValues;
        }

        public BinaryRow addFieldValue(FieldValue fieldValue) {
            this.fieldValues.add(fieldValue);
            return this;
        }

        public BinaryRow addFieldValue(int index, FieldValue fieldValue) {
            this.fieldValues.add(index, fieldValue);
            return this;
        }

        public BinaryRow addEmpty() {
            this.fieldValues.add(FieldValue.EMPTY);
            return this;
        }

        public BinaryRow addEmpty(int index) {
            this.fieldValues.add(index, FieldValue.EMPTY);
            return this;
        }

        public BinaryRow addZero() {
            this.fieldValues.add(FieldValue.ZERO);
            return this;
        }

        public BinaryRow addZero(int index) {
            this.fieldValues.add(index, FieldValue.ZERO);
            return this;
        }

        public BinaryRow addOne() {
            this.fieldValues.add(FieldValue.ONE);
            return this;
        }

        public BinaryRow addOne(int index) {
            this.fieldValues.add(index, FieldValue.ONE);
            return this;
        }

        public void printRow() {
            String rowRepresentation = "";
            for (FieldValue fieldValue : this.fieldValues) {
                if (fieldValue == FieldValue.EMPTY) {
                    rowRepresentation += " -1 ";
                } else if (fieldValue == FieldValue.ONE) {
                    rowRepresentation += "  1 ";
                } else {
                    rowRepresentation += "  0 ";
                }
            }

            System.out.println(rowRepresentation);
        }
    }

//    class PuzzleValidator {
//        public void validateRows(List<Row> rows) {
//            if (rows == null) {
//                throw new NullPointerException("The rows variable is NULL, but should contain " + size + " rows.");
//            }
//
//            if (rows.size() > size) {
//                throw new IllegalArgumentException("Too many rows, there should be " + size + " rows.");
//            }
//
//            if (rows.size() < size) {
//                throw new IllegalArgumentException("Too little rows, there should be " + size + " rows.");
//            }
//
//            for (int rowIndex = 0; rowIndex < size; rowIndex++) {
//                if (rows.get(rowIndex).getFieldValues().size() != size) {
//                    throw new IllegalArgumentException("Row #" + (rowIndex + 1) + " should contain " + size + " field values, but there are " + rows.get(rowIndex).getFieldValues().size() + " found.");
//                }
//            }
//        }
//    }
}
