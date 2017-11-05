package domain;

import exceptions.FieldsEludedException;
import exceptions.FieldsExceededException;
import exceptions.NoRowAvailableException;

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

    public static void destroyInstance() {
        if (binaryPuzzle != null) {
            binaryPuzzle = null;
        }
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
        this.canInstantiateRowCheck();
        this.temporaryRow = new BinaryRow();
        this.rows.add(this.temporaryRow);
        return this;
    }

    public BinaryPuzzle addEmpty() {
        this.canAddFieldToRowCheck();
        this.temporaryRow.addEmpty();
        return this;
    }

    public BinaryPuzzle addZero() {
        this.canAddFieldToRowCheck();
        this.temporaryRow.addZero();
        return this;
    }

    public BinaryPuzzle addOne() {
        this.canAddFieldToRowCheck();
        this.temporaryRow.addOne();
        return this;
    }

    private void canInstantiateRowCheck() {
        if (this.temporaryRow != null && this.temporaryRow.getFieldValues().size() < size)
            throw new FieldsEludedException("The maximum number of fields in the previous row has not been reached yet.");
    }

    private void canAddFieldToRowCheck() {
        if (this.temporaryRow == null)
            throw new NoRowAvailableException("There is no row instantiated to add the field to.");
    }

    private class BinaryRow implements Row {
        private List<FieldValue> fieldValues;

        public BinaryRow() {
            this.fieldValues = new ArrayList<>(getSize());
        }

        public BinaryRow(List<FieldValue> fieldValues) {
            this.fieldValues = fieldValues;
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
            this.fieldAmountCheckForAdd();
            this.fieldValues.add(FieldValue.EMPTY);
            return this;
        }

        public BinaryRow addEmpty(int index) {
            this.fieldValues.add(index, FieldValue.EMPTY);
            return this;
        }

        public BinaryRow addZero() {
            this.fieldAmountCheckForAdd();
            this.fieldValues.add(FieldValue.ZERO);
            return this;
        }

        public BinaryRow addZero(int index) {
            this.fieldValues.add(index, FieldValue.ZERO);
            return this;
        }

        public BinaryRow addOne() {
            this.fieldAmountCheckForAdd();
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

        private void fieldAmountCheckForAdd() {
            if (this.fieldValues.size() + 1 > size)
                throw new FieldsExceededException("The maximum number of fields has been reached.");
        }
    }
}
