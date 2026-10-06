package api;

import game.Board;
import game.GameResult;
import game.Move;
import game.Player;

import boards.TicTacToeBoard;

public class GameEngine {
    

    public Board start(String type){
         if(type.equals("TicTacToeBoard")){
            return new TicTacToeBoard();
        }else{
            throw new IllegalArgumentException();
        }
        

    }

    public void move(Board board, Player player , Move move){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            board1.setCell(Move.getCell(),player.symbol());
        }else{
            throw new IllegalArgumentException("Unsupported board type");
        }

    }

    public GameResult isComplete(Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board; 
            String firstCharacter="-"; //gameresult needs to know which character has completed the row/column/diagonal
            boolean rowComplete,colComplete,diagComplete,revDiagComplete;
            rowComplete = colComplete = diagComplete = revDiagComplete = true; //so java compiler doesn't complain about uninitialized variables

            // Check rows for completion
            for(int i=0;i<3;i++){
                 rowComplete = true;
                firstCharacter=board1.getCell(i, 0);
                for(int j=1;j<3;j++){ //0 is already checked
                    if(!(board1.getCell(i, j).equals(firstCharacter))){
                        rowComplete = false;
                        break; //optimization to break the loop if a mismatch is found
                    }
                }
                if(rowComplete){
                    break; //optimization to break the loop if a complete row is found
                }
            }

            if(rowComplete){ //if a complete row is found, no need to check further
                return new GameResult(true,firstCharacter);
            }

            // Check columns for completion
            
            for(int i=0;i<3;i++){
                 colComplete = true;
                firstCharacter=board1.getCell(0, i);
                for(int j=1;j<3;j++){ //0 is already checked
                    if(!(board1.getCell(j, i).equals(firstCharacter))){
                        colComplete = false;
                        break; //optimization to break the loop if a mismatch is found
                    }
                }
                if(colComplete){
                    break; //optimization to break the loop if a complete column is found
                }
            }

            if(colComplete){ //if a complete column is found, no need to check further
                return new GameResult(true,firstCharacter);
            }
            // Check diagonals for completion (x=y)
             
            for(int i=0;i<3;i++){
                 diagComplete = true;
                firstCharacter=board1.getCell(0, 0);
                
                if(!(board1.getCell(i, i).equals(firstCharacter))){
                    diagComplete = false;
                    break;
                }
                if(diagComplete){
                    break; //optimization to break the loop if a complete diagonal is found
                }
                
            }

            if(diagComplete){ //if a complete diagonal is found, no need to check further
                return new GameResult(true,firstCharacter);
            }
            //reverse diagonal check (x+y=2)
            for(int i=0;i<3;i++){
                 revDiagComplete = true;
                firstCharacter=board1.getCell(0, 2); //last column of first row
                
                if(!(board1.getCell(i, 2-i).equals(firstCharacter))){
                    revDiagComplete = false;
                    break;
                }
                if(revDiagComplete){
                    break; //optimization to break the loop if a complete reverse diagonal is found
                }
            }

            if(revDiagComplete){ //if a complete reverse diagonal is found, no need to check further
                return new GameResult(true,firstCharacter);
            }
            

            int countOfFilledCells=0;
            for(int i=0;i<3;i++){
                for(int j=1;j<3;j++){
                    if(board1.getCell(j, i) !=null){
                        countOfFilledCells++;
                    }
                }
            }
            if(countOfFilledCells == 9){ //if all cells are filled and no winner is found, it's a tie
                return new GameResult(true,"-");
            }
            else{
                return new GameResult(false,"-"); //game is not complete yet
            }
        }
        else{
            return new GameResult(false,"-"); //game is not complete yet
        }
    }
}




