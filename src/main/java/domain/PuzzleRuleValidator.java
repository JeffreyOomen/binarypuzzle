package domain;

import java.util.*;

public class PuzzleRuleValidator {
    private Puzzle puzzle;

    public PuzzleRuleValidator(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    /**
     * Facade method which checks if all Puzzle Rules are adhered by the Puzzle.
     * @return True if all rules are adhered, false otherwise.
     */
    public boolean adhereToPuzzleRules() {
        return this.adhereToRulesHorizontally(this.puzzle.getRows()) &&
                this.adhereToRulesVertically(this.puzzle.getRows()) &&
                this.adhereUniquenessRule(this.puzzle.getRows());
    }

    /**
     * Facade method which checks against Rules which can be checked horizontally.
     * @param rows the rows for which the Horizontal Rules should be checked.
     * @return True if all Horizontal Rules are adhered, false otherwise.
     */
    public boolean adhereToRulesHorizontally(List<Row> rows) {
        for (Row row : rows) {
            if (!this.adhereConsecutiveRule(row) || !this.adhereOccurrencesRule(row)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Facade method which checks against Rules which can be checked vertically.
     * @param rows the rows for which the Vertical Rules should be checked.
     * @return True if all Vertical Rules are adhered, false otherwise.
     */
    public boolean adhereToRulesVertically(List<Row> rows) {
        List<Row> verticalRows = this.transformToVerticalRows(rows);
        return this.adhereToRulesHorizontally(verticalRows);
    }

    /**
     * Checks if the Row adheres to the Consecutive Rule which states that
     * a row cannot contain more than 2 field values which are the same.
     * @param row a Row for which the Consecutive Rule should be checked.
     * @return True if the Row does adhere to this rule, false otherwise.
     */
    public boolean adhereConsecutiveRule(Row row) {
        for (int index = 0; index < row.getFieldValues().size(); index++) {
            if (row.getFieldValues().get(index) == FieldValue.EMPTY) {
                continue;
            }

            // Skip beginning and end, because there is nothing to be compared yet
            // if you're on the first row, same for the last row.
            if (index == 0 || index == row.getFieldValues().size() - 1) {
                continue;
            }

            FieldValue currentValue = row.getFieldValues().get(index);
            FieldValue previousValue = row.getFieldValues().get(index - 1);
            FieldValue nexValue = row.getFieldValues().get(index + 1);
            if (currentValue == previousValue && currentValue == nexValue) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the List of Row-Objects adheres to the Uniqueness Rule which states that
     * in a binary puzzle, each horizontal and vertical rows should be unique.
     * @param rows a List of Row-Objects which should be checked against the Uniqueness Rule.
     * @return True if the List of Row-Objects adheres to the Uniqueness Rule, false otherwise.
     */
    public boolean adhereUniquenessRule(List<Row> rows) {
        final Set<Row> duplicates = new HashSet<>();
        return rows.stream().allMatch(duplicates::add);
    }

    /**
     * Checks if the Row-Object adheres to the Occurrences Rule which states that each row
     * should contain an equal amount of ONE's and ZERO's.
     * @param row a Row-Object which should be checked against the Occurrences Rule.
     * @return True if the Row-Object adheres to the Occurrences Rule, false otherwise.
     */
    public boolean adhereOccurrencesRule(Row row) {
        int halfRowSize = puzzle.getSize() / 2;
        return Collections.frequency(row.getFieldValues(), FieldValue.ONE) == halfRowSize;
    }

    /**
     * Transform a List of horizontal rows to a List of vertical rows.
     * @param horizontalRows a List containing horizontal rows.
     * @return a List of vertical rows.
     */
    public List<Row> transformToVerticalRows(List<Row> horizontalRows) {
        List<Row> verticalRows = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < this.puzzle.getSize(); columnIndex++) {
            Row verticalRow = new BinaryRow(false, this.puzzle.getSize());
            for (Row row : horizontalRows) {
                verticalRow.addFieldValue(row.getFieldValues().get(columnIndex));
            }

            verticalRows.add(verticalRow);
        }

        return verticalRows;
    }
}
