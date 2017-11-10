package domain;

import java.util.List;

public interface Puzzle {

    int getSize();

    void setSize(int size);

    List<Row> getRows();

    void printPuzzle();

    Puzzle instantiateRow();

    Row instantiateAndReturnRow();

    Puzzle addEmpty();

    Puzzle addZero();

    Puzzle addOne();
}
