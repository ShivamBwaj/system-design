package game;

public class GameResult {
    // GameResult implementation
    boolean isOver;
    String winner; //could be "X" or "O" or null if no winner yet
    
    public GameResult(boolean isOver, String winner){
        this.isOver = isOver;
        this.winner = winner;
    }
}