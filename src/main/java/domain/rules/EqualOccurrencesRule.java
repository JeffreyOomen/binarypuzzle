package domain.rules;

import domain.BinaryPuzzle;
import domain.BinaryRow;
import domain.FieldValue;
import domain.Row;

import java.util.ArrayList;
import java.util.List;

public class EqualOccurrencesRule extends RuleValidator {

    public EqualOccurrencesRule(BinaryPuzzle binaryPuzzle) {
        super(binaryPuzzle);
    }

    /**
     * Checks if the binary puzzle complies to the equal occurrences rule.
     * @return true if it does comply, false otherwise.
     */
    public boolean complyToEqualOccurrencesRule() {
        return this.complyToHorizontalEqualAmountOfOnesAndZeros() && this.complyToVerticalEqualAmountOfOnesAndZeros();
    }

    private boolean complyToHorizontalEqualAmountOfOnesAndZeros() {
        return this.complyToEqualAmountOfOnesAndZeros(this.getBinaryPuzzle().getRows());
    }

    private boolean complyToVerticalEqualAmountOfOnesAndZeros() {
        List<Row> verticalRows = this.transformToVerticalRows(this.getBinaryPuzzle().getRows());
        return this.complyToEqualAmountOfOnesAndZeros(verticalRows);
    }

    private boolean complyToEqualAmountOfOnesAndZeros(List<Row> rows) {
        for (Row row : rows) {
            int numOfOnes = 0;
            for (FieldValue fieldValue : row.getFieldValues()) {
                if (fieldValue.equals(FieldValue.ONE)) {
                    numOfOnes++;
                }
            }

            if (numOfOnes != this.getBinaryPuzzle().getSize() / 2) {
                return false;
            }
        }

        return true;
    }
}
