package domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BinaryPuzzleTests {
    private BinaryPuzzle binaryPuzzle;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        this.binaryPuzzle = BinaryPuzzle.getInstance(6);
    }

    @Test
    public void setRowsWithPuzzleSize6ShouldReturnPuzzleInstanceWithSpecifiedFields() {
        // Arrange
        List<Row> rows = Arrays.asList(
                (new Row(6)).addFieldEmpty().addFieldEmpty().addFieldOne().addFieldEmpty().addFieldEmpty().addFieldEmpty(),
                (new Row(6)).addFieldZero().addFieldZero().addFieldEmpty().addFieldOne().addFieldEmpty().addFieldEmpty(),
                (new Row(6)).addFieldZero().addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty(),
                (new Row(6)).addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty(),
                (new Row(6)).addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldOne().addFieldEmpty().addFieldEmpty(),
                (new Row(6)).addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldZero().addFieldEmpty()
        );

        // Act
        this.binaryPuzzle.setRows(rows);

        // Assert
        int emptyOccurrences = 0;
        int zeroOccurrences = 0;
        int oneOccurrences = 0;
        for (Row row : this.binaryPuzzle.getRows()) {
            emptyOccurrences += Collections.frequency(row.getFieldValues(), FieldValue.EMPTY);
            zeroOccurrences += Collections.frequency(row.getFieldValues(), FieldValue.ZERO);
            oneOccurrences += Collections.frequency(row.getFieldValues(), FieldValue.ONE);
        }

        assertThat(binaryPuzzle.getRows().size(), is(6));
        assertThat(emptyOccurrences, is(29));
        assertThat(zeroOccurrences, is(4));
        assertThat(oneOccurrences, is(3));
    }

    // TODO eigen exceptie hiervoor maken?
    @Test
    public void setRowsWithAmountOfRowsThatExceedsPuzzleSizeShouldThrowAnException() {
        // Arrange
        List<Row> rows = Arrays.asList(
                (new Row(6)),
                (new Row(6)),
                (new Row(6)),
                (new Row(6)),
                (new Row(6)),
                (new Row(6)),
                (new Row(6))
        );

        // Assert
        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("Too many rows, there should be 6 rows.");

        // Act
        this.binaryPuzzle.setRows(rows);
    }

    @Test
    public void setRowsWithAmountOfRowsThatIsBelowPuzzleSizeShouldThrowAnException() {
        // Arrange
        List<Row> rows = Arrays.asList(
                (new Row(0)),
                (new Row(0))
        );

        // Assert
        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("Too little rows, there should be 6 rows.");

        // Act
        this.binaryPuzzle.setRows(rows);
    }

    @Test
    public void setRowsWithRowContainingTooManyFieldsShouldThrowAnException() {
        // Arrange
        List<Row> rows = Arrays.asList(
                (new Row(6)).addFieldEmpty().addFieldEmpty().addFieldOne().addFieldEmpty().addFieldEmpty().addFieldEmpty(),
                (new Row(6)).addFieldZero().addFieldZero().addFieldEmpty().addFieldOne().addFieldEmpty().addFieldEmpty(),
                (new Row(7)).addFieldZero().addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty(),
                (new Row(6)).addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty(),
                (new Row(6)).addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldOne().addFieldEmpty().addFieldEmpty(),
                (new Row(6)).addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldZero().addFieldEmpty()
        );

        // Assert
        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("Row #3 should contain 6 field values, but there are 7 found.");

        // Act
        this.binaryPuzzle.setRows(rows);
    }

    @Test
    public void setRowsWithRowContainingTooLittleFieldsShouldThrowAnException() {
        // Arrange
        List<Row> rows = Arrays.asList(
                (new Row(6)).addFieldEmpty().addFieldEmpty().addFieldOne().addFieldEmpty().addFieldEmpty().addFieldEmpty(),
                (new Row(6)).addFieldZero().addFieldZero().addFieldEmpty().addFieldOne().addFieldEmpty().addFieldEmpty(),
                (new Row(6)).addFieldZero().addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty(),
                (new Row(6)).addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty(),
                (new Row(2)).addFieldEmpty().addFieldEmpty(),
                (new Row(6)).addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldEmpty().addFieldZero().addFieldEmpty()
        );

        // Assert
        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("Row #5 should contain 6 field values, but there are 2 found.");

        // Act
        this.binaryPuzzle.setRows(rows);
    }

    @Test
    public void setRowsWithNullValueShouldThrowAnException() {
        // Assert
        this.expectedException.expect(NullPointerException.class);
        this.expectedException.expectMessage("The rows variable is NULL, but should contain 6 rows.");

        // Act
        this.binaryPuzzle.setRows(null);
    }
}
