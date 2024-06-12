/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sud;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class SudokuCellView extends JTextField {

    private boolean isInitialValue;

    public SudokuCellView() {
        super(1);
        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("Arial", Font.PLAIN, 20));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    // Sets the value and appearance of the cell
    public void setValue(int value, boolean isInitial) {
        this.isInitialValue = isInitial;
        setText(value == 0 ? "" : String.valueOf(value));
        setEditable(!isInitial);
        setFocusable(!isInitial);
        setBackground(isInitial ? Color.LIGHT_GRAY : Color.WHITE);
    }
    
    // Sets the background color of the cell based on the input correctness
    public void setInputColor(boolean isCorrect) {
        if (!isInitialValue) {
            setBackground(isCorrect ? Color.GREEN : Color.RED);
        }
    }
    
    // Overrides the paintComponent method to customize the cell appearance
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isInitialValue) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 20));
        } else {
            g.setColor(getForeground());
            g.setFont(new Font("Arial", Font.PLAIN, 20));
        }
        String text = getText();
        if (!text.isEmpty()) {
            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(text)) / 2;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            g.drawString(text, x, y);
        }
    }
}
