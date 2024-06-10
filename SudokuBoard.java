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

    public SudokuBoard(DifficultyLevel difficultyLevel) {
        if (difficultyLevel == null) {
            throw new IllegalArgumentException("Difficulty level cannot be null");
        }
        this.difficultyLevel = difficultyLevel;
        board = new int[9][9];
        initialBoard = new int[9][9];
        generateBoard();
    }

    public void generateBoard() {
        SudokuGenerator generator = new SudokuGenerator();
        generator.generateCompleteBoard(board);
        generator.removeNumbersForDifficulty(board, difficultyLevel);
        System.arraycopy(board, 0, initialBoard, 0, board.length);
    }

    public boolean isValidMove(int row, int col, int number) {
        return SudokuValidator.isValidMove(board, row, col, number);
    }

    public void setNumber(int row, int col, int number) {
        board[row][col] = number;
    }

    public boolean isComplete() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) return false;
            }
        }
        return true;
    }

    public int getBlankCount() {
        int count = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) count++;
            }
        }
        return count;
    }

    public int[][] getBoard() {
        return board;
    }

    public void resetBoard() {
        System.arraycopy(initialBoard, 0, board, 0, board.length);
    }

    public void removeNumbersToMatch(int targetBlanks) {
        int currentBlanks = getBlankCount();
        while (currentBlanks < targetBlanks) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            if (board[row][col] != 0 && initialBoard[row][col] != 0) {
                board[row][col] = 0;
                currentBlanks++;
            }
        }
    }
}
