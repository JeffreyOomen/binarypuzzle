package domain.rules;

import domain.BinaryPuzzle;
import domain.BinaryRow;
import domain.Row;

import java.util.ArrayList;
import java.util.List;

public class RuleValidator {
    private BinaryPuzzle binaryPuzzle;
    private ConsecutiveRule consecutiveRule;
    private UniquenessRule uniquenessRule;
    private EqualOccurrencesRule equalOccurrencesRule;

    public RuleValidator(BinaryPuzzle binaryPuzzle) {
        this.binaryPuzzle = binaryPuzzle;
        this.consecutiveRule = new ConsecutiveRule(this.binaryPuzzle);
        this.uniquenessRule = new UniquenessRule(this.binaryPuzzle);
        this.equalOccurrencesRule = new EqualOccurrencesRule(this.binaryPuzzle);
    }

    public BinaryPuzzle getBinaryPuzzle() {
        return binaryPuzzle;
    }

    public boolean complyToAllRules() {
        boolean complyConsecutiveRule = this.consecutiveRule.complyToConsecutiveRule();
        boolean complyUniquenessRule = this.uniquenessRule.complyToUniquenessRule();
        boolean complyEqualOccurrencesRule = this.equalOccurrencesRule.complyToEqualOccurrencesRule();

        return complyConsecutiveRule && complyUniquenessRule && complyEqualOccurrencesRule;
    }

    /**
     * Transform a List of horizontal rows to a List of vertical rows.
     * @param horizontalRows a List containing horizontal rows.
     * @return a List of vertical rows.
     */
    protected List<Row> transformToVerticalRows(List<Row> horizontalRows) {
        List<Row> verticalRows = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < this.binaryPuzzle.getSize(); columnIndex++) {
            Row verticalRow = new BinaryRow(false, this.binaryPuzzle.getSize());
            for (Row row : this.binaryPuzzle.getRows()) {
                verticalRow.addFieldValue(row.getFieldValues().get(columnIndex));
            }

            verticalRows.add(verticalRow);
        }

        return verticalRows;
    }
}
