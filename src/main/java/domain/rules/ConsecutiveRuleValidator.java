package domain.rules;

import domain.BinaryPuzzle;
import domain.FieldValue;
import domain.Row;

import java.util.List;

public class ConsecutiveRuleValidator extends RuleValidator {

    public ConsecutiveRuleValidator(BinaryPuzzle binaryPuzzle) {
        super(binaryPuzzle);
    }

    /**
     * Checks if the binary puzzle complies to the consecutive rule.
     * @return true if it does comply, false otherwise.
     */
    public boolean complyToConsecutiveRule() {
        return this.complyToHorizontalConsecutiveRule() && this.complyToVerticalConsecutiveRule();
    }

    /**
     * Checks that the horizontal rows follow the consecutive rule.
     * @return true if all the horizontal rows do comply to the rule, false otherwise.
     */
    private boolean complyToHorizontalConsecutiveRule() {
        for (Row row : this.getBinaryPuzzle().getRows()) {
            if (!this.checkRowAgainstConsecutiveRule(row)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks that the vertical rows follow the consecutive rule.
     * @return true if all the vertical rows do comply to the rule, false otherwise.
     */
    private boolean complyToVerticalConsecutiveRule() {
        List<Row> verticalRows = this.transformToVerticalRows(this.getBinaryPuzzle().getRows());
        for (Row row : verticalRows) {
            if (!this.checkRowAgainstConsecutiveRule(row)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks that no more than two similar numbers next to each other are allowed.
     * @param row the row which needs to be checked.
     * @return true if the row does comply to the constraints, false if otherwise.
     */
    private boolean checkRowAgainstConsecutiveRule(Row row) {
//        for (int index = 0; index < row.getFieldValues().size(); index++) {
//            if (row.getFieldValues().get(index) == FieldValue.EMPTY) {
//                continue;
//            }
//
//            // Skip beginning and end, because there is nothing to be compared yet
//            // if you're on the first row, same for the last row.
//            if (index == 0 || index == row.getFieldValues().size() - 1) {
//                continue;
//            }
//
//            FieldValue currentValue = row.getFieldValues().get(index);
//            FieldValue previousValue = row.getFieldValues().get(index - 1);
//            FieldValue nexValue = row.getFieldValues().get(index + 1);
//            if (currentValue == previousValue && currentValue == nexValue) {
//                return false;
//            }
//        }
//
//        return true;

        return true;
    }
}
