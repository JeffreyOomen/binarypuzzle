package domain.rules;

import domain.BinaryPuzzle;
import domain.Row;

import java.util.List;

public class UniquenessRuleValidator extends RuleValidator {

    public UniquenessRuleValidator(BinaryPuzzle binaryPuzzle) {
        super(binaryPuzzle);
    }

    /**
     * Checks if the binary puzzle complies to the uniqueness rule.
     * @return true if it does comply, false otherwise.
     */
    public boolean complyToUniquenessRule() {
        return this.complyToHorizontalUniqueRule() && this.complyToVerticalUniqueRule();
    }

    /**
     * Checks if each horizontal row in the binary puzzle is unique.
     * @return true if the binary puzzle does comply to the uniqueness rule, false otherwise.
     */
    public boolean complyToHorizontalUniqueRule() {
        for (int i = 0; i < this.getBinaryPuzzle().getSize(); i++) {
            for (int rowIndex = 0; rowIndex < this.getBinaryPuzzle().getSize(); rowIndex++) {
                if (i != rowIndex) {
                    if (this.getBinaryPuzzle().getRows().get(i).equals(this.getBinaryPuzzle().getRows().get(rowIndex))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Checks if each vertical row in the binary puzzle is unique.
     * @return true if the binary puzzle does comply to the uniqueness rule, false otherwise.
     */
    public boolean complyToVerticalUniqueRule() {
        List<Row> verticalRows = this.transformToVerticalRows(this.getBinaryPuzzle().getRows());

        for (int rowIndex = 0; rowIndex < verticalRows.size(); rowIndex++) {
            for (int i = 0; i < verticalRows.size(); i++) {
                if (i != rowIndex) {
                    if (verticalRows.get(rowIndex).equals(verticalRows.get(i))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
