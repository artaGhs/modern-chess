//Name : Arta Ghasemi


//Pawn
//This piece can go one square up, and one square down, it only can attack the opposite pieces diagonally and one square



import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Pawn extends Piece {
 
    private BufferedImage img;
    
    public Pawn(boolean isWhite, String img_file) {
        super(isWhite, img_file);
    }
    
    public String toString() {
    	return "A " + super.toString() + " Pawn";
    }
  
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece could move there legally.
    
    //pre-condition: A 2D array of squares of the board should be given to the method, the starting square should be also given to the method
    //post-condition : An array of controlled squares based on the starting square is returned.
	public Square[] getControlledSquares(Square[][] board, Square start) {
		int[] pos = {start.getXNum(),start.getYNum()};
		int xPos = pos[0];
		int yPos = pos[1];
		 
		if(start.getOccupyingPiece().getColor() && yPos > 0) {
			if(xPos > 0 && xPos < 7) {
				Square[] sq = {board[yPos - 1][xPos - 1],board[yPos - 1][xPos + 1]};
				return sq;
			}
			else if(xPos == 0) {
				Square[] sq = {board[yPos - 1][xPos + 1]};
				return sq;
			}
			else {
				Square[] sq = {board[yPos - 1][xPos - 1]};
				return sq;
			}
		}
		if(!(start.getOccupyingPiece().getColor()) && yPos < 7){
			if(xPos > 0 && xPos < 7) {
				Square[] sq = {board[yPos + 1][xPos - 1],board[yPos + 1][xPos + 1]};
				return sq;
			}
			else if(xPos == 0) {
				Square[] sq = {board[yPos + 1][xPos + 1]};
				return sq;
			}
			else {
				Square[] sq = {board[yPos + 1][xPos - 1]};
				return sq;
			}
		}
		
		return null;
		
	}
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
	
	//almost similar to a regular pawn in chess this pawn can move 1 up but it can also move 1 down, (it only can capture pieces diagonally(only
	//one square apart
	// pre-condition : a board populated with some pieces should be given the method, also the starting square should be given to this method,
	// post-condition : An ArrayList of legal squares based on the starting square is returned.
	//
	public ArrayList<Square> getLegalMoves(Board b, Square start) {
		Square[][] squares = b.getSquareArray();
		boolean color = !start.getOccupyingPiece().getColor();
		ArrayList<Square> arr = new ArrayList<Square>(); 
		
		if(start.getYNum() - 1 >= 0 && start.getYNum() - 1 < 8 && !(squares[start.getYNum() - 1][start.getXNum()].isOccupied())) {
			arr.add(squares[start.getYNum() - 1][start.getXNum()]);
		}
		if(start.getYNum() + 1 >= 0 && start.getYNum() + 1 < 8 && !(squares[start.getYNum() + 1][start.getXNum()].isOccupied())) {
			arr.add(squares[start.getYNum() + 1][start.getXNum()]);
		}
		
		
		
		if(start.getOccupyingPiece().getColor() && start.getYNum() > 0) {
			if(start.getXNum() > 0 && start.getXNum() < 7 ) {
				if(squares[start.getYNum() - 1][start.getXNum() - 1].isOccupied() && (squares[start.getYNum() - 1][start.getXNum() - 1].getOccupyingPiece().getColor() == color)  ) {
					arr.add(squares[start.getYNum() - 1][start.getXNum() - 1]);
				}
				if(squares[start.getYNum() - 1][start.getXNum() + 1].isOccupied() && (squares[start.getYNum() - 1][start.getXNum() + 1].getOccupyingPiece().getColor() == color) ) {
					arr.add(squares[start.getYNum() - 1][start.getXNum() + 1]);
				}
			}else if(start.getXNum() == 0 && squares[start.getYNum() - 1][start.getXNum() + 1].isOccupied()  && (squares[start.getYNum() - 1][start.getXNum() + 1].getOccupyingPiece().getColor() == color) ) {
				arr.add(squares[start.getYNum() - 1][start.getXNum() + 1]);
			}
			else if(start.getXNum() == 7 && squares[start.getYNum() - 1][start.getXNum() - 1].isOccupied() && (squares[start.getYNum() - 1][start.getXNum() - 1].getOccupyingPiece().getColor() == color)){
				arr.add(squares[start.getYNum() - 1][start.getXNum() - 1]);
			}
		}if(!(start.getOccupyingPiece().getColor()) && start.getYNum() < 7){
			if(start.getXNum() > 0 && start.getXNum() < 7 ) {
				if(squares[start.getYNum() + 1][start.getXNum() - 1].isOccupied()  && (squares[start.getYNum() + 1][start.getXNum() - 1].getOccupyingPiece().getColor() == color)) {
					arr.add(squares[start.getYNum() + 1][start.getXNum() - 1]);
				}
				if(squares[start.getYNum() + 1][start.getXNum() + 1].isOccupied() && (squares[start.getYNum() + 1][start.getXNum() + 1].getOccupyingPiece().getColor() == color)) {
					arr.add(squares[start.getYNum() + 1][start.getXNum() + 1]);
				}
			}else if(start.getXNum() == 0 && squares[start.getYNum() + 1][start.getXNum() + 1].isOccupied()  && (squares[start.getYNum() + 1][start.getXNum() + 1].getOccupyingPiece().getColor() == color) ) {
				arr.add(squares[start.getYNum() + 1][start.getXNum() + 1]);
			}
			else if(start.getXNum() == 7 && squares[start.getYNum() + 1][start.getXNum() - 1].isOccupied()  && (squares[start.getYNum() + 1][start.getXNum() - 1].getOccupyingPiece().getColor() == color)){
				arr.add(squares[start.getYNum() + 1][start.getXNum() - 1]);
			}
		}
		
		

		/*
		for(Square sq : arr) {
			System.out.println(sq.getYNum() + " / " + sq.getXNum());
		}
		*/
		
		return arr;
	}
}



