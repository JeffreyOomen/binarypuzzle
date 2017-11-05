package domain;

import java.util.List;

public interface Row {

    List<FieldValue> getFieldValues();

    void setFieldValues(List<FieldValue> fieldValues);

    Row addFieldValue(FieldValue fieldValue);

    Row addFieldValue(int index, FieldValue fieldValue);

    Row addEmpty();

    Row addZero();

    Row addOne();

    void printRow();
}
