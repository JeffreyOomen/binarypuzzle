package domain;

import exceptions.FieldsEludedException;
import exceptions.NoRowAvailableException;

import java.util.ArrayList;
import java.util.List;

public class BinaryPuzzle implements Puzzle {
    private List<Row> rows;
    private Row temporaryRow;
    private int size;

    // Private Constructor
    private BinaryPuzzle(int size) {
        this.rows = new ArrayList<>();
        this.size = size;
    }

    public static BinaryPuzzle getInstance(int size) {
        return new BinaryPuzzle(size);
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
        this.temporaryRow = new BinaryRow(false, this.size);
        this.rows.add(this.temporaryRow);
        return this;
    }

    public Row instantiateAndReturnRow() {
        this.canInstantiateRowCheck();
        this.temporaryRow = new BinaryRow(true, this.size);
        return this.temporaryRow;
    }

    public void replaceRow(int index, Row row) {
        this.getRows().set(index, row);
    }

    public BinaryPuzzle addEmpty() {
        this.canAddFieldToRowCheck();
        this.temporaryRow.addEmpty();
        return this;
    }

    public BinaryPuzzle addEmpty(int index) {
        this.canAddFieldToRowCheck();
        this.temporaryRow.addEmpty(index);
        return this;
    }

    public BinaryPuzzle addZero() {
        this.canAddFieldToRowCheck();
        this.temporaryRow.addZero();
        return this;
    }

    public BinaryPuzzle addZero(int index) {
        this.canAddFieldToRowCheck();
        this.temporaryRow.addZero(index);
        return this;
    }

    public BinaryPuzzle addOne() {
        this.canAddFieldToRowCheck();
        this.temporaryRow.addOne();
        return this;
    }

    public BinaryPuzzle addOne(int index) {
        this.canAddFieldToRowCheck();
        this.temporaryRow.addOne(index);
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
}
