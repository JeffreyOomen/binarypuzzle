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
    public void complyToRowConstrainsWithPuzzleWhichHasARowContaining3ZerosBehindEachOtherShouldNotBeAllowed() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        Row row = puzzle.instantiateAndReturnRow().addZero(0).addZero(1).addZero(2);

        boolean doesComply = this.binaryPuzzleCracker.complyToRowConstraints(row);

        assertFalse(doesComply);
    }

    @Test
    public void complyToRowConstrainsWithPuzzleWhichHasARowContaining3OnesBehindEachOtherShouldNotBeAllowed() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        Row row = puzzle.instantiateAndReturnRow().addOne(0).addOne(1).addOne(2);

        boolean doesComply = this.binaryPuzzleCracker.complyToRowConstraints(row);

        assertFalse(doesComply);
    }

    @Test
    public void complyToRowConstrainsWithPuzzleWhichHasARowContaining3EmptiesBehindEachOtherShouldBeAllowed() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        Row row = puzzle.instantiateAndReturnRow().addEmpty(0).addEmpty(1).addEmpty(2);

        boolean doesComply = this.binaryPuzzleCracker.complyToRowConstraints(row);

        assertTrue(doesComply);
    }

    @Test
    public void complyToRowConstrainsWithPuzzleWhichHasARowContaining2ZerosBehindEachOtherShouldBeAllowed() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        Row row = puzzle.instantiateAndReturnRow().addZero(0).addZero(1).addOne(2);

        boolean doesComply = this.binaryPuzzleCracker.complyToRowConstraints(row);

        assertTrue(doesComply);
    }

    @Test
    public void complyToRowConstrainsWithPuzzleWhichHasARowContaining2OnesBehindEachOtherShouldBeAllowed() {
        this.binaryPuzzleCracker = new BinaryPuzzleCracker(3);
        Puzzle puzzle = this.binaryPuzzleCracker.getBinaryPuzzle();
        Row row = puzzle.instantiateAndReturnRow().addOne(0).addOne(1).addZero(2);

        boolean doesComply = this.binaryPuzzleCracker.complyToRowConstraints(row);

        assertTrue(doesComply);
    }
}
