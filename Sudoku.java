import java.util.Scanner;

/**
 * @author Matthew Perry
 * Description: Main class. Playable sudoku puzzle.
 */
public class Sudoku {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        boolean continuePlaying = true;
        // loops until user quits
        while (continuePlaying == true) {

            Board board = new Board();

            System.out.println("Sudoku Rules:");
            System.out.println("1. Each column must contain all digits from 1-9.");
            System.out.println("2. Each row must contain all digits from 1-9.");
            System.out.println("3. Each grid must contain all digits from 1-9.\n");
            System.out.println("How to Solve:");
            System.out.println("Spaces that contain a number are already correctly solved.");
            System.out.println("Spaces that have a '-' need to be solved.");
            System.out.println("When solving, only enter numbers 1-9 to reference rows, columns, and digits.\n");
            System.out.println("Enter -1 for the row, column, and digit if you want to see the solution to the puzzle.");
            // if -1 or -2 are used in combination with numbers 1-9, for row, column, and digit, no space will be solved and the puzzle will continue as normal
            System.out.println("Enter -2 for the row, column, and digit if you want to see the solution and exit the puzzle.\n");
            System.out.println("Time to solve!\n");

            boolean exit = false;
            // loops until board is solved or user exits current board
            while (board.isSolved() == false && exit == false) {

                System.out.println(board.getUnsolvedBoard());
                System.out.print("Enter row: ");
                boolean rowEntered = false;
                int row = 0;

                // user enters row of the space they want to solve. continues to loop until input is valid
                while (rowEntered == false) {
                    try {
                        row = input.nextInt();
                        if (row == 0 || row > 9 || row < -2) {
                            throw new Exception();
                        }
                        rowEntered = true;
                    } catch (Exception e) {
                        System.out.print("Enter row (must be a number from 1 to 9): ");
                        rowEntered = false;
                        input.nextLine();
                    }
                }

                System.out.print("\nEnter column: ");
                boolean columnEntered = false;
                int column = 0;

                // user enters column of the space they want to solve. continues to loop until input is valid
                while (columnEntered == false) {
                    try {
                        column = input.nextInt();
                        if (column == 0 || column > 9 || column < -2) {
                            throw new Exception();
                        }
                        columnEntered = true;
                    } catch (Exception e) {
                        System.out.print("Enter column (must be a number from 1 to 9): ");
                        columnEntered = false;
                        input.nextLine();
                    }
                }

                System.out.print("\nEnter digit: ");
                boolean digitEntered = false;
                int digit = 0;

                // user enters digit they think solves the space they selected. continues to loop until input is valid
                while (digitEntered == false) {
                    try {
                        digit = input.nextInt();
                        if (digit == 0 || digit > 9 || digit < -2) {
                            throw new Exception();
                        }
                        digitEntered = true;
                    } catch (Exception e) {
                        System.out.print("Enter digit (must be a number from 1 to 9): ");
                        digitEntered = false;
                        input.nextLine();
                    }
                }

                // user input is then processed

                // prints solved board (all input is -1)
                if (row == -1 && column == -1 && digit == -1) {
                    System.out.println("\nSOLUTION:");
                    System.out.println(board.getSolvedBoard());
                    System.out.println();
                }

                // prints solved board and exits (all input is -2)
                else if (row == -2 && column == -2 && digit == -2) {
                    System.out.println("\nSOLUTION:");
                    System.out.println(board.getSolvedBoard());
                    exit = true;
                }

                // lets user know if their solve is correct
                else {
                    System.out.println("\nSpace solved: " + board.solve(row, column, digit) + "\n");
                    if (board.isSolved() == true) {
                        System.out.println("Board solved!\n");
                    }
                }
            }

            System.out.println("Do you want to play again? (Type 1 for \"yes\" and 2 for \"no\").");
            int playAgainInt = 0;

            // continues to loop until a valid input is entered
            while (playAgainInt != 1 && playAgainInt != 2) {
                try {
                    playAgainInt = input.nextInt();
                    if (playAgainInt == 1) {
                        continuePlaying = true;
                        System.out.println();
                    }
                    else if (playAgainInt == 2) {
                        continuePlaying = false;
                    }
                    else {
                        throw new Exception();
                    }
                }
                catch (Exception e) {
                    System.out.println("Do you want to play again? (Type 1 for \"yes\" and 2 for \"no\").");
                    continuePlaying = false;
                    input.nextLine();
                }
            }

        }
        System.out.println("Thanks for playing!");
        input.close();
    }
}