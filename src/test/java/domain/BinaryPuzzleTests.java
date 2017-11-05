package domain;

import exceptions.FieldsEludedException;
import exceptions.FieldsExceededException;
import exceptions.NoRowAvailableException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class BinaryPuzzleTests {
    private Puzzle binaryPuzzle;

    @Before
    public void setUp() {
        this.binaryPuzzle = BinaryPuzzle.getInstance(6);
    }

    @Test
    public void setRowsWithPuzzleSize6ShouldReturnPuzzleInstanceWithSpecifiedFields() {
        // Act
        this.binaryPuzzle.instantiateRow().addEmpty().addEmpty().addOne().addEmpty().addEmpty().addEmpty();
        this.binaryPuzzle.instantiateRow().addZero().addZero().addEmpty().addOne().addEmpty().addEmpty();
        this.binaryPuzzle.instantiateRow().addZero().addEmpty().addEmpty().addEmpty().addEmpty().addEmpty();
        this.binaryPuzzle.instantiateRow().addEmpty().addEmpty().addEmpty().addEmpty().addEmpty().addEmpty();
        this.binaryPuzzle.instantiateRow().addEmpty().addEmpty().addEmpty().addOne().addEmpty().addEmpty();
        this.binaryPuzzle.instantiateRow().addEmpty().addEmpty().addEmpty().addEmpty().addZero().addEmpty();

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

    @Test(expected = FieldsExceededException.class)
    public void instantiateRowWithTooManyFieldsShouldThrowAnFieldsExceededException() {
        // Act
        this.binaryPuzzle.instantiateRow().addEmpty().addEmpty().addOne().addEmpty().addZero().addEmpty().addEmpty();
    }

    @Test(expected = FieldsEludedException.class)
    public void instantiateRowWithTooLittleFieldsShouldThrowAnFieldsEludedException() {
        // Act
        this.binaryPuzzle.instantiateRow().addEmpty().addEmpty().addOne().addEmpty().addZero();
        this.binaryPuzzle.instantiateRow().addEmpty().addEmpty().addEmpty().addEmpty().addEmpty();
    }

    @Test(expected = NoRowAvailableException.class)
    public void addEmptyToPuzzleWithoutInstantiatingARowFirstShouldThrowAnNoRowAvailableException() {
        // Act
        this.binaryPuzzle.addEmpty();
    }

    @Test(expected = NoRowAvailableException.class)
    public void addZeroToPuzzleWithoutInstantiatingARowFirstShouldThrowAnNoRowAvailableException() {
        // Act
        this.binaryPuzzle.addZero();
    }

    @Test(expected = NoRowAvailableException.class)
    public void addOneToPuzzleWithoutInstantiatingARowFirstShouldThrowAnNoRowAvailableException() {
        // Act
        this.binaryPuzzle.addOne();
    }

    @After
    public void tearDown() {
        BinaryPuzzle.destroyInstance();
    }
}
