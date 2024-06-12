/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

public class GameState {

    private int blankAnswers;
    private int livesLeft;

    public GameState(int blankAnswers, int livesLeft) {
        this.blankAnswers = blankAnswers;
        this.livesLeft = livesLeft;
    }
    
    // Getter and setter methods for the blank answers and lives left
    public int getBlankAnswers() {
        return blankAnswers;
    }

    public int getLivesLeft() {
        return livesLeft;
    }
}
