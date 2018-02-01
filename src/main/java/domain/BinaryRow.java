package domain;

import exceptions.FieldsExceededException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BinaryRow implements Row {
    private List<FieldValue> fieldValues;
    private int size;

    public BinaryRow(boolean shouldFill, int size) {
        if (!shouldFill) {
            this.fieldValues = new ArrayList<>();
        } else {
            this.fieldValues = new ArrayList<>(Collections.nCopies(size, FieldValue.ZERO));
        }

        this.size = size;
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
        this.fieldValues.set(index, FieldValue.EMPTY);
        return this;
    }

    public BinaryRow addZero() {
        this.fieldAmountCheckForAdd();
        this.fieldValues.add(FieldValue.ZERO);
        return this;
    }

    public BinaryRow addZero(int index) {
        this.fieldValues.set(index, FieldValue.ZERO);
        return this;
    }

    public BinaryRow addOne() {
        this.fieldAmountCheckForAdd();
        this.fieldValues.add(FieldValue.ONE);
        return this;
    }

    public BinaryRow addOne(int index) {
        this.fieldValues.set(index, FieldValue.ONE);
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
        if (this.fieldValues.size() + 1 > this.size)
            throw new FieldsExceededException("The maximum number of fields has been reached.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryRow binaryRow = (BinaryRow) o;
        return Objects.equals(fieldValues, binaryRow.fieldValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldValues);
    }
}
