import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the 
//suggested order of completion that should make testing easier.
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
	// Resource location constants for piece images
    private static final String RESOURCES_WBISHOP_PNG = "wbishop.png";
	private static final String RESOURCES_BBISHOP_PNG = "bbishop.png";
	private static final String RESOURCES_WKNIGHT_PNG = "wknight.png";
	private static final String RESOURCES_BKNIGHT_PNG = "bknight.png";
	private static final String RESOURCES_WROOK_PNG = "wrook.png";
	private static final String RESOURCES_BROOK_PNG = "brook.png";
	private static final String RESOURCES_WKING_PNG = "wking.png";
	private static final String RESOURCES_BKING_PNG = "bking.png";
	private static final String RESOURCES_BQUEEN_PNG = "bqueen.png";
	private static final String RESOURCES_WQUEEN_PNG = "wqueen.png";
	private static final String RESOURCES_WPAWN_PNG = "wpawn.png";
	private static final String RESOURCES_BPAWN_PNG = "bpawn.png";
	private static final String RESOURCES_BAMONGUS_PNG = "amongUs.png";
	private static final String RESOURCES_WAMONGUS_PNG = "WamongUs.png";
	
	// Logical and graphical representations of board
	private final Square[][] board;
    private final GameWindow g;
    
    // List of pieces and whether they are movable
    public final ArrayList<Piece> Bpieces;
    public final ArrayList<Piece> Wpieces;
 
    //contains true if it's white's turn.
    private boolean whiteTurn;

    //if the player is currently dragging a piece this variable contains it.
    private Piece currPiece;
    private Square fromMoveSquare;
    //used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;
    

    
    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        Bpieces = new ArrayList<Piece>();
        Wpieces = new ArrayList<Piece>();
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //TO BE IMPLEMENTED FIRST
        boolean color = true;
        
        for(int y = 0; y < 8; y++) {
        	for(int x = 0; x < 8; x++) {      		
        		board[y][x] = new Square(this, color ,x,y);
        		this.add(board[y][x]);
        		color = !color;
        	}
        	color = !color;
        }
        
        
     
      //for (.....)  
//        	populate the board with squares here. Note that the board is composed of 64 squares alternating from 
//        	white to black.
        

        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;

    }

    
	//set up the board such that the black pieces are on one side and the white pieces are on the other.
	//since we only have one kind of piece for now you need only set the same number of pieces on either side.
	//it's up to you how you wish to arrange your pieces.
    
    
   
    //pre-condition: having a board
    //post-condition : placing 8 Black pieces on the first row, and 8 White pieces on the last row
    private void initializePieces() {
    	
    	Piece Rook = new Rook(false,RESOURCES_BROOK_PNG);
    	board[0][0].put(Rook);
    	board[0][7].put(Rook);
    	
    	
    	Piece BRook = new Rook(true,RESOURCES_WROOK_PNG);
    	board[7][0].put(BRook);
    	board[7][7].put(BRook);
    	
    	
    	Piece Knight = new Knight(true,RESOURCES_WKNIGHT_PNG);
    	board[7][2].put(Knight);
    	board[7][5].put(Knight);
    	
    	
    	
    	Piece BKnight = new Knight(false,RESOURCES_BKNIGHT_PNG);
    	board[0][2].put(BKnight);
    	board[0][5].put(BKnight);
    	
    	
    	Piece BKing = new King(false,RESOURCES_BKING_PNG);
    	board[0][3].put(BKing);
    	
    	Piece King = new King(true,RESOURCES_WKING_PNG);
    	board[7][3].put(King);
    
    	
    	
    	
    	for(int x = 0; x < 8 ; x++) {
    		Piece toAdd = new Pawn(false,RESOURCES_BAMONGUS_PNG);
    		board[1][x].put(toAdd);
    		Bpieces.add(toAdd);
    	}
    	
    	for(int x = 0; x < 8 ; x++) {
    		Piece toAdd = new Pawn(true,RESOURCES_WAMONGUS_PNG);
    		board[6][x].put(toAdd);
    		Wpieces.add(toAdd);
    	}
    	
    	//board[4][3].put(new Piece(true, RESOURCES_BAMONGUS_PNG));
    	//board[4][5].put(new Piece(false, RESOURCES_WAMONGUS_PNG));

    }
    
    
    
    //precondition - the board is initialized and contains a king of either color. The boolean kingColor corresponds to the color of the king we wish to know the status of.
    //postcondition - returns true of the king is in check and false otherwise.
    public boolean isInCheck(boolean kingColor){
    	
    	boolean color = !kingColor;
    	Square[][] sq = this.getSquareArray();
    	
    	for(int i =0; i<8; i++){
    		for(int j=0; j<8; j++){
    			
    			
    			if(sq[i][j].isOccupied() && sq[i][j].getOccupyingPiece().getColor() == color) {
    				
    				
    				for(Square s : sq[i][j].getOccupyingPiece().getControlledSquares(sq, sq[i][j])){
    					
    					//if(s.isOccupied())
    					//	System.out.println(s.getOccupyingPiece().toString());
    					
    					if(s.isOccupied() && s.getOccupyingPiece().getColor() == kingColor && s.getOccupyingPiece() instanceof King){
    						/*
    						System.out.println("y : " + i + " ------ x : " + j );
    	    				System.out.println(sq[i][j].getOccupyingPiece().toString());
    						System.out.println(s.getYNum() + " / " + s.getXNum());
    						*/
    							
    						System.out.println(sq[i][j].getOccupyingPiece().toString() + " Y = " + i + " ------ X : " + j );
    						return true;
    					}
    				}
    			}
    			
    		}
    	}
    	return false;
    }


    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {
        // super.paintComponent(g);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square sq = board[y][x];
                sq.paintComponent(g);
            }
        }

        if (currPiece != null) {
            if ((currPiece.getColor() && whiteTurn)
                    || (!currPiece.getColor()&& !whiteTurn)) {
                final Image i = currPiece.getImage();
                g.drawImage(i, currX, currY, null);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
        
        if (sq.isOccupied()) {
            currPiece = sq.getOccupyingPiece();
            fromMoveSquare = sq;
            if (!currPiece.getColor() && whiteTurn) {
                currPiece = null;
            	return;
            }
            if (currPiece.getColor() && !whiteTurn) {
                currPiece = null;
            	return;
            }
            for(Square s: currPiece.getLegalMoves(this, sq)) {
            	s.setSelected(true);
            }
            sq.setDisplay(false); 
        }
        repaint();
    }

    //TO BE IMPLEMENTED!
    //should move the piece to the desired location only if this is a legal move.
    //use the pieces "legal move" function to determine if this move is legal, then complete it by
    //using the capture function if necessary and moving the new piece to it's new board location. 
    @Override
    
    //pre-condition: our mouse event is done, (we should release the mouse after dragging a piece
    //post-condition : if our movement is legal the piece moves, also if its legal to capture the other side's piece, it will capture it.
    public void mouseReleased(MouseEvent e) {
    	//try
    	 for(Square[] sq: board) {
    		 for(Square s: sq) {
         	s.setSelected(false);
    		 }
         }
    	if(currPiece!=null) {
    		Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
    		
    		Square[] controlledSq = currPiece.getControlledSquares(this.getSquareArray(), fromMoveSquare);
    		
    		Piece captured =null;
    		Piece p = fromMoveSquare.getOccupyingPiece();
    		/*
    		for(Square s : currPiece.getControlledSquares(this.getSquareArray(), fromMoveSquare)) {
    			System.out.println(s.getYNum() + " / " + s.getXNum() );
    		}
    		*/
    		if(this.isInCheck(whiteTurn) ){
    			if(currPiece.getLegalMoves(this,fromMoveSquare).contains(endSquare)) {
    				
    				
 
    				
    				Piece p1 = fromMoveSquare.getOccupyingPiece();

    				if(!endSquare.isOccupied()) {
    					endSquare.put(p1);
    				}
    				else {
    					
    					captured = endSquare.getOccupyingPiece();
    					endSquare.capture(p1);
    				}
    				fromMoveSquare.removePiece();
    				endSquare.setDisplay(true);
    				whiteTurn = !whiteTurn;

    				if(this.isInCheck(!whiteTurn)) {
    					
    					endSquare.removePiece();
    					if(captured != null)
    						endSquare.put(captured);
    					endSquare.setDisplay(true);
    					fromMoveSquare.put(p1);
    					fromMoveSquare.setDisplay(true);
    					whiteTurn = !whiteTurn;
    				}
    			}
    			else {
    				fromMoveSquare.setDisplay(true);
    			}
    			
    			
    		}
    		
    		else if(currPiece.getLegalMoves(this,fromMoveSquare).contains(endSquare) ) {
    			//System.out.println("***********");
    			if(!endSquare.isOccupied()) {
    				endSquare.put(currPiece);
    			}
    			else {
    				endSquare.capture(currPiece);
    			}
    			fromMoveSquare.removePiece();
    			endSquare.setDisplay(true);
    			whiteTurn = !whiteTurn;

    			//System.out.println(whiteTurn);

    			//System.out.println("&&&&&&&&");
    			
    			
    			
    			//System.out.println(whiteTurn);
    			if((this.isInCheck(!whiteTurn))){
    				
    				System.out.println("============");
    				endSquare.removePiece();
    				endSquare.setDisplay(true);
    				fromMoveSquare.put(p);
    				fromMoveSquare.setDisplay(true);
    				whiteTurn = !whiteTurn;
    			}
    		}
    		
    		else {
    			//System.out.println("************");
    			//
    			fromMoveSquare.setDisplay(true);
    			//board[fromMoveSquare.getYNum()][fromMoveSquare.getXNum()].put(currPiece);
    		}

    		//System.out.println(fromMoveSquare.getXNum());
    		//board[endSquare.getYNum()][endSquare.getXNum()].put(currPiece);
    	}
    	currPiece = null;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - 24;
        currY = e.getY() - 24;

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}



