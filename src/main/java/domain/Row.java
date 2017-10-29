package domain;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private List<FieldValue> fieldValues;

    public Row() {
        this.fieldValues = new ArrayList<>();
    }

    public Row(List<FieldValue> fieldValues) {
        this.fieldValues = fieldValues;
    }

    public List<FieldValue> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(List<FieldValue> fieldValues) {
        this.fieldValues = fieldValues;
    }

    public Row addFieldValue(FieldValue fieldValue) {
        this.fieldValues.add(fieldValue);
        return this;
    }

    public Row addFieldValue(int index, FieldValue fieldValue) {
        this.fieldValues.add(index, fieldValue);
        return this;
    }

    public Row addFieldEmpty() {
        this.fieldValues.add(FieldValue.EMPTY);
        return this;
    }

    public Row addFieldEmpty(int index) {
        this.fieldValues.add(index, FieldValue.EMPTY);
        return this;
    }

    public Row addFieldZero() {
        this.fieldValues.add(FieldValue.ZERO);
        return this;
    }

    public Row addFieldZero(int index) {
        this.fieldValues.add(index, FieldValue.ZERO);
        return this;
    }

    public Row addFieldOne() {
        this.fieldValues.add(FieldValue.ONE);
        return this;
    }

    public Row addFieldOne(int index) {
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
