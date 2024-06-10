/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

public class GameState {
    private int blanksCount;
    private int livesLeft;

    public GameState(int blanksCount, int livesLeft) {
        this.blanksCount = blanksCount;
        this.livesLeft = livesLeft;
    }

    public int getBlanksCount() {
        return blanksCount;
    }

    public int getLivesLeft() {
        return livesLeft;
    }
}
