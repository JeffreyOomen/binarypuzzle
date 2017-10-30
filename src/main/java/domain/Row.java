package domain;

import java.util.Collections;
import java.util.Vector;

public class Row {
    private Vector<FieldValue> fieldValues;

    public Row(int size) {
        this.fieldValues = new Vector<>();
        this.fieldValues.setSize(size);
        Collections.fill(this.fieldValues, FieldValue.ZERO);
    }

    public Row(Vector<FieldValue> fieldValues) {
        this.fieldValues = fieldValues;
        this.fieldValues.setSize(2);
    }

    public Vector<FieldValue> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(Vector<FieldValue> fieldValues) {
        this.fieldValues = fieldValues;
    }

    public Row addFieldValue(FieldValue fieldValue) {
        this.fieldValues.add(fieldValue);
        return this;
    }

    public Row addFieldValue(int index, FieldValue fieldValue) {
        this.fieldValues.set(index, fieldValue);
        return this;
    }

    public Row addFieldEmpty() {
        this.fieldValues.add(FieldValue.EMPTY);
        return this;
    }

    public Row addFieldEmpty(int index) {
        this.fieldValues.set(index, FieldValue.EMPTY);
        return this;
    }

    public Row addFieldZero() {
        this.fieldValues.add(FieldValue.ZERO);
        return this;
    }

    public Row addFieldZero(int index) {
        this.fieldValues.set(index, FieldValue.ZERO);
        return this;
    }

    public Row addFieldOne() {
        this.fieldValues.add(FieldValue.ONE);
        return this;
    }

    public Row addFieldOne(int index) {
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
}
