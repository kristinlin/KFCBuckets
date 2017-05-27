import java.io.*;
import java.util.*;

public class Solver {
    
    //~~~~~~~~~~~instance vars~~~~~~~~~~~~~~~~
    private Box[][] board;
    private int numDefinite;
    private final int max = 81;
    ///~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    public Solver(){
	board = new Box[9][9];
	numDefinite = 0;
    }
    
    public Solver(String inputFile){
	this();
	String[][] puzzle = readPuzzle(inputFile);
	for (int r = 0; r < puzzle.length; r++){
	    for (int c = 0; c < puzzle[0].length; c++){
		board[r][c] = new Box();
	    }
	}
	for (int r = 0; r < puzzle.length; r++){
	    for (int c = 0; c < puzzle[0].length; c++){
	        if (!(puzzle[r][c].equals("_"))){
		    int given = Integer.parseInt(puzzle[r][c]);
		    assign(given, r, c);
		}
	    }
	}
    }

    public String[][] readPuzzle(String inputFile){
	String[][] puzzle = new String[9][9];
	int h = 0;
	int w = 0;

	//transcribe maze from file into memory

	try {
	    Scanner sc = new Scanner( new File(inputFile) );
	    System.out.println( "reading in file..." );
	    int row = 0;
	    while( sc.hasNext() ) {
		String line = sc.nextLine();
		if ( w < line.length() ) 
		    w = line.length();
		for( int i=0; i<line.length(); i++ )
		    puzzle[i][row] = line.substring( i, i + 1 );
		h++;
		row++;
	    } 
	} catch( Exception e ) { System.out.println( "Error reading file" ); }

	return puzzle;

    }
    public void removeFromRow(int value, int row){
	//for each element in this row
	for (int c = 0; c < 9; c ++){
	    //only bother if the value is not set
	    if (!board[row][c].getIsDef()){
		//check to see if removing a value solves the box
		boolean isFinished = board[row][c].remove(value);
		//if it does
		if (isFinished){
		    //do it all over again for each row & each column
		    int takenVal = board[row][c].getGuess();
		    removeFromRow(takenVal, row);
		    removeFromCol(takenVal, c);
		}
	    }
	} 
    }

    public void removeFromCol(int value, int col){
	//for each element in this column
	for(int r = 0; r < 9; r++){
	    if(!board[r][col].getIsDef()){
		boolean isFinished = board[r][col].remove(value);
		if(isFinished){
		    int takenVal = board[r][col].getGuess();
		    removeFromRow(takenVal, r);
		    removeFromCol(takenVal, col);
		}
	    }
	}
    }

    //for givens and definites
    public void assign(int newVal, int r, int c) {
	Box temp = board[r][c];
	temp.setGuess(newVal, true);
        removeFromRow(newVal, r);
	removeFromCol(newVal, c);
        //remove from the collection
    }

    public void sRemoveFromRow(int value, int row) {
	//for each element in this row
	for (int c = 0; c < 9; c++) {
	    boolean isFinished = board[row][c].remove(value);
	    if (isFinished) {
		int takenVal = board[row][c].getGuess();
		sRemoveFromRow(takenVal, row);
		sRemoveFromCol(takenVal, c);
	    }
	}
	
    }
    
    //assign for tentatives
    public void assignT(int newGuess, int r, int c) {
	//setGuess
    }

    //check row to look for any missing values
    public int[] checkC(int col) {
	
	
    }
    
    /*========================
     *pre: givens and definites determined from givens already assigned; 
     *function: Time to guess! Checks and finds box with least num possibles,
     *then assigns the least one tentatively. If there is a contradiction, 
     *backtrack and assign the next least one.
      =======================*/
    public void solve() {

    }
    
}
