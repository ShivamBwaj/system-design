package boards;
import game.Board;
import game.Cell;
public class TicTacToeBoard extends Board {
    // TicTacToeBoard implementation
    String cells[][] = new String[3][3];
    
    public String getCell(int x, int y) {
        return cells[x][y];
    }
    public void setCell(Cell cell,String symbol){
        cells[cell.getRow()][cell.getCol()]=symbol;
    }
}