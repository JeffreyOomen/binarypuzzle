package domain;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PuzzleRuleValidatorTests {
    private PuzzleRuleValidator puzzleRuleValidator;
    private Puzzle puzzle;

    @Before
    public void setUp() {
        this.puzzle = BinaryPuzzle.getInstance(6);
        this.puzzleRuleValidator =  new PuzzleRuleValidator(this.puzzle);
    }

    @Test
    public void adhereConsecutiveRuleWithPuzzleWhichAdheresToThisRuleShouldReturnTrue() {
        // Arrange
        Row row = new BinaryRow(false, 6).addEmpty().addEmpty().addOne().addOne().addZero().addZero();

        // Act
        boolean adheresRule = this.puzzleRuleValidator.adhereConsecutiveRule(row);

        // Assert
        assertTrue(adheresRule);
    }

    @Test
    public void adhereConsecutiveRuleWithPuzzleWhichNotAdheresToThisRuleShouldReturnFalse() {
        // Arrange
        Row row = new BinaryRow(false, 6).addEmpty().addEmpty().addOne().addOne().addOne().addZero();

        // Act
        boolean adheresRule = this.puzzleRuleValidator.adhereConsecutiveRule(row);

        // Assert
        assertFalse(adheresRule);
    }

    @Test
    public void adhereUniquenessRuleWithPuzzleWhichAdheresToThisRuleShouldReturnTrue() {
        // Arrange
        this.puzzle.instantiateRow().addEmpty().addEmpty().addOne().addEmpty().addEmpty().addEmpty();
        this.puzzle.instantiateRow().addZero().addZero().addEmpty().addOne().addEmpty().addEmpty();
        this.puzzle.instantiateRow().addZero().addEmpty().addEmpty().addEmpty().addEmpty().addEmpty();
        this.puzzle.instantiateRow().addEmpty().addEmpty().addEmpty().addEmpty().addEmpty().addEmpty();
        this.puzzle.instantiateRow().addEmpty().addEmpty().addEmpty().addOne().addEmpty().addEmpty();
        this.puzzle.instantiateRow().addEmpty().addEmpty().addEmpty().addEmpty().addZero().addEmpty();

        // Act
        boolean adheresRule = this.puzzleRuleValidator.adhereUniquenessRule(this.puzzle.getRows());

        // Assert
        assertTrue(adheresRule);
    }

    @Test
    public void adhereUniquenessRuleWithPuzzleWhichNotAdheresToThisRuleShouldReturnFalse() {
        // Arrange
        this.puzzle.instantiateRow().addEmpty().addEmpty().addOne().addEmpty().addEmpty().addEmpty();
        this.puzzle.instantiateRow().addZero().addZero().addEmpty().addOne().addEmpty().addEmpty();
        this.puzzle.instantiateRow().addZero().addEmpty().addEmpty().addEmpty().addEmpty().addEmpty();
        this.puzzle.instantiateRow().addEmpty().addEmpty().addOne().addEmpty().addEmpty().addEmpty(); // this row is equal to the first row
        this.puzzle.instantiateRow().addEmpty().addEmpty().addEmpty().addOne().addEmpty().addEmpty();
        this.puzzle.instantiateRow().addEmpty().addEmpty().addEmpty().addEmpty().addZero().addEmpty();

        // Act
        boolean adheresRule = this.puzzleRuleValidator.adhereUniquenessRule(this.puzzle.getRows());

        // Assert
        assertFalse(adheresRule);
    }

    @Test
    public void adhereOccurrencesRuleWithPuzzleWhichAdheresToThisRuleShouldReturnTrue() {
        // Arrange
        Row row = new BinaryRow(false, 6).addOne().addOne().addOne().addZero().addZero().addZero();

        // Act
        boolean adheresRule = this.puzzleRuleValidator.adhereOccurrencesRule(row);

        // Assert
        assertTrue(adheresRule);
    }

    @Test
    public void adhereOccurrencesRuleWithPuzzleWhichNotAdheresToThisRuleShouldReturnFalse() {
        // Arrange
        Row row = new BinaryRow(false, 6).addOne().addOne().addOne().addOne().addZero().addZero();

        // Act
        boolean adheresRule = this.puzzleRuleValidator.adhereOccurrencesRule(row);

        // Assert
        assertFalse(adheresRule);
    }

    @Test
    public void adhereToRulesHorizontallyWithPuzzleWhichAdheresTheRulesHorizontallyShouldReturnTrue() {
        // Arrange
        this.puzzle.instantiateRow().addOne().addZero().addOne().addZero().addOne().addZero();
        this.puzzle.instantiateRow().addZero().addZero().addOne().addOne().addZero().addOne();
        this.puzzle.instantiateRow().addZero().addOne().addZero().addZero().addOne().addOne();
        this.puzzle.instantiateRow().addOne().addOne().addZero().addZero().addOne().addZero();
        this.puzzle.instantiateRow().addOne().addZero().addOne().addOne().addZero().addZero();
        this.puzzle.instantiateRow().addZero().addOne().addZero().addOne().addZero().addOne();

        // Act
        boolean adheresRules = this.puzzleRuleValidator.adhereToRulesHorizontally(this.puzzle.getRows());

        // Assert
        assertTrue(adheresRules);
    }

    @Test
    public void adhereToRulesHorizontallyWithPuzzleWhichNotAdheresTheRulesHorizontallyShouldReturnFalse() {
        // Arrange
        this.puzzle.instantiateRow().addOne().addZero().addOne().addZero().addOne().addZero();
        this.puzzle.instantiateRow().addZero().addZero().addOne().addOne().addZero().addOne();
        this.puzzle.instantiateRow().addZero().addOne().addZero().addZero().addOne().addOne();
        this.puzzle.instantiateRow().addOne().addOne().addOne().addZero().addOne().addZero();
        this.puzzle.instantiateRow().addOne().addZero().addOne().addOne().addZero().addZero();
        this.puzzle.instantiateRow().addZero().addOne().addZero().addOne().addZero().addOne();

        // Act
        boolean adheresRules = this.puzzleRuleValidator.adhereToRulesHorizontally(this.puzzle.getRows());

        // Assert
        assertFalse(adheresRules);
    }

    @Test
    public void adhereToRulesVerticallyWithPuzzleWhichAdheresTheRulesVerticallyShouldReturnTrue() {
        // Arrange
        this.puzzle.instantiateRow().addOne().addZero().addOne().addZero().addOne().addZero();
        this.puzzle.instantiateRow().addZero().addZero().addOne().addOne().addZero().addOne();
        this.puzzle.instantiateRow().addZero().addOne().addZero().addZero().addOne().addOne();
        this.puzzle.instantiateRow().addOne().addOne().addZero().addZero().addOne().addZero();
        this.puzzle.instantiateRow().addOne().addZero().addOne().addOne().addZero().addZero();
        this.puzzle.instantiateRow().addZero().addOne().addZero().addOne().addZero().addOne();

        // Act
        boolean adheresRules = this.puzzleRuleValidator.adhereToRulesVertically(this.puzzle.getRows());

        // Assert
        assertTrue(adheresRules);
    }

    @Test
    public void adhereToRulesVerticallyWithPuzzleWhichNotAdheresTheRulesVerticallyShouldReturnFalse() {
        // Arrange
        this.puzzle.instantiateRow().addOne().addZero().addOne().addZero().addOne().addZero();
        this.puzzle.instantiateRow().addZero().addZero().addOne().addOne().addZero().addOne();
        this.puzzle.instantiateRow().addZero().addOne().addZero().addZero().addOne().addOne();
        this.puzzle.instantiateRow().addOne().addOne().addOne().addZero().addOne().addZero();
        this.puzzle.instantiateRow().addOne().addZero().addOne().addOne().addZero().addZero();
        this.puzzle.instantiateRow().addZero().addOne().addZero().addOne().addZero().addOne();

        // Act
        boolean adheresRules = this.puzzleRuleValidator.adhereToRulesVertically(this.puzzle.getRows());

        // Assert
        assertFalse(adheresRules);
    }

    @Test
    public void adhereToPuzzleRulesWithPuzzleWhichAdheresAllRulesShouldReturnTrue() {
        // Arrange
        this.puzzle.instantiateRow().addOne().addZero().addOne().addZero().addOne().addZero();
        this.puzzle.instantiateRow().addZero().addZero().addOne().addOne().addZero().addOne();
        this.puzzle.instantiateRow().addZero().addOne().addZero().addZero().addOne().addOne();
        this.puzzle.instantiateRow().addOne().addOne().addZero().addZero().addOne().addZero();
        this.puzzle.instantiateRow().addOne().addZero().addOne().addOne().addZero().addZero();
        this.puzzle.instantiateRow().addZero().addOne().addZero().addOne().addZero().addOne();

        // Act
        boolean adheresRules = this.puzzleRuleValidator.adhereToPuzzleRules();

        // Assert
        assertTrue(adheresRules);
    }

    @Test
    public void adhereToPuzzleRulesWithPuzzleWhichNotAdheresAllRulesShouldReturnFalse() {
        // Arrange
        this.puzzle.instantiateRow().addOne().addZero().addOne().addZero().addOne().addZero();
        this.puzzle.instantiateRow().addZero().addZero().addOne().addOne().addZero().addOne();
        this.puzzle.instantiateRow().addZero().addOne().addZero().addZero().addOne().addOne();
        this.puzzle.instantiateRow().addOne().addOne().addOne().addZero().addOne().addZero();
        this.puzzle.instantiateRow().addOne().addZero().addOne().addOne().addZero().addZero();
        this.puzzle.instantiateRow().addZero().addOne().addZero().addOne().addZero().addOne();

        // Act
        boolean adheresRules = this.puzzleRuleValidator.adhereToPuzzleRules();

        // Assert
        assertFalse(adheresRules);
    }

    @Test
    public void transformToVerticalRowsWithHorizontalRowsShouldReturnListWithVerticalRows() {
        // Arrange
        this.puzzle.instantiateRow().addOne().addZero().addOne().addZero().addOne().addZero();
        this.puzzle.instantiateRow().addZero().addZero().addOne().addOne().addZero().addOne();
        this.puzzle.instantiateRow().addZero().addOne().addZero().addZero().addOne().addOne();
        this.puzzle.instantiateRow().addOne().addOne().addOne().addZero().addOne().addZero();
        this.puzzle.instantiateRow().addOne().addZero().addOne().addOne().addZero().addZero();
        this.puzzle.instantiateRow().addZero().addOne().addZero().addOne().addZero().addOne();

        // Expectations
        Row row1 = new BinaryRow(false, 6).addOne().addZero().addZero().addOne().addOne().addZero();
        Row row2 = new BinaryRow(false, 6).addZero().addZero().addOne().addOne().addZero().addOne();
        Row row3 = new BinaryRow(false, 6).addOne().addOne().addZero().addOne().addOne().addZero();
        Row row4 = new BinaryRow(false, 6).addZero().addOne().addZero().addZero().addOne().addOne();
        Row row5 = new BinaryRow(false, 6).addOne().addZero().addOne().addOne().addZero().addZero();
        Row row6 = new BinaryRow(false, 6).addZero().addOne().addOne().addZero().addZero().addOne();

        // Act
        List<Row> verticalRows = this.puzzleRuleValidator.transformToVerticalRows(this.puzzle.getRows());

        // Assert
        assertThat(verticalRows.size(), is(this.puzzle.getSize()));
        assertThat(verticalRows, hasItems(
                row1,
                row2,
                row3,
                row4,
                row5,
                row6
        ));
    }
}
