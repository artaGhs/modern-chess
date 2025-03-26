//Ryo Akahata
//Chess piece instructions for Knight
//The knight piece goes two spaces in one direction and one piece in another direction.


import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Knight extends Piece {

    public Knight(boolean isWhite, String img_file) {
    	super(isWhite, img_file);
    }
    
    public String toString() {
    	return "A " + (getColor() ? "White Knight" : "Black Knight");	
    }
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece could move there legally.
    public Square[] getControlledSquares(Square[][] board, Square start) {
    	//Precondition: The get controlled squares will be called with a parameter of Square[][] and a Square. In the code we will use the piece's X, Y, and color, so the piece should have all of those.
    	//Postcondition: This code will return a square[] of the squares that the piece can control.
    	//Get the color, X, and Y of the current piece 
    	boolean color = start.getColor();
    	int x = start.getXNum();
    	int y = start.getYNum();
    	
    	//Determine Size of Array
    	int numberSquaresControl = 0;
    	
    	//Check if knight's legal move for all 8 directions
    	//Right 1, backward 2
    	int xx = x+1;
    	int yy = y+2;
    	//Check if piece doesn't go off board, then check if it is occupied
    	if(xx<=7 && yy<=7) {
    		if(board[yy][xx].isOccupied()==false) {
    			numberSquaresControl+=1;
    		}
    		if(board[yy][xx].isOccupied()==true && !board[yy][xx].getColor()==color) {
    			numberSquaresControl+=1;
    		}
    	}
    	
    	//Right 1, Forward 2
    	xx = x+1;
    	yy = y-2;
    	if(xx<=7 && yy>=0) {
    		if(board[yy][xx].isOccupied()==false) {
    			numberSquaresControl+=1;
    		}
    		if(board[yy][xx].isOccupied()==true && !board[yy][xx].getColor()==color) {
    			numberSquaresControl+=1;
    		}
    	}
    	
    	//Right 2, Backward 1
    	xx = x+2;
    	yy = y+1;
    	if(xx<=7 && yy<=7) {
    		if(board[yy][xx].isOccupied()==false) {
    			numberSquaresControl+=1;
    		}
    		if(board[yy][xx].isOccupied()==true && !board[yy][xx].getColor()==color) {
    			numberSquaresControl+=1;
    		}
    	}
    	
    	//Right 2, Forward 1
    	xx = x+2;
    	yy = y-1;
    	if(xx<=7 && yy>=0) {
    		if(board[yy][xx].isOccupied()==false) {
    			numberSquaresControl+=1;
    		}
    		if(board[yy][xx].isOccupied()==true && !board[yy][xx].getColor()==color) {
    			numberSquaresControl+=1;
    		}
    	}
    	
    	//Left 1, Forward 2
    	xx = x-1;
    	yy = y-2;
    	if(xx>=0 && yy>=0) {
    		if(board[yy][xx].isOccupied()==false) {
    			numberSquaresControl+=1;
    		}
    		if(board[yy][xx].isOccupied()==true && !board[yy][xx].getColor()==color) {
    			numberSquaresControl+=1;
    		}
    	}
    	
    	//Left 1, Backward 2
    	xx = x-1;
    	yy = y+2;
    	if(xx>=0 && yy<=7) {
    		if(board[yy][xx].isOccupied()==false) {
    			numberSquaresControl+=1;
    		}
    		if(board[yy][xx].isOccupied()==true && !board[yy][xx].getColor()==color) {
    			numberSquaresControl+=1;
    		}
    	}
    	
    	//Left 2, Backward 1
    	xx = x-2;
    	yy = y+1;
    	if(xx>=0 && yy<=7) {
    		if(board[yy][xx].isOccupied()==false) {
    			numberSquaresControl+=1;
    		}
    		if(board[yy][xx].isOccupied()==true && !board[yy][xx].getColor()==color) {
    			numberSquaresControl+=1;
    		}
    	}
    	
    	//Left 2, Forward 1
    	xx = x-2;
    	yy = y-1;
    	if(xx>=0 && yy>=0) {
    		if(board[yy][xx].isOccupied()==false) {
    			numberSquaresControl+=1;
    		}
    		if(board[yy][xx].isOccupied()==true && !board[yy][xx].getColor()==color) {
    			numberSquaresControl+=1;
    		}
    	}
    	//System.out.println("Number of squares this piece can control: " + numberSquaresControl);
    	
    	Square[] controlledSquares = new Square[numberSquaresControl];
    	
    	
    	int squareIndex = 0;
    	xx = x+1;
    	yy = y+2;
    	//Check if piece doesn't go off board, then check if it is occupied
    	if(xx<=7 && yy<=7) {
    		if(board[yy][xx].isOccupied()==false) {
    			controlledSquares[squareIndex] = board[yy][xx];
    			squareIndex++;
    		}
    		if(board[yy][xx].isOccupied()==true && !board[yy][xx].getColor()==color) {
    			controlledSquares[squareIndex] = board[yy][xx];
    			squareIndex++;
    		}
    	}
    	
    	//Right 1, Forward 2
    	xx = x+1;
    	yy = y-2;
    	if(xx<=7 && yy>=0) {
    		if(board[yy][xx].isOccupied()==false) {
    			controlledSquares[squareIndex] = board[yy][xx];
    			squareIndex++;
    		}
    		if(board[yy][xx].isOccupied()==true && !board[yy][xx].getColor()==color) {
    			controlledSquares[squareIndex] = board[yy][xx];
    			squareIndex++;
    		}
    	}
    	
    	//Right 2, Backward 1
    	xx = x+2;
    	yy = y+1;
    	if(xx<=7 && yy<=7) {
    		if(board[yy][xx].isOccupied()==false) {
    			controlledSquares[squareIndex] = board[yy][xx];
    			squareIndex++;
    		}
    		if(board[yy][xx].isOccupied()==true && !board[yy][xx].getColor()==color) {
    			controlledSquares[squareIndex] = board[yy][xx];
    			squareIndex++;
    		}
    	}
    	
    	//Right 2, Forward 1
    	xx = x+2;
    	yy = y-1;
    	if(xx<=7 && yy>=0) {
    		if(board[yy][xx].isOccupied()==false) {
    			controlledSquares[squareIndex] = board[yy][xx];
    			squareIndex++;
    		}
    		if(board[yy][xx].isOccupied()==true && !board[yy][xx].getColor()==color) {
    			controlledSquares[squareIndex] = board[yy][xx];
    			squareIndex++;
    		}
    	}
    	
    	//Left 1, Forward 2
    	xx = x-1;
    	yy = y-2;
    	if(xx>=0 && yy>=0) {
    		if(board[yy][xx].isOccupied()==false) {
    			controlledSquares[squareIndex] = board[yy][xx];
    			squareIndex++;
    		}
    		if(board[yy][xx].isOccupied()==true && !board[yy][xx].getColor()==color) {
    			controlledSquares[squareIndex] = board[yy][xx];
    			squareIndex++;
    		}
    	}
    	
    	//Left 1, Backward 2
    	xx = x-1;
    	yy = y+2;
    	if(xx>=0 && yy<=7) {
    		if(board[yy][xx].isOccupied()==false) {
    			controlledSquares[squareIndex] = board[yy][xx];
    			squareIndex++;
    		}
    		if(board[yy][xx].isOccupied()==true && !board[yy][xx].getColor()==color) {
    			controlledSquares[squareIndex] = board[yy][xx];
    			squareIndex++;
    		}
    	}
    	
    	//Left 2, Backward 1
    	xx = x-2;
    	yy = y+1;
    	if(xx>=0 && yy<=7) {
    		if(board[yy][xx].isOccupied()==false) {
    			controlledSquares[squareIndex] = board[yy][xx];
    			squareIndex++;
    		}
    		if(board[yy][xx].isOccupied()==true && !board[yy][xx].getColor()==color) {
    			controlledSquares[squareIndex] = board[yy][xx];
    			squareIndex++;
    		}
    	}
    	
    	//Left 2, Forward 1
    	xx = x-2;
    	yy = y-1;
    	if(xx>=0 && yy>=0) {
    		if(board[yy][xx].isOccupied()==false) {
    			controlledSquares[squareIndex] = board[yy][xx];
    		}
    		if(board[yy][xx].isOccupied()==true && !board[yy][xx].getColor()==color) {
    			controlledSquares[squareIndex] = board[yy][xx];
    		}
    	}
    	
    
    	
     return controlledSquares;
    }
    

    
    
    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    public ArrayList<Square> getLegalMoves(Board b, Square start){
    	
    	//Precondition: The get controlled squares will be called with a parameter of baord and a Square. In the code we will use the piece's X, Y, and color, so the piece should have all of those.
    	//Postcondition: This code will return an ArrayList of Squares that the piece can move to.
    	//Move like a knight
    	boolean color = super.getColor();
    	int x = start.getXNum();
    	int y = start.getYNum();
    	ArrayList<Square> legalMove = new ArrayList<Square>();
    	
    	//Check if knight's legal move for all 8 directions
    	//Right 1, backward 2
    	int xx = x+1;
    	int yy = y+2;
    	//Check if piece doesn't go off board, then check if it is occupied
    	if(xx<=7 && yy<=7) {
    		if(b.getSquareArray()[yy][xx].isOccupied()==false) {
    			legalMove.add(b.getSquareArray()[yy][xx]);
    		}
    		if(b.getSquareArray()[yy][xx].isOccupied()==true && !b.getSquareArray()[yy][xx].getOccupyingPiece().getColor()==color) {
    			legalMove.add(b.getSquareArray()[yy][xx]);
    		}
    	}
    	
    	//Right 1, Forward 2
    	xx = x+1;
    	yy = y-2;
    	if(xx<=7 && yy>=0) {
    		if(b.getSquareArray()[yy][xx].isOccupied()==false) {
    			legalMove.add(b.getSquareArray()[yy][xx]);
    		}
    		if(b.getSquareArray()[yy][xx].isOccupied()==true && !b.getSquareArray()[yy][xx].getOccupyingPiece().getColor()==color) {
    			legalMove.add(b.getSquareArray()[yy][xx]);
    		}
    	}
    	
    	//Right 2, Backward 1
    	xx = x+2;
    	yy = y+1;
    	if(xx<=7 && yy<=7) {
    		if(b.getSquareArray()[yy][xx].isOccupied()==false) {
    			legalMove.add(b.getSquareArray()[yy][xx]);
    		}
    		if(b.getSquareArray()[yy][xx].isOccupied()==true && !b.getSquareArray()[yy][xx].getOccupyingPiece().getColor()==color) {
    			legalMove.add(b.getSquareArray()[yy][xx]);
    		}
    	}
    	
    	//Right 2, Forward 1
    	xx = x+2;
    	yy = y-1;
    	if(xx<=7 && yy>=0) {
    		if(b.getSquareArray()[yy][xx].isOccupied()==false) {
    			legalMove.add(b.getSquareArray()[yy][xx]);
    		}
    		if(b.getSquareArray()[yy][xx].isOccupied()==true && !b.getSquareArray()[yy][xx].getOccupyingPiece().getColor()==color) {
    			legalMove.add(b.getSquareArray()[yy][xx]);
    		}
    	}
    	
    	//Left 1, Forward 2
    	xx = x-1;
    	yy = y-2;
    	if(xx>=0 && yy>=0) {
    		if(b.getSquareArray()[yy][xx].isOccupied()==false) {
    			legalMove.add(b.getSquareArray()[yy][xx]);
    		}
    		if(b.getSquareArray()[yy][xx].isOccupied()==true && !b.getSquareArray()[yy][xx].getOccupyingPiece().getColor()==color) {
    			legalMove.add(b.getSquareArray()[yy][xx]);
    		}
    	}
    	
    	//Left 1, Backward 2
    	xx = x-1;
    	yy = y+2;
    	if(xx>=0 && yy<=7) {
    		if(b.getSquareArray()[yy][xx].isOccupied()==false) {
    			legalMove.add(b.getSquareArray()[yy][xx]);
    		}
    		if(b.getSquareArray()[yy][xx].isOccupied()==true && !b.getSquareArray()[yy][xx].getOccupyingPiece().getColor()==color) {
    			legalMove.add(b.getSquareArray()[yy][xx]);
    		}
    	}
    	
    	
    	//Left 2, Backward 1
    	xx = x-2;
    	yy = y+1;
    	if(xx>=0 && yy<=7) {
    		if(b.getSquareArray()[yy][xx].isOccupied()==false) {
    			legalMove.add(b.getSquareArray()[yy][xx]);
    		}
    		if(b.getSquareArray()[yy][xx].isOccupied()==true && !b.getSquareArray()[yy][xx].getOccupyingPiece().getColor()==color) {
    			legalMove.add(b.getSquareArray()[yy][xx]);
    		}
    	}
    	
    	//Left 2, Forward 1
    	xx = x-2;
    	yy = y-1;
    	if(xx>=0 && yy>=0) {
    		if(b.getSquareArray()[yy][xx].isOccupied()==false) {
    			legalMove.add(b.getSquareArray()[yy][xx]);
    		}
    		if(b.getSquareArray()[yy][xx].isOccupied()==true && !b.getSquareArray()[yy][xx].getOccupyingPiece().getColor()==color) {
    			legalMove.add(b.getSquareArray()[yy][xx]);
    		}
    	}
    	
    	return legalMove;
    }
}