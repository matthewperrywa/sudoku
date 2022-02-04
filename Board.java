import java.util.ArrayList;

/**
 * @author Matthew Perry
 * Description: Board represents the sudoku board. It contains randomly generated solved and unsolved boards.
 *              The unsolved board is updated each time a square is correctly guessed.
 */
public class Board {

    private int[][] solvedBoard;
    private int[][] unsolvedBoard;

    /**
     * Description: Constructs a board.
     * Pre-Condition: None.
     * Post-Condition: Board is constructed and its solved and unsolved states are set.
     */
    public Board() {
        this.solvedBoard = new int[9][9];
        this.generateSolvedBoard();
        this.unsolvedBoard = new int[9][9];
        this.generateUnsolvedBoard();
    }

    /*

        solved board example:

        5 3 4   6 7 8   9 1 2
        6 7 2   1 9 5   3 4 8
        1 9 8   3 4 2   5 6 7

        8 5 9   7 6 1   4 2 3
        4 2 6   8 5 3   7 9 1
        7 1 3   9 2 4   8 5 6

        9 6 1   5 3 7   2 8 4
        2 8 7   4 1 9   6 3 5
        3 4 5   2 8 6   1 7 9

     */

    /**
     * Description: Returns the solved board in String form.
     * Pre-Condition: None.
     * Post-Condition: None.
     */
    public String getSolvedBoard() {
        String output = "";
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                output += this.solvedBoard[row][column];
                // each grid is separated horizontally with a tab
                if (column == 2 || column == 5) {
                    output += "\t";
                }
                // each number is separated horizontally with a space
                else if (column != 8) {
                    output += " ";
                }
            }
            // each grid is separated vertically by a blank line
            if (row == 2 || row == 5) {
                output += "\n";
            }
            output += "\n";
        }
        return output;
    }

    /**
     * Description: Returns the unsolved board in String form.
     * Pre-Condition: None.
     * Post-Condition: None.
     */
    public String getUnsolvedBoard() {
        String output = "";
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                String nextNumber = "" + this.unsolvedBoard[row][column];
                // 0's (blank spaces) are outputted as dashes to make it more clear to the user which spots need to be solved
                if (nextNumber.equals("0")) {
                    nextNumber = "-";
                }
                output += nextNumber;
                // each grid is separated horizontally with a tab
                if (column == 2 || column == 5) {
                    output += "\t";
                }
                // each number is separated horizontally with a space
                else if (column != 8) {
                    output += " ";
                }
            }
            // each grid is separated vertically by a blank line
            if (row == 2 || row == 5) {
                output += "\n";
            }
            output += "\n";
        }
        return output;
    }

    /**
     * Description: Compares coordinates with a number value to coordinates on solved board.
     *              If the numbers match, the unsolved board's number for those coordinates is updated to the that number.
     * Pre-Condition: The row, column, and number must all be between 1 and 9.
     * Post-Condition: If the numbers match, true is returned and unsolved board is updated. Otherwise, false is returned.
     */
    public boolean solve(int row, int column, int number) {
        // invalid values
        if (row > 9 || column > 9 || row < 1 || column < 1 || number < 1 || number > 9) {
            return false;
        }
        // 1 is subtracted from the inputted row and column
        // it makes more sense from a user perspective for rows/columns to be 1-9, opposed to 0-8
        else if (this.solvedBoard[row - 1][column - 1] == number) {
            this.unsolvedBoard[row - 1][column - 1] = number;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Description: Checks to see if the unsolved board is solved. If so, true is returned. False is returned otherwise.
     * Pre-Condition: None.
     * Post-Condition: None.
     */
    public boolean isSolved() {
        boolean solved = false;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (this.unsolvedBoard[row][column] == this.solvedBoard[row][column]) {
                    solved = true;
                }
                else {
                    solved = false;
                    row = 9;
                    column = 9;
                }
            }
        }
        return solved;
    }

    /**
     * Description: Checks to see if a certain number has been added to a specific row in the solved board.
     * Pre-Condition: The row must be a value between 0 and 8 inclusive.
     * Post-Condition: True is returned if the number isn't in the row. False is returned if the number is in the row.
     */
    private boolean numberWorksInRow(int currentNumber, int row) {
        for (int i = 0; i < 9; i++) {
            if (this.solvedBoard[row][i] == currentNumber) {
                return false;
            }
        }
        return true;
    }

    /**
     * Description: Checks to see if a certain number has been added to a specific row in the unsolved board.
     * Pre-Condition: The row must be a value between 0 and 8 inclusive.
     * Post-Condition: True is returned if the number isn't in the row. False is returned if the number is in the row.
     */
    private boolean numberWorksInRow2(int currentNumber, int row) {
        for (int i = 0; i < 9; i++) {
            if (this.unsolvedBoard[row][i] == currentNumber) {
                return false;
            }
        }
        return true;
    }

    /**
     * Description: Checks to see if a certain number has been added to a specific grid in the solved board.
     * Pre-Condition: The specific values for rows and columns must match those of the grids they are associated with.
     *                These values should be between 0 and 8 inclusive.
     * Post-Condition: True is returned if the number isn't in the grid. False is returned if the number is in the grid.
     */
    private boolean numberWorksInGrid(int currentNumber, int startRow, int endRow, int startColumn, int endColumn) {
        // i is row
        for (int i = startRow; i < endRow; i++) {
            // j is column
            for (int j = startColumn; j < endColumn; j++) {
                if (this.solvedBoard[i][j] == currentNumber) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Description: Checks to see if a certain number has been added to a specific grid in the unsolved board.
     * Pre-Condition: The specific values for rows and columns must match those of the grids they are associated with.
     *                These values should be between 0 and 8 inclusive.
     * Post-Condition: True is returned if the number isn't in the grid. False is returned if the number is in the grid.
     */
    private boolean numberWorksInGrid2(int currentNumber, int startRow, int endRow, int startColumn, int endColumn) {
        // i is row
        for (int i = startRow; i < endRow; i++) {
            // j is column
            for (int j = startColumn; j < endColumn; j++) {
                if (this.unsolvedBoard[i][j] == currentNumber) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Description: Checks to see if a certain number has been added to a specific column in the solved board.
     * Pre-Condition: The column must be a value between 0 and 8 inclusive.
     * Post-Condition: True is returned if the number isn't in the column. False is returned if the number is in the column.
     */
    private boolean numberWorksInColumn(int currentNumber, int column) {
        for (int i = 0; i < 9; i++) {
            if (this.solvedBoard[i][column] == currentNumber) {
                return false;
            }
        }
        return true;
    }

    /**
     * Description: Checks to see if a certain number has been added to a specific column in the unsolved board.
     * Pre-Condition: The column must be a value between 0 and 8 inclusive.
     * Post-Condition: True is returned if the number isn't in the column. False is returned if the number is in the column.
     */
    private boolean numberWorksInColumn2(int currentNumber, int column) {
        for (int i = 0; i < 9; i++) {
            if (this.unsolvedBoard[i][column] == currentNumber) {
                return false;
            }
        }
        return true;
    }

    /**
     * Description: Randomly generates valid values for the solved board.
     * Pre-Condition: None.
     * Post-Condition: The values of the solved board are changed to those of a solved sudoku board.
     */
    private void generateSolvedBoard() {

        // each run is a different row of the board
        for (int row = 0; row < 9; row++) {
            // array list number stores numbers 1-9
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            for (int i = 1; i < 10; i++) {
                numbers.add(i);
            }

            // each run is a different column in the row
            for (int column = 0; column < 9; column++) {
                // index of the number that is being placed on the board
                int indexOfCurrentNumber = (int) (Math.random() * numbers.size());
                // number that is being placed on the board
                int currentNumber = numbers.get(indexOfCurrentNumber);
                // numberWorks will be true if the current number can be placed. it will be false if the number cannot be placed
                boolean numberWorks = true;
                // incorrectAttempts is the amount of failed attempts at placing a number in the current space
                int incorrectAttempts = 0;

                if (column == 0 || column == 1 || column == 2) {

                    // upper left grid
                    if (row == 0 || row == 1 || row == 2) {
                        do {
                            indexOfCurrentNumber = (int) (Math.random() * numbers.size());
                            currentNumber = numbers.get(indexOfCurrentNumber);
                            numberWorks = this.numberWorksInColumn(currentNumber, column);
                            if (numberWorks == true) {
                                numberWorks = this.numberWorksInGrid(currentNumber, 0, 3, 0, 3);
                            }
                            incorrectAttempts++;
                        } while (numberWorks == false && incorrectAttempts < 20);
                        this.solvedBoard[row][column] = currentNumber;
                        numbers.remove(indexOfCurrentNumber);
                    }

                    // middle left grid
                    else if (row == 3 || row == 4 || row == 5) {
                        do {
                            indexOfCurrentNumber = (int) (Math.random() * numbers.size());
                            currentNumber = numbers.get(indexOfCurrentNumber);
                            numberWorks = this.numberWorksInColumn(currentNumber, column);
                            if (numberWorks == true) {
                                numberWorks = this.numberWorksInGrid(currentNumber, 3, 6, 0, 3);
                            }
                            incorrectAttempts++;
                        } while (numberWorks == false && incorrectAttempts < 20);
                        this.solvedBoard[row][column] = currentNumber;
                        numbers.remove(indexOfCurrentNumber);
                    }

                    // lower left grid
                    else if (row == 6 || row == 7 || row == 8) {
                        do {
                            indexOfCurrentNumber = (int) (Math.random() * numbers.size());
                            currentNumber = numbers.get(indexOfCurrentNumber);
                            numberWorks = this.numberWorksInColumn(currentNumber, column);
                            if (numberWorks == true) {
                                numberWorks = this.numberWorksInGrid(currentNumber, 6, 9, 0, 3);
                            }
                            incorrectAttempts++;
                        } while (numberWorks == false && incorrectAttempts < 20);
                        this.solvedBoard[row][column] = currentNumber;
                        numbers.remove(indexOfCurrentNumber);
                    }
                }

                else if (column == 3 || column == 4 || column == 5) {

                    // upper middle grid
                    if (row == 0 || row == 1 || row == 2) {
                        do {
                            indexOfCurrentNumber = (int) (Math.random() * numbers.size());
                            currentNumber = numbers.get(indexOfCurrentNumber);
                            numberWorks = this.numberWorksInColumn(currentNumber, column);
                            if (numberWorks == true) {
                                numberWorks = this.numberWorksInGrid(currentNumber, 0, 3, 3, 6);
                            }
                            incorrectAttempts++;
                        } while (numberWorks == false && incorrectAttempts < 20);
                        this.solvedBoard[row][column] = currentNumber;
                        numbers.remove(indexOfCurrentNumber);
                    }

                    // center middle grid
                    else if (row == 3 || row == 4 || row == 5) {
                        do {
                            indexOfCurrentNumber = (int) (Math.random() * numbers.size());
                            currentNumber = numbers.get(indexOfCurrentNumber);
                            numberWorks = this.numberWorksInColumn(currentNumber, column);
                            if (numberWorks == true) {
                                numberWorks = this.numberWorksInGrid(currentNumber, 3, 6, 3, 6);
                            }
                            incorrectAttempts++;
                        } while (numberWorks == false && incorrectAttempts < 20);
                        this.solvedBoard[row][column] = currentNumber;
                        numbers.remove(indexOfCurrentNumber);
                    }

                    // lower middle grid
                    else if (row == 6 || row == 7 || row == 8) {
                        do {
                            indexOfCurrentNumber = (int) (Math.random() * numbers.size());
                            currentNumber = numbers.get(indexOfCurrentNumber);
                            numberWorks = this.numberWorksInColumn(currentNumber, column);
                            if (numberWorks == true) {
                                numberWorks = this.numberWorksInGrid(currentNumber, 6, 9, 3, 6);
                            }
                            incorrectAttempts++;
                        } while (numberWorks == false && incorrectAttempts < 20);
                        this.solvedBoard[row][column] = currentNumber;
                        numbers.remove(indexOfCurrentNumber);
                    }
                }

                else if (column == 6 || column == 7 || column == 8) {

                    // upper right grid
                    if (row == 0 || row == 1 || row == 2) {
                        do {
                            indexOfCurrentNumber = (int) (Math.random() * numbers.size());
                            currentNumber = numbers.get(indexOfCurrentNumber);
                            numberWorks = this.numberWorksInColumn(currentNumber, column);
                            if (numberWorks == true) {
                                numberWorks = this.numberWorksInGrid(currentNumber, 0, 3, 6, 9);
                            }
                            incorrectAttempts++;
                        } while (numberWorks == false && incorrectAttempts < 20);
                        this.solvedBoard[row][column] = currentNumber;
                        numbers.remove(indexOfCurrentNumber);
                    }

                    // middle right grid
                    else if (row == 3 || row == 4 || row == 5) {
                        do {
                            indexOfCurrentNumber = (int) (Math.random() * numbers.size());
                            currentNumber = numbers.get(indexOfCurrentNumber);
                            numberWorks = this.numberWorksInColumn(currentNumber, column);
                            if (numberWorks == true) {
                                numberWorks = this.numberWorksInGrid(currentNumber, 3, 6, 6, 9);
                            }
                            incorrectAttempts++;
                        } while (numberWorks == false && incorrectAttempts < 20);
                        this.solvedBoard[row][column] = currentNumber;
                        numbers.remove(indexOfCurrentNumber);
                    }

                    // lower right grid
                    else if (row == 6 || row == 7 || row == 8) {
                        do {
                            indexOfCurrentNumber = (int) (Math.random() * numbers.size());
                            currentNumber = numbers.get(indexOfCurrentNumber);
                            numberWorks = this.numberWorksInColumn(currentNumber, column);
                            if (numberWorks == true) {
                                numberWorks = this.numberWorksInGrid(currentNumber, 6, 9, 6, 9);
                            }
                            incorrectAttempts++;
                        } while (numberWorks == false && incorrectAttempts < 20);
                        this.solvedBoard[row][column] = currentNumber;
                        numbers.remove(indexOfCurrentNumber);
                    }
                }

                // if 20 incorrect attempts have occurred, the current row is cleared and attempted again
                if (incorrectAttempts == 20) {
                    for (int i = 0; i < 9; i++) {
                        this.solvedBoard[row][i] = 0;
                    }
                    row--;
                }

            }
        }
    }

    /**
     * Description: Randomly removes values from the solved board to create an unsolved sudoku board with only 1 solution.
     * Pre-Condition: None.
     * Post-Condition: The values of the unsolved board are changed to those of a solvable sudoku board.
     */
    private void generateUnsolvedBoard() {

        // the values of the solved board are copied to the unsolved board
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                this.unsolvedBoard[row][column] = this.solvedBoard[row][column];
            }
        }

        /*

            Values are removed from the unsolved board.

            Random squares are chosen to be removed. This process occurs 200 times.

            The reason it occurs 200 times, is because anything lower will result in less blank squares and anything
            over doesn't result in much more blank squares. With 200 runs, the result is generally around 40-45 blank
            squares.

            If the removal of a square would result in a board with multiple solutions, it is not removed.

         */
        for (int i = 0; i < 200; i++) {
            int removedNumber = 0;
            int randomRow = 0;
            int randomColumn = 0;
            // makes sure the removed number is not already removed
            while (removedNumber == 0) {
                randomRow = (int) (Math.random() * 9);
                randomColumn = (int) (Math.random() * 9);
                removedNumber = this.unsolvedBoard[randomRow][randomColumn];
            }
            this.unsolvedBoard[randomRow][randomColumn] = 0;
            int possibleSolutions = 0;
            int startRow = 0;
            int endRow = 0;
            int startColumn = 0;
            int endColumn = 0;

            // removed number will be in a left grid
            if (randomColumn == 0 || randomColumn == 1 || randomColumn == 2) {
                startColumn = 0;
                endColumn = 3;
            }

            // removed number will be in a middle grid
            else if (randomColumn == 3 || randomColumn == 4 || randomColumn == 5) {
                startColumn = 3;
                endColumn = 6;
            }

            // removed number will be in a right grid
            else if (randomColumn == 6 || randomColumn == 7 || randomColumn == 8) {
                startColumn = 6;
                endColumn = 9;
            }

            // removed number will be in a top grid
            if (randomRow == 0 || randomRow == 1 || randomRow == 2) {
                startRow = 0;
                endRow = 3;
            }

            // removed number will be in a middle grid
            else if (randomRow == 3 || randomRow == 4 || randomRow == 5) {
                startRow = 3;
                endRow = 6;
            }

            // removed number will be in a bottom grid
            else if (randomRow == 6 || randomRow == 7 || randomRow == 8) {
                startRow = 6;
                endRow = 9;
            }

            // checks the amount of possible solutions if the number is removed
            for (int j = 0; j < 9; j++) {
                if (this.numberWorksInRow2(j, randomRow) && this.numberWorksInColumn2(j, randomColumn) && this.numberWorksInGrid2(j, startRow, endRow, startColumn, endColumn)) {
                    possibleSolutions++;
                }
            }
            // if the amount of solutions is greater than one, the number is not removed
            if (possibleSolutions > 1) {
                this.unsolvedBoard[randomRow][randomColumn] = removedNumber;
            }
        }
    }

}