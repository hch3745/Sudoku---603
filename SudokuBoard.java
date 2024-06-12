/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

import java.util.Random;

public class SudokuBoard {

    private int[][] board;
    private int[][] initialBoard;
    private DifficultyLevel difficultyLevel;
    private Random random = new Random();
    
    // Initializes the Sudoku board with the specified difficulty level
    public SudokuBoard(DifficultyLevel difficultyLevel) {
        if (difficultyLevel == null) {
            throw new IllegalArgumentException("Difficulty level cannot be null");
        }
        this.difficultyLevel = difficultyLevel;
        board = new int[9][9];
        initialBoard = new int[9][9];
        generateBoard();
    }
    
    // Generates a new Sudoku board
    public void generateBoard() {
        SudokuGenerator generator = new SudokuGenerator();
        generator.generateCompleteBoard(board);
        generator.removeNumbersForDifficulty(board, difficultyLevel);
        System.arraycopy(board, 0, initialBoard, 0, board.length);
    }
    
    // Checks if a move is valid in the current board state
    public boolean isValidMove(int row, int col, int number) {
        return SudokuValidator.isValidMove(board, row, col, number);
    }
    
    // Sets a number in the board at the specified position
    public void setNumber(int row, int col, int number) {
        board[row][col] = number;
    }
    
    // Checks if the board is complete (all cells filled)
    public boolean isComplete() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    // Gets the count of blank cells in the board
    public int getBlankCount() {
        int count = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) {
                    count++;
                }
            }
        }
        return count;
    }
    
    // Getter and setter methods for the board and difficulty level
    public int[][] getBoard() {
        return board;
    }

    public void resetBoard() {
        System.arraycopy(initialBoard, 0, board, 0, board.length);
    }
    
    // Removes numbers from the board to match the target number of blank cells
    public void removeNumbersToMatch(int targetBlanks) {
        int currentBlanks = getBlankCount();
        while (currentBlanks < targetBlanks) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            if (board[row][col] != 0 && initialBoard[row][col] != 0) {
                board[row][col] = 0;
                initialBoard[row][col] = 0; // Also set initialBoard to 0 to prevent these cells from being filled again
                currentBlanks++;
            }
        }
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }
}
