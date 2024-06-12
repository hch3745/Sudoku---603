package sud;

public enum DifficultyLevel {
    EASY(20), MEDIUM(40), HARD(60), CUSTOM(-1);

    private final int blankCells;

    DifficultyLevel(int blankCells) {
        this.blankCells = blankCells;
    }
    
    // Getter and setter methods for the blank cell count
    public int getBlankCells() { 
        return blankCells;
    }
    
    // Determines the difficulty level based on the blank cell count
    public static DifficultyLevel fromBlankCount(int blankCount) { 
        for (DifficultyLevel level : values()) {
            if (level.blankCells == blankCount) {
                return level;
            }
        }
        return CUSTOM;
    }
}
