package domain;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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
}
