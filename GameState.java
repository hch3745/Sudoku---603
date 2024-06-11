/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

public class GameState {

    private int blankAnswers;
    private int livesLeft;
    private DifficultyLevel difficulty;

    public GameState(int blankAnswers, int livesLeft, DifficultyLevel difficulty) {
        this.blankAnswers = blankAnswers;
        this.livesLeft = livesLeft;
        this.difficulty = difficulty;
    }

    public int getBlankAnswers() {
        return blankAnswers;
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }
}
