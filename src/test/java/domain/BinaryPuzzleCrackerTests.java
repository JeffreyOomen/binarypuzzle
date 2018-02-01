package domain;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BinaryPuzzleCrackerTests {
    private BinaryPuzzleCracker binaryPuzzleCracker;

    @Before
    public void setUp() {
        binaryPuzzleCracker = new BinaryPuzzleCracker(2);
    }

    @Test
    public void discoverUniqueRowCombinationsWithBinaryPuzzleOfSize2ShouldReturn4UniqueCombinations() {
        // Act
        List<Row> rowCombinations = binaryPuzzleCracker.discoverUniqueRowCombinations();

        // Assert
        assertThat(rowCombinations.size(), is(4));
        assertThat(rowCombinations.get(0).getFieldValues(), is(new Vector<>(Arrays.asList(FieldValue.ZERO, FieldValue.ZERO))));
        assertThat(rowCombinations.get(1).getFieldValues(), is(new Vector<>(Arrays.asList(FieldValue.ZERO, FieldValue.ONE))));
        assertThat(rowCombinations.get(2).getFieldValues(), is(new Vector<>(Arrays.asList(FieldValue.ONE, FieldValue.ZERO))));
        assertThat(rowCombinations.get(3).getFieldValues(), is(new Vector<>(Arrays.asList(FieldValue.ONE, FieldValue.ONE))));
    }

    @Test
    public void filterRowCombinationsBasedOnFixedPuzzleFieldsWithBinaryPuzzleOfSize2ShouldReturnTwoUniqueCombinations() {
        // Arrange
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addEmpty().addOne();
        puzzle.instantiateRow().addZero().addEmpty();

        List<Row> rowCombinations = binaryPuzzleCracker.discoverUniqueRowCombinations();

        // Act
        Map<Integer, List<Row>> filteredRowCombinations = binaryPuzzleCracker.filterRowCombinationsBasedOnFixedPuzzleFields(rowCombinations);

        assertThat(filteredRowCombinations.get(0).size(), is(2));
        assertThat(filteredRowCombinations.get(1).size(), is(2));
    }

    @Test
    public void complyToConsecutiveConstraintWithPuzzleWhichHasARowContaining3ZerosBehindEachOtherShouldNotBeAllowed() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        Row row = puzzle.instantiateAndReturnRow().addZero(0).addZero(1).addZero(2);

        boolean doesComply = this.binaryPuzzleCracker.complyToConsecutiveConstraint(row);

        assertFalse(doesComply);
    }

    @Test
    public void complyToConsecutiveConstraintWithPuzzleWhichHasARowContaining3OnesBehindEachOtherShouldNotBeAllowed() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        Row row = puzzle.instantiateAndReturnRow().addOne(0).addOne(1).addOne(2);

        boolean doesComply = this.binaryPuzzleCracker.complyToConsecutiveConstraint(row);

        assertFalse(doesComply);
    }

    @Test
    public void complyToConsecutiveConstraintWithPuzzleWhichHasARowContaining3EmptiesBehindEachOtherShouldBeAllowed() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        Row row = puzzle.instantiateAndReturnRow().addEmpty(0).addEmpty(1).addEmpty(2);

        boolean doesComply = this.binaryPuzzleCracker.complyToConsecutiveConstraint(row);

        assertTrue(doesComply);
    }

    @Test
    public void complyToConsecutiveConstraintWithPuzzleWhichHasARowContaining2ZerosBehindEachOtherShouldBeAllowed() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        Row row = puzzle.instantiateAndReturnRow().addZero(0).addZero(1).addOne(2);

        boolean doesComply = this.binaryPuzzleCracker.complyToConsecutiveConstraint(row);

        assertTrue(doesComply);
    }

    @Test
    public void complyToConsecutiveConstraintWithPuzzleWhichHasARowContaining2OnesBehindEachOtherShouldBeAllowed() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        Row row = puzzle.instantiateAndReturnRow().addOne(0).addOne(1).addZero(2);

        boolean doesComply = this.binaryPuzzleCracker.complyToConsecutiveConstraint(row);

        assertTrue(doesComply);
    }

    @Test
    public void complyToHorizontalConsecutiveConstraintWithPuzzleWhichDoesNotComplyWithThisConstraintShouldReturnFalse() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addEmpty().addEmpty().addOne();
        puzzle.instantiateRow().addZero().addZero().addZero();
        puzzle.instantiateRow().addZero().addOne().addEmpty();

        boolean doesComply = this.binaryPuzzleCracker.complyToHorizontalConsecutiveConstraint();

        assertFalse(doesComply);
    }

    @Test
    public void complyToHorizontalConsecutiveConstraintWithPuzzleWhichDoesComplyWithThisConstraintShouldReturnTrue() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addEmpty().addEmpty().addOne();
        puzzle.instantiateRow().addZero().addOne().addZero();
        puzzle.instantiateRow().addZero().addOne().addEmpty();

        boolean doesComply = this.binaryPuzzleCracker.complyToHorizontalConsecutiveConstraint();

        assertTrue(doesComply);
    }

    @Test
    public void complyToVerticalConsecutiveConstraintWithPuzzleWhichDoesNotComplyWithThisConstraintShouldReturnFalse() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addZero().addEmpty().addOne();
        puzzle.instantiateRow().addZero().addZero().addZero();
        puzzle.instantiateRow().addZero().addOne().addEmpty();

        boolean doesComply = this.binaryPuzzleCracker.complyToVerticalConsecutiveConstraint();

        assertFalse(doesComply);
    }

    @Test
    public void complyToVerticalConsecutiveConstraintWithPuzzleWhichDoesComplyWithThisConstraintShouldReturnTrue() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addZero().addEmpty().addOne();
        puzzle.instantiateRow().addOne().addZero().addZero();
        puzzle.instantiateRow().addZero().addOne().addEmpty();

        boolean doesComply = this.binaryPuzzleCracker.complyToVerticalConsecutiveConstraint();

        assertTrue(doesComply);
    }

    @Test
    public void complyToHorizontalUniqueConstraintWithPuzzleContainingRowsThatComplyToThisRuleShouldReturnTrue() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(6);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addOne().addZero().addOne().addZero().addOne().addZero();
        puzzle.instantiateRow().addZero().addZero().addOne().addOne().addZero().addOne();
        puzzle.instantiateRow().addZero().addOne().addZero().addZero().addOne().addOne();
        puzzle.instantiateRow().addOne().addOne().addZero().addZero().addOne().addZero();
        puzzle.instantiateRow().addOne().addZero().addOne().addOne().addZero().addZero();
        puzzle.instantiateRow().addZero().addOne().addZero().addOne().addZero().addOne();

        boolean doesComply = this.binaryPuzzleCracker.complyToHorizontalUniqueConstraint();

        assertTrue(doesComply);
    }

    @Test
    public void complyToHorizontalUniqueConstraintWithPuzzleContainingRowsThatDoNotComplyToThisRuleShouldReturnFalse() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addEmpty().addEmpty().addOne();
        puzzle.instantiateRow().addZero().addZero().addEmpty();
        puzzle.instantiateRow().addZero().addZero().addEmpty();

        boolean doesComply = this.binaryPuzzleCracker.complyToHorizontalUniqueConstraint();

        assertFalse(doesComply);
    }

    @Test
    public void complyToVerticalUniqueConstraintWithPuzzleContainingRowsThatComplyToThisRuleShouldReturnTrue() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addEmpty().addEmpty().addOne();
        puzzle.instantiateRow().addZero().addZero().addEmpty();
        puzzle.instantiateRow().addZero().addOne().addEmpty();

        boolean doesComply = this.binaryPuzzleCracker.complyToVerticalUniqueConstraint();

        assertTrue(doesComply);
    }

    @Test
    public void complyToVerticalUniqueConstraintWithPuzzleContainingRowsThatDoNotComplyToThisRuleShouldReturnFalse() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addOne().addEmpty().addOne();
        puzzle.instantiateRow().addOne().addZero().addOne();
        puzzle.instantiateRow().addOne().addZero().addOne();

        boolean doesComply = this.binaryPuzzleCracker.complyToVerticalUniqueConstraint();

        assertFalse(doesComply);
    }

    @Test
    public void complyToRowConstraintsWithPuzzleContainingRowsThatViolatesTheHorizontalConsecutiveConstraintShouldReturnFalse() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addOne().addOne().addOne();
        puzzle.instantiateRow().addZero().addZero().addOne();
        puzzle.instantiateRow().addOne().addZero().addZero();

        boolean doesComply = this.binaryPuzzleCracker.complyToRowConstraints();

        assertFalse(doesComply);
    }

    @Test
    public void complyToRowConstraintsWithPuzzleContainingRowsThatViolatesTheVerticalConsecutiveConstraintShouldReturnFalse() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addOne().addZero().addOne();
        puzzle.instantiateRow().addZero().addZero().addOne();
        puzzle.instantiateRow().addOne().addZero().addZero();

        boolean doesComply = this.binaryPuzzleCracker.complyToRowConstraints();

        assertFalse(doesComply);
    }

    @Test
    public void complyToRowConstraintsWithPuzzleContainingRowsThatViolatesTheHorizontalUniquenessConstraintShouldReturnFalse() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addOne().addZero().addZero();
        puzzle.instantiateRow().addZero().addOne().addOne();
        puzzle.instantiateRow().addOne().addZero().addZero();

        boolean doesComply = this.binaryPuzzleCracker.complyToRowConstraints();

        assertFalse(doesComply);
    }

    @Test
    public void complyToRowConstraintsWithPuzzleContainingRowsThatViolatesTheVerticalUniquenessConstraintShouldReturnFalse() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addOne().addEmpty().addOne();
        puzzle.instantiateRow().addZero().addOne().addZero();
        puzzle.instantiateRow().addOne().addZero().addOne();

        boolean doesComply = this.binaryPuzzleCracker.complyToRowConstraints();

        assertFalse(doesComply);
    }

    @Test
    public void complyToRowConstraintsWithSolvablePuzzleShouldReturnTrue() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(6);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addOne().addZero().addOne().addZero().addOne().addZero();
        puzzle.instantiateRow().addZero().addZero().addOne().addOne().addZero().addOne();
        puzzle.instantiateRow().addZero().addOne().addZero().addZero().addOne().addOne();
        puzzle.instantiateRow().addOne().addOne().addZero().addZero().addOne().addZero();
        puzzle.instantiateRow().addOne().addZero().addOne().addOne().addZero().addZero();
        puzzle.instantiateRow().addZero().addOne().addZero().addOne().addZero().addOne();

        boolean doesComply = this.binaryPuzzleCracker.complyToRowConstraints();

        assertTrue(doesComply);
    }

    @Test
    public void complyToHorizontalEqualAmountOfOnesAndZerosWithPuzzleWhichHasARowWhichDoesNotComplyShouldReturnFalse() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(6);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addOne().addOne().addOne().addZero().addOne().addZero();
        puzzle.instantiateRow().addZero().addZero().addOne().addOne().addZero().addOne();
        puzzle.instantiateRow().addZero().addOne().addZero().addZero().addOne().addOne();
        puzzle.instantiateRow().addOne().addOne().addZero().addZero().addOne().addZero();
        puzzle.instantiateRow().addOne().addZero().addOne().addOne().addZero().addZero();
        puzzle.instantiateRow().addZero().addOne().addZero().addOne().addZero().addOne();

        boolean doesComply = this.binaryPuzzleCracker.complyToHorizontalEqualAmountOfOnesAndZeros();

        assertFalse(doesComply);
    }

    @Test
    public void complyToHorizontalEqualAmountOfOnesAndZerosWithPuzzleWhichDoesComplyShouldReturnTrue() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(6);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addOne().addZero().addOne().addZero().addOne().addZero();
        puzzle.instantiateRow().addZero().addZero().addOne().addOne().addZero().addOne();
        puzzle.instantiateRow().addZero().addOne().addZero().addZero().addOne().addOne();
        puzzle.instantiateRow().addOne().addOne().addZero().addZero().addOne().addZero();
        puzzle.instantiateRow().addOne().addZero().addOne().addOne().addZero().addZero();
        puzzle.instantiateRow().addZero().addOne().addZero().addOne().addZero().addOne();

        boolean doesComply = this.binaryPuzzleCracker.complyToHorizontalEqualAmountOfOnesAndZeros();

        assertTrue(doesComply);
    }

    @Test
    public void complyToVerticalEqualAmountOfOnesAndZerosWithPuzzleWhichHasARowWhichDoesNotComplyShouldReturnFalse() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(6);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addOne().addOne().addOne().addZero().addOne().addZero();
        puzzle.instantiateRow().addZero().addZero().addOne().addOne().addZero().addOne();
        puzzle.instantiateRow().addZero().addOne().addZero().addZero().addOne().addOne();
        puzzle.instantiateRow().addOne().addOne().addZero().addZero().addOne().addZero();
        puzzle.instantiateRow().addZero().addZero().addOne().addOne().addZero().addZero();
        puzzle.instantiateRow().addZero().addOne().addZero().addOne().addZero().addOne();

        boolean doesComply = this.binaryPuzzleCracker.complyToVerticalEqualAmountOfOnesAndZeros();

        assertFalse(doesComply);
    }

    @Test
    public void complyToVerticalEqualAmountOfOnesAndZerosWithPuzzleWhichHasARowWhichDoesComplyShouldReturnTrue() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(6);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addOne().addZero().addOne().addZero().addOne().addZero();
        puzzle.instantiateRow().addZero().addZero().addOne().addOne().addZero().addOne();
        puzzle.instantiateRow().addZero().addOne().addZero().addZero().addOne().addOne();
        puzzle.instantiateRow().addOne().addOne().addZero().addZero().addOne().addZero();
        puzzle.instantiateRow().addOne().addZero().addOne().addOne().addZero().addZero();
        puzzle.instantiateRow().addZero().addOne().addZero().addOne().addZero().addOne();

        boolean doesComply = this.binaryPuzzleCracker.complyToVerticalEqualAmountOfOnesAndZeros();

        assertTrue(doesComply);
    }

    @Test
    public void crackBinaryPuzzleWithSolvablePuzzleShouldReturnIsCrackableTrue() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(6);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        puzzle.instantiateRow().addEmpty().addEmpty().addOne().addEmpty().addEmpty().addEmpty();
        puzzle.instantiateRow().addZero().addZero().addEmpty().addOne().addEmpty().addEmpty();
        puzzle.instantiateRow().addZero().addEmpty().addEmpty().addEmpty().addEmpty().addEmpty();
        puzzle.instantiateRow().addEmpty().addEmpty().addEmpty().addEmpty().addEmpty().addEmpty();
        puzzle.instantiateRow().addEmpty().addEmpty().addEmpty().addOne().addEmpty().addEmpty();
        puzzle.instantiateRow().addEmpty().addEmpty().addEmpty().addEmpty().addZero().addEmpty();

        List<Row> uniqueRowCombinations = this.binaryPuzzleCracker.discoverUniqueRowCombinations();
        Map<Integer, List<Row>> possibleRowCombinationsPerRowIndex = this.binaryPuzzleCracker.filterRowCombinationsBasedOnFixedPuzzleFields(uniqueRowCombinations);

        this.binaryPuzzleCracker.crackBinaryPuzzle(possibleRowCombinationsPerRowIndex, 0);

        assertTrue(this.binaryPuzzleCracker.isCrackable());
        puzzle.printPuzzle();
    }
}
