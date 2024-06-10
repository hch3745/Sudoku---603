/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {
    private Random random = new Random();

    public void generateCompleteBoard(int[][] board) {
        solveSudoku(board);
    }

    private boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
                    while (!numbers.isEmpty()) {
                        int index = random.nextInt(numbers.size());
                        int num = numbers.remove(index);
                        if (SudokuValidator.isValidMove(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void removeNumbersForDifficulty(int[][] board, DifficultyLevel difficulty) {
        if (difficulty == null) {
            throw new IllegalArgumentException("Difficulty level cannot be null");
        }

        int cellsToRemove;
        switch (difficulty) {
            case EASY:
                cellsToRemove = 20;
                break;
            case MEDIUM:
                cellsToRemove = 40;
                break;
            case HARD:
                cellsToRemove = 60;
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + difficulty);
        }

        while (cellsToRemove > 0) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            if (board[row][col] != 0) {
                board[row][col] = 0;
                cellsToRemove--;
            }
        }
    }
}
