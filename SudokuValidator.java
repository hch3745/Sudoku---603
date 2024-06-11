/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

public class SudokuValidator {

    public static boolean isValidMove(int[][] board, int row, int col, int number) {
        return !isInRow(board, row, number) && !isInColumn(board, col, number) && !isInBox(board, row - row % 3, col - col % 3, number);
    }

    private static boolean isInRow(int[][] board, int row, int number) {
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInColumn(int[][] board, int col, int number) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInBox(int[][] board, int startRow, int startCol, int number) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == number) {
                    return true;
                }
            }
        }
        return false;
    }
}
