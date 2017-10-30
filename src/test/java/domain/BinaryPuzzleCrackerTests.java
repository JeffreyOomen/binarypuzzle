package domain;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BinaryPuzzleCrackerTests {

    @Test
    public void discoverUniqueRowCombinationsWithBinaryPuzzleOfSize2ShouldReturn4UniqueCombinations() {
        // Arrange
        BinaryPuzzleCracker binaryPuzzleCracker = new BinaryPuzzleCracker(2);

        // Act
        List<Row> rowCombinations = binaryPuzzleCracker.discoverUniqueRowCombinations();

        // Assert
        assertThat(rowCombinations.size(), is(4));
        assertThat(rowCombinations.get(0).getFieldValues(), is(new Vector<FieldValue>(Arrays.asList(FieldValue.ZERO, FieldValue.ZERO))));
        assertThat(rowCombinations.get(1).getFieldValues(), is(new Vector<FieldValue>(Arrays.asList(FieldValue.ZERO, FieldValue.ONE))));
        assertThat(rowCombinations.get(2).getFieldValues(), is(new Vector<FieldValue>(Arrays.asList(FieldValue.ONE, FieldValue.ZERO))));
        assertThat(rowCombinations.get(3).getFieldValues(), is(new Vector<FieldValue>(Arrays.asList(FieldValue.ONE, FieldValue.ONE))));
    }

    @Test
    public void filterRowCombinationsBasedOnFixedPuzzleFieldsWithBinaryPuzzleOfSize2ShouldReturnTwoUniqueCombinations() {
        // Arrange
        BinaryPuzzle binaryPuzzle = BinaryPuzzle.getInstance(2);

        List<Row> rows = Arrays.asList(
                (new Row(2)).addFieldEmpty().addFieldOne(),
                (new Row(2)).addFieldZero().addFieldEmpty()
        );

        binaryPuzzle.setRows(rows);

        BinaryPuzzleCracker binaryPuzzleCracker = new BinaryPuzzleCracker(2);
        List<Row> rowCombinations = binaryPuzzleCracker.discoverUniqueRowCombinations();

        // Act
        Map<Integer, List<Row>> filteredRowCombinations = binaryPuzzleCracker.filterRowCombinationsBasedOnFixedPuzzleFields(binaryPuzzle, rowCombinations);

        assertThat(filteredRowCombinations.get(0).size(), is(1));
        assertThat(filteredRowCombinations.get(1).size(), is(1));
    }
}
